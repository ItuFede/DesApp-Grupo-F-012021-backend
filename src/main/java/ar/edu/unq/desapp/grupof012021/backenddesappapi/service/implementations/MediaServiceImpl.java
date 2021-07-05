package ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.MediaDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.MediaRedisDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReviewDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.ReviewFilterDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Genre;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Media;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.UserEntity;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaGenreType;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.GenreRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.MediaRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.UserEntityRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.security.JwtTokenUtil;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.FirebaseService;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.MediaService;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("mediaService")
public class MediaServiceImpl implements MediaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    MediaRepository repository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    ReviewService reviewService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    FirebaseService firebaseService;

    public MediaServiceImpl() { }

    public MediaServiceImpl(MediaRepository repository) {
        this.repository = repository;
    }

    public MediaServiceImpl(MediaRepository repository, ReviewService reviewService, JwtTokenUtil jwtTokenUtil) {
        this.repository = repository;
        this.reviewService = reviewService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public void addReviewTo(String idMediaString, ReviewDTO reviewDTO, String accessToken) {
        Review aReview = reviewService.createReview(reviewDTO, accessToken);
        Media aMedia = repository.findByIdStringMedia(idMediaString);
        aReview.setMediaReview(aMedia);
        aMedia.getReviews().add(aReview);
        repository.save(aMedia);

        if (this.isSubscribedForNotifications(mediaId)) {
            String username = jwtTokenUtil.getUsernameFromToken(accessToken);
            HashMap<String, Object> notification = new HashMap<>();
            notification.put("reviewShortText", aReview.getShortText());
            notification.put("reviewLongText", aReview.getLongText());
            notification.put("reviewScore", aReview.getScore());
            notification.put("author", username);
            notification.put("mediaId", mediaId);
            firebaseService.post("notifications", notification);
        }
    }

    @Override
    public List<Review> findAllReviewsFrom(long idMedia) {
        Media aMedia = this.findById(idMedia);
        return aMedia.getReviews();
    }

    @Override
    public List<Review> findAllReviewsFilter(ReviewFilterDTO reviewFilterDTO, String idStringMedia, int offset, int limit) {

        long idMedia = repository.findByIdStringMedia(idStringMedia).getId();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> cq = cb.createQuery(Review.class);

        Root<Review> reviewRoot = cq.from(Review.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(reviewRoot.get("mediaReview"), idMedia));

        if (reviewFilterDTO.isPremium){
            predicates.add(cb.isTrue(reviewRoot.get("isPremium")));
        }
        if (reviewFilterDTO.originalPlatform != null){
            predicates.add(cb.equal(reviewRoot.get("originalPlatform"), reviewFilterDTO.originalPlatform));
        }
        if (reviewFilterDTO.region != null){
            predicates.add(cb.equal(reviewRoot.get("region"), reviewFilterDTO.region));
        }
        if (reviewFilterDTO.language != null){
            predicates.add(cb.equal(reviewRoot.get("language"), reviewFilterDTO.language));
        }
        if (reviewFilterDTO.hasSpoilers){
            predicates.add(cb.isTrue(reviewRoot.get("hasSpoilers")));

        }
        if (validOrderType(reviewFilterDTO.isOrdererType)){
            String orderBy = reviewFilterDTO.isOrdererType == "score" ? "score" : "date";
            if(reviewFilterDTO.isOrderAsc){
                cq.orderBy(cb.asc(reviewRoot.get(orderBy)));
            }
            else{
                cq.orderBy(cb.desc(reviewRoot.get(orderBy)));
            }
        }

        cq.where(predicates.toArray(new Predicate[0]));
        Query query = entityManager.createQuery(cq);

        query.setFirstResult((offset - 1) * limit);
        query.setMaxResults(limit);

        return query.getResultList();
    }

    private boolean validOrderType(String isOrdererType) {
        return "date".equals(isOrdererType) || "score".equals(isOrdererType);
    }

    public Media findById(long mediaId) {
        return repository.findById(mediaId);
    }

    public Media findByStringId(String mediaId) {
        return repository.findByIdStringMedia(mediaId);
    }

    @Override
    public List<Media> findAllMediaFilter(MediaDTO mediaDTO, int offset, int limit) throws Exception {

        String queryJoins = "";
        String queryParams = "";

        if (mediaDTO.year != null){
            queryParams+= GetPrefix(queryParams) + String.format(" m.year = %s ", mediaDTO.year);
        }

        if (mediaDTO.endYear != null){
            queryParams+= GetPrefix(queryParams) + String.format(" m.endYear = %s ", mediaDTO.endYear);
        }

        MediaGenreType mediaGenreType;
        if (mediaDTO.genre != null){
            mediaGenreType = Genre.getMediaGenreTypeFromString(mediaDTO.genre);
            queryJoins+= "LEFT JOIN m.genres mg ";
            queryParams+= GetPrefix(queryParams) + String.format(" mg.genreName = %2d ", mediaGenreType.ordinal());
        }

        if (mediaDTO.scoreReview != null){
            queryJoins+= "LEFT JOIN m.reviews r ";
            queryParams+= GetPrefix(queryParams) + String.format(Locale.US, " r.score >= %.2f ", mediaDTO.scoreReview);
        }

        if (mediaDTO.positiveVote != null){
            queryJoins+= "LEFT JOIN r.reviewRankings rr ";
            queryParams+= GetPrefix(queryParams) + String.format(Locale.US, " rr.isPositiveVote = %s ", mediaDTO.positiveVote);
        }

        if (mediaDTO.actorName != null){
            queryJoins+= "LEFT JOIN m.actors a ";
            queryParams+= GetPrefix(queryParams) + String.format(Locale.US, " a.name = '%s' ", mediaDTO.actorName);
        }

        String queryString = "SELECT DISTINCT m FROM Media m " + queryJoins + queryParams;
        System.out.println("Query string: " + queryString);
        Query query = entityManager.createQuery(queryString);

        return query.getResultList();
    }

    private String GetPrefix(String queryString){
        return queryString.contains("WHERE") ? "AND" : "WHERE";
    }

    @Override
    @Cacheable(value = "mediaData")
    public MediaRedisDTO findMediaRedis(String idStringMedia) {
        MediaRedisDTO mediaRedisDTO = new MediaRedisDTO();
        Media media = repository.findByIdStringMedia(idStringMedia);
        mediaRedisDTO.idStringMedia = media.getIdStringMedia();
        mediaRedisDTO.originalTitle = media.getOriginalTitle();
        mediaRedisDTO.reviewAmount = media.getReviews().size();
        mediaRedisDTO.averageScore = media.getReviews().stream().mapToDouble(x -> x.getScore()).average().orElse(0);
        return mediaRedisDTO;
    }

    @Override
    public void subscribeForNotifications(String idMedia, String accessToken) throws ExecutionException, InterruptedException {
        String username = jwtTokenUtil.getUsernameFromToken(accessToken.replace("Bearer ", ""));
        HashMap<String, Object> subscription = new HashMap<>();
        subscription.put("username", username);
        subscription.put("mediaId", idMedia);
        firebaseService.post("subscriptions", subscription);
    }

    private boolean isSubscribedForNotifications(String mediaId) throws ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> subscriptions = firebaseService.get("subscriptions");
        List<Object> mediaIds = subscriptions.stream().map((sub) -> sub.get("mediaId")).collect(Collectors.toList());
        Boolean result = mediaIds.contains(mediaId);
        return result;
    }
}
