package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

    private WorldMap map;
    private Animal animal;

    @BeforeEach
    public void setUp() {
        SphereWorldMap map = new SphereWorldMap(10, 10, BehaviourType.CompletePredestination, 8);
        this.map = map;
        animal = new Animal(map);
    }

    @Test
    public void testDefaultValues() {
        assertEquals(MapDirection.NORTH, animal.getOrientation());
        assertEquals(new Vector2d(2, 2), animal.getPosition());
        assertNull(animal.getGene());
        assertEquals(0, animal.getEnergy());
    }

    @Test
    public void testCustomValues() {
        Vector2d initialPosition = new Vector2d(5, 5);
        Gene gene = new Gene(8, BehaviourType.CompletePredestination);
        Animal customAnimal = new Animal(map, initialPosition, gene);

        assertEquals(initialPosition, customAnimal.getPosition());
        assertEquals(gene, customAnimal.getGene());
    }

    @Test
    public void testToString() {
        assertEquals("N", animal.toString());
    }

    @Test
    public void testIsAt() {
        Vector2d position = new Vector2d(2, 2);
        assertTrue(animal.isAt(position));

        Vector2d differentPosition = new Vector2d(3, 3);
        assertFalse(animal.isAt(differentPosition));
    }

    @Test
    public void testSetPosition() {
        Vector2d newPosition = new Vector2d(4, 4);
        animal.setPosition(newPosition);
        assertEquals(newPosition, animal.getPosition());
    }

    @Test
    public void testSetOrientation() {
        MapDirection newOrientation = MapDirection.WEST;
        animal.setOrientation(newOrientation);
        assertEquals(newOrientation, animal.getOrientation());
    }

    @Test
    public void testSetAndGetEnergy() {
        int energyValue = 50;
        animal.setEnergy(energyValue);
        assertEquals(energyValue, animal.getEnergy());
    }

    @Test
    public void testAddAndReduceEnergy() {
        int initialEnergy = animal.getEnergy();
        int energyToAdd = 20;
        int energyToReduce = 10;

        animal.addEnergy(energyToAdd);
        assertEquals(initialEnergy + energyToAdd, animal.getEnergy());

        animal.reduceEnergy(energyToReduce);
        assertEquals(initialEnergy + energyToAdd - energyToReduce, animal.getEnergy());
    }
}
