package agh.ics.oop;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected int AnimalsLen;
    protected Vector2d LeftDown;
    protected Vector2d RightUp;
    protected Map<Vector2d, Animal> animals;
    public Map<Vector2d, Animal> GetDictionary(){
        return animals;
    }
    public boolean place(Animal animal){
        LeftDown = LeftDown.lowerLeft(animal.Get());
        RightUp = RightUp.upperRight(animal.Get());
        this.animals.put(animal.Get(),animal);
        //this.animals.add(animal);
        //System.out.println(animal.Get());
        //System.out.println(((Animal)objectAt(animal.Get())).toString());
        this.AnimalsLen = this.AnimalsLen + 1;

        return true;
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        LeftDown = LeftDown.lowerLeft(newPosition);
        RightUp = RightUp.upperRight(newPosition);
        if(!isOccupied(newPosition)){

            Animal A = animals.get(oldPosition);
            animals.put(newPosition,A);
            animals.remove(oldPosition);
        }

    }
    public String toString(){
        //System.out.println(animals);
        MapVisualizer mp = new MapVisualizer(this);
        return mp.draw(this.LeftDown,this.RightUp);
    }
    public boolean isOccupied(Vector2d position){
        return this.animals.get(position) != null;
    }


}