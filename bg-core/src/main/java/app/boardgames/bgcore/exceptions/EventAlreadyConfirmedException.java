package app.boardgames.bgcore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EventAlreadyConfirmedException extends RuntimeException {
    public EventAlreadyConfirmedException(String message) {
        super(message);
    }
}