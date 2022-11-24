package agh.ics.oop;

import java.util.LinkedList;

public class SimulationEngine implements IEngine{
    private MoveDirection[] directions;
    private Vector2d[] positions;
    LinkedList<IPositionChangeObserver> obs;
    private final int NoAnimals;
    public SimulationEngine(MoveDirection[] directions,IWorldMap map,Vector2d[] starts,LinkedList<IPositionChangeObserver> observers){
        this.directions = directions;
        this.positions = starts;
        this.NoAnimals = starts.length;;
        this.obs = observers;
    }

    public void run(IWorldMap map){

        for (Vector2d start : positions) {
            Animal a = new Animal(map,start);
            for(IPositionChangeObserver o : obs){
                a.addObserver(o);
            }
            map.place(a);
        }
        System.out.println(map.toString());
        int ind=0;
        for(MoveDirection direction : directions){

            //System.out.println(positions[ind].toString());
            Animal A = (Animal)map.objectAt(positions[ind]);
            A.Move(direction);
            positions[ind] = A.Get();
            //System.out.println(A.toString());
            //System.out.println(A.Get());
            ind = (ind + 1) % NoAnimals;
            if(ind == 0){
                System.out.println(map.toString());
            }
        }
        System.out.println(map.toString());
    }
}
