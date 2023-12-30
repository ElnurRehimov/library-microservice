package az.express.bookservice.mapper;

import az.express.bookservice.data.dto.BookDTO;
import az.express.bookservice.data.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO bookToBookDTO(Book book);

    List<BookDTO> mapEntityListToDtoList(List<Book> books);

}
