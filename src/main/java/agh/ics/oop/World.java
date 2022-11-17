package agh.ics.oop;

import java.util.Arrays;
import java.util.stream.Collectors;

public class World {
    public static MoveDirection[] conversion(String[] args){
        MoveDirection[] Directions = new MoveDirection[args.length];
        for (int i = 0 ; i < args.length; ++i) {

            MoveDirection direction = MoveDirection.FORWARD;
            switch (args[i]) {
                case "f":
                    direction = MoveDirection.FORWARD;
                    break;
                case "b":
                    direction = MoveDirection.BACKWARD;
                    break;
                case "r":
                    direction = MoveDirection.RIGHT;
                    break;
                case "l":
                    direction = MoveDirection.LEFT;
                    break;
                default:
                    System.out.println("Nieznana komenda");
            }
            Directions[i] = direction;
        }
        return Directions;
    }
    public static void run(MoveDirection[] args){
        System.out.println("Zwierzak idzie do przodu!");
        int i = 0;
        int len = args.length-1;
        for(MoveDirection argument : args){
            System.out.print(argument);
            if(i != len)
                System.out.print(",");
            i++;
        }
        System.out.println();
        System.out.println("Start");
        for(MoveDirection argument : args){
            String message = switch (argument){
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tyłu";
                case RIGHT -> "Zwierzak obraca się w prawo";
                case LEFT -> "Zwierzak obraca się w lewo";
            };
            System.out.println(message);
        }

        System.out.println("Stop");
        System.out.println();
    }
    public static void main(String[] args) {

        /*MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IPositionChangeObserver obs = (IPositionChangeObserver) map;
        IEngine engine = new SimulationEngine(directions, map, positions,obs);
        engine.run(map);*/
        args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IPositionChangeObserver obs = (IPositionChangeObserver) map;
        IEngine engine = new SimulationEngine(directions, map, positions,obs);
        engine.run(map);

    }
}