package pg.inf.sem5.gr2.songmanagement.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongReadDTO {
    private UUID id;
    private String title;
    private int duration;
    private String album;
}
