package agh.ics.oop;
import java.util.Arrays;

public class OptionsParser {
        public static MoveDirection[] parse(String[] args) throws IllegalArgumentException
        {
            return Arrays.stream(args).map(arg -> switch(arg) {
                                case "f","forward" -> MoveDirection.FORWARD;
                                case "r","right" -> MoveDirection.RIGHT;
                                case "l","left" -> MoveDirection.LEFT;
                                case "b","backward" -> MoveDirection.BACKWARD;
                                default -> throw new IllegalArgumentException(arg + " is not legal command");
                            }
                    ).toArray(MoveDirection[]::new);

        }
    }
