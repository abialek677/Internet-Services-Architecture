package pg.inf.sem5.gr2.albummanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pg.inf.sem5.gr2.albummanagement.model.Album;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlbumRepository extends JpaRepository<Album, UUID> {
    Album findByTitle(String title);

    List<Album> findByAuthor(String author);
}
