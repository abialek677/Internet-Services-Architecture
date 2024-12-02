package pg.inf.sem5.gr2.songmanagement.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongCreateDTO {
    private String title;
    private String album;
    private int duration;
}