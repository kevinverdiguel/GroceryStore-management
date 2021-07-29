package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductoPedidoDAO
{
    public static ObservableList<ProductoPedido> getDataProductoPedido(int id_ped)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            con = UConnection.getConnection();
            ObservableList<ProductoPedido> list = FXCollections.observableArrayList();
            String sql = "SELECT * FROM productopedido WHERE id_ped = ?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1,id_ped);
            rs = pstm.executeQuery();
            ProductoPedido prod = null;
            while(rs.next())
            {
                prod= new ProductoPedido();
                prod.setId_pp(rs.getInt("indexpp"));
                prod.setId_pe(rs.getInt("id_ped"));
                prod.setId_prod(rs.getInt("id_prod"));
                prod.setNom_pp(rs.getString("nom_prod"));
                prod.setPrecio_pp(rs.getDouble("precio_prod"));
                prod.setUnid_pp(rs.getInt("unid_ped"));
                prod.setSubt_pp(rs.getDouble("subt_ped"));
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

    public static void agregarProductoPedido(ProductoPedido pp)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            con = UConnection.getConnection();
            con.setAutoCommit(false);
            String sql = "INSERT into productopedido (id_ped, id_prod, nom_prod, precio_prod, unid_ped, subt_ped)";
            sql+="values (?,?,?,?,?,?)";

            pstm = con.prepareStatement(sql);

            pstm.setInt(1,pp.getId_pe());
            pstm.setInt(2,pp.getId_prod());
            pstm.setString(3,pp.getNom_pp());
            pstm.setDouble(4,pp.getPrecio_pp());
            pstm.setInt(5,pp.getUnid_pp());
            pstm.setDouble(6,pp.getSubt_pp());

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
    public static void eliminarProductoPedido(int id_pp)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
                con = UConnection.getConnection();
                con.setAutoCommit(false);
                String sql = "DELETE FROM productopedido WHERE indexpp = ?";

                pstm = con.prepareStatement(sql);

                pstm.setInt(1, id_pp);

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

    public static ProductoPedido buscarProductoPedido(Integer id_pp)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            con = UConnection.getConnection();
            String sql = "SELECT *  FROM productopedido WHERE indexpp = ?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id_pp);
            rs = pstm.executeQuery();
            ProductoPedido prod=null;
            while(rs.next())
            {
                prod = new ProductoPedido();
                prod.setId_pp(rs.getInt("indexpp"));
                prod.setId_pe(rs.getInt("id_ped"));
                prod.setId_prod(rs.getInt("id_prod"));
                prod.setNom_pp(rs.getString("nom_prod"));
                prod.setPrecio_pp(rs.getDouble("precio_prod"));
                prod.setUnid_pp(rs.getInt("unid_ped"));
                prod.setSubt_pp(rs.getDouble("subt_ped"));
            }
            return prod;
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
        System.out.println(buscarProductoPedido(1));
    */
}
