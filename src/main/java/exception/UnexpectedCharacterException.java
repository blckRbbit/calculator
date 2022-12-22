package exception;

public class UnexpectedCharacterException extends IllegalArgumentException {
    public UnexpectedCharacterException(String message) {
        super(message);
    }
}
