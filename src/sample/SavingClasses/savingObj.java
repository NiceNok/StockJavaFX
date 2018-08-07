package sample.SavingClasses;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;


public class savingObj extends Group {
    private double orgSceneX, orgSceneY,orgTranslateX, orgTranslateY;

    public  Double newTranslateX;
    public  Double newTranslateY;
    public  String str;

    public savingObj(Double newTranslateX, Double newTranslateY, String str){
        this.str=str;
        Image imageTree = new Image(getClass().getResourceAsStream(str));
        ImageView iv3 = new ImageView();
        ContextMenu contextMenu = new ContextMenu();
        iv3.setImage(imageTree);
        Rectangle2D viewportRect = new Rectangle2D(0,0,110,170);
        iv3.setViewport(viewportRect);

        MenuItem deletePile = new MenuItem("Удалить объект");
        deletePile.setOnAction(new EventHandler<ActionEvent>() {//deleting pile from pane
            @Override
            public void handle(ActionEvent event) {
                getChildren().remove(iv3);
            }
        });
        contextMenu.getItems().addAll(deletePile);
        iv3.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

            @Override
            public void handle(ContextMenuEvent event) {

                contextMenu.show(iv3, event.getScreenX(), event.getScreenY());
            }
        });



        this.setOnMousePressed(mousePressed);
        this.setOnMouseDragged(mouseDragged);

        //makedragable(iv3);
        this.setTranslateX(newTranslateY);
        this.setTranslateY(newTranslateX);
        this.getChildren().addAll(iv3);


    }
    EventHandler<MouseEvent> mousePressed =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((Node)(t.getSource())).getTranslateX();
                    orgTranslateY = ((Node)(t.getSource())).getTranslateY();
                }};

    EventHandler<MouseEvent> mouseDragged =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    newTranslateX = orgTranslateX + offsetX;
                    newTranslateY = orgTranslateY + offsetY;
                    ((Node)(t.getSource())).setTranslateX(newTranslateX);
                    ((Node)(t.getSource())).setTranslateY(newTranslateY);
                }};


}
