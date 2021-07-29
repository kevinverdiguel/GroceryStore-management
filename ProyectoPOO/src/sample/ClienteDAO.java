package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteDAO
{
    public static ObservableList<ClienteDTO> getDataClienteDTO()
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            con = UConnection.getConnection();
            ObservableList<ClienteDTO> list = FXCollections.observableArrayList();
            pstm = con.prepareStatement("select * from cliente");
            rs= pstm.executeQuery();
            ClienteDTO cliente;
            while(rs.next())
            {
                cliente=new ClienteDTO();
                cliente.setId(rs.getInt("id_cl"));
                cliente.setNom(rs.getString("nom_cl"));
                cliente.setDir(rs.getString("dir_cl"));
                cliente.setTel(rs.getString("tel_cl"));
                cliente.setMail(rs.getString("mail_cl"));
                list.add(cliente);
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

    public static void agregarCliente(ClienteDTO cliente)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            con = UConnection.getConnection();
            con.setAutoCommit(false);
            String sql = "INSERT into cliente (nom_cl, dir_cl, tel_cl, mail_cl)";
            sql+="values (?,?,?,?)";

            pstm = con.prepareStatement(sql);

            pstm.setString(1, cliente.getNom());
            pstm.setString(2, cliente.getDir());
            pstm.setString(3, cliente.getTel());
            pstm.setString(4, cliente.getMail());

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
    public static void modificarCliente(ClienteDTO cliente,int id_b)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            con = UConnection.getConnection();
            con.setAutoCommit(false);
            String sql = "UPDATE cliente ";
            sql+="SET ";
            sql+=" nom_cl = ?, dir_cl = ?, tel_cl = ?, mail_cl = ?";
            sql+=" WHERE id_cl = ?" ;

            pstm = con.prepareStatement(sql);

            pstm.setString(1, cliente.getNom());
            pstm.setString(2, cliente.getDir());
            pstm.setString(3, cliente.getTel());
            pstm.setString(4, cliente.getMail());
            pstm.setInt(5, id_b);

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
    public static void eliminarCliente(int id_cl)
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
                String sql = "DELETE FROM cliente WHERE id_cl = ?";

                pstm = con.prepareStatement(sql);

                pstm.setInt(1, id_cl);

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

    public static ClienteDTO buscarCliente(String nombre)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            con = UConnection.getConnection();
            String sql = "SELECT id_cl, nom_cl, dir_cl, tel_cl, mail_cl  FROM cliente WHERE nom_cl = ?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, nombre);
            rs = pstm.executeQuery();
            ClienteDTO cliente = new ClienteDTO();
            while(rs.next())
            {
                cliente.setId(rs.getInt("id_cl"));
                cliente.setNom(rs.getString("nom_cl"));
                cliente.setDir(rs.getString("dir_cl"));
                cliente.setTel(rs.getString("tel_cl"));
                cliente.setMail(rs.getString("mail_cl"));
            }
            return cliente;
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

    public static ClienteDTO buscarCliente(Integer id)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            con = UConnection.getConnection();
            String sql = "SELECT id_cl, nom_cl, dir_cl, tel_cl, mail_cl  FROM cliente WHERE id_cl = ?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            ClienteDTO cliente = new ClienteDTO();
            while(rs.next())
            {
                cliente.setId(rs.getInt("id_cl"));
                cliente.setNom(rs.getString("nom_cl"));
                cliente.setDir(rs.getString("dir_cl"));
                cliente.setTel(rs.getString("tel_cl"));
                cliente.setMail(rs.getString("mail_cl"));
            }
            return cliente;
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
        System.out.println(buscarCliente("Kevin"));

    }*/
}
