package agh.ics.oop.model.presenter;

import agh.ics.oop.SetupParameters;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import agh.ics.oop.model.util.MapVisualizer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;

public class SimulationPresenter implements MapChangeListener {
    @FXML
    private Button startNewSimulationButton;
    @FXML
    private Label infoLabel;
    @FXML
    private TextField moveListTextField;

    @FXML
    private Button startButton;
    @FXML
    private GridPane mapGrid;
    private WorldMap worldMap;
    private int simulationCounter = 1;

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;

    }

    public WorldMap getWorldMap() {
        return worldMap;
    }

    @Override
    public void mapChanged(WorldMap worldMap) {
        Platform.runLater(() -> {
            drawMap(worldMap);
        });
    }


    private void clearGrid() {
        mapGrid.getChildren().clear();
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    /*public void drawMap(WorldMap worldMap){
        clearGrid();
        MapVisualizer mapVisualizer = new MapVisualizer(worldMap);
        Vector2d lowerLeft = worldMap.getCurrentBounds().lowerLeft();
        Vector2d upperRight = worldMap.getCurrentBounds().upperRight();
        String mapContent = mapVisualizer.draw(lowerLeft, upperRight);
        infoLabel.setText(mapContent);
    }*/


    public void drawMap(WorldMap worldMap) {
        clearGrid();


        MapVisualizer mapVisualizer = new MapVisualizer(worldMap);
        Vector2d lowerLeft = worldMap.getCurrentBounds().lowerLeft();
        Vector2d upperRight = worldMap.getCurrentBounds().upperRight();
        int rows_number = upperRight.getY() - lowerLeft.getY() + 1;
        int columns_number = upperRight.getX() - lowerLeft.getX() + 1;
        for(int i = 0; i<= columns_number; i++){
            mapGrid.getRowConstraints().add(new RowConstraints(40));
            mapGrid.getColumnConstraints().add(new ColumnConstraints(40));
        }
        for(int i = 0; i <= rows_number; i++){
            mapGrid.getColumnConstraints().add(new ColumnConstraints(40));
            mapGrid.getRowConstraints().add(new RowConstraints(40));
        }


        Label primary_label = new Label("y/x");
        mapGrid.add(primary_label, 0, 0);
        for(int i = lowerLeft.getX(); i <= upperRight.getX();i++){
            Label column_label = new Label(String.valueOf(i));
            mapGrid.add(column_label, (i - lowerLeft.getX()) + 1 , 0);
        }
        for(int i = upperRight.getY(); i >= lowerLeft.getY();i--){
            Label row_label = new Label(String.valueOf(i));
            mapGrid.add(row_label, 0 , (upperRight.getY()-i ) + 1);
        }
        for (int i = upperRight.getY(); i >= lowerLeft.getY(); i--) {
            for (int j = lowerLeft.getX(); j <= upperRight.getX(); j++) {
                Label label = new Label(mapVisualizer.drawObject(new Vector2d(j, i)));
                label.setMinWidth(50); // CELL_WIDTH to stała szerokość komórki w pikselach
                label.setMinHeight(50); // CELL_HEIGHT to stała wysokość komórki w pikselach
                mapGrid.add(label, (j - lowerLeft.getX()) + 1, (upperRight.getY()-i ) + 1);
            }
        }

    }
    public void onSimulationStartClicked() throws Exception {
        if(simulationCounter %2 == 0){
            SimulationApp.startSimulation(simulationCounter);
        }
        else{
            worldMap = getWorldMap();
            String setup = moveListTextField.getText();

            String[] setupArray = setup.split(" ");
            ArrayList<Simulation> simulations = new ArrayList<>(1);
            SetupParameters parameters = new SetupParameters(setupArray);
            Simulation simulation = new Simulation(parameters);
            simulations.add(simulation);
            SimulationEngine simulationEngine = new SimulationEngine(simulations);
            Thread engineThread = new Thread(simulationEngine);
            engineThread.start();
        }
        simulationCounter++;
    }
    public void onNewSimulationClicked(){

    }
}
