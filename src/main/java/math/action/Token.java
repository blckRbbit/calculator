package math.action;

public interface Token {
    String getSymbol();
    int getPriority();

    default boolean isNumber() {return false;}
    default boolean isEquals(Token a, Token b) {
        return a.getSymbol().trim().equals(b.getSymbol().trim());
    }
}
