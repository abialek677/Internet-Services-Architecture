package pg.inf.sem5.gr2.songmanagement.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongCollectionReadDTO {
    private List<SongReadDTO> songs;
}
