package com.example.agrostore01.CapaEntidades;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Arrays;

public class Producto extends Entidad implements Parcelable {

    private long idProducto;
    private String producto;
    private java.math.BigDecimal precioTonelada;
    private java.math.BigDecimal precioKilogramo;
    private byte[] foto;
    private boolean temporada;
    private int idCategoria;

    public Producto() {
    }

    public Producto(String producto, BigDecimal precioTonelada, byte[] foto, boolean temporada, int idCategoria, BigDecimal precioKilogramo) {
        this.producto = producto;
        this.precioTonelada = precioTonelada;
        this.foto = foto;
        this.temporada = temporada;
        this.idCategoria = idCategoria;
        this.precioKilogramo = precioKilogramo;
    }

    public Producto(long idProducto, String producto, BigDecimal precioTonelada, byte[] foto, boolean temporada, int idCategoria, BigDecimal precioKilogramo) {
        this.idProducto = idProducto;
        this.producto = producto;
        this.precioTonelada = precioTonelada;
        this.foto = foto;
        this.temporada = temporada;
        this.idCategoria = idCategoria;
        this.precioKilogramo = precioKilogramo;
    }

    protected Producto(Parcel in) {
        idProducto = in.readLong();
        producto = in.readString();
        foto = in.createByteArray();
        temporada = in.readByte() != 0;
        idCategoria = in.readInt();
    }

    public static final Creator<Producto> CREATOR = new Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public BigDecimal getPrecioTonelada() {
        return precioTonelada;
    }

    public void setPrecioTonelada(BigDecimal precioTonelada) {
        this.precioTonelada = precioTonelada;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public boolean isTemporada() {
        return temporada;
    }

    public void setTemporada(boolean temporada) {
        this.temporada = temporada;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public BigDecimal getPrecioKilogramo() {
        return precioKilogramo;
    }

    public void setPrecioKilogramo(BigDecimal precioKilogramo) {
        this.precioKilogramo = precioKilogramo;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", producto='" + producto + '\'' +
                ", precioTonelada=" + precioTonelada +
                ", foto=" + Arrays.toString(foto) +
                ", temporada=" + temporada +
                ", idCategoria=" + idCategoria +
                ", precioKilogramo=" + precioKilogramo +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idProducto);
        dest.writeString(producto);
        dest.writeByteArray(foto);
        dest.writeByte((byte) (temporada ? 1 : 0));
        dest.writeInt(idCategoria);
    }
}
