package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import static agh.ics.oop.model.BehaviourType.CompletePredestination;
import static org.junit.jupiter.api.Assertions.*;

class TunnelWorldMapTest {

    @Test
    void testGenerateTunnels() {
        // Create an instance of TunnelWorldMap
        BehaviourType behaviour =  CompletePredestination;
        int genomeSize = 8;
        TunnelWorldMap tunnelWorldMap = new TunnelWorldMap(10,10, behaviour, genomeSize);

        // Test if tunnels are generated properly
        tunnelWorldMap.generateTunnels(3);

        // Add assertions based on the expected behavior of the generateTunnels method

        // Add more tests for other methods as needed
    }
    @Test
    void testAbstractWorldMap() {
        // Create an instance of your AbstractWorldMap
        AbstractWorldMap worldMap = new SphereWorldMap(10, 10, BehaviourType.CompletePredestination, 8);

        // Test the methods

        // Example: Test generateAnimals method
        worldMap.generateAnimals(5);
        assertEquals(5, worldMap.getAnimalsQuantity());

        // Example: Test place method
        Animal animal = new Animal(worldMap, new Vector2d(2, 3), new Gene(8, CompletePredestination));
        worldMap.place(animal);


    }

    @Test
    void testSphereWorldMap() {
        // Create an instance of SphereWorldMap
        SphereWorldMap sphereWorldMap = new SphereWorldMap(10, 10, BehaviourType.CompletePredestination, 8);

        // Test the overridden canMoveTo method
        assertTrue(sphereWorldMap.canMoveTo(new Vector2d(5, 5)));
        assertFalse(sphereWorldMap.canMoveTo(new Vector2d(15, 15))); // Adjust coordinates based on your map size

        // Add more tests for other methods
    }

    @Test
    void testTunnelWorldMap() {
        // Create an instance of TunnelWorldMap
        TunnelWorldMap tunnelWorldMap = new TunnelWorldMap(10, 10, BehaviourType.CompletePredestination, 8);

        // Test the overridden canMoveTo method
        assertTrue(tunnelWorldMap.canMoveTo(new Vector2d(5, 5)));
        assertFalse(tunnelWorldMap.canMoveTo(new Vector2d(15, 5))); // Adjust coordinates based on your map size

        // Test the generateTunnels method
        tunnelWorldMap.generateTunnels(2); // Assuming 2 tunnels are generated
        // Add assertions to check if tunnels are correctly generated

        // Add more tests for other methods
    }

    @Test
    void testAdvanceAnimals() {
        BehaviourType behaviour =  CompletePredestination;
        int genomeSize = 8;
        // Create an instance of TunnelWorldMap
        TunnelWorldMap tunnelWorldMap = new TunnelWorldMap(10,10, behaviour, genomeSize);

        // Add animals to the map
        Animal animal = new Animal(tunnelWorldMap, new Vector2d(2,2), new Gene(genomeSize,behaviour));
        tunnelWorldMap.place(animal);

        // Test if animals move properly through tunnels
        tunnelWorldMap.advanceAnimals();

        // Add assertions based on the expected behavior of the advanceAnimals method

        // Add more tests for other methods as needed
    }
}
