package agh.ics.oop;
import org.junit.jupiter.api.Assertions;


class GrassFieldTest {
    IWorldMap map;
    @org.junit.jupiter.api.BeforeEach
    public void init(){

        String[] args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        this.map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run(this.map);

    }
    @org.junit.jupiter.api.Test
    void getList() {
        Assertions.assertEquals(this.map.GetList(),this.map.GetList());
    }

    @org.junit.jupiter.api.Test
    void toStringTest() {
        Assertions.assertEquals(this.map.objectAt(new Vector2d(3,7)).toString(),"N");
        Assertions.assertEquals(this.map.objectAt(new Vector2d(2,-1)).toString(),"S");


    }
        @org.junit.jupiter.api.Test
    void canMoveTo() {
        Assertions.assertFalse(this.map.canMoveTo(new Vector2d(3,7)));
        Assertions.assertFalse(this.map.canMoveTo(new Vector2d(2,-1)));
        Assertions.assertTrue(this.map.canMoveTo(new Vector2d(100,0)));
        Assertions.assertTrue(this.map.canMoveTo(new Vector2d(-100,0)));
        Assertions.assertTrue(this.map.canMoveTo(new Vector2d(0,100)));
        Assertions.assertTrue(this.map.canMoveTo(new Vector2d(0,-100)));
        Assertions.assertTrue(this.map.canMoveTo(new Vector2d(0,0)));
    }

    @org.junit.jupiter.api.Test
    void place() {
        Assertions.assertEquals(this.map.GetList().get(0).Get(),new Vector2d(2,-1));
        Assertions.assertEquals(this.map.GetList().get(1).Get(),new Vector2d(3,7));
    }

    @org.junit.jupiter.api.Test
    void isOccupied() {
        Assertions.assertTrue(this.map.isOccupied(new Vector2d(2,-1)));
        Assertions.assertTrue(this.map.isOccupied(new Vector2d(3,7)));
        Assertions.assertFalse(this.map.isOccupied(new Vector2d(0,0)));

    }

    @org.junit.jupiter.api.Test
    void objectAt() {
        Assertions.assertEquals(this.map.GetList().get(0),this.map.objectAt(new Vector2d(2,-1)));
        Assertions.assertEquals(this.map.GetList().get(1),this.map.objectAt(new Vector2d(3,7)));
    }

}