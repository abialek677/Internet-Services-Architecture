package pg.inf.sem5.gr2.songmanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pg.inf.sem5.gr2.songmanagement.DTOs.SongCreateDTO;
import pg.inf.sem5.gr2.songmanagement.DTOs.SongReadDTO;
import pg.inf.sem5.gr2.songmanagement.repositories.SongRepository;
import pg.inf.sem5.gr2.songmanagement.model.Song;

import java.util.List;
import java.util.UUID;

@Service
public class SongService {

    private final SongRepository songRepository;
    private RestTemplate restTemplate;

    @Value("${album-service.url}")
    private String albumServiceUrl;

    @Autowired
    public SongService(SongRepository songRepository, RestTemplate restTemplate) {
        this.songRepository = songRepository;
        this.restTemplate = restTemplate;
    }

    public List<Song> findAll() {
        return songRepository.findAll();
    }

    public Song findById(UUID id) {
        return songRepository.findById(id).orElse(null);
    }

    public Song save(Song song) {
        return songRepository.save(song);
    }

    public void delete(UUID id) {
        songRepository.deleteById(id);
    }

    public List<Song> findByAlbumId(UUID albumId) {
        return songRepository.findByAlbumId(albumId);
    }

    public void deleteByAlbumId(UUID albumId) {
        songRepository.deleteByAlbumId(albumId);
    }

    public List<Song> findByTitle(String title) {
        return songRepository.findByTitle(title);
    }

    public Song create(SongCreateDTO songDTO) {
        String albumTitle = songDTO.getAlbum();
        UUID albumId = UUID.fromString(restTemplate.getForObject(albumServiceUrl + "/id/" + albumTitle, String.class));

        Song song = Song.builder()
                .title(songDTO.getTitle())
                .albumId(albumId)
                .duration(songDTO.getDuration())
                .build();

        songRepository.save(song);
        return song;
    }

    public void update(Song song, SongCreateDTO songDTO) {
        String albumTitle = songDTO.getAlbum();
        UUID albumId = UUID.fromString(restTemplate.getForObject(albumServiceUrl + "/id/" + albumTitle, String.class));

        song.setTitle(songDTO.getTitle());
        song.setAlbumId(albumId);
        song.setDuration(songDTO.getDuration());

        songRepository.save(song);
    }

    public String getAlbumTitle(Song song) {
        String albumTitle = restTemplate.getForObject(albumServiceUrl + "/title/" + song.getAlbumId(), String.class);
        return albumTitle;
    }
}
