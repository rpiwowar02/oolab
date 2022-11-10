package agh.ics.oop;
import org.junit.jupiter.api.Assertions;


class RectangularMapTest {
    IWorldMap map;
    @org.junit.jupiter.api.BeforeEach
    public void init(){

        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        this.map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, this.map, positions);
        engine.run(this.map);

    }
    @org.junit.jupiter.api.Test
    void getList() {
        Assertions.assertEquals(this.map.GetList(),this.map.GetList());
    }

    @org.junit.jupiter.api.Test
    void toStringTest() {
        Assertions.assertEquals(this.map.objectAt(new Vector2d(3,5)).toString(),"N");
        Assertions.assertEquals(this.map.objectAt(new Vector2d(2,0)).toString(),"S");


    }
        @org.junit.jupiter.api.Test
    void canMoveTo() {
        Assertions.assertFalse(this.map.canMoveTo(new Vector2d(3,5)));
        Assertions.assertFalse(this.map.canMoveTo(new Vector2d(2,0)));
        Assertions.assertFalse(this.map.canMoveTo(new Vector2d(100,0)));
        Assertions.assertFalse(this.map.canMoveTo(new Vector2d(-100,0)));
        Assertions.assertFalse(this.map.canMoveTo(new Vector2d(0,100)));
        Assertions.assertFalse(this.map.canMoveTo(new Vector2d(0,-100)));
        Assertions.assertTrue(this.map.canMoveTo(new Vector2d(0,0)));
    }

    @org.junit.jupiter.api.Test
    void place() {
        Assertions.assertEquals(this.map.GetList().get(0).Get(),new Vector2d(2,0));
        Assertions.assertEquals(this.map.GetList().get(1).Get(),new Vector2d(3,5));
    }

    @org.junit.jupiter.api.Test
    void isOccupied() {
        Assertions.assertTrue(this.map.isOccupied(new Vector2d(2,0)));
        Assertions.assertTrue(this.map.isOccupied(new Vector2d(3,5)));

    }

    @org.junit.jupiter.api.Test
    void objectAt() {
        Assertions.assertEquals(this.map.GetList().get(0),this.map.objectAt(new Vector2d(2,0)));
        Assertions.assertEquals(this.map.GetList().get(1),this.map.objectAt(new Vector2d(3,5)));
    }

}