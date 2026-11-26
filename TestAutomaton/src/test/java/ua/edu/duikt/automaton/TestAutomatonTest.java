package ua.edu.duikt.automaton;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
class TestAutomatonTest {
    static Stream<Arguments> cases() { return Stream.of(
            Arguments.of("", State.S),
            Arguments.of("abc", State.S),
            Arguments.of("T", State.ONE),
            Arguments.of("TE", State.TWO),
            Arguments.of("TES", State.THREE),
            Arguments.of("TEST", State.F),
            Arguments.of("abcTESTabc", State.F),
            Arguments.of("abcTES", State.THREE),
            Arguments.of("TX", State.S),
            Arguments.of("TEX", State.S),
            Arguments.of("TESX", State.S),
            Arguments.of("TT", State.ONE),
            Arguments.of("TET", State.ONE),
            Arguments.of("TTEST", State.F),
            Arguments.of("TETEST", State.F),
            Arguments.of("TESTTEST", State.F),
            Arguments.of("test", State.S),
            Arguments.of("ТEST", State.ONE),
            Arguments.of("TE T", State.ONE),
            Arguments.of("TEST\t", State.F)
    ); }
    @ParameterizedTest @MethodSource("cases")
    void finalState(String input, State expected) { assertEquals(expected, new TestAutomaton().recognize(input)); }
    @ParameterizedTest @ValueSource(strings={"TTEST", "TETEST"})
    void regression(String input) {
        TestAutomaton a = new TestAutomaton();
        assertNotEquals(State.F, a.recognizeOriginal(input));
        assertEquals(State.F, a.recognize(input));
    }
}
