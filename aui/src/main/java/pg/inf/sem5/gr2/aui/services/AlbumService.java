package pg.inf.sem5.gr2.aui.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg.inf.sem5.gr2.aui.models.Album;
import pg.inf.sem5.gr2.aui.repositories.AlbumRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
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
}
