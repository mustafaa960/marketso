package com.miq.sms.models.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 *
 * @author Mustafa
 */
//calss database access object
public class Dao {
     public Connection getConnection()  {
        // variables
        Connection connection = null;
        // Step 1: Loading or registering Oracle JDBC driver class
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException cnfex) {
            
            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }
        // Step 2: Opening database connection
        try {
            String msAccDB = "..\\marketso\\src\\main\\resources\\db\\sms.accdb";
//            String msAccDB = "..\\sms\\src\\main\\resources\\db\\sms.accdb";
            String dbURL = "jdbc:ucanaccess://" + msAccDB;
            // Step 2.A: Create and get connection using DriverManager class
            connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
//                JOptionPane.showMessageDialog(null, "connected");
                return connection;
            }

        } catch (SQLException sqlex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
                       alert.setTitle("خطأ");
                       alert.setHeaderText("حدث خطأ في الاتصال بقاعدة البيانات");
                       alert.setContentText(sqlex.getMessage());
                       alert.showAndWait();
//            JOptionPane.showMessageDialog(null, "حدث خطأ في الاتصال بقاعدة البيانات");

           }

        return null;
    }

    public void CloseConnection(Connection con) throws Exception {

        // Step 3: Closing database connection
        try {
            if (con != null) {

                con.close();
                
            }
        } catch (SQLException sqlex) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
                       alert.setTitle("خطأ");
                       alert.setHeaderText(sqlex.getMessage());
                       alert.setContentText(sqlex.toString());
                       alert.showAndWait();
        }
    }
}