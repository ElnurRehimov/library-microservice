package az.express.bookservice.service.impl;

import az.express.bookservice.data.dto.BookDTO;
import az.express.bookservice.data.dto.BookIdDTO;
import az.express.bookservice.data.entity.Book;
import az.express.bookservice.exception.NotFoundException;
import az.express.bookservice.mapper.BookMapper;
import az.express.bookservice.repository.BookRepository;
import az.express.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Override
    public List<BookDTO> getAllBooks() {

        List<Book> books = bookRepository.findAll();
        var bookList = bookMapper.mapEntityListToDtoList(books);
        return bookList;
    }

    @Override
    public BookIdDTO findByIsbn(String isbn) {

        return bookRepository.getBookByIsbn(isbn)
                .map(book -> new BookIdDTO(book.getId(), book.getIsbn()))
                .orElseThrow(() -> new NotFoundException("Book could not found by isbn: " + isbn, "BOOK_NOT_FOUND"));
    }

    @Override
    public BookDTO findBookDetailsById(UUID bookId) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + bookId, "BOOK_NOT_FOUND"));
        return bookMapper.bookToBookDTO(book);
    }
}
