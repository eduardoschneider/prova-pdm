package br.edu.iftm.pdm.prova;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.iftm.pdm.prova.data.DAOReport;
import br.edu.iftm.pdm.prova.model.Report;

public class NewReportActivity extends AppCompatActivity {

    private ImageButton imgBtnPhoto;
    private EditText etxtDescricao;
    private EditText etxtData;
    private EditText etxtTipo;
    private Spinner dropdown;
    private Spinner dropdown2;
    private Bitmap profilePicture;
    private static final int REQUEST_CAMERA_CAPTURE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_report);
        initialize();
    }

    public void initialize() {
        this.etxtDescricao = findViewById(R.id.etxtDescricao);
        this.etxtData = findViewById(R.id.etxtData);
        this.imgBtnPhoto = findViewById(R.id.imgBtnPhoto);
        this.dropdown = findViewById(R.id.spinnerTipo);
        String[] items = new String[]{"Depredação", "Mal Funcionamento", "Uso Indevido/Ilegal"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        this.dropdown2 = findViewById(R.id.spinnerLocal);
        String[] items2 = new String[]{"A","B","C","D","E","F","G","H"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);
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
        saveContact(descricao, natureza, data, tipo);
    }

    public void saveContact(String descricao, String natureza, String data, String tipo) {
        if (!descricao.isEmpty() && !natureza.isEmpty() &&
                !data.isEmpty() && !tipo.isEmpty()) {
            Report report = new Report(descricao, natureza, data, tipo, this.profilePicture);
            DAOReport.getINSTANCE().addContact(report);
            Toast.makeText(this, getString(R.string.contact_created), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, getString(R.string.all_fields_must_be_filled), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CAMERA_CAPTURE && resultCode == RESULT_OK && data != null) {
            setContactPhoto(data);
        }
    }

    private void setContactPhoto(@Nullable Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle.containsKey("data")) {
            Object object = bundle.get("data");
            if (object instanceof Bitmap) {
                this.profilePicture = (Bitmap) object;
                this.imgBtnPhoto.setImageBitmap(this.profilePicture);
            }
        }
    }
}
