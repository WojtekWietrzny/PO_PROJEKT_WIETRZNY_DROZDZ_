package agh.ics.oop;

public class SimulationEngine implements Runnable{
    private int currentDay;

    public SimulationEngine(){
        this.currentDay = 0;
        //tu będą jeszcze przekazywane ustawienia z klasy settings zapisującej te konfiguracje jakoś do pliku i czytającej z niego
        // + statystyki
    }

    @Override
    public void run() {

    }
}
