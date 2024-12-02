package pg.inf.sem5.gr2.albummanagement.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pg.inf.sem5.gr2.albummanagement.model.Album;
import pg.inf.sem5.gr2.albummanagement.repositories.AlbumRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    @Value("${song-service.url}")
    private String songServiceUrl;

    @Autowired
    public AlbumService(AlbumRepository albumRepository, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.albumRepository = albumRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    public Album findById(UUID id) {
        return albumRepository.findById(id).orElse(null);
    }

    public Album save(Album album) {
        return albumRepository.save(album);
    }

    public void delete(UUID id) {
        albumRepository.deleteById(id);
    }

    public Album findByTitle(String title) {
        return albumRepository.findByTitle(title);
    }

    public List<Album> findByAuthor(String author) {
        return albumRepository.findByAuthor(author);
    }

    @Transactional
    public void deleteAlbum(UUID id){
        String songDeleteUrl = songServiceUrl + "/delete-by-album/" + id;
        restTemplate.delete(songDeleteUrl);

        albumRepository.deleteById(id);
    }

    public List<JsonNode> getAlbumsSongs(UUID id) {
        String songGetUrl = songServiceUrl + "/albumId/" + id;

        JsonNode response = restTemplate.getForObject(songGetUrl, JsonNode.class);

        if (response != null && response.has("songs")) {
            JsonNode songsArray = response.get("songs");

            List<JsonNode> songs = new ArrayList<>();
            for (JsonNode song : songsArray) {
                songs.add(song);
            }

            return songs;
        }

        return List.of();
    }

}
