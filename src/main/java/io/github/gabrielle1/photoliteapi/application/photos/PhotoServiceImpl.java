package io.github.gabrielle1.photoliteapi.application.photos;

import io.github.gabrielle1.photoliteapi.domain.entity.Photo;
import io.github.gabrielle1.photoliteapi.domain.service.PhotoService;
import io.github.gabrielle1.photoliteapi.infra.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    @Override
    @Transactional
    public Photo save(Photo photo) {
        var photoSaved = this.photoRepository.save(photo);
        return photoSaved;
    }

    @Override
    public Optional<Photo> findById(String id) {
        return this.photoRepository.findById(id);
    }
}
