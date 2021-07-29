package sample;

public class test
{
    /*public static void main(String[] args)
    {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            con = UConnection.getConnection();
            String sql = "SELECT id_cl, nom_cl, dir_cl, tel_cl, mail_cl FROM cliente";

            pstm = con.prepareStatement(sql);

            rs = pstm.executeQuery();
            int i=1;
            while(rs.next())
            {
                System.out.println("Cliente no: "+i);
                System.out.println(rs.getInt("id_cl"));
                System.out.println(rs.getString("nom_cl"));
                System.out.println(rs.getString("dir_cl"));
                System.out.println(rs.getString("tel_cl"));
                System.out.println(rs.getString("mail_cl"));
                i++;
            }
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
    }*/
}
