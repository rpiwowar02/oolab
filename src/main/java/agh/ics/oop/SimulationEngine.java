package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Platform;

import java.util.LinkedList;

import static java.lang.Thread.sleep;

public class SimulationEngine implements IEngine,Runnable{
    private MoveDirection[] directions;
    private Vector2d[] positions;
    LinkedList<IPositionChangeObserver> obs;
    IWorldMap map;
    App app;
    private long MoveDelay;
    private final int NoAnimals;
    public SimulationEngine(MoveDirection[] directions,IWorldMap m,Vector2d[] starts,LinkedList<IPositionChangeObserver> observers,App ap,long delay){
        this.directions = directions;
        this.positions = starts;
        this.NoAnimals = starts.length;;
        this.obs = observers;
        this.map = m;
        this.app = ap;
        this.MoveDelay = delay;

    }

    public SimulationEngine(IWorldMap m,Vector2d[] starts,LinkedList<IPositionChangeObserver> observers,App ap,long delay){
        this.positions = starts;
        this.NoAnimals = starts.length;;
        this.obs = observers;
        this.map = m;
        this.app = ap;
        this.MoveDelay = delay;
        for (Vector2d start : positions) {
            Animal a = new Animal(this.map,start);
            for(IPositionChangeObserver o : obs){
                a.addObserver(o);
            }
            this.map.place(a);
        }
    }
    public void setDirections(MoveDirection [] dir){
        this.directions = dir;
    }
    public Vector2d[] getPositions(){
        return this.positions;
    }

    @Override
    public void run() {

        System.out.println("Thread started.");
        //System.out.println(directions.toString());
        int ind = 0;
        try {
            Thread.sleep(MoveDelay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for(MoveDirection direction : directions){
            try {
                Thread.sleep(MoveDelay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //System.out.println(positions[ind].toString());
            Animal A = (Animal)map.objectAt(positions[ind]);
            A.Move(direction);
            positions[ind] = A.getPosition();
            //System.out.println(A.toString());
            //System.out.println(A.Get());
            ind = (ind + 1) % NoAnimals;
            if(ind == 0){

                //System.out.println(map.toString());
            }

            app.update();


        }


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
            positions[ind] = A.getPosition();
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
