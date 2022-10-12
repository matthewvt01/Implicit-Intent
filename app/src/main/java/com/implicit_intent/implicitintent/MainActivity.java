package com.implicit_intent.implicitintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et_url, et_lokasi, et_text;
    private Button btn_buka_website, btn_buka_lokasi, btn_bagikan_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_url = findViewById(R.id.et_url);
        et_lokasi = findViewById(R.id.et_lokasi);
        et_text = findViewById(R.id.et_text);

        btn_buka_website = findViewById(R.id.btn_buka_website);
        btn_buka_lokasi = findViewById(R.id.btn_buka_lokasi);
        btn_bagikan_text = findViewById(R.id.btn_bagikan_text);

        btn_buka_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = et_url.getText().toString();
                Uri uriUrl = Uri.parse(url);
                Intent bukaWebsite = new Intent(Intent.ACTION_VIEW, uriUrl);

                try{
                    startActivity(bukaWebsite);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Ada Kesalahan ! Cek kembali.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_buka_lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lokasi = et_lokasi.getText().toString();
                Uri uriLokasi = Uri.parse("geo:0,0?q=" + lokasi);
                Intent bukaLokasi = new Intent(Intent.ACTION_VIEW, uriLokasi);
                startActivity(bukaLokasi);
            }
        });

        btn_bagikan_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = et_text.getText().toString();
                String mimeType = "text/plain";
                new ShareCompat
                        .IntentBuilder(MainActivity.this)
                        .setType(mimeType)
                        .setChooserTitle("Bagikan Text Ini")
                        .setText(text)
                        .startChooser();
            }
        });
    }
}