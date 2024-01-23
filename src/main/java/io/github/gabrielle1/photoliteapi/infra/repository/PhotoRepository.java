package io.github.gabrielle1.photoliteapi.infra.repository;

import io.github.gabrielle1.photoliteapi.domain.entity.Photo;
import io.github.gabrielle1.photoliteapi.domain.enums.PhotoExtension;
import io.github.gabrielle1.photoliteapi.infra.repository.specs.GenericSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import java.util.List;

import static io.github.gabrielle1.photoliteapi.infra.repository.specs.GenericSpecs.conjunction;
import static io.github.gabrielle1.photoliteapi.infra.repository.specs.PhotoSpecs.*;
import static org.springframework.data.jpa.domain.Specification.anyOf;
import static org.springframework.data.jpa.domain.Specification.where;

public interface PhotoRepository extends JpaRepository<Photo, String>, JpaSpecificationExecutor<Photo> {

    default List<Photo> findByExtensionAndNameOrTagsLike(PhotoExtension extension, String query) {
        // SELECT * FROM PHOTO WHERE 1 = 1
        Specification<Photo> specification = where(conjunction());

        if (extension != null) {
            // AND EXTENSION = 'PNG'
            specification = specification.and(extensionEqual(extension));
        }

        if (StringUtils.hasText(query)) {
            // AND NAME LIKE 'QUERY' OR TAGS LIKE 'QUERY'
            specification = specification.and(anyOf(nameLike(query), tagsLike(query)));
        }

        return findAll(specification);
    }

}
