package io.github.gabrielle1.photoliteapi.infra.repository;

import io.github.gabrielle1.photoliteapi.domain.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, String> {
}
