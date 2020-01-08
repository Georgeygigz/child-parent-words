
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Georgey
 */
public class Learnconnect {
    private static Connection connection;
    public static Connection learnconnect(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection=DriverManager.getConnection("jdbc:sqlite:Learning_aid.sqlite");
            return  connection;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return  null;
        }
    
    
    
    }
    
}
