package sample;

import javafx.event.EventHandler;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.SavingClasses.DAT;
import sample.SavingClasses.DAT1;
import sample.SavingClasses.SavingPile;
import sample.SavingClasses.savingObj;
import sample.childClasses.Const;
import sample.childClasses.DBConn;
import sample.childWindows.ModalWindow;
import sample.childWindows.radioWindow;

import java.util.logging.Level;
import java.util.logging.Logger;

import static sample.childClasses.DBConn.getConnection;

public class Controller implements Initializable {
    @FXML
    TableView<ModelTable> table;
    @FXML
    TableColumn<ModelTable, String> col_id;
    @FXML
    TableColumn<ModelTable, String> col_pile;
    @FXML
    TableColumn<ModelTable, String>  col_amount;
    @FXML
    TableColumn<ModelTable, String>  col_lat;
    @FXML
    TableColumn<ModelTable, String>  col_lng;
    @FXML
    RadioButton rb1,rb2;
    @FXML
    Pane overlay = new Pane();
    @FXML
    Button buttonAdd,buttonAdd1,buttonAdd2,buttonAdd3,buttonAdd4,buttonAdd5;
    @FXML
    MenuItem save, load;

    private ArrayList<DAT> dBList = new ArrayList<>();//ArrayList для хранения существующих на форме объектов
    private ArrayList<DAT1> dList = new ArrayList<>();//ArrayList для хранения существующих на форме объектов
    private ArrayList<SavingPile> pileList = new ArrayList<>();//ArrayList для хранения существующих на форме объектов
    private ArrayList<savingObj> objList = new ArrayList<>();//ArrayList для хранения существующих на форме объектов

    public static ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //DB

            try {
                Connection con = getConnection();
                ResultSet rs = con.createStatement().executeQuery("SELECT * FROM " + Const.TABLE);
                while (rs.next()) {
                    oblist.add(new ModelTable(
                            rs.getInt(Const.PILE_ID),
                            rs.getString(Const.PILE_NAME),
                            rs.getInt(Const.PILE_AMOUNT),
                            rs.getDouble(Const.PILE_LAT),
                            rs.getDouble(Const.PILE_LNG)));
                }
            }catch (SQLException ex){
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }


         //main table


        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_pile.setCellValueFactory(new PropertyValueFactory<>("pile"));
        col_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_lat.setCellValueFactory(new PropertyValueFactory<>("lat"));
        col_lng.setCellValueFactory(new PropertyValueFactory<>("lng"));

        table.setItems(oblist);

