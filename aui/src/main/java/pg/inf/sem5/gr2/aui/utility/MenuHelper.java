package pg.inf.sem5.gr2.aui.utility;

import pg.inf.sem5.gr2.aui.models.Album;
import pg.inf.sem5.gr2.aui.models.Song;
import pg.inf.sem5.gr2.aui.services.AlbumService;
import pg.inf.sem5.gr2.aui.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MenuHelper {
    private final Scanner scanner = new Scanner(System.in);
    private final AlbumService albumService;
    private final SongService songService;

    @Autowired
    public MenuHelper(AlbumService albumService, SongService songService) {
        this.albumService = albumService;
        this.songService = songService;
    }

    public static void printAllAlbums() {
        printAlbums(albumService.findAll());
    }

    public static void printAllSongs() {
        songService.findAll().forEach(song -> System.out.println(song));
    }

    public static void printAllSongsInAlbum() {
        List<Album> albums = albumService.findAll();
        Album album = selectAlbum(albums);
        songService.findByAlbum(album).forEach(song -> System.out.println(song));
    }

    public static void addNewAlbum() {
        System.out.print("Enter album title: ");
        String title = scanner.nextLine();
        System.out.print("Enter album author: ");
        String author = scanner.nextLine();

        Album album = Album.builder()
                .title(title)
                .author(author)
                .build();

        albumService.save(album);
    }

    public static void addNewSong() {
        List<Album> albums = albumService.findAll();
        Album album = selectAlbum(albums);

        System.out.print("Enter song title: ");
        String title = scanner.nextLine();
        System.out.print("Enter song duration: ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        Song song = Song.builder()
                .title(title)
                .duration(duration)
                .album(album)
                .build();

        songService.save(song);
    }

    public static void deleteAlbum() {
        List<Album> albums = albumService.findAll();
        Album album = selectAlbum(albums);
        albumService.delete(album.getId());
    }

    public static void deleteSong() {
        List<Song> songs = songService.findAll();
        Song song = selectSong(songs);
        songService.delete(song.getId());
    }

    // Helper methods
    private void printAlbums(List<Album> albums) {
        AtomicInteger counter = new AtomicInteger(1);
        System.out.println("Albums: ");
        albums.forEach(album -> System.out.println(counter.getAndIncrement() + " " + album.getTitle()));
    }

    private Album selectAlbum(List<Album> albums) {
        printAlbums(albums);
        System.out.print("Enter album number: ");
        int num = scanner.nextInt();
        scanner.nextLine();
        return albums.get(num - 1);
    }

    private Song selectSong(List<Song> songs) {
        AtomicInteger counter = new AtomicInteger(1);
        System.out.println("Songs: ");
        songs.forEach(song -> System.out.println(counter.getAndIncrement() + " " + song.getTitle()));
        System.out.print("Enter song number: ");
        int num = scanner.nextInt();
        scanner.nextLine();
        return songs.get(num - 1);
    }
}
