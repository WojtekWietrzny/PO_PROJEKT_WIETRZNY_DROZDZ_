package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.enums.BehaviourType;
import org.junit.jupiter.api.Test;

import static agh.ics.oop.model.enums.BehaviourType.CompletePredestination;
import static org.junit.jupiter.api.Assertions.*;

class TunnelWorldMapTest {

    @Test
    void testGenerateTunnels() {
        BehaviourType behaviour =  CompletePredestination;
        int genomeSize = 8;
        TunnelWorldMap tunnelWorldMap = new TunnelWorldMap(10,10, behaviour, genomeSize);

        tunnelWorldMap.generateTunnels(3);

    }
    @Test
    void testAbstractWorldMap() {
        AbstractWorldMap worldMap = new SphereWorldMap(10, 10, BehaviourType.CompletePredestination, 8);

        worldMap.generateAnimals(5);
        assertEquals(5, worldMap.getAnimalsQuantity());

        Animal animal = new Animal(worldMap, new Vector2d(2, 3), new Gene(8, CompletePredestination));
        worldMap.place(animal);


    }

    @Test
    void testSphereWorldMap() {
        SphereWorldMap sphereWorldMap = new SphereWorldMap(10, 10, BehaviourType.CompletePredestination, 8);

        assertTrue(sphereWorldMap.canMoveTo(new Vector2d(5, 5)));
        assertFalse(sphereWorldMap.canMoveTo(new Vector2d(15, 15))); // Adjust coordinates based on your map size

    }

    @Test
    void testTunnelWorldMap() {
        TunnelWorldMap tunnelWorldMap = new TunnelWorldMap(10, 10, BehaviourType.CompletePredestination, 8);

        assertTrue(tunnelWorldMap.canMoveTo(new Vector2d(5, 5)));
        assertFalse(tunnelWorldMap.canMoveTo(new Vector2d(15, 5))); // Adjust coordinates based on your map size

    }

    @Test
    void testAdvanceAnimals() {
        BehaviourType behaviour =  CompletePredestination;
        int genomeSize = 8;
        TunnelWorldMap tunnelWorldMap = new TunnelWorldMap(10,10, behaviour, genomeSize);

        Animal animal = new Animal(tunnelWorldMap, new Vector2d(2,2), new Gene(genomeSize,behaviour));
        tunnelWorldMap.place(animal);

        tunnelWorldMap.advanceAnimals();

    }
}
