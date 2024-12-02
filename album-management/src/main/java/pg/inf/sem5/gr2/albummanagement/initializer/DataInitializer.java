package pg.inf.sem5.gr2.albummanagement.initializer;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pg.inf.sem5.gr2.albummanagement.model.Album;
import pg.inf.sem5.gr2.albummanagement.repositories.AlbumRepository;

import java.util.UUID;

@Component
public class DataInitializer implements InitializingBean {
    private AlbumRepository albumRepository;

    @Autowired
    public DataInitializer(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Album u2Album = Album.builder()
                .title("The Joshua Tree")
                .author("U2")
                .build();

        Album depecheModeAlbum = Album.builder()
                .title("Violator")
                .author("Depeche Mode")
                .build();

        Album coldplayAlbum = Album.builder()
                .title("A Rush of Blood to the Head")
                .author("Coldplay")
                .build();

        albumRepository.save(u2Album);
        albumRepository.save(depecheModeAlbum);
        albumRepository.save(coldplayAlbum);
    }

}