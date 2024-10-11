package org.pg.inf.sem5.gr2.entities;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Song implements Serializable {
    private String title;
    private int duration;
    private Album album;


    @Override
    public String toString() {
        return "Song{" +
                "title=" + title + '\n' +
                ", duration=" + duration +
                ", album=" + album.getTitle() +
                '}' + '\n';
    }
}
