package org.pg.inf.sem5.gr2.entities;

import lombok.Builder;
import lombok.Data;

@Builder
public class SongDTO implements Comparable<SongDTO> {
    private String title;
    private int duration;
    private String album;

    @Override
    public int compareTo(SongDTO other) {
        return Integer.compare(this.duration, other.duration); // Natural order by title
    }
}
