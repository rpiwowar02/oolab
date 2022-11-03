package agh.ics.oop;

public class Animal {
    private Vector2d Position;
    private MapDirection Orientation;
    private IWorldMap map;
    //public Animal(){
    //    Position = new Vector2d(2,2);
    //    Orientation = MapDirection.NORTH;
    //}
    public Animal(IWorldMap map){
        this.map = map;
    }
    public Animal(IWorldMap map,Vector2d position){
        this.map = map;
        if (map.canMoveTo(position)){
            this.Position = position;
            this.Orientation = MapDirection.NORTH;
        }
    }
    public String toString(){
        return "" + this.Orientation;
    }
    public boolean isAt(Vector2d position){
        return (this.Position).equals(position);
    }
    public Vector2d Get(){
        return Position;
    }
    public void Move(MoveDirection direction){
        System.out.println(direction.toString());
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
                    this.Position = Move;
                    }
                }break;
            case LEFT: {
                this.Orientation = (this.Orientation).previous();
            }break;
            case RIGHT: {
                this.Orientation = (this.Orientation).next();
            }break;




            }

        }

    }
