package agh.ics.oop;

public class SimulationEngine implements IEngine{
    private MoveDirection[] directions;
    private Vector2d[] starts;
    private final int NoAnimals;
    public SimulationEngine(MoveDirection[] directions,IWorldMap map,Vector2d[] starts){
        this.directions = directions;
        this.starts = starts;
        this.NoAnimals = starts.length;;
    }

    public void run(IWorldMap map){
        for (Vector2d start : starts) {
            map.place(new Animal(map, start));
        }
        //System.out.println(map.toString());
        int ind=0;
        for(MoveDirection direction : directions){

            map.GetList().get(ind).Move(direction);
            ind = (ind + 1) % NoAnimals;
            if(ind == 0){
                //System.out.println(map.toString());
            }
        }
        //System.out.println(map.toString());
    }
}
