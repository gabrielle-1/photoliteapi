package io.github.gabrielle1.photoliteapi.domain.enums;

import org.springframework.http.MediaType;

import java.util.Arrays;

public enum PhotoExtension {
    PNG(MediaType.IMAGE_PNG),
    GIF(MediaType.IMAGE_GIF),
    JPEG(MediaType.IMAGE_JPEG);

    private MediaType mediaType;

    PhotoExtension(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public static PhotoExtension getValueOfMediaType(MediaType mediaType) {
        return Arrays.stream(values())
                .filter(imageExtension -> imageExtension.mediaType.equals(mediaType))
                .findFirst()
                .orElse(null);
    }
}
