package agh.ics.oop;

import java.util.ArrayList;
import java.lang.Math;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class GrassField extends AbstractWorldMap {
    Map<Vector2d, Grass> Grasses;
    private int NoGrass;
    public GrassField(int no){

        int range = (int)Math.sqrt(no*10);
        this.animals = new HashMap<>();
        this.Grasses = new HashMap<>();
        this.NoGrass = no;
        super.LeftDown = new Vector2d(0,0);
        super.RightUp = new Vector2d(0,0);
        RandomGrass(range);
    }
    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public boolean isGrass(Vector2d position,int NoGrass){
        return this.Grasses.get(position) != null;
    }
    private void RandomGrass(int range){

        if(range == 0)
            return;
        int rand = getRandomNumber(0,range*range);
        boolean tmp;

        for(int i=1;i<NoGrass;i++){
            rand = getRandomNumber(0,range*range - i);
            tmp = isGrass(new Vector2d(rand/range,rand%range),i);
            if(tmp){
                rand = range*range - 1;
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
    public boolean placeGrass(Grass grass){
        this.Grasses.put(grass.getPosition(),grass);
        super.LeftDown = super.LeftDown.lowerLeft(grass.getPosition());
        super.RightUp = super.RightUp.upperRight(grass.getPosition());

        return true;
    }

    public boolean isOccupied(Vector2d position){
        return super.isOccupied(position);
    }
    public Object objectAt(Vector2d position){
        Object tmp = super.animals.get(position);
        if(tmp == null)
            return this.Grasses.get(position);
        return tmp;
    }

}
