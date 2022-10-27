package agh.ics.oop;
import java.util.Arrays;

public class OptionsParser {
        public static MoveDirection[] parse(String[] args){
            return Arrays.stream(args).filter(arg ->
                            (arg.equals("r") || arg.equals("f") || arg.equals("b") || arg.equals("l")
                            || arg.equals("right") || arg.equals("forward") || arg.equals("backward") || arg.equals("left")
                    ))
                    .map(arg -> switch(arg) {
                                case "f","forward" -> MoveDirection.FORWARD;
                                case "r","right" -> MoveDirection.RIGHT;
                                case "l","left" -> MoveDirection.LEFT;
                                case "b","backward" -> MoveDirection.BACKWARD;
                                default -> MoveDirection.FORWARD;
                            }
                    ).toArray(MoveDirection[]::new);

        }
    }
