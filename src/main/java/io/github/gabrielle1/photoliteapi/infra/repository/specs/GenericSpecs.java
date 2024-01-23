package io.github.gabrielle1.photoliteapi.infra.repository.specs;

import org.springframework.data.jpa.domain.Specification;

public class GenericSpecs {

    private GenericSpecs(){}

    public static <T> Specification<T> conjunction() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }
}
