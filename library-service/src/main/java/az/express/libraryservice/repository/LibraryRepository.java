package az.express.libraryservice.repository;

import az.express.libraryservice.data.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LibraryRepository extends JpaRepository<Library, UUID> {
}