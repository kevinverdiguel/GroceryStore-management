package sample;

public class ClienteDTO
{
    private int id_cl;
    private String nom_cl;
    private String dir_cl;
    private String tel_cl;
    private String mail_cl;

    public ClienteDTO(int id_cl, String nom_cl, String dir_cl, String tel_cl, String mail_cl) {
        this.id_cl = id_cl;
        this.nom_cl = nom_cl;
        this.dir_cl = dir_cl;
        this.tel_cl = tel_cl;
        this.mail_cl = mail_cl;
    }
    public ClienteDTO() {

    }

    public String toString()
    {
        return id_cl+" / "+nom_cl+" / "+dir_cl+" / "+tel_cl+" / "+mail_cl;
    }

    //Id
    public void setId(int id_cl)
    {
        this.id_cl=id_cl;
    }
    public int getId()
    {
        return id_cl;
    }

    //Nombre
    public void setNom(String nom_cl)
    {
        this.nom_cl=nom_cl;
    }
    public String getNom()
    {
        return nom_cl;
    }

    //Direccion
    public void setDir(String dir_cl)
    {
        this.dir_cl=dir_cl;
    }
    public String getDir()
    {
        return dir_cl;
    }

    //Telefono
    public void setTel(String tel_cl)
    {
        this.tel_cl=tel_cl;
    }
    public String getTel()
    {
        return tel_cl;
    }

    //Mail
    public void setMail(String mail_cl)
    {
        this.mail_cl=mail_cl;
    }
    public String getMail()
    {
        return mail_cl;
    }
}
