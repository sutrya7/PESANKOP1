package com.khurmainsutrya13gmail.pesankop1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TugasParsing extends AppCompatActivity {
    private static final String urlix= "https://cumabelajar.com/latihan/datawayang.json";
    private OkHttpClient client = new OkHttpClient();
    private String vnama,valamat,vfoto,vhp,vrmh;
    TextView tnama;
    TextView talamat;
    TextView tfoto;
    TextView thp;
    TextView trumah;

    Button arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tnama= (TextView) findViewById(R.id.id_nama);
        talamat = (TextView) findViewById(R.id.id_alamat);
        tfoto  = (TextView) findViewById(R.id.id_url);
        thp = (TextView) findViewById(R.id.id_hp);
        trumah = (TextView) findViewById(R.id.id_rmh);
        arr=(Button) findViewById(R.id.btArray);
    }
    public void ambilArray(View v){
        ambiljson();
    }


    private void ambilObject(){

    }

    private void ambiljson(){

        Request request = new Request.Builder()
                .url(urlix)
                .cacheControl(new CacheControl.Builder().noCache().build())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("ON FAILURE", e.getStackTrace().toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                if (!response.isSuccessful()) {

                    Log.d("ON RESPON ERROR", String.valueOf(response));
                    throw new IOException("Unexpected code " +  response);
                } else {
                    final String hasil =response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            parsingarray(hasil);
                        }
                    });

                }
            };
        });

    }

    private void parsingarray(String datajson){
        try {

            JSONObject jsonObj = new JSONObject(datajson); //ambil object json
            JSONObject jsonpandawa = jsonObj.getJSONObject("pandawa"); //ambil obj pandawa
            JSONArray anggota = jsonpandawa.getJSONArray("anggota"); //ambil array anggota
            for (int i = 0; i < anggota.length(); i++) {
                JSONObject jsonobject = anggota.getJSONObject(i);
                vnama = jsonobject.getString("nama");
                valamat = jsonobject.getString("alamat");
                vfoto = jsonobject.getString("foto");
                JSONObject  kontak = jsonobject.getJSONObject("tlp"); //ambil array tlp
                vhp=kontak.getString("hp");
                vrmh=kontak.getString("rumah");
            }
            tnama.setText(vnama);
            talamat.setText(valamat);
            tfoto.setText(vfoto);
            thp.setText(vhp);
            trumah.setText(vrmh);
        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + datajson + "\"");
        }
    }
}

    public void ambilArray(View view) {
    }
}
