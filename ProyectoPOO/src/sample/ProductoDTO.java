package sample;

public class ProductoDTO
{
    private int id_prod;
    private String nom_prod;
    private double precio_prod;
    private int unid_prod;

    public ProductoDTO(int id_prod, String nom_prod, double precio_prod, int unid_prod)
    {
        this.id_prod = id_prod;
        this.nom_prod = nom_prod;
        this.precio_prod = precio_prod;
        this.unid_prod = unid_prod;
    }
    public ProductoDTO()
    {

    }

    public String toString()
    {
        return id_prod+" / "+nom_prod+" / "+unid_prod+" / "+precio_prod;
    }

    //Id
    public void setId(int id_prod)
    {
        this.id_prod=id_prod;
    }
    public int getId()
    {
        return id_prod;
    }

    //Nombre
    public void setNom(String nom_prod)
    {
        this.nom_prod=nom_prod;
    }
    public String getNom()
    {
        return nom_prod;
    }

    //Precio
    public void setPrecio(double precio_prod)
    {
        this.precio_prod=precio_prod;
    }
    public double getPrecio()
    {
        return precio_prod;
    }

    //Unidades
    public void setUnid(int unid_prod)
    {
        this.unid_prod=unid_prod;
    }
    public int getUnid()
    {
        return unid_prod;
    }
}
