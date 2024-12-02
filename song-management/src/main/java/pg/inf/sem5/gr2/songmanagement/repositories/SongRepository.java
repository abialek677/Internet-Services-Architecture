package pg.inf.sem5.gr2.songmanagement.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pg.inf.sem5.gr2.songmanagement.model.Song;

import java.beans.Transient;
import java.util.List;
import java.util.UUID;

@Repository
public interface SongRepository extends JpaRepository<Song, UUID> {

    @Modifying
    @Transactional
    @Query("SELECT s FROM Song s WHERE s.albumId = :albumId")
    List<Song> findByAlbumId(UUID albumId);

    List<Song> findByTitle(String title);

    @Modifying
    @Transactional
    @Query("DELETE FROM Song s WHERE s.albumId = :albumId")
    void deleteByAlbumId(UUID albumId);
}
