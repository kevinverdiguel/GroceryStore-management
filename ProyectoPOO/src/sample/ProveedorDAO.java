package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProveedorDAO
{
    public static ObservableList<ProveedorDTO> getDataProveedorDTO()
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            con = UConnection.getConnection();
            ObservableList<ProveedorDTO> list = FXCollections.observableArrayList();
            String sql = "SELECT * FROM proveedor";
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            ProveedorDTO pd = null;
            while(rs.next())
            {
                pd= new ProveedorDTO();
                pd.setId(rs.getInt("id_prov"));
                pd.setNom(rs.getString("nom_prov"));
                pd.setDir(rs.getString("dir_prov"));
                pd.setTel(rs.getString("tel_prov"));
                pd.setMail(rs.getString("mail_prov"));
                list.add(pd);
            }
            return list;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        finally
        {
            try
            {
                if(rs!=null) rs.close();
                if(pstm!=null) pstm.close();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }

    public static void agregarProveedor(ProveedorDTO proveedor)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            con = UConnection.getConnection();
            con.setAutoCommit(false);
            String sql = "INSERT into proveedor (nom_prov, dir_prov, tel_prov, mail_prov)";
            sql+="values (?,?,?,?)";

            pstm = con.prepareStatement(sql);

            pstm.setString(1, proveedor.getNom());
            pstm.setString(2, proveedor.getDir());
            pstm.setString(3, proveedor.getTel());
            pstm.setString(4, proveedor.getMail());

            int rtdo = pstm.executeUpdate();

            if(rtdo==1)
            {
                JOptionPane.showMessageDialog(null,"1 fila correctamente insertada");
                con.commit();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Error al insertar la fila");
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Error al insertar la fila");
        }
        finally
        {
            try
            {
                if(con!=null)con.rollback();
                if(rs!=null) rs.close();
                if(pstm!=null) pstm.close();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }

    public static void modificarProveedor(ProveedorDTO proveedor)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            con = UConnection.getConnection();
            con.setAutoCommit(false);
            String sql = "UPDATE proveedor ";
            sql+="SET nom_prov = ?, dir_prov = ?, tel_prov = ?, mail_prov = ?";
            sql+=" WHERE id_prov = ?" ;

            pstm = con.prepareStatement(sql);

            pstm.setString(1, proveedor.getNom());
            pstm.setString(2, proveedor.getDir());
            pstm.setString(3, proveedor.getTel());
            pstm.setString(4, proveedor.getMail());
            pstm.setInt(5, proveedor.getId());

            int rtdo = pstm.executeUpdate();

            if(rtdo==1)
            {
                JOptionPane.showMessageDialog(null,"1 fila correctamente modificada");
                con.commit();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Error al modificar la fila");
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Error al modificar la fila");
        }
        finally
        {
            try
            {
                if(con!=null)con.rollback();
                if(rs!=null) rs.close();
                if(pstm!=null) pstm.close();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }

    public static void eliminarProveedor(int id_prov)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            int seleccion = JOptionPane.showOptionDialog(
                    null,
                    "¿Desea eliminar esa fila?",
                    "Eliminar cliente",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,    // null para icono por defecto.
                    new Object[] { "No", "Si" },
                    "No");
            if(seleccion==1)
            {
                con = UConnection.getConnection();
                con.setAutoCommit(false);
                String sql = "DELETE FROM proveedor WHERE id_prov = ?";

                pstm = con.prepareStatement(sql);

                pstm.setInt(1, id_prov);

                int rtdo = pstm.executeUpdate();

                if(rtdo==1)
                {
                    JOptionPane.showMessageDialog(null,"1 fila correctamente eliminada");
                    con.commit();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Error al eliminar la fila");
                }
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Error al eliminar la fila");
        }
        finally
        {
            try
            {
                if(con!=null)con.rollback();
                if(rs!=null) rs.close();
                if(pstm!=null) pstm.close();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }




    /*public static void main(String[] args)
    {
        for(ProveedorDTO pv: getDataProveedorDTO())
        {
            System.out.println(pv);
        }
    }*/
}
