package modelos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static String server = "localhost";
    private static String user = "root";
    private static String pass = "moy";
    private static String db = "restaurante"; //el static sirve para

    public static Connection conexion;//genrra una sola la estatica

    public  static void createConnection(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mariadb://" + server + ":3306/" +db,user,pass);
        }catch (Exception e){
            e.printStackTrace();

        }
    }
}
