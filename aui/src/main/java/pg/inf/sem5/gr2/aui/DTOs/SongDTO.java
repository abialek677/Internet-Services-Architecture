package pg.inf.sem5.gr2.aui.DTOs;

import lombok.Builder;

@Builder
public class SongDTO implements Comparable<SongDTO> {
    private String title;
    private int duration;
    private String album;

    @Override
    public int compareTo(SongDTO other) {
        return Integer.compare(this.duration, other.duration);
    }
}

