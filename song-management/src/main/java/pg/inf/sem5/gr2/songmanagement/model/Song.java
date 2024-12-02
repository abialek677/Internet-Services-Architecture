package pg.inf.sem5.gr2.songmanagement.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

@Data
@SuperBuilder
@Entity
@Table(name = "songs")
public class Song implements Serializable, Comparable<Song> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;
    private int duration;

    @Column(name = "album_id")
    private UUID albumId;



    public Song() {
        this.id = UUID.randomUUID();
    }


    @Override
    public String toString() {
        return "\n{" +
                "\nSongTitle=" + title +
                ",\nSongDuration=" + duration +
                "\n}" + '\n';
    }

    @Override
    public int compareTo(Song other) {
        return Integer.compare(this.duration, other.duration);
    }
}
