package agh.ics.oop;

public class Grass {
    private Vector2d Position;
    public Grass(Vector2d pos){
        this.Position = pos;
    }
    public Vector2d getPosition(){
        return this.Position;
    }
    public String toString(){
        return "*";
    }
    public boolean isAt(Vector2d position){
        return (this.Position).equals(position);
    }
}
