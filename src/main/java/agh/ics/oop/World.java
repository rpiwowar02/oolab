package agh.ics.oop;

public class World {
    public static Direction[] conversion(String[] args){
        Direction[] Directions = new Direction[args.length];
        for (int i = 0 ; i < args.length; ++i) {

            Direction direction = Direction.FORWARD;
            switch (args[i]) {
                case "f":
                    direction = Direction.FORWARD;
                    break;
                case "b":
                    direction = Direction.BACKWARD;
                    break;
                case "r":
                    direction = Direction.RIGHT;
                    break;
                case "l":
                    direction = Direction.LEFT;
                    break;
                default:
                    System.out.println("Nieznana komenda");
            };
            Directions[i] = direction;
        }
        return Directions;
    }
    public static void run(Direction[] args){
        System.out.println("Zwierzak idzie do przodu!");
        int i = 0;
        int len = args.length-1;
        for(Direction argument : args){
            System.out.print(argument);
            if(i != len)
                System.out.print(",");
            i++;
        }
        System.out.println();
        System.out.println("Start");
        for(Direction argument : args){
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

        System.out.println("System wystartował poprawnie!");
        Direction[] directions = conversion(args);
        run(directions);
        System.out.println("System zakończył działanie!");
    }
}