package az.express.libraryservice.service.impl;

import az.express.libraryservice.client.BookServiceClient;
import az.express.libraryservice.data.dto.AddBookRequest;
import az.express.libraryservice.data.dto.BookDTO;
import az.express.libraryservice.data.dto.LibraryDTO;
import az.express.libraryservice.data.entity.Library;
import az.express.libraryservice.exception.NotFoundException;
import az.express.libraryservice.repository.LibraryRepository;
import az.express.libraryservice.service.LibraryService;
import az.express.libraryservice.util.FeignResponseUtil;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static az.express.libraryservice.util.FeignResponseUtil.convertResponse;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;

    private final BookServiceClient bookServiceClient;

    @Override
    public LibraryDTO getAllBooksInLibraryById(UUID id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Library could not found by id: " + id, "LIBRARY_NOT_FOUND"));

        List<BookDTO> books = library.getUserBook()
                .stream()
                .map(bookId -> {
                    try {
                        Response bookResponse = bookServiceClient.getBookById(UUID.fromString(bookId));
                        return FeignResponseUtil.convertResponse(bookResponse, BookDTO.class);
                    } catch (Exception e) {
                        throw new RuntimeException("Error converting book response", e);
                    }
                })
                .collect(Collectors.toList());

        return new LibraryDTO(library.getId(), books);
    }

    @Override
    public LibraryDTO createLibrary() {
        Library newLibrary = libraryRepository.save(new Library());
        return new LibraryDTO(newLibrary.getId(), null);
    }

    @Override
    public void addBookToLibrary(AddBookRequest request) {
        Response bookResponse = bookServiceClient.getBookByIsbn(request.getIsbn());
        if (bookResponse.status() != 200) {
            throw new NotFoundException("Book not found by ISBN: " + request.getIsbn(), "BOOK_NOT_FOUND");
        }

        try {
            BookDTO bookDTO = convertResponse(bookResponse, BookDTO.class);

            if (bookDTO == null || bookDTO.getId() == null) {
                throw new NotFoundException("Book not found by ISBN: " + request.getIsbn(), "BOOK_NOT_FOUND");
            }

            UUID bookId = bookDTO.getId();

            Library library = libraryRepository.findById(request.getId())
                    .orElseThrow(() -> new NotFoundException("Library not found by id: " + request.getId(), "LIBRARY_NOT_FOUND"));

            library.getUserBook().add(bookId.toString());
            libraryRepository.save(library);
        } catch (Exception e) {
            throw new RuntimeException("Error processing book response", e);
        }
    }

    @Override
    public List<UUID> getAllLibraries() {
        return libraryRepository.findAll()
                .stream()
                .map(l -> l.getId())
                .collect(Collectors.toList());
    }
}
