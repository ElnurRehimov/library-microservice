package az.express.bookservice.repository;

import az.express.bookservice.data.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    Optional<Book> getBookByIsbn(String isbn);

    Optional<Book> findById(UUID id);
}
