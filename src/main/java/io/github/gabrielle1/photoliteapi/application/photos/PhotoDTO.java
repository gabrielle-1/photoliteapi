package io.github.gabrielle1.photoliteapi.application.photos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PhotoDTO {
    private String url;
    private String name;
    private String extension;
    private Long size;
    private LocalDate uploadDate;
}
