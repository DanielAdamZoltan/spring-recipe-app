package danieladamzoltan.userservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidOldPasswordException extends RuntimeException{

    public InvalidOldPasswordException(String message) {
        super(message);
    }

    public InvalidOldPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidOldPasswordException(Throwable cause) {
        super(cause);
    }
}
