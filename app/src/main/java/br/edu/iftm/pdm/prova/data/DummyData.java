package br.edu.iftm.pdm.prova.data;

import java.util.ArrayList;
import br.edu.iftm.pdm.prova.model.Report;

class DummyData {

    protected static void generate(ArrayList<Report> reports) {
        Report report;
        report = new Report("Odell", "Uso Indevido/Ilegal", "21/04/1998", "A");
        reports.add(report);
    }
}
