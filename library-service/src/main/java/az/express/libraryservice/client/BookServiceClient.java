package az.express.libraryservice.client;

import az.express.libraryservice.config.FeignConfig;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "book-service", path = "/v1/book", configuration = FeignConfig.class)
public interface BookServiceClient {

    @GetMapping("/isbn/{isbn}")
    Response getBookByIsbn(@PathVariable(value = "isbn") String isbn);

    @GetMapping("/id/{id}")
    Response getBookById(@PathVariable(value = "id") UUID bookId);

}