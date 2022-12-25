package math.action.impl;

import math.action.Token;
import math.action.MathAction;

public final class SubtractionToken implements Token, MathAction {
    private final String symbol;
    private final int priority;

    public SubtractionToken() {
        this.symbol = "-";
        this.priority = 2;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public double calculate(double... args) {
        return args[0] - args[1];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        SubtractionToken token = (SubtractionToken) obj;
        return isEquals(this, token);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + symbol.hashCode();
        result = prime * result + priority;
        result = prime * result + Integer.hashCode(priority);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Token = {symbol: %s, priority: %s}", symbol, priority);
    }
}
