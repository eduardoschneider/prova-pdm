package br.edu.iftm.pdm.prova.model;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Report implements Comparable<Report>, Parcelable {

    private long id;
    private String descricao;
    private String natureza;
    private String data;
    private String tipo;
    private Bitmap reportPhoto1;
    private Bitmap reportPhoto2;
    private Bitmap reportPhoto3;
    private boolean selected;

    public Report(String descricao, String natureza, String data, String tipo) {
        this.descricao = descricao;
        this.natureza = natureza;
        this.data = data;
        this.tipo = tipo;
        this.selected = false;
    }

    public Report(String descricao, String natureza, String data, String tipo, Bitmap reportPhoto1, Bitmap reportPhoto2, Bitmap reportPhoto3) {
        this.descricao = descricao;
        this.natureza = natureza;
        this.data = data;
        this.tipo = tipo;
        this.reportPhoto1 = reportPhoto1;
        this.reportPhoto2 = reportPhoto2;
        this.reportPhoto3 = reportPhoto3;
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

    public Bitmap getReportPhoto1() {
        return reportPhoto1;
    }

    public Bitmap getReportPhoto2() { return reportPhoto2; }

    public Bitmap getReportPhoto3() { return reportPhoto3; }

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Report)) return false;
        if (obj == this) return true;
        return ((Report) obj).id == this.id;
    }

    @Override
    public int compareTo(Report o) {
        try {
            Date dateThis = new SimpleDateFormat("dd/MM/yyyy").parse(this.data);
            Date dateOther = new SimpleDateFormat("dd/MM/yyyy").parse(o.data);
            return dateThis.compareTo(dateOther);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Report(Parcel in) {
        id = in.readLong();
        descricao = in.readString();
        natureza = in.readString();
        data = in.readString();
        tipo = in.readString();
        reportPhoto1 = in.readParcelable(Bitmap.class.getClassLoader());
        reportPhoto2 = in.readParcelable(Bitmap.class.getClassLoader());
        reportPhoto3 = in.readParcelable(Bitmap.class.getClassLoader());
        selected = in.readInt() == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(descricao);
        dest.writeString(natureza);
        dest.writeString(data);
        dest.writeString(tipo);
        dest.writeParcelable(reportPhoto1, 0);
        dest.writeParcelable(reportPhoto2, 0);
        dest.writeParcelable(reportPhoto3, 0);
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
