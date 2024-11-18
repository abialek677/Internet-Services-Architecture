package pg.inf.sem5.gr2.aui.DTOs.album;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumReadDTO {
    private UUID id;
    private String title;
    private String author;
}
