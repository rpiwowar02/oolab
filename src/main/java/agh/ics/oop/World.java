package agh.ics.oop;

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
            };
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


        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        MapDirection direct = MapDirection.EAST;
        System.out.println(direct.toString());
        System.out.println((direct.next()).toString());
        System.out.println((direct.previous()).toString());
        System.out.println((direct.toUnitVector()).toString());
        //System.out.println("System wystartował poprawnie!");
        //Direction[] directions = conversion(args);
        // run(directions);
        //System.out.println("System zakończył działanie!");
    }
}