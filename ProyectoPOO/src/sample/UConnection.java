package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class UConnection
{
    private static Connection con=null;

    public static Connection getConnection()
    {
        try
        {
            if(con==null)
            {
                Runtime.getRuntime().addShutdownHook(new MiShDwnHook());

                String usr="root";
                String pwd="";
                String url="jdbc:mysql://localhost:3306/odcajknsystem";
                con = DriverManager.getConnection(url,usr,pwd);
            }
            return con;

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new RuntimeException("Error al crear la conexión", ex);
        }
    }

    static class MiShDwnHook  extends Thread
    {
        //Cerrar conexión
        public void run()
        {
            try
            {
                Connection con = UConnection.getConnection();
                con.close();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }
}
