package agh.ics.oop;
import org.junit.jupiter.api.Assertions;

import java.util.LinkedList;


class GrassFieldTest {
    IWorldMap map;
    @org.junit.jupiter.api.BeforeEach
    public void init(){

        String[] args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        LinkedList<IPositionChangeObserver> obs = new LinkedList<>();
        MoveDirection[] directions = OptionsParser.parse(args);
        IPositionChangeObserver borders = new MapBoundary();
        this.map = new GrassField(5, (MapBoundary) borders);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IPositionChangeObserver obsMap = (IPositionChangeObserver) map;
        obs.add(borders);
        obs.add(obsMap);
        IEngine engine = new SimulationEngine(directions, map, positions,obs);
        engine.run(map);

    }
    @org.junit.jupiter.api.Test
    void test_exception_input(){
        String s2 = "";
        try {
            String[] args = new String[]{"f", "b", "r", "a", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
            LinkedList<IPositionChangeObserver> obs = new LinkedList<>();
            MoveDirection[] directions = OptionsParser.parse(args);
            IPositionChangeObserver borders = new MapBoundary();
            this.map = new GrassField(5, (MapBoundary) borders);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            IPositionChangeObserver obsMap = (IPositionChangeObserver) map;
            obs.add(borders);
            obs.add(obsMap);
            IEngine engine = new SimulationEngine(directions, map, positions,obs);
            engine.run(map);

        } catch(IllegalArgumentException ex) {
            s2 = ex.getMessage();
            System.out.println(ex.getMessage());

            return;
        }
        Assertions.assertEquals("a is not legal command",s2);
    }
    @org.junit.jupiter.api.Test
    void test_exception_place(){
        String s2 = "";
        try {
        String[] args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        LinkedList<IPositionChangeObserver> obs = new LinkedList<>();
        MoveDirection[] directions = OptionsParser.parse(args);
        IPositionChangeObserver borders = new MapBoundary();
        this.map = new GrassField(5, (MapBoundary) borders);
        Vector2d[] positions = { new Vector2d(2,2),new Vector2d(2,2), new Vector2d(3,4) };
        IPositionChangeObserver obsMap = (IPositionChangeObserver) map;
        obs.add(borders);
        obs.add(obsMap);
        IEngine engine = new SimulationEngine(directions, map, positions,obs);
        engine.run(map);

        } catch(IllegalArgumentException ex) {
            s2 = ex.getMessage();
            System.out.println(ex.getMessage());

            return;
        }
        Assertions.assertEquals("(2,2) is occupied",s2);
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
        Assertions.assertEquals(this.map.GetDictionary().get((new Vector2d(3,7))),this.map.objectAt(new Vector2d(3,7)));
        Assertions.assertEquals(this.map.GetDictionary().get((new Vector2d(2,-1))),this.map.objectAt(new Vector2d(2,-1)));
    }

    @org.junit.jupiter.api.Test
    void isOccupied() {
        Assertions.assertTrue(this.map.isOccupied(new Vector2d(2,-1)));
        Assertions.assertTrue(this.map.isOccupied(new Vector2d(3,7)));
        Assertions.assertFalse(this.map.isOccupied(new Vector2d(0,0)));

    }

    @org.junit.jupiter.api.Test
    void objectAt() {
        Assertions.assertEquals(this.map.GetDictionary().get((new Vector2d(3,7))),this.map.objectAt(new Vector2d(3,7)));
        Assertions.assertEquals(this.map.GetDictionary().get((new Vector2d(2,-1))),this.map.objectAt(new Vector2d(2,-1)));
    }

}