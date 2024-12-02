package pg.inf.sem5.gr2.albummanagement.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pg.inf.sem5.gr2.albummanagement.DTOs.AlbumCreateDTO;
import pg.inf.sem5.gr2.albummanagement.DTOs.AlbumReadDTO;
import pg.inf.sem5.gr2.albummanagement.DTOs.AlbumCollectionReadDTO;
import pg.inf.sem5.gr2.albummanagement.model.Album;
import pg.inf.sem5.gr2.albummanagement.services.AlbumService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public ResponseEntity<AlbumCollectionReadDTO> getAllAlbums() {
        List<Album> albums = albumService.findAll();
        List<AlbumReadDTO> albumList = albums.stream()
                .map(album -> AlbumReadDTO.builder()
                        .id(album.getId())
                        .title(album.getTitle())
                        .author(album.getAuthor())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(AlbumCollectionReadDTO.builder().albums(albumList).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumReadDTO> getAlbumById(@PathVariable UUID id) {
        Album album = albumService.findById(id);

        if (album != null) {
            AlbumReadDTO albumReadDTO = AlbumReadDTO.builder()
                    .id(album.getId())
                    .title(album.getTitle())
                    .author(album.getAuthor())
                    .build();
            return ResponseEntity.ok(albumReadDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    @PostMapping
    public ResponseEntity<AlbumReadDTO> createAlbum(@RequestBody AlbumCreateDTO albumDTO) {
        Album newAlbum = Album.builder()
                .title(albumDTO.getTitle())
                .author(albumDTO.getAuthor())
                .build();

        albumService.save(newAlbum);

        return ResponseEntity.status(HttpStatus.CREATED).body(AlbumReadDTO.builder()
                .id(newAlbum.getId())
                .title(newAlbum.getTitle())
                .author(newAlbum.getAuthor())
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumReadDTO> updateAlbum(@PathVariable UUID id, @RequestBody AlbumCreateDTO albumDTO) {
        Album album = albumService.findById(id);

        if (album != null) {
            album.setTitle(albumDTO.getTitle());
            album.setAuthor(albumDTO.getAuthor());

            albumService.save(album);

            return ResponseEntity.ok(AlbumReadDTO.builder()
                    .id(album.getId())
                    .title(album.getTitle())
                    .author(album.getAuthor())
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable UUID id) {
        Album album = albumService.findById(id);

        if (album != null) {
            albumService.deleteAlbum(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<List<JsonNode>> getSongsByAlbumId(@PathVariable UUID id) {
        List<JsonNode> songs = albumService.getAlbumsSongs(id);
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/id/{title}")
    public ResponseEntity<String> getAlbumIdByTitle(@PathVariable String title) {
        Album album = albumService.findByTitle(title);
        if (album != null) {
            return ResponseEntity.ok(album.getId().toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/title/{id}")
    public ResponseEntity<String> getAlbumTitleById(@PathVariable UUID id) {
        Album album = albumService.findById(id);
        if (album != null) {
            return ResponseEntity.ok(album.getTitle());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
