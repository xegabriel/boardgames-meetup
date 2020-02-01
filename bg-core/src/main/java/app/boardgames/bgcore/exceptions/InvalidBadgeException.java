package app.boardgames.bgcore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidBadgeException extends RuntimeException {
    public InvalidBadgeException(String message) {
        super(message);
    }
}