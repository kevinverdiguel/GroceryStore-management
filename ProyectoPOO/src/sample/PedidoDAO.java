package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PedidoDAO
{
    public static ObservableList<PedidoDTO> getDataPedidoDTO()
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            con = UConnection.getConnection();
            ObservableList<PedidoDTO> list = FXCollections.observableArrayList();
            String sql = "SELECT * FROM pedido";
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            PedidoDTO ped = null;
            while(rs.next())
            {
                ped= new PedidoDTO();
                ped.setId_ped(rs.getInt("id_ped"));
                ped.setId_cl(rs.getInt("id_cl"));
                ped.setNom_cl(rs.getString("nom_cl"));
                ped.setTotal_ped(rs.getDouble("total_ped"));
                ped.setFecha_ped(rs.getDate("fecha_ped"));
                ped.setHora_ped(rs.getTime("hora_ped"));
                list.add(ped);
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

    public static void agregarpedido(PedidoDTO pedido)
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
                String sql = "INSERT into pedido (id_cl, nom_cl, total_ped, fecha_ped, hora_ped)";
                sql += "values (?,?,?,?,?)";

                pstm = con.prepareStatement(sql);

                pstm.setInt(1, pedido.getId_cl());
                pstm.setString(2, pedido.getNom_cl());
                pstm.setDouble(3,pedido.getTotal_ped());
                pstm.setDate(4,pedido.getFecha_ped());
                pstm.setTime(5,pedido.getHora_ped());

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

    public static void modificarPedido(PedidoDTO pedido)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            con = UConnection.getConnection();
            con.setAutoCommit(false);
            String sql = "UPDATE pedido ";
            sql+=" SET ";
            sql+=" id_cl = ?, nom_cl = ?, total_ped = ?, fecha_ped =?, hora_ped= ?";
            sql+=" WHERE id_ped = ?" ;

            pstm = con.prepareStatement(sql);

            pstm.setInt(1, pedido.getId_cl());
            pstm.setString(2, pedido.getNom_cl());
            pstm.setDouble(3, pedido.getTotal_ped());
            pstm.setDate(4, pedido.getFecha_ped());
            pstm.setTime(5, pedido.getHora_ped());
            pstm.setInt(6, pedido.getId_ped());

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

    public static void eliminarPedido(int id_ped)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            int seleccion = JOptionPane.showOptionDialog(
                    null,
                    "¿Desea eliminar esa fila?",
                    "Eliminar pedido",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,    // null para icono por defecto.
                    new Object[] { "No", "Si" },
                    "No");
            if(seleccion==1)
            {
                con = UConnection.getConnection();
                con.setAutoCommit(false);
                String sql = "DELETE FROM pedido WHERE id_ped = ?";

                pstm = con.prepareStatement(sql);

                pstm.setInt(1, id_ped);

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

    public static PedidoDTO buscarPedido(Integer id_ped)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            con = UConnection.getConnection();
            String sql = "SELECT *  FROM pedido WHERE id_ped = ?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id_ped);
            rs = pstm.executeQuery();
            PedidoDTO ped = new PedidoDTO();
            while(rs.next())
            {
                ped= new PedidoDTO();
                ped.setId_ped(rs.getInt("id_ped"));
                ped.setId_cl(rs.getInt("id_cl"));
                ped.setNom_cl(rs.getString("nom_cl"));
                ped.setTotal_ped(rs.getDouble("total_ped"));
                ped.setFecha_ped(rs.getDate("fecha_ped"));
                ped.setHora_ped(rs.getTime("hora_ped"));
            }
            return ped;
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
        for(PedidoDTO pedido:getDataPedidoDTO())
        {
            System.out.println(pedido);
        }
    }*/
}
