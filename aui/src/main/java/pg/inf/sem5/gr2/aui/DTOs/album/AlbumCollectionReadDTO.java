package pg.inf.sem5.gr2.aui.DTOs.album;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumCollectionReadDTO {
    private List<AlbumReadDTO> albums;
}
