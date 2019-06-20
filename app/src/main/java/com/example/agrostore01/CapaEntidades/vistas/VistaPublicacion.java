package com.example.agrostore01.CapaEntidades.vistas;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.agrostore01.CapaEntidades.Entidad;

import java.math.BigDecimal;
import java.util.Arrays;

public class VistaPublicacion extends Entidad implements Parcelable {

    private String tituloPublicacion;
    private long idProducto;
    private long idTerreno;
    private BigDecimal precio;
    private int hectareas;
    private String descripcion;
    private String temporada;
    private byte[] foto;

    public VistaPublicacion() {}

    public VistaPublicacion(String tituloPublicacion, long idProducto, long idTerreno, BigDecimal precio, int hectareas, String descripcion, String temporada, byte[] foto) {
        this.tituloPublicacion = tituloPublicacion;
        this.idProducto = idProducto;
        this.idTerreno = idTerreno;
        this.precio = precio;
        this.hectareas = hectareas;
        this.descripcion = descripcion;
        this.temporada = temporada;
        this.foto = foto;
    }

    protected VistaPublicacion(Parcel in) {
        tituloPublicacion = in.readString();
        idProducto = in.readLong();
        idTerreno = in.readLong();
        hectareas = in.readInt();
        descripcion = in.readString();
        temporada = in.readString();
        foto = in.createByteArray();
    }

    public static final Creator<VistaPublicacion> CREATOR = new Creator<VistaPublicacion>() {
        @Override
        public VistaPublicacion createFromParcel(Parcel in) {
            return new VistaPublicacion(in);
        }

        @Override
        public VistaPublicacion[] newArray(int size) {
            return new VistaPublicacion[size];
        }
    };

    public String getTituloPublicacion() {
        return tituloPublicacion;
    }

    public void setTituloPublicacion(String tituloPublicacion) {
        this.tituloPublicacion = tituloPublicacion;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public long getIdTerreno() {
        return idTerreno;
    }

    public void setIdTerreno(long idTerreno) {
        this.idTerreno = idTerreno;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getHectareas() {
        return hectareas;
    }

    public void setHectareas(int hectareas) {
        this.hectareas = hectareas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "VistaPublicacion{" +
                "tituloPublicacion='" + tituloPublicacion + '\'' +
                ", idProducto=" + idProducto +
                ", idTerreno=" + idTerreno +
                ", precio=" + precio +
                ", hectareas=" + hectareas +
                ", descripcion='" + descripcion + '\'' +
                ", temporada='" + temporada + '\'' +
                ", foto=" + Arrays.toString(foto) +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tituloPublicacion);
        dest.writeLong(idProducto);
        dest.writeLong(idTerreno);
        dest.writeInt(hectareas);
        dest.writeString(descripcion);
        dest.writeString(temporada);
        dest.writeByteArray(foto);
    }
}
