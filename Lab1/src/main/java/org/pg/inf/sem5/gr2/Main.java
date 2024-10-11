package org.pg.inf.sem5.gr2;

import org.pg.inf.sem5.gr2.entities.*;
import org.pg.inf.sem5.gr2.helpers.Utility;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Album album1 = Album.builder()
                .title("Album1")
                .author("Author1")
                .build();

        Album album2 = Album.builder()
                .title("Album2")
                .author("Author2")
                .build();

        Album album3 = Album.builder()
                .title("Album3")
                .author("Author3")
                .build();

        Album album4 = Album.builder()
                .title("Album4")
                .author("Author4")
                .build();

        Album album5 = Album.builder()
                .title("Album5")
                .author("Author5")
                .build();

        Song song1 = Song.builder()
                .title("Song1")
                .duration(150)
                .album(album1)
                .build();

        Song song2 = Song.builder()
                .title("Song2")
                .duration(340)
                .album(album1)
                .build();

        Song song3 = Song.builder()
                .title("Song3")
                .duration(290)
                .album(album2)
                .build();
        
        Song song4 = Song.builder()
                .title("Song4")
                .duration(273)
                .album(album2)
                .build();

        Song song5 = Song.builder()
                .title("Song5")
                .duration(210) // 3 minutes and 30 seconds
                .album(album3)
                .build();

        Song song6 = Song.builder()
                .title("Song6")
                .duration(220) // 3 minutes and 40 seconds
                .album(album3)
                .build();

        Song song7 = Song.builder()
                .title("Song7")
                .duration(180) // 3 minutes
                .album(album4)
                .build();

        Song song8 = Song.builder()
                .title("Song8")
                .duration(400) // 6 minutes and 40 seconds
                .album(album4)
                .build();

        Song song9 = Song.builder()
                .title("Song9")
                .duration(250) // 4 minutes and 10 seconds
                .album(album5)
                .build();

        Song song10 = Song.builder()
                .title("Song10")
                .duration(300) // 5 minutes
                .album(album5)
                .build();

        album1.getSongList().add(song1);
        album1.getSongList().add(song2);

        album2.getSongList().add(song3);
        album2.getSongList().add(song4);

        album3.getSongList().add(song5);
        album3.getSongList().add(song6);

        album4.getSongList().add(song7);
        album4.getSongList().add(song8);

        album5.getSongList().add(song9);
        album5.getSongList().add(song10);

        var albums = List.of(album1, album2, album3, album4, album5);


        Utility.printAlbums(albums);

        Utility.serializeAlbums(albums);
        var deserializedAlbums = Utility.deserializeAlbums();

        Utility.printAlbums(deserializedAlbums);


        Utility.processAlbumsParallel(albums);
    }
}