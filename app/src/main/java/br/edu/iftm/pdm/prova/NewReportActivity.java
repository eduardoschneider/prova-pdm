package br.edu.iftm.pdm.prova;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import br.edu.iftm.pdm.prova.data.DAOReport;
import br.edu.iftm.pdm.prova.model.Report;

public class NewReportActivity extends AppCompatActivity {

    private ImageButton imgBtnPhoto;
    private ImageButton imgBtnPhoto2;
    private ImageButton imgBtnPhoto3;
    private EditText etxtDescricao;
    private EditText etxtData;
    private Button btnPickDate;
    private Spinner dropdown;
    private Spinner dropdown2;
    private Bitmap reportPhoto1;
    private Bitmap reportPhoto2;
    private Bitmap reportPhoto3;
    private int qualFoto;
    private Calendar myCalendar;

    private static final int REQUEST_CAMERA_CAPTURE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_new_report);
        initialize();
    }

    public void initialize() {
        this.etxtDescricao = findViewById(R.id.etxtDescricao);
        this.etxtData = findViewById(R.id.etxtData);
        this.imgBtnPhoto = findViewById(R.id.imgBtnPhoto);
        this.imgBtnPhoto2 = findViewById(R.id.imgBtnPhoto2);
        this.imgBtnPhoto3 = findViewById(R.id.imgBtnPhoto3);
        this.btnPickDate = findViewById(R.id.btnPickDate);

        this.dropdown = findViewById(R.id.spinnerTipo);
        String[] items = new String[]{getString(R.string.option_1), getString(R.string.option_2), getString(R.string.option_3)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, items);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        this.dropdown2 = findViewById(R.id.spinnerLocal);
        String[] items2 = new String[]{"A","B","C","D","E","F","G","H"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.spinner_item, items2);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        dropdown2.setAdapter(adapter2);

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
                new DatePickerDialog(NewReportActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        this.qualFoto = 0;
    }

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));

        etxtData.setText(sdf.format(myCalendar.getTime()));
    }

    public void onClickTakePic(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CAMERA_CAPTURE);
        }
    }

    public void onClickSave(View view) {
        String descricao = this.etxtDescricao.getText().toString();
        String natureza = this.dropdown.getSelectedItem().toString();
        String data = this.etxtData.getText().toString();
        String tipo = this.dropdown2.getSelectedItem().toString();
        saveReport(descricao, natureza, data, tipo);
    }

    public void saveReport(String descricao, String natureza, String data, String tipo) {
        if (!descricao.isEmpty() && !natureza.isEmpty() &&
                !data.isEmpty() && !tipo.isEmpty()) {
            Report report = new Report(descricao, natureza, data, tipo, this.reportPhoto1, this.reportPhoto2, this.reportPhoto3);
            DAOReport.getINSTANCE().addReport(report);
            Toast.makeText(this, getString(R.string.report_created), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, getString(R.string.all_fields_must_be_filled), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CAMERA_CAPTURE && resultCode == RESULT_OK && data != null) {
            setReportPhoto(data);
        }
    }

    private void setReportPhoto(@Nullable Intent data) {
        Bundle bundle = data.getExtras();
            Object object = bundle.get("data");
            if (object instanceof Bitmap) {
                switch(this.qualFoto){
                    case 0: this.reportPhoto1 = (Bitmap) object; this.imgBtnPhoto.setImageBitmap(this.reportPhoto1); break;
                    case 1: this.reportPhoto2 = (Bitmap) object; this.imgBtnPhoto2.setImageBitmap(this.reportPhoto2); break;
                    case 2: this.reportPhoto3 = (Bitmap) object; this.imgBtnPhoto3.setImageBitmap(this.reportPhoto3); break;
                }
                this.qualFoto++;
                if (this.qualFoto == 3) this.qualFoto = 0;
            }

    }
}
