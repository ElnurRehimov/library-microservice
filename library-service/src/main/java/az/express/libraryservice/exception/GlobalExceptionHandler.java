package az.express.libraryservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handle(NotFoundException ex) {

        log.error("NotFoundException: {}", ExceptionUtils.getStackTrace(ex));
        return ExceptionResponse.of(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(LibraryNotFoundException.class)
    public ResponseEntity<?> handle(LibraryNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handle(BookNotFoundException exception) {
        return new ResponseEntity<>(exception.getExceptionMessage(), HttpStatus.resolve(exception.getExceptionMessage().status()));
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handle(BadRequestException ex) {

        log.error("BadRequestException: {}", ExceptionUtils.getStackTrace(ex));
        return ExceptionResponse.of(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ExceptionResponse handle(Exception ex) {
        log.error("Exception: {}", ExceptionUtils.getStackTrace(ex));
        return ExceptionResponse.of("Unexpected exception occurred", "UNEXPECTED_EXCEPTION");
    }

}
