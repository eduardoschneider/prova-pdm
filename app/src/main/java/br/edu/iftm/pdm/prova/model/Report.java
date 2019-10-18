package br.edu.iftm.pdm.prova.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Report implements Comparable<Report>, Parcelable {

    private long id;
    private String descricao;
    private String natureza;
    private String data;
    private String tipo;
    private Bitmap profilePicture;
    private boolean selected;

    public Report(String descricao, String natureza, String data, String tipo) {
        this.descricao = descricao;
        this.natureza = natureza;
        this.data = data;
        this.tipo = tipo;
        this.selected = false;
    }

    public Report(String descricao, String natureza, String data, String tipo, Bitmap profilePicture) {
        this.descricao = descricao;
        this.natureza = natureza;
        this.data = data;
        this.tipo = tipo;
        this.profilePicture = profilePicture;
        this.selected = false;
    }

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNatureza() {
        return natureza;
    }

    public String getData() {
        return data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Bitmap getProfilePicture() {
        return profilePicture;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    // ---> Equals

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Report)) return false;
        if (obj == this) return true;
        return ((Report) obj).id == this.id;
    }

    // ---> Comparable
    @Override
    public int compareTo(Report o) {
        String nameThis = this.descricao + " " + this.natureza;
        String nameOther = o.descricao + " " + o.natureza;
        return nameThis.toUpperCase().compareTo(nameOther.toUpperCase());
    }

    // ---> Parcelable
    private Report(Parcel in) {
        id = in.readLong();
        descricao = in.readString();
        natureza = in.readString();
        data = in.readString();
        tipo = in.readString();
        profilePicture = in.readParcelable(Bitmap.class.getClassLoader());
        selected = in.readInt() == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(descricao);
        dest.writeString(natureza);
        dest.writeString(data);
        dest.writeString(tipo);
        dest.writeParcelable(profilePicture, 0);
        dest.writeInt((selected) ? 1 : 0);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Report> CREATOR = new Creator<Report>() {
        @Override
        public Report createFromParcel(Parcel in) {
            return new Report(in);
        }

        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };
}
