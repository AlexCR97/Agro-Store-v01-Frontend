package com.example.agrostore01.CapaEntidades.vistas;

import android.os.Parcel;
import android.os.Parcelable;

public class VistaMisCompras implements Parcelable {

    private int numeroDeCompra;
    private int numeroDeLote;
    private boolean estado;
    private String tiempo;

    public VistaMisCompras(int numeroDeCompra, int numeroDeLote, boolean estado, String tiempo) {
        this.numeroDeCompra = numeroDeCompra;
        this.numeroDeLote = numeroDeLote;
        this.estado = estado;
        this.tiempo = tiempo;
    }

    protected VistaMisCompras(Parcel in) {
        numeroDeCompra = in.readInt();
        numeroDeLote = in.readInt();
        estado = in.readByte() != 0;
        tiempo = in.readString();
    }

    public static final Creator<VistaMisCompras> CREATOR = new Creator<VistaMisCompras>() {
        @Override
        public VistaMisCompras createFromParcel(Parcel in) {
            return new VistaMisCompras(in);
        }

        @Override
        public VistaMisCompras[] newArray(int size) {
            return new VistaMisCompras[size];
        }
    };

    public int getNumeroDeCompra() {
        return numeroDeCompra;
    }

    public void setNumeroDeCompra(int numeroDeCompra) {
        this.numeroDeCompra = numeroDeCompra;
    }

    public int getNumeroDeLote() {
        return numeroDeLote;
    }

    public void setNumeroDeLote(int numeroDeLote) {
        this.numeroDeLote = numeroDeLote;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "VistaMisCompras{" +
                "numeroDeCompra=" + numeroDeCompra +
                ", numeroDeLote=" + numeroDeLote +
                ", estado=" + estado +
                ", tiempo='" + tiempo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(numeroDeCompra);
        dest.writeInt(numeroDeLote);
        dest.writeByte((byte) (estado ? 1 : 0));
        dest.writeString(tiempo);
    }
}