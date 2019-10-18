package br.edu.iftm.pdm.prova;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.iftm.pdm.prova.data.DAOReport;
import br.edu.iftm.pdm.prova.model.Report;
import br.edu.iftm.pdm.prova.view.ReportRemoveDialogFragment;

public class ShowReportActivity extends AppCompatActivity
    implements ReportRemoveDialogFragment.OnReportRemoveListener {

    public static final String contactKey = "ShowReportActvity.CONTACT";
    public static final String deletionReport = "ShowReportActvity.REMOVED";
    private TextView txtShowDescricao;
    private TextView txtShowNatureza;
    private TextView txtShowData;
    private TextView txtShowTipo;
    private ImageView imgPhoto;
    private Report report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_report);
        initialize();
    }

    private void initialize(){
        this.txtShowDescricao = findViewById(R.id.txtShowDescricao);
        this.txtShowNatureza = findViewById(R.id.txtShowNatureza);
        this.txtShowData = findViewById(R.id.txtShowData);
        this.txtShowTipo = findViewById(R.id.txtShowTipo);
        this.imgPhoto = findViewById(R.id.imgPhoto);
        this.report = getIntent().getParcelableExtra(contactKey);

        this.txtShowDescricao.setText(report.getDescricao());
        this.txtShowNatureza.setText(report.getNatureza());
        this.txtShowData.setText(report.getData());
        this.txtShowTipo.setText(report.getTipo());
        if(this.report.getProfilePicture() != null) {
            this.imgPhoto.setImageBitmap(this.report.getProfilePicture());
        }
    }

    public void onClickRemoveReport(View view) {
        ReportRemoveDialogFragment crdf = new ReportRemoveDialogFragment();
        crdf.show(getSupportFragmentManager(), "");
    }


    public void onClickSaveReport(View view) {
        for(Report report : DAOReport.getINSTANCE().getReports()){
            if (report.getId() == this.report.getId()){
                report.setDescricao(txtShowDescricao.getText().toString());
                report.setNatureza(txtShowNatureza.getText().toString());
                report.setData(txtShowData.getText().toString());
                report.setTipo(txtShowTipo.getText().toString());
            }
        }
        super.onBackPressed();
    }

    @Override
    public void onReportRemove() {
        Intent output = new Intent();
        output.putExtra(deletionReport, this.report);
        setResult(RESULT_OK, output);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
    }
}
