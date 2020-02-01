package app.boardgames.bgcore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EventIsDisabledException extends RuntimeException {
    public EventIsDisabledException(String message) {
        super(message);
    }
}