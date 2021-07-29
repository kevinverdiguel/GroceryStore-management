package sample;

public class ProductoPedido
{
    private int id_pe;
    private int id_prod;
    private int id_pp;
    private String nom_pp;
    private double precio_pp;
    private int unid_pp;
    private double subt_pp;

    @Override
    public String toString() {
        return  nom_pp + "\t\t$" + precio_pp + "\t\t\t" + unid_pp + "\t\t\t$" + subt_pp;
    }

    public ProductoPedido()
    {

    }

    public int getId_pe() {
        return id_pe;
    }

    public void setId_pe(int id_pe) {
        this.id_pe = id_pe;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public int getId_pp() {
        return id_pp;
    }

    public void setId_pp(int id_pp) {
        this.id_pp = id_pp;
    }

    public String getNom_pp() {
        return nom_pp;
    }

    public void setNom_pp(String nom_pp) {
        this.nom_pp = nom_pp;
    }

    public double getPrecio_pp() {
        return precio_pp;
    }

    public void setPrecio_pp(double precio_pp) {
        this.precio_pp = precio_pp;
    }

    public int getUnid_pp() {
        return unid_pp;
    }

    public void setUnid_pp(int unid_pp) {
        this.unid_pp = unid_pp;
    }

    public double getSubt_pp() {
        return subt_pp;
    }

    public void setSubt_pp(double subt_pp) {
        this.subt_pp = subt_pp;
    }
}
