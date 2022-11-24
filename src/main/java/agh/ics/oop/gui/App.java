package agh.ics.oop.gui;
import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.awt.*;
import java.util.LinkedList;

public class App extends Application{
    private IWorldMap map;
    IPositionChangeObserver borders;
    int width = 20;
    int height = 20;
    @Override
    public void init(){
        try {
            // kod który może rzucić wyjątek
            /*MoveDirection[] directions = OptionsParser.parse(args);
            IWorldMap map = new RectangularMap(10, 5);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            IPositionChangeObserver obs = (IPositionChangeObserver) map;
            IEngine engine = new SimulationEngine(directions, map, positions,obs);
            engine.run(map);*/
            String[] args = getParameters().getRaw().toArray(new String[0]);
            LinkedList<IPositionChangeObserver> obs = new LinkedList<>();
            MoveDirection[] directions = OptionsParser.parse(args);
            this.borders = new MapBoundary();
            this.map = new GrassField(5, (MapBoundary) this.borders);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            IPositionChangeObserver obsMap = (IPositionChangeObserver) this.map;
            obs.add(borders);
            obs.add(obsMap);
            IEngine engine = new SimulationEngine(directions, this.map, positions,obs);
            engine.run(this.map);

        } catch(IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return;
        }
    }
    private String drawObject(Vector2d currentPosition) {
        String result = null;
        //if (this.map.isOccupied(currentPosition)) {
        Object object = this.map.objectAt(currentPosition);
        if (object != null) {
            result = object.toString();
        } else {
            result = " ";
        }
        return result;
    }
    public void start(Stage primaryStage) throws Exception {


        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        MapBoundary bound = (MapBoundary) borders;

        gridPane.add(new Label("y/x"), 0, 0, 1, 1);
        int ind = 1;
        gridPane.getColumnConstraints().add(new ColumnConstraints(this.width));
        for(int i = bound.GetLeftDown().x;i <= bound.GetRightUp().x;i++){
            gridPane.getColumnConstraints().add(new ColumnConstraints(this.width));
            Label label = new Label(Integer.toString(i));
            //label.setAlignment(Pos.CENTER);
            GridPane.setHalignment(label, HPos.CENTER);
            gridPane.add(label, ind, 0, 1, 1);
            ind = ind + 1;
        }
        ind = 1;
        gridPane.getRowConstraints().add(new RowConstraints(this.height));
        for(int i = bound.GetRightUp().y;i >= bound.GetLeftDown().y;i--){
            gridPane.getRowConstraints().add(new RowConstraints(this.height));
            Label label = new Label(Integer.toString(i));
            //label.setAlignment(Pos.CENTER);
            GridPane.setHalignment(label, HPos.CENTER);
            gridPane.add(label, 0, ind, 1, 1);
            ind = ind + 1;
        }
        ind = bound.GetRightUp().y;
        int ind_x = 1;
        int ind_y = 1;
        for(int i = bound.GetRightUp().y;i >= bound.GetLeftDown().y;i--){
            ind_x = 1;
            for(int j = bound.GetLeftDown().x;j <= bound.GetRightUp().x;j++){

                Label label = new Label(drawObject(new Vector2d(j,i)));
                //label.setAlignment(Pos.CENTER);
                GridPane.setHalignment(label, HPos.CENTER);
                gridPane.add(label, ind_x, ind_y, 1, 1);
                ind_x = ind_x + 1;
            }
            ind_y = ind_y + 1;

        }


        Scene scene = new Scene(gridPane, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
