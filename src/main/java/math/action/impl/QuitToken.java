package math.action.impl;

import math.action.Token;

import java.util.Locale;

public final class QuitToken implements Token {
    private final String symbol;
    private final int priority;

    public QuitToken() {
        this.symbol = "q".toLowerCase(Locale.ROOT);
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
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        QuitToken token = (QuitToken) obj;
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
