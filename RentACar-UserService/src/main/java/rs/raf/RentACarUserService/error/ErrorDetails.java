package rs.raf.RentACarUserService.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@Getter
@Setter
public class ErrorDetails {

    @JsonProperty("error_code")
    private ErrorType errorType;
    @JsonProperty("error_message")
    private String errorMessage;
    private Instant timestamp;
}
