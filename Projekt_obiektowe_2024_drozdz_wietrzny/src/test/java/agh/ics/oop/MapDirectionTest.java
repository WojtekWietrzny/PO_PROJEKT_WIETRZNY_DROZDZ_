package agh.ics.oop;

import agh.ics.oop.model.enums.MapDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {

    @Test
    public void testToString() {
        assertEquals("Północ", MapDirection.NORTH.toString());
        assertEquals("Północny-Wschód", MapDirection.NORTHEAST.toString());
        assertEquals("Wschód", MapDirection.EAST.toString());
        assertEquals("Południowy-Wschód", MapDirection.SOUTHEAST.toString());
        assertEquals("Południe", MapDirection.SOUTH.toString());
        assertEquals("Południowy-Zachód", MapDirection.SOUTHWEST.toString());
        assertEquals("Zachód", MapDirection.WEST.toString());
        assertEquals("Północy-Zachód", MapDirection.NORTHWEST.toString());
    }

    @Test
    public void testNext() {
        assertEquals(MapDirection.NORTHEAST, MapDirection.NORTH.next());
        assertEquals(MapDirection.EAST, MapDirection.NORTHEAST.next());
        assertEquals(MapDirection.SOUTHEAST, MapDirection.EAST.next());
        assertEquals(MapDirection.SOUTH, MapDirection.SOUTHEAST.next());
        assertEquals(MapDirection.SOUTHWEST, MapDirection.SOUTH.next());
        assertEquals(MapDirection.WEST, MapDirection.SOUTHWEST.next());
        assertEquals(MapDirection.NORTHWEST, MapDirection.WEST.next());
        assertEquals(MapDirection.NORTH, MapDirection.NORTHWEST.next());
    }

    @Test
    public void testPrevious() {
        assertEquals(MapDirection.NORTHWEST, MapDirection.NORTH.previous());
        assertEquals(MapDirection.NORTH, MapDirection.NORTHEAST.previous());
        assertEquals(MapDirection.NORTHEAST, MapDirection.EAST.previous());
        assertEquals(MapDirection.EAST, MapDirection.SOUTHEAST.previous());
        assertEquals(MapDirection.SOUTHEAST, MapDirection.SOUTH.previous());
        assertEquals(MapDirection.SOUTH, MapDirection.SOUTHWEST.previous());
        assertEquals(MapDirection.SOUTHWEST, MapDirection.WEST.previous());
        assertEquals(MapDirection.WEST, MapDirection.NORTHWEST.previous());
    }

    @Test
    public void testRotate() {
        assertEquals(MapDirection.SOUTH, MapDirection.NORTH.rotate(4));
        assertEquals(MapDirection.EAST, MapDirection.SOUTH.rotate(6));
        assertEquals(MapDirection.NORTH, MapDirection.WEST.rotate(2));
    }

    @Test
    public void testOpposite() {
        assertEquals(MapDirection.SOUTH, MapDirection.NORTH.opposite());
        assertEquals(MapDirection.SOUTHWEST, MapDirection.NORTHEAST.opposite());
        assertEquals(MapDirection.NORTH, MapDirection.SOUTH.opposite());
        assertEquals(MapDirection.NORTHWEST, MapDirection.SOUTHEAST.opposite());
        assertEquals(MapDirection.EAST, MapDirection.WEST.opposite());
        assertEquals(MapDirection.NORTHEAST, MapDirection.SOUTHWEST.opposite());
    }

    @Test
    public void testToUnitVector() {
        assertEquals(new Vector2d(0, 1), MapDirection.NORTH.toUnitVector());
        assertEquals(new Vector2d(1, 1), MapDirection.NORTHEAST.toUnitVector());
        assertEquals(new Vector2d(1, 0), MapDirection.EAST.toUnitVector());
        assertEquals(new Vector2d(1, -1), MapDirection.SOUTHEAST.toUnitVector());
        assertEquals(new Vector2d(0, -1), MapDirection.SOUTH.toUnitVector());
        assertEquals(new Vector2d(-1, -1), MapDirection.SOUTHWEST.toUnitVector());
        assertEquals(new Vector2d(-1, 0), MapDirection.WEST.toUnitVector());
        assertEquals(new Vector2d(-1, 1), MapDirection.NORTHWEST.toUnitVector());
    }
}
