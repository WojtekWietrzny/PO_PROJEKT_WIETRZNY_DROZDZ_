package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.enums.BehaviourType;
import agh.ics.oop.model.enums.MapDirection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SphereWorldMapTest {

    private SphereWorldMap sphereWorldMap;

    @BeforeEach
    public void setUp() {
        sphereWorldMap = new SphereWorldMap(10, 10, BehaviourType.CompletePredestination, 8);
    }

    @Test
    public void testAdvanceAnimalsWithinBounds() {
        Gene gene = new Gene(8, BehaviourType.CompletePredestination);
        gene.setDna(new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0)));
        Animal animal = new Animal(sphereWorldMap, new Vector2d(5, 5), gene);
        animal.setOrientation(MapDirection.EAST);
        sphereWorldMap.place(animal);

        Vector2d newPosition = new Vector2d(6, 5);

        assertEquals(newPosition, animal.wantToMove());
        sphereWorldMap.advanceAnimals();
        assertEquals(newPosition, animal.getPosition());
    }

    @Test
    public void testAdvanceAnimalsOppositeDirection() {
        Gene gene = new Gene(8, BehaviourType.CompletePredestination);
        gene.setDna(new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0)));
        Animal animal = new Animal(sphereWorldMap, new Vector2d(0, 0), gene);
        sphereWorldMap.place(animal);

        Vector2d animalWantPosition = new Vector2d(-1, 0);
        Vector2d newPosition = new Vector2d(9, 0);
        animal.setOrientation(MapDirection.WEST);

        assertEquals(animalWantPosition, animal.wantToMove());
        sphereWorldMap.advanceAnimals();
        assertEquals(newPosition, animal.getPosition());
    }

    @Test
    public void testCanMoveTo() {
        assertTrue(sphereWorldMap.canMoveTo(new Vector2d(5, 5)));
        assertFalse(sphereWorldMap.canMoveTo(new Vector2d(15, 15)));
        assertFalse(sphereWorldMap.canMoveTo(new Vector2d(5, -5)));
        assertTrue(sphereWorldMap.canMoveTo(new Vector2d(-1, 5)));
    }
}