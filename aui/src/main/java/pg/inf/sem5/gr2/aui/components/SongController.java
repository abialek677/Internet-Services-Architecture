package pg.inf.sem5.gr2.aui.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pg.inf.sem5.gr2.aui.DTOs.song.SongCollectionReadDTO;
import pg.inf.sem5.gr2.aui.DTOs.song.SongCreateDTO;
import pg.inf.sem5.gr2.aui.DTOs.song.SongReadDTO;
import pg.inf.sem5.gr2.aui.models.Album;
import pg.inf.sem5.gr2.aui.models.Song;
import pg.inf.sem5.gr2.aui.services.AlbumService;
import pg.inf.sem5.gr2.aui.services.SongService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/songs")
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;

    @Autowired
    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping
    public ResponseEntity<SongCollectionReadDTO> getAllSongs(){
        List<Song> songs = songService.findAll();

        List<SongReadDTO> songReadDTOs = songs.stream()
                .map(song -> SongReadDTO.builder()
                        .id(song.getId())
                        .title(song.getTitle())
                        .album(song.getAlbum().getTitle())
                        .duration(song.getDuration())
                        .build())
                .toList();

        return ResponseEntity.ok(SongCollectionReadDTO.builder()
                .songs(songReadDTOs)
                .build());
    }

    @PostMapping
    public ResponseEntity<SongReadDTO> createSong(@RequestBody SongCreateDTO songDTO){
        Album album = albumService.findByTitle(songDTO.getAlbum());
        if(album == null){
            return ResponseEntity.badRequest().build();
        }

        Song song = Song.builder()
                .title(songDTO.getTitle())
                .album(album)
                .duration(songDTO.getDuration())
                .build();

        album.getSongList().add(song);
        songService.save(song);

        return ResponseEntity.ok(SongReadDTO.builder()
                .id(song.getId())
                .title(song.getTitle())
                .album(song.getAlbum().getTitle())
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
                .album(song.getAlbum().getTitle())
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

        Album album = albumService.findByTitle(songDTO.getAlbum());
        if(album == null){
            return ResponseEntity.badRequest().build();
        }

        song.setTitle(songDTO.getTitle());
        song.setDuration(songDTO.getDuration());
        song.setAlbum(album);

        songService.save(song);

        return ResponseEntity.ok(SongReadDTO.builder()
                .id(song.getId())
                .title(song.getTitle())
                .album(song.getAlbum().getTitle())
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
}
