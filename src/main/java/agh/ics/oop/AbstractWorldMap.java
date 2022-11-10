package agh.ics.oop;
import java.util.ArrayList;

abstract class AbstractWorldMap implements IWorldMap {
    protected ArrayList<Animal> animals = new ArrayList<>();
    protected int AnimalsLen;
    public ArrayList <Animal> GetList(){
        return this.animals;
    }
    public boolean place(Animal animal){
        this.animals.add(animal);
        this.AnimalsLen = this.AnimalsLen + 1;

        return true;
    }
    public String toString(Vector2d LeftDown,Vector2d RightUp){
        MapVisualizer mp = new MapVisualizer(this);
        return mp.draw(LeftDown,RightUp);
    }
    public boolean isOccupied(Vector2d position){
        for(int i = 0;i<this.AnimalsLen;i++){
            if(this.animals.get(i).isAt(position)){
                return true;
            }
        }
        return false;
    }
    public Object objectAt(Vector2d position){

        for(int i = 0;i<this.AnimalsLen;i++){
            if(this.animals.get(i).isAt(position)){

                return (Object) this.animals.get(i);
            }
        }
        return 0;
    }

}