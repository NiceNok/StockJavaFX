package sample.SavingClasses;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.GoogleMap;
import sample.childClasses.DragResizeMod;

public class SavingPile extends Group  {
    private double orgSceneX, orgSceneY,orgTranslateX, orgTranslateY;

    public Integer id;
    public Integer amount;
    public String pile;
    public Double lat;
    public Double lng;
    public boolean radio;
    public Double width;
    public Double height;
    public  Double newTranslateX;
    public  Double newTranslateY;


    public  SavingPile( Integer id, Integer amount, String pile, Double lat,
                        Double lng,boolean radio, Double width, Double height,Double newTranslateX, Double newTranslateY) {
        this.id = id;
        this.amount = amount;
        this.pile = pile;
        this.lat = lat;
        this.lng = lng;
        this.radio = radio;

        Rectangle myRct = new Rectangle(width, height);
        //myRct.setX(newTranslateX);
       // myRct.setY(newTranslateY);
        myRct.setStroke(Color.BLACK);
        if (radio) {
            myRct.setFill(Color.rgb(0, 128, 0));
        } else {
            myRct.setFill(Color.rgb(173, 50, 39));

        }
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menumap = new MenuItem("Посмотреть на карте");
        MenuItem deletePile = new MenuItem("Удалить штабель");
        deletePile.setOnAction(new EventHandler<ActionEvent>() {//deleting pile from pane
            @Override
            public void handle(ActionEvent event) {

                getChildren().remove(myRct);
            }
        });
        menumap.setOnAction(new EventHandler<ActionEvent>() {//opening google map

            @Override
            public void handle(ActionEvent event) {
                Stage window = new Stage();
                window.initModality(Modality.APPLICATION_MODAL);


                StackPane root = new StackPane();
                System.out.println(lat + "," + lng);//checking cooords in console
                root.getChildren().addAll(new GoogleMap());
                GoogleMap.setWidth(800);
                GoogleMap.setHeight(600);

                //set coordinates for map
                GoogleMap.setMarkerPosition(lng, lat);

                Scene scene = new Scene(root, 800, 600);
                window.setScene(scene);
                window.setTitle("GoogleMap");
                window.setResizable(false);
                window.showAndWait();

            }

        });

        Tooltip tooltip = new Tooltip(String.valueOf("ID " + id + ":\n  " + pile + "\n" +
                "Заказчик:\n" + "  Имя Фамилия\n" + "Координаты:\n" + "  " + lng + " , " + lat));
        Tooltip.install(myRct, tooltip);

        contextMenu.getItems().addAll(menumap, deletePile);
        myRct.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

            @Override
            public void handle(ContextMenuEvent event) {

                contextMenu.show(myRct, event.getScreenX(), event.getScreenY());
            }
        });
        DragResizeMod.makeResizable(myRct);

        setOnMouseReleased(e->{
            DragResizeMod.makeResizable(myRct);
            this.width = myRct.getWidth();
            this.height = myRct.getHeight();
            this.setOnMousePressed(mousePressed);
            this.setOnMouseDragged(mouseDragged);
            });

        this.setTranslateX(newTranslateX);
        this.setTranslateY(newTranslateY);

        this.getChildren().addAll(myRct);

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
