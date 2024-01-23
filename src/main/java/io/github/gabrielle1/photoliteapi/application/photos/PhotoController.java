package io.github.gabrielle1.photoliteapi.application.photos;

import io.github.gabrielle1.photoliteapi.domain.entity.Photo;
import io.github.gabrielle1.photoliteapi.domain.enums.PhotoExtension;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/photos")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
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

    @GetMapping("{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) {

        var possiblePhoto = this.service.findById(id);
        if (possiblePhoto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var photo = possiblePhoto.get();
        var headers = new HttpHeaders();
        headers.setContentType(photo.getExtension().getMediaType());
        headers.setContentLength(photo.getSize());
        // inline; filename="image.PNG"
        headers.setContentDispositionFormData("inline; filename=\"" + photo.getFileName() + "\"", photo.getFileName());

        return new ResponseEntity<>(photo.getFile(), headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PhotoDTO>> search(
            @RequestParam(value="extension", required = false, defaultValue = "") String extension,
            @RequestParam(value="query", required = false) String query) {

        var result = service.search(PhotoExtension.getValueOfExtensionName(extension), query);

        var photos = result.stream()
                    .map(photo -> {
                    var url = buildPhotoURL(photo);
                    return mapper.photoToDTO(photo, url.toString());
                }).collect(Collectors.toList());


        return ResponseEntity.ok(photos);
    }

    private URI buildPhotoURL(Photo photo) {
        String photoPath = "/" + photo.getId();
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path(photoPath)
                .build().toUri();
    }

}
