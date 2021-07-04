package ar.edu.unq.desapp.grupof012021.backenddesappapi;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.dto.UserCredentialsDTO;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.*;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaType;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaGenreType.*;

@Component
public class DataLoader implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewReportRepository reviewReportRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Your application started with option names : {}", args.getOptionNames());
        logger.info("Creating Actor");
        addActor();
        logger.info("Creating Genres");
        addGenre();
        logger.info("Add Platform");
        addPlatform();
        logger.info("Add Fake user");
        addUser();
        logger.info("Creating Media");
        addMedia();
        logger.info("Add 5 Review");
        addReviewDonnieDarko("Review 1");
        addReviewDonnieDarko("Review 2");
        addReviewDonnieDarko("Review 3");
        addReviewDonnieDarko("Review 4");
        addReviewDonnieDarko("Review 5");
        addReviewShrek();
        logger.info("Add ReportMotive");
        addReportMotive();

        logger.info("DataLoader Success");
    }

    private void addActor(){
        List<Actor> actors = new ArrayList<Actor>();
        //DONNIE DARKO
        actors.add(new Actor("nm0350453", "Jake Gyllenhaal"));
        actors.add(new Actor("nm0651660", "Holmes Osborne"));
        actors.add(new Actor("nm0540441", "Jena Malone"));
        actors.add(new Actor("nm0001521", "Mary McDonnell"));
        //SHREK
        actors.add(new Actor("nm0000196", "Mike Myers"));
        actors.add(new Actor("nm0000552", "Eddie Murphy"));
        actors.add(new Actor("nm0001475", "John Lithgow"));
        actors.add(new Actor("nm0000139", "Cameron Diaz"));
        actorRepository.saveAll(actors);
        logger.info("Actors added");
    }

    private void addGenre(){
        List<Genre> genres = new ArrayList<Genre>();
        genres.add(new Genre(ACTION));
        genres.add(new Genre(COMEDY));
        genres.add(new Genre(DRAMA));
        genres.add(new Genre(ADVENTURE));
        genres.add(new Genre(ANIMATION));
        genres.add(new Genre(CRIME));
        genres.add(new Genre(MISTERY));
        genres.add(new Genre(FAMILY));
        genres.add(new Genre(SCIFI));
        genreRepository.saveAll(genres);
    }

    private void addMedia(){
        List<Genre> genresDonnieDarko = new ArrayList<Genre>();
        genresDonnieDarko.add(genreRepository.findByGenreName(DRAMA));
        genresDonnieDarko.add(genreRepository.findByGenreName(MISTERY));

        List<Actor> actorsDonnieDarko = new ArrayList<Actor>();
        actorsDonnieDarko.add(actorRepository.findByName("Jake Gyllenhaal"));
        actorsDonnieDarko.add(actorRepository.findByName("Holmes Osborne"));
        actorsDonnieDarko.add(actorRepository.findByName("Jena Malone"));
        actorsDonnieDarko.add(actorRepository.findByName("Mary McDonnell"));

        Media donnieDarkoMedia = new Media(
                "tt0246578",
                "donnieDarko",
                "donnieDarko",
                2001,
                null,
                113,
                MediaType.MOVIE,
                null,
                genresDonnieDarko,
                actorsDonnieDarko
        );
        mediaRepository.save(donnieDarkoMedia);
        logger.info("Media name: {}", mediaRepository.findById(donnieDarkoMedia.getId()).getId());

        List<Genre> genresShrek = new ArrayList<Genre>();
        genresShrek.add(genreRepository.findByGenreName(ADVENTURE));
        genresShrek.add(genreRepository.findByGenreName(ANIMATION));
        genresShrek.add(genreRepository.findByGenreName(COMEDY));

        List<Actor> actorsShrek = new ArrayList<Actor>();
        actorsShrek.add(actorRepository.findByName("Mike Myers"));
        actorsShrek.add(actorRepository.findByName("Eddie Murphy"));
        actorsShrek.add(actorRepository.findByName("John Lithgow"));
        actorsShrek.add(actorRepository.findByName("Cameron Diaz"));

        Media shrekMedia = new Media(
                "tt0126029",
                "shrek",
                "shrek",
                2001,
                null,
                90,
                MediaType.MOVIE,
                null,
                genresShrek,
                actorsShrek
        );
        mediaRepository.save(shrekMedia);
        logger.info("Media name: {}", mediaRepository.findById(shrekMedia.getId()).getId());
    }

    private void addReviewDonnieDarko(String numberReview){
        Media donnieDarkoMedia = mediaRepository.findByIdStringMedia("tt0246578");
        List<Review> reviews =donnieDarkoMedia.getReviews();

        UserEntity userEntity = userEntityRepository.findByUsername("Pepe");

        Review aReview = new Review(
                numberReview,
                "Donnie Darko is a truly fascinating film experience. It's not a perfect film, but it's an ambitious one, and for the most part, it fulfills its ambition.",
                "Netflix",
                "EN_US",
                false,
                false,
                "EE.UU",
                4.0,
                donnieDarkoMedia,
                userEntity
        );
        reviews.add(aReview);
        donnieDarkoMedia.setReviews(reviews);
        mediaRepository.save(donnieDarkoMedia);
    }

    private void addReviewShrek(){
        Media shrekMedia = mediaRepository.findByIdStringMedia("tt0126029");
        List<Review> reviews = shrekMedia.getReviews();

        UserEntity userEntity = userEntityRepository.findByUsername("Pepe");

        Review aReview = new Review(
                "Great movie.",
                "Shrek is life.",
                "Amazon Prime",
                "EN_US",
                false,
                true,
                "EE.UU",
                5.0,
                shrekMedia,
                userEntity
        );
        reviews.add(aReview);
        shrekMedia.setReviews(reviews);
        mediaRepository.save(shrekMedia);
    }

    private void addReportMotive(){
        Review donnieDarkoReview = reviewRepository.findAll().iterator().next();
        UserEntity userEntity = userEntityRepository.findByUsername("Pepe");

        ReportMotive aReportMotive = new ReportMotive(
                "It's perfect",
                donnieDarkoReview,
                userEntity
        );
        reviewReportRepository.save(aReportMotive);
    }

    private void addPlatform() throws Exception {
        List<Platform> platforms = new ArrayList<Platform>();
        platforms.add(new Platform("Netflix"));
        platforms.add(new Platform("Disney Plus"));
        platforms.add(new Platform("Amazon Prime"));
        platformRepository.saveAll(platforms);
    }

    private  void addUser() throws Exception {
        UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO();
        userCredentialsDTO.setUsername("Pepe");
        userCredentialsDTO.setPassword("1234");

        Platform platform = platformRepository.findByPlatformType(new Platform("Netflix").getPlatformType());

        UserEntity userEntity = new UserEntity(userCredentialsDTO, platform);
        userEntityRepository.save(userEntity);
    }

}
