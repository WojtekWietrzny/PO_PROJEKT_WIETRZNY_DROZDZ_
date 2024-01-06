package agh.ics.oop.model;

public record SimulationParameters(MapType mapType, BehaviourType behaviourType, int startingPlantAmount,
                                   int plantGrowthRate, int startingAnimalAmount, int startingAnimalEnergy,
                                   int energyToReproduce, int energyConsumedByReproduction, int minMutations,
                                   int maxMutations, int genomeSize) {
}
