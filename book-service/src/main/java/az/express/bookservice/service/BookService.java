package az.express.bookservice.service;

import az.express.bookservice.data.dto.BookDTO;
import az.express.bookservice.data.dto.BookIdDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface BookService {

    List<BookDTO> getAllBooks();

    BookIdDTO findByIsbn(String isbn);

    BookDTO findBookDetailsById(UUID id);
}
