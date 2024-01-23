package io.github.gabrielle1.photoliteapi.infra.repository.specs;

import io.github.gabrielle1.photoliteapi.domain.entity.Photo;
import io.github.gabrielle1.photoliteapi.domain.enums.PhotoExtension;
import org.springframework.data.jpa.domain.Specification;

public class PhotoSpecs {

    // Constructor private to avoid instantiation
    private PhotoSpecs(){}

    public static Specification<Photo> extensionEqual(PhotoExtension extension) {
        return (root, q, criteriaBuilder) -> criteriaBuilder.equal(root.get("extension"), extension);
    }

    public static Specification<Photo> nameLike(String name) {
        return (root, q, criteriaBuilder) -> criteriaBuilder.like( criteriaBuilder.upper(root.get("name") ), "%" + name.toUpperCase() + "%");
    }

    public static Specification<Photo> tagsLike(String tags) {
        return (root, q, criteriaBuilder) -> criteriaBuilder.like( criteriaBuilder.upper(root.get("tags") ), "%" + tags.toUpperCase() + "%");
    }

}
