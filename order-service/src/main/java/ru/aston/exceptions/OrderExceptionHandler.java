package ru.aston.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.aston.domain.dto.ApiError;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class OrderExceptionHandler {

    @ExceptionHandler(BadParameters.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError badParam(Exception exception) {
        log.info("Получен статус 400 BAD_REQUEST {}", exception.getMessage());
        return new ApiError(HttpStatus.BAD_REQUEST, "Integrity constraint has been violated.",
                exception.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError notFound(Exception exception) {
        log.info("Получен статус 404 CONFLICT {}", exception.getMessage());
        return new ApiError(HttpStatus.NOT_FOUND, "Not found.",
                exception.getMessage(), LocalDateTime.now());
    }
}
