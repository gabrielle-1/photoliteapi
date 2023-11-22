package io.github.gabrielle1.photoliteapi.domain.service;

import io.github.gabrielle1.photoliteapi.domain.entity.Photo;

import java.util.Optional;

public interface PhotoService {

    Photo save(Photo photo);
    Optional<Photo> findById(String id);

}
