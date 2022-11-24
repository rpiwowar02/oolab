package agh.ics.oop;
import org.junit.jupiter.api.Assertions;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {

    @org.junit.jupiter.api.Test
    void run() {
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};

        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IPositionChangeObserver obsMap = (IPositionChangeObserver) map;
        LinkedList<IPositionChangeObserver> obs = new LinkedList<>();
        obs.add(obsMap);
        IEngine engine = new SimulationEngine(directions, map, positions,obs);
        engine.run(map);


    }
}