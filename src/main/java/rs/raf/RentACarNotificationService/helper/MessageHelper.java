package rs.raf.RentACarNotificationService.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MessageHelper {

    // ovo je jackson klasa - korisit se JSON
    private ObjectMapper objectMapper;

    public MessageHelper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // deserijalizacija poruke (pretvaranje iz JSON stringa u objekat)
    public <T> T getMessage(Message message, Class<T> clazz) throws RuntimeException, JMSException {
        try {
            String json = ((TextMessage) message).getText();
            T data = objectMapper.readValue(json, clazz);

            return data;
        } catch (IOException exception) {
            throw new RuntimeException("Message parsing fails.", exception);
        }
    }

    // Poziva jackson metodu za zapisivanje objekta kao JSON
    public String createTextMessage(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Problem with creating text message");
        }
    }


}
