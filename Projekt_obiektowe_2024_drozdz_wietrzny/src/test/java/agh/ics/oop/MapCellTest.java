package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.enums.BehaviourType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapCellTest {

    private MapCell mapCell;

    @BeforeEach
    public void setUp() {
        mapCell = new MapCell();
    }

    @Test
    public void testDefaultState() {
        assertFalse(mapCell.isTunelStart());
        assertFalse(mapCell.isTunelEnd());
        assertFalse(mapCell.isJungle());
        assertFalse(mapCell.isGrassPresent());
        assertEquals(0, mapCell.getDeathCounter());
        assertTrue(mapCell.getAnimals().isEmpty());
    }

    @Test
    public void testSetTunelStart() {
        mapCell.setTunelStart(true);
        assertTrue(mapCell.isTunelStart());
    }

    @Test
    public void testSetTunelEnd() {
        mapCell.setTunelEnd(true);
        assertTrue(mapCell.isTunelEnd());
    }

    @Test
    public void testSetTunnelDestination() {
        Vector2d destination = new Vector2d(5, 5);
        mapCell.setTunnelDestination(destination);
        assertEquals(destination, mapCell.getTunnelDestination());
    }

    @Test
    public void testSetJungle() {
        mapCell.setJungle();
        assertTrue(mapCell.isJungle());
    }

    @Test
    public void testGrowAndEatGrass() {
        assertFalse(mapCell.isGrassPresent());

        mapCell.growGrass();
        assertTrue(mapCell.isGrassPresent());

        mapCell.eatGrass();
        assertFalse(mapCell.isGrassPresent());
    }

    @Test
    public void testDeathCounter() {
        assertEquals(0, mapCell.getDeathCounter());

        mapCell.upDeathCounter();
        assertEquals(1, mapCell.getDeathCounter());

        mapCell.setDeathCounter(5);
        assertEquals(5, mapCell.getDeathCounter());
    }

    @Test
    public void testAddAndRemoveAnimal() {
        SphereWorldMap map = new SphereWorldMap(10,10,BehaviourType.CompletePredestination, 8);
        Animal animal = new Animal(map);
        assertTrue(mapCell.getAnimals().isEmpty());

        mapCell.addAnimal(animal);
        assertTrue(mapCell.getAnimals().contains(animal));

        mapCell.removeAnimal(animal);
        assertFalse(mapCell.getAnimals().contains(animal));
    }

    @Test
    public void testAnimalDied() {
        SphereWorldMap map = new SphereWorldMap(10,10,BehaviourType.CompletePredestination, 8);
        Animal animal = new Animal(map);
        mapCell.addAnimal(animal);
        assertTrue(mapCell.getAnimals().contains(animal));

        mapCell.animalDied(animal);
        assertFalse(mapCell.getAnimals().contains(animal));
        assertEquals(1, mapCell.getDeathCounter());
    }
}
