package com.example.agrostore01.CapaEntidades.vistas;

import android.os.Parcel;
import android.os.Parcelable;

public class VistaCompra implements Parcelable {

    private int idNumProducto;
    private String idUsuario;
    private int cantidad;

    public VistaCompra() {}

    public VistaCompra(int idNumProducto, String idUsuario, int cantidad) {
        this.idNumProducto = idNumProducto;
        this.idUsuario = idUsuario;
        this.cantidad = cantidad;
    }

    protected VistaCompra(Parcel in) {
        idNumProducto = in.readInt();
        idUsuario = in.readString();
        cantidad = in.readInt();
    }

    public static final Creator<VistaCompra> CREATOR = new Creator<VistaCompra>() {
        @Override
        public VistaCompra createFromParcel(Parcel in) {
            return new VistaCompra(in);
        }

        @Override
        public VistaCompra[] newArray(int size) {
            return new VistaCompra[size];
        }
    };

    public int getIdNumProducto() {
        return idNumProducto;
    }

    public void setIdNumProducto(int idNumProducto) {
        this.idNumProducto = idNumProducto;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "VistaCompra{" +
                "idNumProducto=" + idNumProducto +
                ", idUsuario='" + idUsuario + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idNumProducto);
        dest.writeString(idUsuario);
        dest.writeInt(cantidad);
    }
}
