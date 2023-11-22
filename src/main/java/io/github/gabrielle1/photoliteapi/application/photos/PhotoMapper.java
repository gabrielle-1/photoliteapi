package io.github.gabrielle1.photoliteapi.application.photos;

import io.github.gabrielle1.photoliteapi.domain.entity.Photo;
import io.github.gabrielle1.photoliteapi.domain.enums.PhotoExtension;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class PhotoMapper {

    public Photo mapToPhoto(MultipartFile file, String name, List<String> tags) throws IOException {

        var photoExtension = PhotoExtension.getValueOfMediaType(MediaType.parseMediaType(file.getContentType()));

        return Photo.builder()
                .name(name)
                .size(file.getSize())
                .extension(photoExtension)
                .tags(String.join(",", tags))
                .file(file.getBytes())
                .build();
    }

    public PhotoDTO photoToDTO(Photo photo, String url) {
        return PhotoDTO.builder()
                .url(url)
                .name(photo.getName())
                .extension(photo.getExtension().name())
                .size(photo.getSize())
                .uploadDate(photo.getUploadDate().toLocalDate())
                .build();
    }

}
