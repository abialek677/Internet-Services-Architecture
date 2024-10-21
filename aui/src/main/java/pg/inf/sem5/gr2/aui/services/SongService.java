package pg.inf.sem5.gr2.aui.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg.inf.sem5.gr2.aui.models.Album;
import pg.inf.sem5.gr2.aui.repositories.SongRepository;
import pg.inf.sem5.gr2.aui.models.Song;

import java.util.List;
import java.util.UUID;

@Service
public class SongService {

    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
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

    public List<Song> findByAlbum(Album album) {
        return songRepository.findByAlbum(album);
    }

    public List<Song> findByTitle(String title) {
        return songRepository.findByTitle(title);
    }
}
