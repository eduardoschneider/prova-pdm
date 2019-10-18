package br.edu.iftm.pdm.prova.data;

import java.util.ArrayList;
import java.util.Collections;
import br.edu.iftm.pdm.prova.model.Report;

public class DAOReport {
    private static DAOReport INSTANCE;
    private ArrayList<Report> reports;
    private long serial;

    private DAOReport() {
        this.reports = new ArrayList<>();
        this.serial = 0;
        DummyData.generate(this.reports);
        for(Report report : this.reports) {
            report.setId(this.serial++);
        }
        Collections.sort(this.reports);
    }

    public static DAOReport getINSTANCE() {
        if(INSTANCE == null)
            INSTANCE = new DAOReport();
        return INSTANCE;
    }

    public void addContact(Report report) {
        report.setId(this.serial++);
        this.reports.add(report);
        Collections.sort(this.reports);
    }

    public void unselectAllContacts() {
        for (Report report : this.reports) {
            report.setSelected(false);
        }
    }

    public ArrayList<Report> getReports()
    {
        return this.reports;
    }
}
