package ua.edu.duikt.automaton;
import java.util.Objects;
public class TestAutomaton {
    public State recognize(String input) { return run(input, true); }
    public State recognizeOriginal(String input) { return run(input, false); }
    private State run(String input, boolean fixed) {
        Objects.requireNonNull(input, "input");
        State state = State.S;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            State fallback = fixed && c == 'T' ? State.ONE : State.S;
            state = switch (state) {
                case S -> c == 'T' ? State.ONE : State.S;
                case ONE -> c == 'E' ? State.TWO : fallback;
                case TWO -> c == 'S' ? State.THREE : fallback;
                case THREE -> c == 'T' ? State.F : State.S;
                case F -> State.F;
            };
        }
        return state;
    }
}
