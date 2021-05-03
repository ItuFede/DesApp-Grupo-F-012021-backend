package ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Media;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity.Review;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.persistence.MediaRepository;
import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("mediaService")
public class MediaServiceImpl implements MediaService {
    @Autowired
    MediaRepository repository;

    public MediaServiceImpl() { }

    public MediaServiceImpl(MediaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addReviewTo(long mediaId, Review aReview) {
        Media aMedia = this.findById(mediaId);
        aReview.setMediaReview(aMedia);
        aMedia.getReviews().add(aReview);
        repository.save(aMedia);
    }

    public Media findById(long mediaId) {
        return repository.findById(mediaId);
    }
}
