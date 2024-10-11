package org.pg.inf.sem5.gr2.helpers;

import org.pg.inf.sem5.gr2.entities.Album;
import org.pg.inf.sem5.gr2.entities.Song;
import org.pg.inf.sem5.gr2.entities.SongDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class Utility {
    public static void printAlbums(List<Album> albums)
    {
        albums.forEach(album -> {
            System.out.println(album);

            album.getSongList().forEach(song -> {
                System.out.println(song);
            });
        });
    }

    public static void printSongsWithStream(List<Album> albums)
    {
        Set<Song> songs = albums.stream().flatMap(album -> album.getSongList().stream()).collect(Collectors.toSet());

        songs.forEach(song -> {
            System.out.println(song);
        });
    }

    public static void printAndFilterWithStream(List<Album> albums)
    {
        albums.stream().flatMap(album -> album.getSongList().stream()).filter(song -> song.getDuration() > 200).sorted(Comparator.comparing(Song::getTitle)).collect(Collectors.toList()).forEach(
                song -> {
                    System.out.println(song);
                }
        );
    }

    public static void processAlbumsIntoSongsDTO(List<Album> albums)
    {
        List<SongDTO> songs = albums.stream().flatMap(album -> album.getSongList().stream())
                .map(song -> SongDTO.builder()
                        .title(song.getTitle())
                        .duration(song.getDuration())
                        .album(song.getAlbum().getTitle())
                        .build()).sorted(Comparator.naturalOrder()).toList();

        songs.forEach(song -> {
            System.out.println(song);
        });
    }

    public static void serializeAlbums(List<Album> albums)
    {
        try
        {
            var outputStream = new ObjectOutputStream(new FileOutputStream("albums.dat"));
            for (Album album : albums)
            {
                outputStream.writeObject(album);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static List<Album> deserializeAlbums()
    {
        var albums = new ArrayList<Album>();
        try
        {
            var inputStream = new ObjectInputStream(new FileInputStream("albums.dat"));
            while (true)
            {
                try
                {
                    albums.add((Album) inputStream.readObject());
                }
                catch (EOFException e)
                {
                    break;
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }

        return albums;
    }

    public static void processAlbumsParallel(List<Album> albums)
    {
        var forkJoinPool = new ForkJoinPool(3);

        forkJoinPool.submit(() -> {
            albums.parallelStream().forEach(album -> {
                System.out.println("Processing album: " + album.getTitle() + '\n');

                album.getSongList().forEach(song -> {
                    System.out.println("Processing song: " + song.getTitle() + '\n');
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(song + "\n\n");
                });
            });
        }).join();
    }
}
