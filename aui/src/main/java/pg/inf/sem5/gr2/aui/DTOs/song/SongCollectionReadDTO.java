package pg.inf.sem5.gr2.aui.DTOs.song;

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
