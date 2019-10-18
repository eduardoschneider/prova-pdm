package br.edu.iftm.pdm.prova;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import br.edu.iftm.pdm.prova.data.DAOReport;
import br.edu.iftm.pdm.prova.model.Report;
import br.edu.iftm.pdm.prova.view.ReportAdapter;
import br.edu.iftm.pdm.prova.view.ReportRemoveDialogFragment;

public class ListReportsActivity extends AppCompatActivity
        implements ReportAdapter.OnContactClickListener,
        ReportRemoveDialogFragment.OnContactRemoveListener,
        MenuItem.OnMenuItemClickListener {

    private ArrayList<Report> reports;
    private RecyclerView rvContacts;
    private ReportAdapter reportAdapter;
    private static final int showContact = 1234;
    private MenuItem trashItem;
    private int nSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            this.nSelected = savedInstanceState.getInt("ListContactActivity.nSelected", 0);
        initialize();
    }

    private void initialize() {
        setContentView(R.layout.activity_list_reports);
        this.rvContacts = findViewById(R.id.rvContacts);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this);
        this.rvContacts.setLayoutManager(linearLayoutManager);
        this.rvContacts.setHasFixedSize(true);
        this.reports = DAOReport.getINSTANCE().getReports();
        this.reportAdapter = new ReportAdapter(this, this.reports);
        this.rvContacts.setAdapter(this.reportAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_contact, menu);
        this.trashItem = menu.findItem(R.id.actionDelete);
        this.trashItem.setVisible(this.nSelected > 0);
        this.trashItem.setOnMenuItemClickListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onContactClick(Report report) {
        Intent intent = new Intent(this, ShowReportActivity.class);
        intent.putExtra(ShowReportActivity.contactKey, report);
        startActivityForResult(intent, showContact);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        ReportRemoveDialogFragment crdf = new ReportRemoveDialogFragment();
        crdf.show(getSupportFragmentManager(), "");
        return true;
    }

    @Override
    public void onLongContactClick(Report report) {
        if (report.isSelected())
            this.nSelected++;
        else
            this.nSelected--;

        if (this.nSelected > 0)
            this.trashItem.setVisible(true);
        else
            this.trashItem.setVisible(false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("ListContactActivity.nSelected", this.nSelected);
    }

    @Override
    public void onContactRemove() {
        performDelete();
    }

    private void performDelete() {
        this.reportAdapter.removeSelectedContacts();
        this.trashItem.setVisible(false);
        this.nSelected = 0;
    }

    @Override
    public void onBackPressed() {
        if (this.nSelected > 0)
            cancelSelection();
        else
            super.onBackPressed();
    }

    public void cancelSelection() {
        DAOReport.getINSTANCE().unselectAllContacts();
        this.reportAdapter.notifyDataSetChanged();
        this.trashItem.setVisible(false);
        this.nSelected = 0;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        this.reportAdapter.notifyDataSetChanged();
        if (requestCode == showContact && resultCode == RESULT_OK && data != null) {
            deleteContact(data);
        }
    }

    private void deleteContact(@Nullable Intent data) {
        Report report = data.getParcelableExtra(ShowReportActivity.deletionContact);
        this.reportAdapter.removeContact(report);
        if (report.isSelected()) {
            this.nSelected--;
            if (this.nSelected > 0)
                this.trashItem.setVisible(true);
            else
                this.trashItem.setVisible(false);
        }
    }

}