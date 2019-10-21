package br.edu.iftm.pdm.prova;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class ShowImageActivity extends Activity {


    ImageView imageview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_image);

        Intent intent = getIntent();
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("image");

        System.out.println("CONTEUDO = " + bitmap);

        imageview = (ImageView) findViewById(R.id.imgBtnPhoto);
        imageview.setImageBitmap(bitmap);

    }

}