        draw();
        rbuttons();
        images();
        serialize();
        save();
    }

    public void save(){
        save.setOnAction(e ->{//сохраняем объекты
            try {
                saveNode(pileList, dBList);
                saveNode1(objList, dList);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        load.setOnAction(e ->{try {
            //открываем объекты
            openNode();
            openNode1();

        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
    }

    public void serialize(){
        /*Stage primaryStage = new Stage();
        ObservableList<Edge> edges = FXCollections.observableArrayList() ;
        edges.addListener((ListChangeListener.Change<? extends Edge> c) -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    c.getAddedSubList().stream()
                            .map(Edge::getView)
                            .forEach(overlay.getChildren()::add);
                }
                if (c.wasRemoved()) {
                    c.getRemoved().stream()
                            .map(Edge::getView)
                            .forEach(overlay.getChildren()::remove);
                }
            }
        });

        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Edge files", "*.edges"));

        Button load = new Button("Load");
        load.setOnAction(event -> {
            File file = chooser.showOpenDialog(primaryStage);
            if (file != null) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                    List<Edge> loadedEdges = (List<Edge>) in.readObject() ;
                    edges.setAll(loadedEdges);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        });

        Button save = new Button("Save") ;
        save.setOnAction(event -> {
            File file = chooser.showSaveDialog(primaryStage);
            if (file != null) {
                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                    out.writeObject(new ArrayList<>(edges));
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        });
        ColorPicker colorPicker = new ColorPicker(Color.BLACK) ;

        ObjectProperty<Edge> currentEdge = new SimpleObjectProperty<>();

        overlay.setOnDragDetected(e -> {
            currentEdge.set(new Edge(e.getX(), e.getY(), e.getX(), e.getY(), colorPicker.getValue()));
            edges.add(currentEdge.get());
        });
        overlay.setOnMouseReleased(e -> currentEdge.set(null));
        overlay.setOnMouseDragged(e -> {
            if (currentEdge.get() != null) {
                currentEdge.get().setX2(e.getX());
                currentEdge.get().setY2(e.getY());
            }
        });

        HBox controls = new HBox(5, colorPicker, load, save);
        controls.setAlignment(Pos.CENTER);
        controls.setPadding(new Insets(10));
        BorderPane root = new BorderPane(drawingpane, controls, null, null, null);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

         */}


    // Creating images/static elements;
    private void images() {
        Image ImageOk = new Image(getClass().getResourceAsStream("css/img/tree.png"));
        Image ImageOk1 = new Image(getClass().getResourceAsStream("css/img/buildings.png"));
        Image ImageOk2 = new Image(getClass().getResourceAsStream("css/img/elevator.png"));
        Image ImageOk3 = new Image(getClass().getResourceAsStream("css/img/e1.png"));

        buttonAdd1.graphicProperty().setValue(new ImageView(ImageOk));
        buttonAdd2.graphicProperty().setValue(new ImageView(ImageOk1));
        buttonAdd3.graphicProperty().setValue(new ImageView(ImageOk2));
        buttonAdd4.graphicProperty().setValue(new ImageView(ImageOk3));

    }

    //Radio Button Events
    private void rbuttons() {
        final ToggleGroup group = new ToggleGroup();

        rb1.setText("Норма");
        rb1.setToggleGroup(group);
        rb2.setText("Брак");
        rb2.setToggleGroup(group);
    }

    //Drawing a piles on the pane
    private void draw() {


    //button actions


        //main button properties
        buttonAdd.setOnAction(e -> { //creating new element on pane
            ModelTable selectedItem = table.getSelectionModel().getSelectedItem();
            if(!rb1.isSelected()&&!rb2.isSelected()) {//checking radio buttons
                radioWindow.newWindow("Внимание!");
            }
            else{

                if (selectedItem.amount > 0) {//checking for piles amount
                    /*ContextMenu contextMenu = new ContextMenu();
                    Rectangle rectangle = new Rectangle(115, 55);
                    rectangle.setStroke(Color.BLACK);

                    //interactive menu
                    MenuItem menumap = new MenuItem("Check on map");
                    MenuItem deletePile = new MenuItem("Delete pile");
                    deletePile.setOnAction(new EventHandler<ActionEvent>() {//deleting pile from pane
                        @Override
                        public void handle(ActionEvent event) {
                            overlay.getChildren().remove(rectangle);
                            selectedItem.amount+=1;
                            table.refresh();
                        }
                    });
                    menumap.setOnAction(new EventHandler<ActionEvent>() {//opening google map

                        @Override
                        public void handle(ActionEvent event) {
                            Stage window = new Stage();
                            window.initModality(Modality.APPLICATION_MODAL);


                            StackPane root = new StackPane();
                            //root.setPrefSize( Double.MAX_VALUE, Double.MAX_VALUE );
                            System.out.println(selectedItem.lat+","+selectedItem.lng);//checking cooords in console
                            root.getChildren().addAll(new GoogleMap());
                            GoogleMap.setWidth(800);
                            GoogleMap.setHeight(600);

                            //set coordinates for map
                            GoogleMap.setMarkerPosition(selectedItem.lng,selectedItem.lat);

                            Scene scene = new Scene (root, 800,600);
                            window.setScene(scene);
                            window.setTitle("GoogleMap");
                            window.setResizable(false);
                            window.showAndWait();

                        }

                    });
                    DragResizeMod.makeResizable(rectangle, null);
                    boolean radio;
                    if (rb1.isSelected()) {
                        radio = true;
                        rectangle.setFill(Color.rgb(0, 128, 0));
                    }
                    else {
                        radio = false;
                        rectangle.setFill(Color.rgb(173, 50, 39));

                    }
                    overlay.getChildren().addAll(rectangle);
                    Tooltip tooltip = new Tooltip(String.valueOf("ID " + selectedItem.id + ":\n  " + selectedItem.pile+"\n"+
                    "Заказчик:\n"+"  Имя Фамилия\n"+"Координаты:\n"+ "  " +selectedItem.lng+" , "+selectedItem.lat));
                    Tooltip.install(rectangle, tooltip);

                    contextMenu.getItems().addAll(menumap,deletePile);
                    rectangle.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

                        @Override
                        public void handle(ContextMenuEvent event) {

                            contextMenu.show(rectangle, event.getScreenX(), event.getScreenY());
                        }
                    });*/
                    DBConn dbconn = new DBConn();
                    dbconn.countPile(
                            selectedItem.pile,
                            selectedItem.amount-1);

                    selectedItem.amount = selectedItem.amount - 1;
                    table.refresh();



                    //------------Saving-pile-properties-----------------------------------------
                    boolean radio;
                    if (rb1.isSelected())radio = true;
                    else radio = false;

                    Integer id = selectedItem.id;
                    Integer amount = selectedItem.amount;
                    String pile = selectedItem.pile;
                    Double lng = selectedItem.lng;
                    Double lat = selectedItem.lat;

                    SavingPile t = new SavingPile(id,amount,pile,lat,lng,radio,
                            115d,50d,0d,0d);
                    overlay.getChildren().add(t);
                    pileList.add(t);

                   /* ObjectProperty<DAT> currentEdge = new SimpleObjectProperty<>();
                    currentEdge.set(new DAT(50.0,50.0,id,amount,pile,lat,lng));
                    dBList.add(currentEdge.get());*/

                  /*  Color color =  Color.color(1,0,1.0,1.0) ;

                    ObjectProperty<Edge> currentEdge = new SimpleObjectProperty<>();

                    Integer id = selectedItem.id;
                    Integer amount = selectedItem.amount;
                    String pile = selectedItem.pile;
                    Double lng = selectedItem.lng;
                    Double lat = selectedItem.lng;

                        currentEdge.set(new Edge(id, amount, pile, lat,lng, color));
                        edges.add(currentEdge.get());
*/


                }
                else {
                    ModalWindow.newWindow("Внимание!");
                }

            }
        });
        buttonAdd1.setOnAction(e-> {
            String s = "img/treesmall.png";

            savingObj t = new savingObj(0d,0d,s);
            overlay.getChildren().add(t);
            objList.add(t);
        });
        buttonAdd2.setOnAction(e-> {
            String s = "img/buildingssmall.png";

            savingObj t = new savingObj(0d,0d,s);
            overlay.getChildren().add(t);
            objList.add(t);
        });
        buttonAdd3.setOnAction(e-> {
            String s = "img/elevatorsmall.png";

            savingObj t = new savingObj(0d,0d,s);
            overlay.getChildren().add(t);
            objList.add(t);
        });
        buttonAdd4.setOnAction(e-> {
            String s = "img/elsmall.png";

            savingObj t = new savingObj(0d,0d,s);
            overlay.getChildren().add(t);
            objList.add(t);
        });
        buttonAdd5.setOnAction(e-> {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newPileWindow.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Создание штабеля");
                stage.setScene(new Scene(root1));
                stage.show();
                stage.setResizable(false);
            }
            catch (IOException exc){
                System.out.println("error");
            }
        });

    }



    private void saveNode(ArrayList<SavingPile> pileList, ArrayList<DAT> dBList1) throws IOException {
        for(SavingPile db:pileList){
            dBList1.add(new DAT(
                    db.id,
                    db.amount,
                    db.pile,
                    db.lat,
                    db.lng,
                    db.radio,
                    db.width,
                    db.height,
                    db.newTranslateX,
                    db.newTranslateY));
        }

        try(ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream("Node.dat"))){
            ous.writeObject(dBList1);//сохраняем объект с данными о Node
        }
    }
    private void saveNode1(ArrayList<savingObj> objList, ArrayList<DAT1> dList1) throws IOException {

        for(savingObj db:objList){
            dList1.add(new DAT1(
                    db.newTranslateX,
                    db.newTranslateY,
                    db.str));

        }
        try(ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream("Node1.dat"))){
            ous.writeObject(dList1);//сохраняем объект с данными о Node
        }
    }

    //метод для восстановления объектов
    private void openNode() throws IOException, ClassNotFoundException {
        ArrayList<SavingPile> pileList1 = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Node.dat"))){
            dBList=(ArrayList<DAT>) ois.readObject();
        }
        for(DAT dat:dBList){
            pileList1.add(new SavingPile(
                    dat.getId(),
                    dat.getAmount(),
                    dat.getPile(),
                    dat.getLat(),
                    dat.getLng(),
                    dat.getRadio(),
                    dat.getWidth(),
                    dat.getHeight(),
                    dat.getNewTranslateX(),
                    dat.getNewTranslateY()));
        }

        overlay.getChildren().removeAll(dBList);
        pileList=pileList1;
        overlay.getChildren().addAll(pileList1);
    }
    private void openNode1() throws IOException, ClassNotFoundException {
        ArrayList<savingObj>  objList1 = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Node1.dat"))){
            dList= (ArrayList<DAT1>) ois.readObject();
        }
        for(DAT1 dat:dList){
            objList1.add(new savingObj(
                    dat.getNewTranslateX(),
                    dat.getNewTranslateY(),
                    dat.getStr()));
        }
        overlay.getChildren().removeAll(dList);
        objList=objList1;
        overlay.getChildren().addAll(objList1);
    }

}





