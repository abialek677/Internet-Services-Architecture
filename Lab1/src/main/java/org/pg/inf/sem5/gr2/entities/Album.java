package org.pg.inf.sem5.gr2.entities;

import lombok.Builder;
import lombok.Data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@Builder
@Entity
public class Album implements Serializable {
    private String title;
    private String author;
    private List<Song> songList;

    public Album(String title, String author, List<Song> songList) {
        this.title = title;
        this.author = author;
        this.songList = (songList != null) ? songList : new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Album{" +
                "title=" + title + '\n' +
                ", author=" + author + '\n' +
                ", songs=" + '\n' +songList + '\n'+
                '}' + '\n';
    }
}
