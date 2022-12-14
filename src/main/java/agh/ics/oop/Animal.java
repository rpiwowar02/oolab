package agh.ics.oop;

import java.util.LinkedList;

public class Animal implements IMapElement{
    private Vector2d Position;
    private MapDirection Orientation;
    private static IWorldMap map;
    private LinkedList<IPositionChangeObserver> observers = new LinkedList<>();
    //public Animal(){
    //    Position = new Vector2d(2,2);
    //    Orientation = MapDirection.NORTH;
    //}
    public Animal(IWorldMap map){
        this.map = map;
        Position = new Vector2d(2,2);
        Orientation = MapDirection.NORTH;
    }
    public String getLabel(){
        return "Z " + getPosition().toString();
    }
    public Animal(IWorldMap map,Vector2d position){
        this.map = map;
        this.Position = position;
        this.Orientation = MapDirection.NORTH;
    }
    private void positionChanged(Vector2d newPosition){
        for(IPositionChangeObserver o: observers){
            o.positionChanged(this.Position,newPosition);
        }
    }
    public String getResources() {
        return switch (Orientation){
            case NORTH -> "up";
            case EAST -> "right";
            case SOUTH -> "down";
            case WEST -> "left";
                };
    }
    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }


    public String toString(){
        return "" + this.Orientation;
    }
    public boolean isAt(Vector2d position){
        return (this.Position).equals(position);
    }
    public Vector2d getPosition(){
        return Position;
    }

    public void Move(MoveDirection direction){

        //System.out.println(direction.toString());
        switch (direction){
            case FORWARD,BACKWARD: {
                Vector2d Move = switch(Orientation){
                    case NORTH -> new Vector2d(0,1);
                    case EAST -> new Vector2d(1,0);
                    case SOUTH -> new Vector2d(0,-1);
                    case WEST -> new Vector2d(-1,0);
                    };
                if (direction.equals(MoveDirection.BACKWARD)) {
                    Move = Move.opposite();
                    }
                Move = Move.add(this.Position);

                if (map.canMoveTo(Move)){
                    this.positionChanged(Move);
                    this.Position = Move;
                    }
                }break;
            case LEFT: {
                this.Orientation = (this.Orientation).previous();
                this.positionChanged(this.Position);
            }break;
            case RIGHT: {
                this.Orientation = (this.Orientation).next();
                this.positionChanged(this.Position);
            }break;




            }

        //return this.Position;
        }





    }
