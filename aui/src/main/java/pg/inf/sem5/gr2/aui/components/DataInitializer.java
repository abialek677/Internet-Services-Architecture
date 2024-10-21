package pg.inf.sem5.gr2.aui.components;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pg.inf.sem5.gr2.aui.models.Album;
import pg.inf.sem5.gr2.aui.models.Song;
import pg.inf.sem5.gr2.aui.services.AlbumService;
import pg.inf.sem5.gr2.aui.services.SongService;

@Component
public class DataInitializer {
    private final AlbumService albumService;
    private final SongService songService;

    @Autowired
    public DataInitializer(AlbumService albumService, SongService songService) {
        this.albumService = albumService;
        this.songService = songService;
    }

    @PostConstruct
    public void init() {
        Album u2Album = Album.builder()
                .title("The Joshua Tree")
                .author("U2")
                .build();

        Song u2Song1 = Song.builder()
                .title("With or Without You")
                .duration(296)
                .album(u2Album)
                .build();

        Song u2Song2 = Song.builder()
                .title("I Still Haven't Found What I'm Looking For")
                .duration(277)
                .album(u2Album)
                .build();

        Album depecheModeAlbum = Album.builder()
                .title("Violator")
                .author("Depeche Mode")
                .build();

        Song depecheModeSong1 = Song.builder()
                .title("Personal Jesus")
                .duration(285)
                .album(depecheModeAlbum)
                .build();

        Song depecheModeSong2 = Song.builder()
                .title("Enjoy the Silence")
                .duration(246)
                .album(depecheModeAlbum)
                .build();

        Album coldplayAlbum = Album.builder()
                .title("A Rush of Blood to the Head")
                .author("Coldplay")
                .build();

        Song coldplaySong1 = Song.builder()
                .title("The Scientist")
                .duration(311)
                .album(coldplayAlbum)
                .build();

        Song coldplaySong2 = Song.builder()
                .title("Clocks")
                .duration(307)
                .album(coldplayAlbum)
                .build();

        albumService.save(u2Album);
        albumService.save(depecheModeAlbum);
        albumService.save(coldplayAlbum);

        songService.save(u2Song1);
        songService.save(u2Song2);
        songService.save(depecheModeSong1);
        songService.save(depecheModeSong2);
        songService.save(coldplaySong1);
        songService.save(coldplaySong2);
    }
}
