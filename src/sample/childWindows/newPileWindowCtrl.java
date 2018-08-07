package sample.childWindows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Controller;
import sample.ModelTable;
import sample.childClasses.Const;
import sample.childClasses.DBConn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static sample.Controller.oblist;
import static sample.childClasses.DBConn.getConnection;

public class newPileWindowCtrl{
    @FXML
    Button newPileBtn;
    @FXML
    TextField txt1, txt2, lat, lng;

    @FXML
    void initialize() {
        DBConn dbconn = new DBConn();

        newPileBtn.setOnAction(event->{
            Integer t1 = Integer.parseInt(txt2.getText());
            Float t2 = Float.parseFloat(lat.getText());
            Float t3 = Float.parseFloat(lng.getText());
            dbconn.addPile(

                    txt1.getText(),
                    t1,
                    t2,
                    t3);
            try {
                Connection con = getConnection();
                ResultSet rs = con.createStatement().executeQuery("SELECT * FROM " + Const.TABLE);
                if (rs.last()) {
                    oblist.add(new ModelTable(
                            rs.getInt(Const.PILE_ID),
                            rs.getString(Const.PILE_NAME),
                            rs.getInt(Const.PILE_AMOUNT),
                            rs.getDouble(Const.PILE_LAT),
                            rs.getDouble(Const.PILE_LNG)));                            }
            }catch (SQLException ex){
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }}
