package br.edu.iftm.pdm.prova;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

import br.edu.iftm.pdm.prova.data.DAOReport;
import br.edu.iftm.pdm.prova.data.DAOUser;
import br.edu.iftm.pdm.prova.data.DummyData;
import br.edu.iftm.pdm.prova.model.Report;
import br.edu.iftm.pdm.prova.view.ReportGenerateDummyDialogFragment;
import br.edu.iftm.pdm.prova.view.ReportRemoveDialogFragment;

public class MainActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener, ReportGenerateDummyDialogFragment.OnReportGenerateListener {

    private MenuItem addItem;
    private int serial = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickNewReport(View view) {

        if (DAOUser.isAnyoneLogged()) {
            Intent intent = new Intent(this, NewReportActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_load_dummy, menu);
        this.addItem = menu.findItem(R.id.actionAdd);
        this.addItem.setOnMenuItemClickListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    public void onClickListReports(View view) {
        Intent intent = new Intent(this, ListReportsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        ReportGenerateDummyDialogFragment crdf = new ReportGenerateDummyDialogFragment();
        crdf.show(getSupportFragmentManager(), "");
        return true;
    }

    @Override
    public void onReportGenerate() {
        this.serial = 0;
        DummyData.generate(DAOReport.getINSTANCE().getReports());
        for(Report report : DAOReport.getINSTANCE().getReports()) {
            report.setId(this.serial++);
        }

        Collections.sort(DAOReport.getINSTANCE().getReports());

        DAOReport.getINSTANCE().setSerial(serial);
    }

}
