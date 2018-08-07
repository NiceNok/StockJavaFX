package sample.childClasses;

import java.sql.*;

public class DBConn {
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/sqlad?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
        return connection;
    }

    public void addPile(String gruz, int amount, float lat, float lng) {

        String insert = "INSERT INTO " + Const.TABLE + "(" +
                Const.PILE_NAME + "," +
                Const.PILE_AMOUNT + "," +
                Const.PILE_LAT + "," +
                Const.PILE_LNG + ")" +
                "VALUES(?,?,?,?)";
        try {
            PreparedStatement prst = getConnection().prepareStatement(insert);
            prst.setString(1,gruz);
            prst.setInt(2, amount);
            prst.setFloat(3, lat);
            prst.setFloat(4, lng);

            prst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }
    public void countPile(String name, Integer am){

        String upd = "UPDATE Piles SET amount =? WHERE cargo =?";


        try {
            PreparedStatement prss = getConnection().prepareStatement(upd);
            prss.setInt(1,am);
            prss.setString(2,name);

            prss.executeUpdate();
        } catch (Exception e) {
            System.out.println("fiasco");
        }
    }
}