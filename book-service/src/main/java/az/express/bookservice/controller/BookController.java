package az.express.bookservice.controller;

import az.express.bookservice.data.dto.BookDTO;
import az.express.bookservice.data.dto.BookIdDTO;
import az.express.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@RequestMapping("/v1/book")
@RestController
@RequiredArgsConstructor
@Validated
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBook() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookIdDTO> getBookByIsbn(@PathVariable @NotEmpty String isbn) {
        return ResponseEntity.ok(bookService.findByIsbn(isbn));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable @NotEmpty UUID id) {
        return ResponseEntity.ok(bookService.findBookDetailsById(id));
    }
}