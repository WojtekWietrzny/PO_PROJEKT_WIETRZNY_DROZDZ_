package agh.ics.oop.model;

import agh.ics.oop.model.enums.BehaviourType;
import agh.ics.oop.model.enums.MapType;

public record SimulationParameters(int width, int height, MapType mapType, BehaviourType behaviourType, int startingPlantAmount,
                                   int plantGrowthRate, int startingAnimalAmount, int startingAnimalEnergy,
                                   int energyToReproduce, int energyConsumedByReproduction, int minMutations,
                                   int maxMutations, int genomeSize) {
}
