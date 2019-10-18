package br.edu.iftm.pdm.prova.data;

import java.util.ArrayList;
import br.edu.iftm.pdm.prova.model.Report;

class DummyData {

    protected static void generate(ArrayList<Report> reports) {
        Report report;
        report = new Report("Odell", "Sawer", "(467) 3054567", "osawer0@yahoo.co.jp");
        reports.add(report);
        report = new Report("Ebba", "Malkie", "(694) 3488687", "emalkie1@issuu.com");
        reports.add(report);
        report = new Report("Eadmund", "Rohlfs", "(940) 4047958", "erohlfs2@diigo.com");
        reports.add(report);
        report = new Report("Theadora", "Shillito", "(320) 5685423", "tshillito3@npr.org");
        reports.add(report);
        report = new Report("Lindsy", "Markushkin", "(483) 6718371", "lmarkushkin4@weebly.com");
        reports.add(report);
    }
}
