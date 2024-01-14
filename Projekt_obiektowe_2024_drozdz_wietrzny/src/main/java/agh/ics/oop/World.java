package agh.ics.oop;

import agh.ics.oop.model.ConsoleMapDisplay;
import agh.ics.oop.model.SphereWorldMap;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.enums.BehaviourType;
import agh.ics.oop.model.enums.MapType;
import agh.ics.oop.model.SimulationParameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class World {
    public static void main(String[] args) throws Exception {
        System.out.println(args[0]);
        try {
            List<String[]> existingSetups = ReadParameters.read();

            boolean configExists = false;
            for (String[] setup : existingSetups) {
                if (setup.length > 0 && setup[0].equals(args[0])) {
                    configExists = true;
                    break;
                }
            }

            if (!configExists) {
                ReadParameters.insertData(args);
                System.out.println("New configuration saved successfully.");
            } else {
                System.out.println("Configuration with the same identifier already exists.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        int numberOfSimulations = 1;
        ArrayList<Simulation> simulations = new ArrayList<>(numberOfSimulations);
        for (int i = 0; i < numberOfSimulations; i++) {
            SetupParameters parameters = new SetupParameters(args);
            Simulation simulation = new Simulation(parameters);
            simulations.add(simulation);
        }

        SimulationEngine simulationEngine = new SimulationEngine(simulations);
        Thread engineThread = new Thread(simulationEngine);
        engineThread.start();
        System.out.println("System zakończył działanie");
    }
}
