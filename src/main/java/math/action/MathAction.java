package math.action;

@FunctionalInterface
public interface MathAction {
    double calculate(double ... args);
}
