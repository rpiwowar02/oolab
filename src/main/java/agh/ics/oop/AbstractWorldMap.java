package agh.ics.oop;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected int AnimalsLen;
    protected Map<Vector2d, Animal> animals;
    public Map<Vector2d, Animal> GetDictionary(){
        return animals;
    }
    public void place(Animal animal){
        if(isOccupied(animal.Get())){
            throw new IllegalArgumentException(animal.Get() + " is occupied");
            //return false;
        }

        this.animals.put(animal.Get(),animal);
        //this.animals.add(animal);
        //System.out.println(animal.Get());
        //System.out.println(((Animal)objectAt(animal.Get())).toString());
        this.AnimalsLen = this.AnimalsLen + 1;

    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        if(!isOccupied(newPosition)){

            Animal A = animals.get(oldPosition);
            animals.put(newPosition,A);
            animals.remove(oldPosition);
        }

    }
    public String toString(Vector2d LeftDown,Vector2d RightUp){
        //System.out.println(animals);
        MapVisualizer mp = new MapVisualizer(this);
        return mp.draw(LeftDown,RightUp);
    }
    public boolean isOccupied(Vector2d position){
        return this.animals.get(position) != null;
    }


}