package pg.inf.sem5.gr2.aui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pg.inf.sem5.gr2.aui.models.Album;
import pg.inf.sem5.gr2.aui.models.Song;

import java.util.List;
import java.util.UUID;

@Repository
public interface SongRepository extends JpaRepository<Song, UUID> {
    List<Song> findByAlbum(Album album);

    List<Song> findByTitle(String title);
}
