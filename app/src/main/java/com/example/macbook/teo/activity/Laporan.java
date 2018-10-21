package com.example.macbook.teo.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macbook.teo.R;
import com.example.macbook.teo.model.ModelLokasiPematang;
import com.example.macbook.teo.response.ResponseLokasiPematang;
import com.example.macbook.teo.rest.ApiClient;
import com.example.macbook.teo.rest.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Laporan extends AppCompatActivity {
    Button simpan;
    EditText deskripsi;
    ApiInterface apiService;
    Context context;
    String nama,namapematang,lat,lng,des;
    private static final String TAG = "FormBendaharaActivity";

    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);
        final TextView namalengkap = findViewById(R.id.namalengkap);
        final TextView lokasi = findViewById(R.id.lokasi);
        final TextView tvlat = findViewById(R.id.tvlat);
        final TextView tvlng = findViewById(R.id.tvlng);
        deskripsi = findViewById(R.id.deskripsi);
        simpan = (Button) findViewById(R.id.simpan);
        final String ds = deskripsi.getText().toString();

        String id_lokasi = getIntent().getStringExtra("idlokasi");
        Toast.makeText(getApplicationContext(), "ID LOKASI PEMATANG " + id_lokasi, Toast.LENGTH_SHORT).show();
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        apiService.detail(id_lokasi).enqueue(new Callback<ResponseLokasiPematang>() {
            @Override
            public void onResponse(Call<ResponseLokasiPematang> call, Response<ResponseLokasiPematang> response) {
                System.out.println("Test Pengajuan" + response.toString());
                System.out.println("Test Pengajuan" + response.body().toString());
                if (response.isSuccessful()) {
                    List<ModelLokasiPematang> pengajuanKors = response.body().getData();
                    ModelLokasiPematang pengajuanKor = pengajuanKors.get(0);
                    namalengkap.setText(pengajuanKor.getNama());
                    lokasi.setText(pengajuanKor.getDaerah());
                    tvlat.setText(pengajuanKor.getLat());
                    tvlng.setText(pengajuanKor.getLng());
                    /*tvjudul.setText(pengajuanKor.getJudul());
                    tvPembimbing1.setText(pengajuanKor.getCalonPembimbing1());
                    tvpembimbing2.setText(pengajuanKor.getCalonPembimbing2());
                    tvreviewer1.setText(pengajuanKor.getReviewer1());
                    tvstatus.setText(pengajuanKor.getStatus());
                    tvreviewer2.setText(pengajuanKor.getReviewer2());*/
                    final String nama = namalengkap.getText().toString();
                    final String namapematang = namalengkap.getText().toString();
                    final String lat = tvlat.getText().toString();
                    final String lng = tvlng.getText().toString();

                    simpan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            simpanlaporan(nama, namapematang, lat, lng, ds);
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Tidak Terhubung Kedalam Server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLokasiPematang> call, Throwable t) {
                System.out.println("Response" + t.getLocalizedMessage());
            }
        });


    }

    private void simpanlaporan(String satu,  String dua,  String tiga,  String empat,  String lima) {
        Toast.makeText(getApplicationContext(), "Nama Pamatang " + satu + tiga, Toast.LENGTH_SHORT).show();
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        apiService.laporankan(satu, dua, tiga, empat, lima).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse: Berhasil Menambah Data" + response.body());
                System.out.println("status benar nggdak " + response.isSuccessful());
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Berhasil Melaporakan", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Tidak Berhasil Melaporakan", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(FormLaporan.this, "Gagal terhubung dengan server", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onResponse: tidak" + t.getLocalizedMessage());
//                Toast.makeText(getApplicationContext(), "onFailure" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
