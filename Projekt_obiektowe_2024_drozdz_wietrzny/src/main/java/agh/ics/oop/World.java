package agh.ics.oop;

import agh.ics.oop.model.enums.BehaviourType;
import agh.ics.oop.model.enums.MapType;
import agh.ics.oop.model.SimulationParameters;

public class World {
    public static void main(String args[]){
        SimulationParameters parameters = new SimulationParameters(MapType.SphereMap, BehaviourType.CompletePredestination,
                10, 0,10,10, 5,3,
                1,3,7);
        Simulation simulation = new Simulation(10,10,parameters);
        simulation.run();
    }
}
