package agh.ics.oop;

public class Animal {
    private Vector2d Position;
    private MapDirection Orientation;
    public Animal(){
        Position = new Vector2d(2,2);
        Orientation = MapDirection.NORTH;
    }
    public String toString(){
        return "(" + this.Position.x + "," + this.Position.y + "," + this.Orientation + ")";
    }
    public boolean isAt(Vector2d position){
        return (this.Position).equals(position);
    }
    public void Move(MoveDirection direction){

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
                if (Move.x < 0 || Move.x > 4 || Move.y < 0 || Move.y > 4) {
                    return;
                    }
                this.Position = Move;
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
