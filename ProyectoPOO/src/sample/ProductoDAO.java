package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductoDAO
{
    private JOptionPane OptionPane;

    public static ObservableList<ProductoDTO> getDataProductoDTO()
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            con = UConnection.getConnection();
            ObservableList<ProductoDTO> list = FXCollections.observableArrayList();
            String sql = "SELECT * FROM producto";
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            ProductoDTO prod = null;
            while(rs.next())
            {
                prod= new ProductoDTO();
                prod.setId(rs.getInt("id_prod"));
                prod.setNom(rs.getString("nom_prod"));
                prod.setPrecio(rs.getDouble("precio_prod"));
                prod.setUnid(rs.getInt("unid_prod"));
                list.add(prod);
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

    public static void insertarProducto(ProductoDTO producto)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            con = UConnection.getConnection();
            if(con!=null)
            {
                con.setAutoCommit(false);
                String sql = "INSERT into producto (nom_prod, precio_prod, unid_prod)";
                sql += "values (?,?,?)";

                pstm = con.prepareStatement(sql);

                pstm.setString(1, producto.getNom());
                pstm.setDouble(2, producto.getPrecio());
                pstm.setInt(3, producto.getUnid());

                int rtdo = pstm.executeUpdate();

                if (rtdo == 1) {
                    JOptionPane.showMessageDialog(null, "1 fila correctamente insertada");
                    con.commit();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al insertar la fila");
                }
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

    public static void modificarProducto(ProductoDTO producto)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            con = UConnection.getConnection();
            con.setAutoCommit(false);
            String sql = "UPDATE producto ";
            sql+=" SET ";
            sql+=" nom_prod = ?, precio_prod = ?, unid_prod = ?";
            sql+=" WHERE id_prod = ?" ;

            pstm = con.prepareStatement(sql);

            pstm.setString(1, producto.getNom());
            pstm.setDouble(2, producto.getPrecio());
            pstm.setInt(3, producto.getUnid());
            pstm.setInt(4, producto.getId());

            int rtdo = pstm.executeUpdate();

            if(rtdo==1)
            {
                //System.out.println("1 producto correctamente modificado");
                JOptionPane.showMessageDialog(null,"1 fila correctamente modificada");
                con.commit();
            }
            else
            {
                //System.out.println("No se pudo realizar la modificación");
                JOptionPane.showMessageDialog(null,"Error al modificar la fila");
            }
        }
        catch(Exception ex)
        {
            //System.out.println("No se pudo realizar la modificación");
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
    public static void eliminarProducto(int id_prod)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            int seleccion = JOptionPane.showOptionDialog(
                    null,
                    "¿Desea eliminar esa fila?",
                    "Eliminar producto",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,    // null para icono por defecto.
                    new Object[] { "No", "Si" },
                    "No");
            if(seleccion==1)
            {
                con = UConnection.getConnection();
                con.setAutoCommit(false);
                String sql = "DELETE FROM producto WHERE id_prod = ?";

                pstm = con.prepareStatement(sql);

                pstm.setInt(1, id_prod);

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
    public static ProductoDTO buscarPdNombre(String nombre)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try
        {
            con = UConnection.getConnection();
            String sql = "SELECT id_prod, nom_prod, precio_prod, unid_prod  FROM producto WHERE nom_prod = ?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, nombre);
            rs = pstm.executeQuery();
            ProductoDTO producto = new ProductoDTO();
            while(rs.next())
            {
                producto.setId(rs.getInt("id_prod"));
                producto.setNom(rs.getString("nom_prod"));
                producto.setPrecio(rs.getDouble("precio_prod"));
                producto.setUnid(rs.getInt("unid_prod"));
            }
            return producto;
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


    /*public static void main(String[] args)
    {
        System.out.println(buscarPdNombre(""));
    }*/
}
