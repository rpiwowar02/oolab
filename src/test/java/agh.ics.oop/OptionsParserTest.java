package agh.ics.oop;
import org.junit.jupiter.api.Assertions;

import static agh.ics.oop.OptionsParser.parse;

class OptionsParserTest {

    @org.junit.jupiter.api.Test
    void parsed() {
        MoveDirection[] expected = {MoveDirection.RIGHT,MoveDirection.FORWARD,MoveDirection.LEFT,MoveDirection.FORWARD,
                MoveDirection.BACKWARD,MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.RIGHT,MoveDirection.LEFT,
                MoveDirection.BACKWARD,MoveDirection.FORWARD};
        int i = 0;
        String[] args = { "r", "f", "l", "f", "b", "d", "f","forward","right","left","backward","forward"};

        MoveDirection[] answers = parse(args);
        for (MoveDirection d: expected) {
            Assertions.assertEquals(d,answers[i]);
            i++;
        }

    }
}