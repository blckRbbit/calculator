package validator;

public interface Validator {
    boolean validate();
    boolean isDigit(String in);
    boolean isOperator(String in);
}
