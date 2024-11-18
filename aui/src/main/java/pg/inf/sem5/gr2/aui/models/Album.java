package pg.inf.sem5.gr2.aui.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Builder
@Entity
@Data

@Table(name = "albums")
public class Album implements Serializable, Comparable<Album> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;
    private String author;

    @OneToMany(mappedBy = "album", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(name = "songs")
    private List<Song> songList;

    public Album(UUID id, String title, String author, List<Song> songList) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.songList = (songList != null) ? songList : new ArrayList<>();
    }

    public Album() {
        this.id = UUID.randomUUID();
        this.songList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return
                "\n{" +
                "\nAlbumTitle=" + title +
                ",\nAlbumAuthor=" + author +
                ",\nAlbumSongs=" + '\n' + songList + '\n' +
                '}' + '\n';
    }

    @Override
    public int compareTo(Album other) {
        return this.title.compareTo(other.title);
    }
}
