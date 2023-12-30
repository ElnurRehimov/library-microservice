package az.express.libraryservice.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "LIBRARY")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "library_id")
    private UUID id;

    @Column
    @ElementCollection
    private List<String> userBook;
}
