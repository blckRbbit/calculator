package math.action.impl;

import math.action.Token;

public final class NumberToken implements Token {
    private final String symbol;
    private final int priority;

    public NumberToken() {
        this.symbol = "0";
        this.priority = 0;
    }

    public NumberToken(String symbol) {
        this.symbol = symbol;
        this.priority = 0;
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
    public boolean isNumber() {return true;}

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        NumberToken token = (NumberToken) obj;
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
