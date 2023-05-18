package rs.raf.RentACarUserService.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException{

    private ErrorType errorType;
    private HttpStatus httpStatus;

    public CustomException(String message, ErrorType errorType){
        super(message);
        this.errorType = errorType;
        generate();
    }

    private void generate(){
        switch (errorType) {
            case NOT_FOUND: httpStatus = HttpStatus.NOT_FOUND;
        }
    }
}
