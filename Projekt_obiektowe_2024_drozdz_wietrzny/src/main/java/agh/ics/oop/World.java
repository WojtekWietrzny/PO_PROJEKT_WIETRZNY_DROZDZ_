package agh.ics.oop;

import agh.ics.oop.model.BehaviourType;
import agh.ics.oop.model.ConsoleMapDisplay;
import agh.ics.oop.model.MapType;
import agh.ics.oop.model.SimulationParameters;

import static agh.ics.oop.model.BehaviourType.CompletePredestination;
import static agh.ics.oop.model.MapType.SphereMap;

public class World {
    public static void main(String args[]){
        SimulationParameters parameters = new SimulationParameters(MapType.SphereMap, BehaviourType.CompletePredestination,
                10, 5,10,10, 5,3,
                1,3,7);
        Simulation simulation = new Simulation(10,10,parameters);
        simulation.run();
    }
}
