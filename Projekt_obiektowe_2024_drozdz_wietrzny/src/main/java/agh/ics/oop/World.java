package agh.ics.oop;

import agh.ics.oop.model.ConsoleMapDisplay;
import agh.ics.oop.model.SphereWorldMap;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.enums.BehaviourType;
import agh.ics.oop.model.enums.MapType;
import agh.ics.oop.model.SimulationParameters;

import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args) {
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4)
        );

        int numberOfSimulations = 1000;
        ArrayList<Simulation> simulations = new ArrayList<>(numberOfSimulations);
        for (int i = 0; i < numberOfSimulations; i++) {
            SimulationParameters parameters = new SimulationParameters(10,10,MapType.SphereMap, BehaviourType.CompletePredestination,
                    10, 0, 10, 10, 5, 3,
                    1, 3, 7);
            Simulation simulation = new Simulation(parameters);
            simulations.add(simulation);
        }

        SimulationEngine simulationEngine = new SimulationEngine(simulations);
        Thread engineThread = new Thread(simulationEngine);
        engineThread.start();
        System.out.println("System zakończył działanie");
    }
}
