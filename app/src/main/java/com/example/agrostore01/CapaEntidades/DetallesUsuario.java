package com.example.agrostore01.CapaEntidades;

import android.os.Parcel;
import android.os.Parcelable;

public class DetallesUsuario extends Entidad implements Parcelable {

    private long idDetalles;
    private String nombres;
    private String apellidos;
    private String calle;
    private String colonia;
    private String estado;
    private String pais;
    private int cp;
    private String escrituraOPermiso;
    private float estrellas;
    private String rfc;
    private String firmaElectronica;
    private String cuidad;
    private String fechaNac; // anio-mes-dia
    private String telefono;

    public DetallesUsuario() {}

    public DetallesUsuario(String nombres, String apellidos, String calle, String colonia, String estado, String pais, int cp, String escrituraOPermiso, float estrellas, String rfc, String firmaElectronica, String cuidad, String fechaNac, String telefono) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.calle = calle;
        this.colonia = colonia;
        this.estado = estado;
        this.pais = pais;
        this.cp = cp;
        this.escrituraOPermiso = escrituraOPermiso;
        this.estrellas = estrellas;
        this.rfc = rfc;
        this.firmaElectronica = firmaElectronica;
        this.cuidad = cuidad;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
    }

    public DetallesUsuario(long idDetalles, String nombres, String apellidos, String calle, String colonia, String estado, String pais, int cp, String escrituraOPermiso, float estrellas, String rfc, String firmaElectronica, String cuidad, String fechaNac, String telefono) {
        this.idDetalles = idDetalles;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.calle = calle;
        this.colonia = colonia;
        this.estado = estado;
        this.pais = pais;
        this.cp = cp;
        this.escrituraOPermiso = escrituraOPermiso;
        this.estrellas = estrellas;
        this.rfc = rfc;
        this.firmaElectronica = firmaElectronica;
        this.cuidad = cuidad;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
    }

    protected DetallesUsuario(Parcel in) {
        idDetalles = in.readLong();
        nombres = in.readString();
        apellidos = in.readString();
        calle = in.readString();
        colonia = in.readString();
        estado = in.readString();
        pais = in.readString();
        cp = in.readInt();
        escrituraOPermiso = in.readString();
        estrellas = in.readFloat();
        rfc = in.readString();
        firmaElectronica = in.readString();
        cuidad = in.readString();
        fechaNac = in.readString();
        telefono = in.readString();
    }

    public static final Creator<DetallesUsuario> CREATOR = new Creator<DetallesUsuario>() {
        @Override
        public DetallesUsuario createFromParcel(Parcel in) {
            return new DetallesUsuario(in);
        }

        @Override
        public DetallesUsuario[] newArray(int size) {
            return new DetallesUsuario[size];
        }
    };

    public long getIdDetalles() {
        return idDetalles;
    }

    public void setIdDetalles(long idDetalles) {
        this.idDetalles = idDetalles;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getEscrituraOPermiso() {
        return escrituraOPermiso;
    }

    public void setEscrituraOPermiso(String escrituraOPermiso) {
        this.escrituraOPermiso = escrituraOPermiso;
    }

    public float getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(float estrellas) {
        this.estrellas = estrellas;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getFirmaElectronica() {
        return firmaElectronica;
    }

    public void setFirmaElectronica(String firmaElectronica) {
        this.firmaElectronica = firmaElectronica;
    }

    public String getCuidad() {
        return cuidad;
    }

    public void setCuidad(String cuidad) {
        this.cuidad = cuidad;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "DetallesUsuario{" +
                "idDetalles=" + idDetalles +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", calle='" + calle + '\'' +
                ", colonia='" + colonia + '\'' +
                ", estado='" + estado + '\'' +
                ", pais='" + pais + '\'' +
                ", cp=" + cp +
                ", escrituraOPermiso='" + escrituraOPermiso + '\'' +
                ", estrellas=" + estrellas +
                ", rfc='" + rfc + '\'' +
                ", firmaElectronica='" + firmaElectronica + '\'' +
                ", cuidad='" + cuidad + '\'' +
                ", fechaNac='" + fechaNac + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idDetalles);
        dest.writeString(nombres);
        dest.writeString(apellidos);
        dest.writeString(calle);
        dest.writeString(colonia);
        dest.writeString(estado);
        dest.writeString(pais);
        dest.writeInt(cp);
        dest.writeString(escrituraOPermiso);
        dest.writeFloat(estrellas);
        dest.writeString(rfc);
        dest.writeString(firmaElectronica);
        dest.writeString(cuidad);
        dest.writeString(fechaNac);
        dest.writeString(telefono);
    }
}
