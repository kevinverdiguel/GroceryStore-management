package sample;

public class ProveedorDTO
{
    private int id_prov;
    private String nom_prov;
    private String dir_prov;
    private String tel_prov;
    private String mail_prov;

    public ProveedorDTO(int id_prov, String nom_prov, String dir_prov, String tel_prov, String mail_prov) {
        this.id_prov = id_prov;
        this.nom_prov = nom_prov;
        this.dir_prov = dir_prov;
        this.tel_prov = tel_prov;
        this.mail_prov = mail_prov;
    }
    public ProveedorDTO()
    {

    }
    public String toString()
    {
        return id_prov+" / "+nom_prov+" / "+dir_prov+" / "+tel_prov+" / "+mail_prov;
    }

    //Id
    public void setId(int id_prov)
    {
        this.id_prov=id_prov;
    }
    public int getId()
    {
        return id_prov;
    }

    //Nombre
    public void setNom(String nom_prov)
    {
        this.nom_prov=nom_prov;
    }
    public String getNom()
    {
        return nom_prov;
    }

    //Direccion
    public void setDir(String dir_prov)
    {
        this.dir_prov=dir_prov;
    }
    public String getDir()
    {
        return dir_prov;
    }

    //Telefono
    public void setTel(String tel_prov)
    {
        this.tel_prov=tel_prov;
    }
    public String getTel()
    {
        return tel_prov;
    }

    //Mail
    public void setMail(String mail_prov)
    {
        this.mail_prov=mail_prov;
    }
    public String getMail()
    {
        return mail_prov;
    }
}
