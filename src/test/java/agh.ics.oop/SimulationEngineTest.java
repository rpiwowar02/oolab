package agh.ics.oop;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {

    @org.junit.jupiter.api.Test
    void run() {
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};

        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run(map);
        Assertions.assertEquals( new Vector2d(2,0),map.GetList().get(0).Get());
        Assertions.assertEquals( new Vector2d(3,5),map.GetList().get(1).Get());


    }
}