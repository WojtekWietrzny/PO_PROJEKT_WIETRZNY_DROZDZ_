package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private int updateCount = 0 ;

    @Override
    public synchronized void mapChanged(WorldMap worldMap) {
            updateCount++;
            System.out.println(worldMap.toString());
    }
}
