package com.khurmainsutrya13gmail.pesankop1;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView ddaftar;
    EditText nama;
    RadioGroup pil;
    CheckBox krim, coklat, vanila;
    Button simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ddaftar = findViewById(R.id.ddaftar);
        nama = findViewById(R.id.editText);
        pil = findViewById(R.id.pilihan);
        krim = findViewById(R.id.checkBox);
        coklat = findViewById(R.id.checkBox2);
        vanila = findViewById(R.id.checkBox3);
        simpan = findViewById(R.id.button);

        simpan.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                String inputnama = String.valueOf(nama.getText().toString());

                int pilihan = pil.getCheckedRadioButtonId();
                RadioButton pil = findViewById(pilihan);
                String inputpilihan = String.valueOf(pil.getText().toString());

                if (!krim.isChecked() && !coklat.isChecked() && !vanila.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Topping Belum Dipilih", Toast.LENGTH_SHORT).show();
                } else {
                    String a = "";
                    if (krim.isChecked()) a += "Krim ";
                    String b = "";
                    if (coklat.isChecked()) b += "Coklat ";
                    String c = "";
                    if (vanila.isChecked()) {
                        c += "Vanila ";
                    }

                    ddaftar.setText("\n" +
                            "NAMA\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t: " + inputnama + "\n\n" +
                            "PILIHAN\t\t\t\t\t\t\t\t\t\t\t\t\t\t: " + inputpilihan + "\n\n" +
                            "TAMBAHKAN TOPPING\t: " + a + "" + b + "" + c);
                }
            }
        });
    }
}
