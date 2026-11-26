package ua.edu.duikt.automaton;
public enum State { S, ONE, TWO, THREE, F;
    @Override public String toString() { return switch(this) { case ONE -> "1"; case TWO -> "2"; case THREE -> "3"; default -> name(); }; }
}
