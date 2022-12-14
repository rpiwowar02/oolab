package agh.ics.oop.gui;
import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.util.LinkedList;
import java.util.Objects;

public class App extends Application implements IPositionChangeObserver{
    private IWorldMap map;
    IPositionChangeObserver borders;
    MapBoundary bound;
    int width = 40;
    int height = 40;
    SimulationEngine engine;
    Thread engineThread;
    public GridPane grid;
    Stage primaryStage;
    Vector2d LeftDown;
    Vector2d RightUp;
    Vector2d[] positions;
    Integer first_animal_index;
    Integer current_animal_index;
    private Vector2d VectorToGrid(Vector2d v){
         return new Vector2d(v.x+1,1+(this.bound.GetRightUp().y-v.y));
    }
    private void start_simulation(String[] args){

        MoveDirection[] directions = OptionsParser.parse(args);
        this.engine.setDirections(directions);
        LinkedList<IPositionChangeObserver> obs = new LinkedList<>();
        //this.map = new GrassField(10, (MapBoundary) this.borders);
        //this.positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4)};
        IPositionChangeObserver obsMap = (IPositionChangeObserver) this.map;
        obs.add(borders);
        obs.add(obsMap);
        obs.add(this);
        this.LeftDown = bound.GetLeftDown();
        this.RightUp = bound.GetRightUp();
        this.engine = new SimulationEngine(directions,this.map, positions,obs,this,1000);
        this.engineThread = new Thread(this.engine);
        System.out.println("<>");
        System.out.println(this.map);
        //engineThread.start();
    }

