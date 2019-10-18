package br.edu.iftm.pdm.prova;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import br.edu.iftm.pdm.prova.data.DAOUser;

public class LoginActivity extends AppCompatActivity {
    private EditText etxtEmail;
    private EditText etxtNome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        initalize();
    }

    private void initalize() {
        this.etxtEmail = findViewById(R.id.etxtEmail);
        this.etxtNome = findViewById(R.id.etxtNome);
    }

    public void onClickLogin(View view) {
        String nome = etxtNome.getText().toString();
        String email = etxtEmail.getText().toString();

        DAOUser.getINSTANCE(nome, email);
        Intent intent = new Intent(this, NewReportActivity.class);
        startActivity(intent);
    }
}
