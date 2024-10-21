package pg.inf.sem5.gr2.aui.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "songs")
public class Song implements Serializable, Comparable<Song> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;
    private int duration;

    @ManyToOne
    @JoinColumn(name = "album")
    private Album album;

    public Song(UUID id, String title, int duration, Album album) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.album = album;

        if (album != null) {
            album.getSongList().add(this);
        }
    }

    public Song() {
        this.id = UUID.randomUUID();
    }


    @Override
    public String toString() {
        return "\n{" +
                "\nSongTitle=" + title +
                ",\nSongDuration=" + duration +
                ",\n =" + album.getTitle() +
                "\n}" + '\n';
    }

    @Override
    public int compareTo(Song other) {
        return Integer.compare(this.duration, other.duration);
    }
}