    @Override
    public void init(){

        this.grid = new GridPane();
        this.grid.setGridLinesVisible(true);
        LinkedList<IPositionChangeObserver> obs = new LinkedList<>();

        this.borders = new MapBoundary();
        this.bound = (MapBoundary) this.borders;
        this.map = new GrassField(10, (MapBoundary) this.borders);
        this.positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4)};
        IPositionChangeObserver obsMap = (IPositionChangeObserver) this.map;
        obs.add(borders);
        obs.add(obsMap);
        obs.add(this);
        this.LeftDown = bound.GetLeftDown();
        this.RightUp = bound.GetRightUp();
        MoveDirection[] directions = OptionsParser.parse(getParameters().getRaw().toArray(new String[0]));
        this.engine = new SimulationEngine(this.map, positions,obs,this,300);
        this.engineThread = new Thread(this.engine);


    }


    private String drawObject(Vector2d currentPosition) {
        String result = null;
        //if (this.map.isOccupied(currentPosition)) {
        if(this.map == null)
            return " ";
        Object object = this.map.objectAt(currentPosition);
        if (object != null) {
            result = object.toString();
        } else {
            result = " ";
        }
        return result;
    }
    public void makeGrid(){
        if(this.map == null)
            return;
        this.bound = (MapBoundary) this.borders;
        //System.out.println(bound.GetLeftDown());
        //System.out.println(bound.GetRightUp());
        this.grid.setGridLinesVisible(true);
        Image grass = GuiElementBox.GetImage("grass");
        this.grid.add(new Label("y/x"), 0, 0, 1, 1);
        int ind = 1;
        this.grid.getColumnConstraints().add(new ColumnConstraints(this.width));
        for(int i = bound.GetLeftDown().x;i <= bound.GetRightUp().x;i++){
            this.grid.getColumnConstraints().add(new ColumnConstraints(this.width));
            Label label = new Label(Integer.toString(i));
            //label.setAlignment(Pos.CENTER);
            GridPane.setHalignment(label, HPos.CENTER);
            this.grid.add(label, ind, 0, 1, 1);
            ind = ind + 1;
        }
        ind = 1;
        this.grid.getRowConstraints().add(new RowConstraints(this.height));
        for(int i = bound.GetRightUp().y;i >= bound.GetLeftDown().y;i--){
            this.grid.getRowConstraints().add(new RowConstraints(this.height));
            Label label = new Label(Integer.toString(i));
            //label.setAlignment(Pos.CENTER);
            GridPane.setHalignment(label, HPos.CENTER);
            this.grid.add(label, 0, ind, 1, 1);
            ind = ind + 1;
        }
        ind = bound.GetRightUp().y;
        int ind_x = 1;
        int ind_y = 1;
        /*
        this.first_animal_index = this.grid.getChildren().toArray().length;
        this.current_animal_index = this.first_animal_index;
        for (Vector2d position : this.positions){
            Label label = new Label(drawObject(position));
            IMapElement el = this.map.objectAt(position );
            if(el != null){
                GuiElementBox box = new GuiElementBox(el);
                //label.setAlignment(Pos.CENTER);
                GridPane.setHalignment(label, HPos.CENTER);
                this.grid.add(box.getBox(),VectorToGrid(position).x,VectorToGrid(position).y,1,1);
                //gridPane.add(label, ind_x, ind_y, 1, 1);
            }
        }*/

        for(int i = bound.GetRightUp().y;i >= bound.GetLeftDown().y;i--){
            ind_x = 1;
            for(int j = bound.GetLeftDown().x;j <= bound.GetRightUp().x;j++){

                Label label = new Label(drawObject(new Vector2d(j,i)));
                IMapElement el = this.map.objectAt(new Vector2d(j,i) );

                if(el instanceof Grass){
                    GuiElementBox box = new GuiElementBox(el,grass);
                    //label.setAlignment(Pos.CENTER);
                    GridPane.setHalignment(label, HPos.CENTER);
                    this.grid.add(box.getBox(),ind_x,ind_y,1,1);
                    //gridPane.add(label, ind_x, ind_y, 1, 1);
                }
                else if(el != null){
                    GuiElementBox box = new GuiElementBox(el);
                    //label.setAlignment(Pos.CENTER);
                    GridPane.setHalignment(label, HPos.CENTER);
                    this.grid.add(box.getBox(),ind_x,ind_y,1,1);
                    //gridPane.add(label, ind_x, ind_y, 1, 1);
                }

                ind_x = ind_x + 1;
            }
            ind_y = ind_y + 1;

        }
        this.grid.setGridLinesVisible(true);
    }


    public void start(Stage primaryS) throws Exception {
        this.primaryStage = primaryS;
        if(this.map == null)
            return;

        makeGrid();


        Button button1 = new Button("Start");
        button1.minWidth(100);
        button1.minHeight(100);

        //this.grid.add(0,0,1,1);
        //Scene scene = new Scene(this.grid, 800, 800);
        TextField textField = new TextField();
        textField.minWidth(500);
        HBox hb = new HBox(button1,textField);
        VBox bx = new VBox(hb,grid);
        Scene scene = new Scene(bx, 800, 800);
        button1.setOnAction(actionEvent ->  {
            try {
            //System.out.println(textField.getCharacters().toString());
            start_simulation(textField.getCharacters().toString().split(""));
            engineThread.start();
            } catch(IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                this.positions = engine.getPositions();
                //this.map = null;
                return;
            }
            //... do something in here.
        });
        //start_simulation(getParameters().getRaw().toArray(new String[0]));

        this.grid.setGridLinesVisible(true);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    public void update(){

        //System.out.println(this.grid.getChildren());
        Platform.runLater(
                () -> {
                    //grid.getChildren().clear();
                    //makeGrid();
                    // Update UI here.
                    this.grid.setGridLinesVisible(true);
                    primaryStage.show();
                }
        );


    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater(
                () -> {
                    /*if(this.LeftDown.equals(bound.GetLeftDown()) && this.RightUp.equals(bound.GetRightUp()) && 1==0){
                        Vector2d gridDelVector = VectorToGrid(oldPosition);
                        System.out.println(this.current_animal_index);
                        grid.getChildren().remove(this.current_animal_index,this.current_animal_index+1);
                        this.current_animal_index = (this.current_animal_index + 1) % positions.length + this.first_animal_index;
                        IMapElement el = map.objectAt(newPosition);
                        if(el != null) {
                            GuiElementBox box = new GuiElementBox(el);
                            //label.setAlignment(Pos.CENTER);
                            Vector2d gridVector = VectorToGrid(newPosition);
                            grid.add(box.getBox(), gridVector.x, gridVector.y, 1, 1);
                        }
                    }
                    else*/{
                        this.grid.setGridLinesVisible(false);
                        this.grid.getColumnConstraints().clear();
                        this.grid.getRowConstraints().clear();
                        this.grid.getChildren().clear();
                        //this.grid.getChildren().replaceAll(null);
                        makeGrid();
                        this.grid.setGridLinesVisible(true);
                        primaryStage.show();
                    }


                }
        );

    }
}
