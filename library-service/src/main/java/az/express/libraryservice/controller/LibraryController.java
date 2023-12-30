package az.express.libraryservice.controller;

import az.express.libraryservice.data.dto.AddBookRequest;
import az.express.libraryservice.data.dto.LibraryDTO;
import az.express.libraryservice.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/v1/library")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('READ_LIBRARY')")
    public ResponseEntity<LibraryDTO> getLibraryById(@PathVariable UUID id) {
        return ResponseEntity.ok(libraryService.getAllBooksInLibraryById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADD_LIBRARY')")
    public ResponseEntity<LibraryDTO> createLibrary() {
        return ResponseEntity.ok(libraryService.createLibrary());
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADD_BOOK_TO_LIBRARY')")
    public ResponseEntity<Void> addBookToLibrary(@RequestBody AddBookRequest request) {
        libraryService.addBookToLibrary(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('READ_LIBRARY')")
    public ResponseEntity<List<UUID>> getAllLibraries() {
        return ResponseEntity.ok(libraryService.getAllLibraries());
    }

}