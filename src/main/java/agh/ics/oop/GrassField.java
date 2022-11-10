package agh.ics.oop;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Vector;

public class GrassField extends AbstractWorldMap {
    ArrayList<Grass> Grasses;
    private int NoGrass;
    public GrassField(int no){

        int range = (int)Math.sqrt(no*10);
        this.animals = new ArrayList<>();
        this.Grasses = new ArrayList<>();
        this.NoGrass = no;
        RandomGrass(range);
    }
    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public ArrayList <Grass> getGrass(){
        return this.Grasses;
    }
    public int isGrass(Vector2d position,int NoGrass){
        for(int i = 0;i<NoGrass;i++){
            if(this.Grasses.get(i).isAt(position)){
                return i;
            }
        }
        return -1;
    }
    private void RandomGrass(int range){

        if(range == 0)
            return;
        int rand = getRandomNumber(0,range*range);
        int tmp;


        //rand = 10;
        placeGrass(new Grass(new Vector2d(rand/range,rand%range)));

        for(int i=1;i<NoGrass;i++){
            rand = getRandomNumber(0,range*range - i);
            tmp = isGrass(new Vector2d(rand/range,rand%range),i);
            if(tmp >=0){
                rand = range*range - tmp - 1;
                placeGrass(new Grass(new Vector2d(rand/range,rand%range)));
            }
            else{
                placeGrass(new Grass(new Vector2d(rand/range,rand%range)));
            }

        }
        //for(int i = 0;i<1;i++){
        //    System.out.println(this.Grasses.get(0).getPosition().toString());
        //}
    }
    public boolean canMoveTo(Vector2d position){
        return !super.isOccupied(position);
    }
    public String toString(){

        Vector2d LeftDown = new Vector2d(0,0);
        Vector2d RightUp = new Vector2d(0,0);

        if(super.AnimalsLen > 0)
        {
            LeftDown = super.animals.get(0).Get();
            RightUp = super.animals.get(0).Get();
        }

        for(Animal animal : super.animals){
            LeftDown = LeftDown.lowerLeft(animal.Get());
            RightUp = RightUp.upperRight(animal.Get());
        }
        for(Grass G : Grasses) {
            LeftDown = LeftDown.lowerLeft(G.getPosition());
            RightUp = RightUp.upperRight(G.getPosition());
        }
        return super.toString(LeftDown,RightUp);
    }
    public boolean placeGrass(Grass grass){
        this.Grasses.add(grass);
        return true;
    }

    public boolean isOccupied(Vector2d position){
        return super.isOccupied(position) || isGrass(position,NoGrass) != -1;
    }
    public Object objectAt(Vector2d position){

        for(int i = 0;i<this.AnimalsLen;i++){
            if(this.animals.get(i).isAt(position)){

                return (Object) this.animals.get(i);
            }
        }
        for(int i=0;i<this.NoGrass;i++){
            if(this.Grasses.get(i).isAt(position)){
                return (Object) this.Grasses.get(i);
            }
        }
        return false;
    }

}
