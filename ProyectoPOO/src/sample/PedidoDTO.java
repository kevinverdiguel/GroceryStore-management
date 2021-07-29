package sample;

import java.sql.Date;
import java.sql.Time;

public class PedidoDTO
{
    private int id_ped;
    private int id_cl;
    private String nom_cl;
    private double total_ped;
    private Date fecha_ped;
    private Time hora_ped;

    public Date getFecha_ped() {
        return fecha_ped;
    }

    public void setFecha_ped(Date fecha_ped) {
        this.fecha_ped = fecha_ped;
    }

    public Time getHora_ped() {
        return hora_ped;
    }

    public void setHora_ped(Time hora_ped) {
        this.hora_ped = hora_ped;
    }

    @Override
    public String toString() {
        return "PedidoDTO{" +
                "id_ped=" + id_ped +
                ", id_cl=" + id_cl +
                ", nom_cl='" + nom_cl + '\'' +
                ", total_ped=" + total_ped +
                ", fecha_ped=" + fecha_ped +
                ", hora_ped=" + hora_ped +
                '}';
    }

    public PedidoDTO()
    {

    }

    public int getId_ped() {
        return id_ped;
    }

    public void setId_ped(int id_ped) {
        this.id_ped = id_ped;
    }

    public int getId_cl() {
        return id_cl;
    }

    public void setId_cl(int id_cl) {
        this.id_cl = id_cl;
    }

    public String getNom_cl() {
        return nom_cl;
    }

    public void setNom_cl(String nom_cl) {
        this.nom_cl = nom_cl;
    }

    public double getTotal_ped() {
        return total_ped;
    }

    public void setTotal_ped(double total_ped) {
        this.total_ped = total_ped;
    }


}
