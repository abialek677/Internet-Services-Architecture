package org.pg.inf.sem5.gr2.entities;

import lombok.Builder;
import lombok.Data;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
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
