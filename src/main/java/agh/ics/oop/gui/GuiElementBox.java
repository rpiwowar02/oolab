package agh.ics.oop.gui;


import javax.swing.text.Element;

import agh.ics.oop.IMapElement;
import agh.ics.oop.Vector2d;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

import java.util.Objects;

public class GuiElementBox {
    private Image image;
    private ImageView img;
    private Label label;
    private VBox vb;
    public static Image GetImage(String path){
        return new Image(path + ".png");
    }


    public GuiElementBox(IMapElement mapElement){

        String path = mapElement.getResources();
        //Image image = new Image("src/main/resources/" + path + ".png");
        image = new Image(path + ".png");
        //Image image = new Image(Objects.requireNonNull(getClass().getResource("grass.png")).toExternalForm());
        // simple displays ImageView the image as is
        label = new Label(mapElement.getLabel());
        vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        img = new ImageView(image);
        img.setFitHeight(20);
        img.setFitWidth(20);
        GridPane.setHalignment(img, HPos.CENTER);
        GridPane.setHalignment(label, HPos.CENTER);
        ObservableList list = vb.getChildren();
        GridPane.setHalignment(vb, HPos.CENTER);
        list.addAll(img,label);
    }
    public GuiElementBox(IMapElement mapElement,Image image){

        String path = mapElement.getResources();
        //Image image = new Image("src/main/resources/" + path + ".png");
        //Image image = new Image(Objects.requireNonNull(getClass().getResource("grass.png")).toExternalForm());
        // simple displays ImageView the image as is
        label = new Label(mapElement.getLabel());
        vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        img = new ImageView(image);
        img.setFitHeight(20);
        img.setFitWidth(20);
        GridPane.setHalignment(img, HPos.CENTER);
        GridPane.setHalignment(label, HPos.CENTER);
        ObservableList list = vb.getChildren();
        GridPane.setHalignment(vb, HPos.CENTER);
        list.addAll(img,label);
    }
    public VBox getBox(){
        return vb;
    }
}
