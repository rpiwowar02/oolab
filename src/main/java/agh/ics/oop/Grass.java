package agh.ics.oop;

public class Grass implements IMapElement{
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


    public String getResources() {
        return "grass";
    }
    public String getLabel(){
        return "Trawa";
    }

    public boolean isAt(Vector2d position){
        return (this.Position).equals(position);
    }
}
