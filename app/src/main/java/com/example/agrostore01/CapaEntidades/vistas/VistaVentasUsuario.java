package com.example.agrostore01.CapaEntidades.vistas;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

public class VistaVentasUsuario implements Parcelable {

    private int numeroDeVenta;
    private BigDecimal precio;
    private String producto;
    private String fecha;

    public VistaVentasUsuario(int numeroDeVenta, BigDecimal precio, String producto, String fecha) {
        this.numeroDeVenta = numeroDeVenta;
        this.precio = precio;
        this.producto = producto;
        this.fecha = fecha;
    }

    public int getNumeroDeVenta() {
        return numeroDeVenta;
    }

    public void setNumeroDeVenta(int numeroDeVenta) {
        this.numeroDeVenta = numeroDeVenta;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    protected VistaVentasUsuario(Parcel in) {
        numeroDeVenta = in.readInt();
        producto = in.readString();
        fecha = in.readString();
    }

    public static final Creator<VistaVentasUsuario> CREATOR = new Creator<VistaVentasUsuario>() {
        @Override
        public VistaVentasUsuario createFromParcel(Parcel in) {
            return new VistaVentasUsuario(in);
        }

        @Override
        public VistaVentasUsuario[] newArray(int size) {
            return new VistaVentasUsuario[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(numeroDeVenta);
        parcel.writeString(producto);
        parcel.writeString(fecha);
    }
}
