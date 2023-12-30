package az.express.bookservice.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private UUID id;

    private String title;

    private Integer bookYear;

    private String author;

    private String pressName;

    private String isbn;

}
