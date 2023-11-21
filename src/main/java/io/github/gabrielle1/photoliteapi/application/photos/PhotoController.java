package io.github.gabrielle1.photoliteapi.application.photos;

import io.github.gabrielle1.photoliteapi.domain.entity.Photo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/photos")
@Slf4j
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoServiceImpl service;
    private final PhotoMapper mapper;

    // Formato para receber upload de arquivos: mult-part/formdata
    @PostMapping
    public ResponseEntity save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("tags") List<String> tags
    ) throws IOException {

        log.info("Imagem recebida: name: {}, size: {}", file.getOriginalFilename(), file.getSize());
        // log.info("Content Type: {}", file.getContentType()); // image/png

        var photo = mapper.mapToPhoto(file, name, tags);

        var photoSaved = this.service.save(photo);

        // build url to return
        URI photoURI = buildPhotoURL(photoSaved);

        return ResponseEntity.created(photoURI).build();
    }

    private URI buildPhotoURL(Photo photo) {
        String photoPath = "/" + photo.getId();
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(photoPath)
                .build().toUri();
    }

}
