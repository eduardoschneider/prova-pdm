package br.edu.iftm.pdm.prova;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import br.edu.iftm.pdm.prova.data.DAOReport;
import br.edu.iftm.pdm.prova.model.Report;


public class StatisticsActivity extends AppCompatActivity {

    private TextView txt01;
    private TextView txt02;
    private TextView txt03;
    private TextView txt04;
    private TextView txt05;

    private String localComMais;
    private int qtdeMais = 0;
    private ArrayList<Report> depredacoes;
    private ArrayList<Report> indevidos;
    private ArrayList<String> natureza;
    private ArrayList<String> localProblema;
    private int a, b, c, d, e, f, g, h, i, j, k, l;
    private int mesMais;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_statistics);
        initialize();
        depredacao();
        usoIndevido();
        natureza();
        localProblema();
        depredacaoMes();
    }

    private void initialize() {

        this.depredacoes = new ArrayList<>();
        this.indevidos = new ArrayList<>();
        this.natureza = new ArrayList<>();
        this.localProblema = new ArrayList<>();
        this.txt01 = findViewById(R.id.txt01);
        this.txt02 = findViewById(R.id.txt02);
        this.txt03 = findViewById(R.id.txt03);
        this.txt04 = findViewById(R.id.txt04);
        this.txt05 = findViewById(R.id.txt05);
    }

    private void depredacao() {
        ArrayList<Report> reports = DAOReport.getINSTANCE().getReports();

        for (Report r : reports) {
            if (r.getNatureza().equals(getString(R.string.option_1))) {
                this.depredacoes.add(r);
            }
        }

        for (Report re : depredacoes) {
            switch (re.getTipo()) {
                case "A": {
                    this.a++;
                    if (a > qtdeMais) {
                        qtdeMais = a;
                        localComMais = re.getTipo();
                    }
                    break;
                }
                case "B": {
                    this.b++;
                    if (b > qtdeMais) {
                        qtdeMais = b;
                        localComMais = re.getTipo();
                    }
                    break;
                }
                case "C": {
                    this.c++;
                    if (c > qtdeMais) {
                        qtdeMais = c;
                        localComMais = re.getTipo();
                    }
                    break;
                }
                case "D": {
                    this.d++;
                    if (d > qtdeMais) {
                        qtdeMais = d;
                        localComMais = re.getTipo();
                    }
                    break;
                }
                case "E": {
                    this.e++;
                    if (e > qtdeMais) {
                        qtdeMais = e;
                        localComMais = re.getTipo();
                    }
                    break;
                }
                case "F": {
                    this.f++;
                    if (f > qtdeMais) {
                        qtdeMais = f;
                        localComMais = re.getTipo();
                    }
                    break;
                }
                case "G": {
                    this.g++;
                    if (g > qtdeMais) {
                        qtdeMais = g;
                        localComMais = re.getTipo();
                    }
                    break;
                }
                case "H": {
                    this.h++;
                    if (h > qtdeMais) {
                        qtdeMais = h;
                        localComMais = re.getTipo();
                    }
                    break;
                }

            }
        }

        if (localComMais == null)
            this.txt01.setText(R.string.no_data);

        else
            this.txt01.setText(getString(R.string.most_depred) + " " + localComMais + " " + getString(R.string.amount) + " " + qtdeMais + " " + getString(R.string.ocurrency));

        localComMais = null;
        qtdeMais = 0;
        a = 0;
        b = 0;
        c = 0;
        d = 0;
        e = 0;
        f = 0;
        g = 0;
        h = 0;

    }

    private void usoIndevido() {
        ArrayList<Report> reports = DAOReport.getINSTANCE().getReports();

        for (Report r : reports) {
            if (r.getNatureza().equals(getString(R.string.option_3))) {
                this.indevidos.add(r);
            }
        }

        for (Report re : indevidos) {
            switch (re.getTipo()) {
                case "A": {
                    this.a++;
                    if (a > qtdeMais) {
                        qtdeMais = a;
                        localComMais = re.getTipo();
                    }
                    break;
                }
                case "B": {
                    this.b++;
                    if (b > qtdeMais) {
                        qtdeMais = b;
                        localComMais = re.getTipo();
                    }
                    break;
                }
                case "C": {
                    this.c++;
                    if (c > qtdeMais) {
                        qtdeMais = c;
                        localComMais = re.getTipo();
                    }
                    break;
                }
                case "D": {
                    this.d++;
                    if (d > qtdeMais) {
                        qtdeMais = d;
                        localComMais = re.getTipo();
                    }
                    break;
                }
                case "E": {
                    this.e++;
                    if (e > qtdeMais) {
                        qtdeMais = e;
                        localComMais = re.getTipo();
                    }
                    break;
                }
                case "F": {
                    this.f++;
                    if (f > qtdeMais) {
                        qtdeMais = f;
                        localComMais = re.getTipo();
                    }
                    break;
                }
                case "G": {
                    this.g++;
                    if (g > qtdeMais) {
                        qtdeMais = g;
                        localComMais = re.getTipo();
                    }
                    break;
                }
                case "H": {
                    this.h++;
                    if (h > qtdeMais) {
                        qtdeMais = h;
                        localComMais = re.getTipo();
                    }
                    break;
                }

            }
        }

        if (localComMais == null)
            this.txt02.setText(R.string.no_data);
        else
            this.txt02.setText(getString(R.string.most_bad_use) + " " + localComMais + " " +getString(R.string.amount) + qtdeMais + " " + getString(R.string.ocurrency));

        localComMais = null;
        qtdeMais = 0;
        a = 0;
        b = 0;
        c = 0;
        d = 0;
        e = 0;
        f = 0;
        g = 0;
        h = 0;
    }

    private void natureza() {
        ArrayList<Report> reports = DAOReport.getINSTANCE().getReports();

        for (Report r : reports) {
            this.natureza.add(r.getNatureza());
        }

        for (String re : natureza) {
            switch (re) {
                case "Uso Indevido/Ilegal": {
                    this.a++;
                    if (a > qtdeMais) {
                        qtdeMais = a;
                        localComMais = re;
                    }
                    break;
                }
                case "Illegal/Inappropriate Use": {
                    this.a++;
                    if (a > qtdeMais) {
                        qtdeMais = a;
                        localComMais = re;
                    }
                    break;
                }
                case "Depredação": {
                    this.b++;
                    if (b > qtdeMais) {
                        qtdeMais = b;
                        localComMais = re;
                    }
                    break;
                }
                case "Depredation": {
                    this.b++;
                    if (b > qtdeMais) {
                        qtdeMais = b;
                        localComMais = re;
                    }
                    break;
                }
                case "Mal Funcionamento": {
                    this.c++;
                    if (c > qtdeMais) {
                        qtdeMais = c;
                        localComMais = re;
                    }
                    break;
                }
                case "Malfunction": {
                    this.c++;
                    if (c > qtdeMais) {
                        qtdeMais = c;
                        localComMais = re;
                    }
                    break;
                }

            }
        }

        if (localComMais == null)
            this.txt03.setText(R.string.no_data);

        else
            this.txt03.setText(getString(R.string.most_nature) +" " + localComMais);

        localComMais = null;
        qtdeMais = 0;
        a = 0;
        b = 0;
        c = 0;
        d = 0;
        e = 0;
        f = 0;
        g = 0;
        h = 0;
    }

    private void localProblema() {
        ArrayList<Report> reports = DAOReport.getINSTANCE().getReports();

        for (Report r : reports) {
            this.localProblema.add(r.getTipo());
        }

        for (String re : localProblema) {
            switch (re) {
                case "A": {
                    this.a++;
                    if (a > qtdeMais) {
                        qtdeMais = a;
                        localComMais = re;
                    }
                    break;
                }
                case "B": {
                    this.b++;
                    if (b > qtdeMais) {
                        qtdeMais = b;
                        localComMais = re;
                    }
                    break;
                }
                case "C": {
                    this.c++;
                    if (c > qtdeMais) {
                        qtdeMais = c;
                        localComMais = re;
                    }
                    break;
                }
                case "D": {
                    this.d++;
                    if (d > qtdeMais) {
                        qtdeMais = d;
                        localComMais = re;
                    }
                    break;
                }
                case "E": {
                    this.e++;
                    if (e > qtdeMais) {
                        qtdeMais = e;
                        localComMais = re;
                    }
                    break;
                }
                case "F": {
                    this.f++;
                    if (f > qtdeMais) {
                        qtdeMais = f;
                        localComMais = re;
                    }
                    break;
                }
                case "G": {
                    this.g++;
                    if (g > qtdeMais) {
                        qtdeMais = g;
                        localComMais = re;
                    }
                    break;
                }
                case "H": {
                    this.h++;
                    if (h > qtdeMais) {
                        qtdeMais = h;
                        localComMais = re;
                    }
                    break;
                }

            }
        }

        if (localComMais == null)
            this.txt04.setText(R.string.no_data);

        else
            this.txt04.setText(getString(R.string.most_trouble) + " " + localComMais);

        localComMais = null;
        qtdeMais = 0;
        a = 0;
        b = 0;
        c = 0;
        d = 0;
        e = 0;
        f = 0;
        g = 0;
        h = 0;
    }

    private void depredacaoMes() {
        ArrayList<Report> reports = DAOReport.getINSTANCE().getReports();

        for (Report r : reports) {
            if (r.getNatureza().equals(getString(R.string.option_1))) {
                this.depredacoes.add(r);
            }
        }

        for (Report re : depredacoes) {
            String str[] = re.getData().split("/");
            int month = Integer.parseInt(str[1]);
            if (month == 1){
                this.a++;
                if (a > qtdeMais) {
                    qtdeMais = a;
                    mesMais = month;
                }
            } else if (month == 2){
                this.b++;
                if (b > qtdeMais) {
                    qtdeMais = b;
                    mesMais = month;
                }
            } else if (month == 3) {
                this.c++;
                if (c > qtdeMais) {
                    qtdeMais = c;
                    mesMais = month;
                }
            } else if (month == 4){
                this.d++;
                if (d > qtdeMais) {
                    qtdeMais = d;
                    mesMais = month;
                }
            } else if (month == 5){
                this.e++;
                if (e > qtdeMais) {
                    qtdeMais = e;
                    mesMais = month;
                }
            } else if (month == 6) {
                this.f++;
                if (f > qtdeMais) {
                    qtdeMais = f;
                    mesMais = month;
                }
            } else if (month == 7){
                this.g++;
                if (g > qtdeMais) {
                    qtdeMais = g;
                    mesMais = month;
                }
            } else if (month == 8){
                this.h++;
                if (h > qtdeMais) {
                    qtdeMais = h;
                    mesMais = month;
                }
            } else if (month == 9) {
                this.i++;
                if (i > qtdeMais) {
                    qtdeMais = i;
                    mesMais = month;
                }
            } else if (month == 10){
                this.j++;
                if (j > qtdeMais) {
                    qtdeMais = j;
                    mesMais = month;
                }
            } else if (month == 11){
                this.k++;
                if (k > qtdeMais) {
                    qtdeMais = k;
                    mesMais = month;
                }
            } else if (month == 12) {
                this.l++;
                if (l > qtdeMais) {
                    qtdeMais = l;
                    mesMais = month;
                }
            }
        }


        if (mesMais == 0)
            this.txt05.setText(R.string.no_data);
        else
            this.txt05.setText(getString(R.string.month_trouble) + " " + getMes(mesMais));

        mesMais = 0;
        qtdeMais = 0;
        a = 0;
        b = 0;
        c = 0;
        d = 0;
        e = 0;
        f = 0;
        g = 0;
        h = 0;
        i = 0;
        j = 0;
        k = 0;
        l = 0;

    }

    private String getMes(int mesMais) {
        if (Locale.getDefault().getDisplayLanguage().equals("português")) {
            switch (mesMais) {
                case 1:
                    return "Janeiro";
                case 2:
                    return "Fevereiro";
                case 3:
                    return "Março";
                case 4:
                    return "Abril";
                case 5:
                    return "Maio";
                case 6:
                    return "Junho";
                case 7:
                    return "Julho";
                case 8:
                    return "Agosto";
                case 9:
                    return "Setembro";
                case 10:
                    return "Outubro";
                case 11:
                    return "Novembro";
                case 12:
                    return "Dezembro";
            }
        } else {
            switch (mesMais) {
                case 1:
                    return "January";
                case 2:
                    return "February";
                case 3:
                    return "March";
                case 4:
                    return "April";
                case 5:
                    return "May";
                case 6:
                    return "June";
                case 7:
                    return "July";
                case 8:
                    return "August";
                case 9:
                    return "September";
                case 10:
                    return "October";
                case 11:
                    return "November";
                case 12:
                    return "December";
            }
        }
        return null;
    }

}
