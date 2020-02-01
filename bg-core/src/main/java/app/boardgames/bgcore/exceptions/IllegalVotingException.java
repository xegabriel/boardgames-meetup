package app.boardgames.bgcore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IllegalVotingException extends RuntimeException {
    public IllegalVotingException(String message) {
        super(message);
    }
}