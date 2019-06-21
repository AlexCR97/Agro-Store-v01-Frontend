package com.example.agrostore01.CapaEntidades.vistas;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.Arrays;

public class VistaCarrito implements Parcelable {

    private int idNumProducto;
    private String producto;
    private int cantidad;
    private String precio;
    private byte[] foto;

    public VistaCarrito() {}

    public VistaCarrito(int idNumProducto, String producto, int cantidad, String precio, byte[] foto) {
        this.idNumProducto = idNumProducto;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.foto = foto;
    }

    protected VistaCarrito(Parcel in) {
        idNumProducto = in.readInt();
        producto = in.readString();
        cantidad = in.readInt();
        precio = in.readString();
        foto = in.createByteArray();
    }

    public static final Creator<VistaCarrito> CREATOR = new Creator<VistaCarrito>() {
        @Override
        public VistaCarrito createFromParcel(Parcel in) {
            return new VistaCarrito(in);
        }

        @Override
        public VistaCarrito[] newArray(int size) {
            return new VistaCarrito[size];
        }
    };

    public int getIdNumProducto() {
        return idNumProducto;
    }

    public void setIdNumProducto(int idNumProducto) {
        this.idNumProducto = idNumProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "VistaCarrito{" +
                "idNumProducto=" + idNumProducto +
                ", producto='" + producto + '\'' +
                ", cantidad=" + cantidad +
                ", precio='" + precio + '\'' +
                ", foto=" + Arrays.toString(foto) +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idNumProducto);
        dest.writeString(producto);
        dest.writeInt(cantidad);
        dest.writeString(precio);
        dest.writeByteArray(foto);
    }
}
