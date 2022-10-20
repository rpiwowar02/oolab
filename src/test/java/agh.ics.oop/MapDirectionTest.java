package agh.ics.oop;
import org.junit.jupiter.api.Assertions;


class MapDirectionTest {

    @org.junit.jupiter.api.Test
    void next() {
        Assertions.assertEquals(MapDirection.EAST,MapDirection.NORTH.next());
        Assertions.assertEquals(MapDirection.SOUTH,MapDirection.EAST.next());
        Assertions.assertEquals(MapDirection.WEST,MapDirection.SOUTH.next());
        Assertions.assertEquals(MapDirection.NORTH,MapDirection.WEST.next());
    }

    @org.junit.jupiter.api.Test
    void previous() {
        Assertions.assertEquals(MapDirection.EAST,MapDirection.SOUTH.previous());
        Assertions.assertEquals(MapDirection.NORTH,MapDirection.EAST.previous());
        Assertions.assertEquals(MapDirection.WEST,MapDirection.NORTH.previous());
        Assertions.assertEquals(MapDirection.SOUTH,MapDirection.WEST.previous());
    }

    @org.junit.jupiter.api.Test
    void toUnitVector() {
        Vector2d tmp1 = new Vector2d(1,0);
        Assertions.assertEquals(tmp1,MapDirection.EAST.toUnitVector());
        Vector2d tmp2 = new Vector2d(-1,0);
        Assertions.assertEquals(tmp2,MapDirection.WEST.toUnitVector());
        Vector2d tmp3 = new Vector2d(0,1);
        Assertions.assertEquals(tmp3,MapDirection.NORTH.toUnitVector());
        Vector2d tmp4 = new Vector2d(0,-1);
        Assertions.assertEquals(tmp4,MapDirection.SOUTH.toUnitVector());
    }
}