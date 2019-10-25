package br.edu.iftm.pdm.prova;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.iftm.pdm.prova.data.DAOUser;

public class LoginActivity extends AppCompatActivity {
    private EditText etxtEmail;
    private EditText etxtNome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        initalize();
    }

    private void initalize() {
        this.etxtEmail = findViewById(R.id.etxtEmail);
        this.etxtNome = findViewById(R.id.etxtNome);
    }

    public void onClickLogin(View view) {
        if (!etxtEmail.getText().toString().isEmpty() && !etxtNome.getText().toString().isEmpty()) {
            String nome = etxtNome.getText().toString();
            String email = etxtEmail.getText().toString();

            DAOUser.getINSTANCE(nome, email);
            Toast.makeText(this, getString(R.string.welcome), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, NewReportActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, getString(R.string.all_fields_must_be_filled), Toast.LENGTH_SHORT).show();
        }
    }
}
