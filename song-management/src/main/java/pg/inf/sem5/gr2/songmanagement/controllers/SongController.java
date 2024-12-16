package pg.inf.sem5.gr2.songmanagement.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pg.inf.sem5.gr2.songmanagement.DTOs.SongCollectionReadDTO;
import pg.inf.sem5.gr2.songmanagement.DTOs.SongCreateDTO;
import pg.inf.sem5.gr2.songmanagement.DTOs.SongReadDTO;
import pg.inf.sem5.gr2.songmanagement.model.Song;
import pg.inf.sem5.gr2.songmanagement.services.SongService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/songs")
public class SongController {
    private final SongService songService;
    private final ObjectMapper objectMapper;

    @Autowired
    public SongController(SongService songService, ObjectMapper objectMapper) {
        this.songService = songService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public ResponseEntity<SongCollectionReadDTO> getAllSongs(){
        List<Song> songs = songService.findAll();



        List<SongReadDTO> songReadDTOs = songs.stream()
                .map(song -> SongReadDTO.builder()
                        .id(song.getId())
                        .title(song.getTitle())
                        .album(songService.getAlbumTitle(song))
                        .duration(song.getDuration())
                        .build())
                .toList();

        return ResponseEntity.ok(SongCollectionReadDTO.builder()
                .songs(songReadDTOs)
                .build());
    }

    @DeleteMapping("/delete-by-album/{albumId}")
    public ResponseEntity<Void> deleteSongsByAlbumId(@PathVariable UUID albumId) {
        songService.deleteByAlbumId(albumId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<SongReadDTO> createSong(@RequestBody SongCreateDTO songDTO){

        Song song = songService.create(songDTO, null);

        return ResponseEntity.ok(SongReadDTO.builder()
                .id(song.getId())
                .title(song.getTitle())
                .duration(song.getDuration())
                .build());
    }

    @PostMapping("/{albumId}")
    public ResponseEntity<SongReadDTO> createSongByAlbumId(@RequestBody SongCreateDTO songDTO, @PathVariable UUID albumId) {
        Song song = songService.create(songDTO, albumId);

        return ResponseEntity.ok(SongReadDTO.builder()
                .id(song.getId())
                .title(song.getTitle())
                .duration(song.getDuration())
                .build());
    }



    @GetMapping("/{id}")
    public ResponseEntity<SongReadDTO> getSongById(@PathVariable UUID id) {
        Song song = songService.findById(id);
        if (song == null) {
            return ResponseEntity.notFound().build();
        }

        SongReadDTO songDTO = SongReadDTO.builder()
                .id(song.getId())
                .title(song.getTitle())
                .duration(song.getDuration())
                .build();

        return ResponseEntity.ok(songDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SongReadDTO> updateSong(@PathVariable UUID id, @RequestBody SongCreateDTO songDTO) {
        Song song = songService.findById(id);
        if (song == null) {
            return ResponseEntity.notFound().build();
        }

        songService.update(song, songDTO, null);

        return ResponseEntity.ok(SongReadDTO.builder()
                .id(song.getId())
                .title(song.getTitle())
                .duration(song.getDuration())
                .build());
    }

    @PutMapping("/{albumId}/edit/{id}")
    public ResponseEntity<SongReadDTO> updateSongInAlbum(@PathVariable UUID id,@PathVariable UUID albumId, @RequestBody SongCreateDTO songDTO) {
        Song song = songService.findById(id);
        if (song == null) {
            return ResponseEntity.notFound().build();
        }

        songService.update(song, songDTO, albumId);

        return ResponseEntity.ok(SongReadDTO.builder()
                .id(song.getId())
                .title(song.getTitle())
                .duration(song.getDuration())
                .build());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable UUID id) {
        Song song = songService.findById(id);
        if (song == null) {
            return ResponseEntity.notFound().build();
        }

        songService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/albumId/{albumId}")
    public ResponseEntity<JsonNode> getSongsByAlbumId(@PathVariable UUID albumId) {
        List<Song> songs = songService.findByAlbumId(albumId);

        List<SongReadDTO> songReadDTOs = songs.stream()
                .map(song -> SongReadDTO.builder()
                        .id(song.getId())
                        .title(song.getTitle())
                        .duration(song.getDuration())
                        .build())
                .toList();

        SongCollectionReadDTO songCollection = SongCollectionReadDTO.builder()
                .songs(songReadDTOs)
                .build();

        JsonNode jsonNode = objectMapper.valueToTree(songCollection);

        return ResponseEntity.ok(jsonNode);
    }

}
