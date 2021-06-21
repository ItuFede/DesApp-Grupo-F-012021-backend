package ar.edu.unq.desapp.grupof012021.backenddesappapi;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.*;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.MediaType;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.Year;
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
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewReportRepository reviewReportRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Your application started with option names : {}", args.getOptionNames());
        logger.info("Creating Actor");
        addActor();
        logger.info("Creating Genres");
        addGenre();
        logger.info("Creating Media");
        addMedia();
        logger.info("Add Review");
        addReview();
        logger.info("Add ReportMotive");
        addReportMotive();

        logger.info("DataLoader Success");
    }

    private void addActor(){
        List<Actor> actors = new ArrayList<Actor>();
        actors.add(new Actor("nm0350453", "Jake Gyllenhaal"));
        actors.add(new Actor("nm0651660", "Holmes Osborne"));
        actors.add(new Actor("nm0540441", "Jena Malone"));
        actors.add(new Actor("nm0001521", "Mary McDonnell"));
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
        List<Genre> genres = new ArrayList<Genre>();
        genres.add(genreRepository.findByGenreName(DRAMA));
        genres.add(genreRepository.findByGenreName(MISTERY));
        Media aMedia = new Media(
                "tt0246578",
                "donnieDarko",
                "donnieDarko",
                Year.of(2001),
                null,
                113,
                MediaType.MOVIE,
                null,
                genres
        );
        mediaRepository.save(aMedia);
        logger.info("Media name: {}", mediaRepository.findById(aMedia.getId()).getId());
    }

    private void addReview(){
        Media donnieDarkoMedia = mediaRepository.findByIdStringMedia("tt0246578");
        List<Review> reviews =donnieDarkoMedia.getReviews();

        Review aReview = new Review(
                "Bizarre, but oh so great!",
                "Donnie Darko is a truly fascinating film experience. It's not a perfect film, but it's an ambitious one, and for the most part, it fulfills its ambition.",
                "Netflix",
                "EN_US",
                false,
                false,
                "EE.UU",
                4.0,
                donnieDarkoMedia
        );
        reviews.add(aReview);
        donnieDarkoMedia.setReviews(reviews);
        mediaRepository.save(donnieDarkoMedia);

    }

    private void addReportMotive(){
        Review donnieDarkoReview = reviewRepository.findById(15); //TODO: refactor reviewId
        ReportMotive aReportMotive = new ReportMotive(
                "It's perfect",
                donnieDarkoReview,
                null
        );
        reviewReportRepository.save(aReportMotive);
    }

}
