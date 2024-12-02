package pg.inf.sem5.gr2.albummanagement.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;


@SuperBuilder
@Entity
@Data

@Table(name = "albums")
public class Album implements Serializable, Comparable<Album> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;
    private String author;

    public Album() {
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return
                "\n{" +
                "\nAlbumTitle=" + title +
                ",\nAlbumAuthor=" + author +
                '}' + '\n';
    }

    @Override
    public int compareTo(Album other) {
        return this.title.compareTo(other.title);
    }
}
