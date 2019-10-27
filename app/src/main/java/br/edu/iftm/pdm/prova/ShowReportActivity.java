package br.edu.iftm.pdm.prova;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import br.edu.iftm.pdm.prova.data.DAOReport;
import br.edu.iftm.pdm.prova.model.Report;
import br.edu.iftm.pdm.prova.view.ReportRemoveDialogFragment;
import br.edu.iftm.pdm.prova.view.ReportShowImageDialogFragment;

public class ShowReportActivity extends AppCompatActivity
    implements ReportRemoveDialogFragment.OnReportRemoveListener {

    public static final String reportKey = "ShowReportActvity.CONTACT";
    public static final String deletionReport = "ShowReportActvity.REMOVED";
    private TextView txtShowDescricao;
    private ImageView imgPhoto;
    private ImageView imgPhoto2;
    private ImageView imgPhoto3;
    private EditText etxtData;
    private EditText etxtHora;
    private Button btnPickDate;
    private Button btnPickHour;
    private Report report;
    private Spinner dropdown;
    private Spinner dropdown2;
    private Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_show_report);
        initialize();
    }

    private void initialize(){
        this.txtShowDescricao = findViewById(R.id.txtShowDescricao);
        this.dropdown = findViewById(R.id.spinnerTipo);
        this.dropdown2 = findViewById(R.id.spinnerLocal);

        String[] items = new String[]{"Depredação", "Mal Funcionamento", "Uso Indevido/Ilegal"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, items);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        String[] items2 = new String[]{"A", "B", "C", "D", "E", "F","G", "H"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.spinner_item, items2);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        dropdown2.setAdapter(adapter2);

        this.etxtData = findViewById(R.id.etxtData);
        this.btnPickDate = findViewById(R.id.btnPickDate);
        this.etxtHora = findViewById(R.id.etxtHour);
        this.btnPickHour = findViewById(R.id.btnPickHour);
        this.imgPhoto = findViewById(R.id.imgPhoto);
        this.imgPhoto2 = findViewById(R.id.imgPhoto2);
        this.imgPhoto3 = findViewById(R.id.imgPhoto3);
        this.report = getIntent().getParcelableExtra(reportKey);
        this.txtShowDescricao.setText(report.getDescricao());
        switch (report.getNatureza()){
            case "Depredação": { this.dropdown.setSelection(0); break;}
            case "Mal Funcionamento": { this.dropdown.setSelection(1); break;}
            case "Uso Indevido/Ilegal": { this.dropdown.setSelection(2); break;}
        }

        switch (report.getTipo()){
            case "A": { this.dropdown2.setSelection(0); break;}
            case "B": { this.dropdown2.setSelection(1); break;}
            case "C": { this.dropdown2.setSelection(2); break;}
            case "D": { this.dropdown2.setSelection(3); break;}
            case "E": { this.dropdown2.setSelection(4); break;}
            case "F": { this.dropdown2.setSelection(5); break;}
            case "G": { this.dropdown2.setSelection(6); break;}
            case "H": { this.dropdown2.setSelection(7); break;}
        }
        this.etxtData.setText(report.getData());
        this.etxtHora.setText(report.getHora());
        if(this.report.getReportPhoto1() != null) {
            this.imgPhoto.setImageBitmap(this.report.getReportPhoto1());
        }
        if(this.report.getReportPhoto2() != null) {
            this.imgPhoto2.setImageBitmap(this.report.getReportPhoto2());
        }
        if(this.report.getReportPhoto3() != null) {
            this.imgPhoto3.setImageBitmap(this.report.getReportPhoto3());
        }

        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        btnPickDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(ShowReportActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnPickHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ShowReportActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if (selectedHour < 10) {
                            if (selectedMinute < 10)
                                etxtHora.setText( "0" + selectedHour + ":" + "0" + selectedMinute);
                            else
                                etxtHora.setText( "0" + selectedHour + ":" + selectedMinute);
                        } else {
                            if (selectedMinute < 10)
                                etxtHora.setText(selectedHour + ":" + "0" + selectedMinute);
                            else
                                etxtHora.setText(selectedHour + ":" + selectedMinute);
                        }

                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
    }

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));

        etxtData.setText(sdf.format(myCalendar.getTime()));
    }

    public void onClickRemoveReport(View view) {
        ReportRemoveDialogFragment crdf = new ReportRemoveDialogFragment();
        crdf.show(getSupportFragmentManager(), "");
    }

    public void onClickShowImage(View view){
        Bitmap foto = null;
        switch (view.getTag().toString()){
            case "image1":{foto = this.report.getReportPhoto1(); break;}
            case "image2":{foto = this.report.getReportPhoto2(); break;}
            case "image3":{foto = this.report.getReportPhoto3(); break;}
        }

        if (foto != null) {
            Intent intent = new Intent(this, ShowImageActivity.class);
            intent.putExtra("image", foto);
            startActivity(intent);
        } else {
            ReportShowImageDialogFragment crdf = new ReportShowImageDialogFragment();
            crdf.show(getSupportFragmentManager(), "");
        }
    }

    public void onClickSaveReport(View view) {
        for(Report report : DAOReport.getINSTANCE().getReports()){
            if (report.getId() == this.report.getId()){
                report.setDescricao(txtShowDescricao.getText().toString());
                report.setNatureza(this.dropdown.getSelectedItem().toString());
                report.setData(etxtData.getText().toString());
                report.setHora(etxtHora.getText().toString());
                report.setTipo(this.dropdown2.getSelectedItem().toString());
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
