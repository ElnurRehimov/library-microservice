package az.express.libraryservice.service;

import az.express.libraryservice.data.dto.AddBookRequest;
import az.express.libraryservice.data.dto.LibraryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface LibraryService {

    LibraryDTO getAllBooksInLibraryById(UUID id);

    LibraryDTO createLibrary();

    void addBookToLibrary(AddBookRequest request);

    List<UUID> getAllLibraries();
}
