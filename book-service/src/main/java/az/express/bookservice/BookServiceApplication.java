package az.express.bookservice;

import az.express.bookservice.data.entity.Book;
import az.express.bookservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@RequiredArgsConstructor
@EnableDiscoveryClient
public class BookServiceApplication implements CommandLineRunner {

    private final BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Book book1 = new Book(UUID.randomUUID(), "Dünyanın Gözü", 2000, "Robert Jordan", "Baki Nesriyyat", "123456");
        Book book2 = new Book(UUID.randomUUID(), "Uzüklerin Efendisi", 1960, "J.R.R Tolkien", "Baki Nesriyyat", "456789");
        Book book3 = new Book(UUID.randomUUID(), "Harry Porter ve Felsefe Daşı", 1997, "J. K. Rowling", "YKB Nesriyyat", "987654");

        List<Book> bookList = bookRepository.saveAll(Arrays.asList(book1, book2, book3));

        System.out.println(bookList);

    }
}
