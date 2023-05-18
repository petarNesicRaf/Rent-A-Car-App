package rs.raf.RentACarUserService.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> generateError(CustomException customException) {
        ErrorDetails errorDetails = new ErrorDetails(customException.getErrorType(), customException.getMessage(), Instant.now());
        return new ResponseEntity<>(errorDetails, customException.getHttpStatus());
    }
}
