package pg.inf.sem5.gr2.aui.DTOs.album;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumCreateDTO {
    private String title;
    private String author;
}
