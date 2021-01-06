package com.example.demotruyen.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demotruyen.R;
import com.example.demotruyen.Service.APIService;
import com.example.demotruyen.Service.Dataservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKyActivity extends AppCompatActivity {

    EditText editTextdangkytk, editTextdangkymk;
    Button buttondangkydongy, buttondangkyhuy;

    String textdangkytaikhoan, textdangkymatkhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        AnhXa();
        IntentLogin();
        //Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        buttondangkydongy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textdangkytaikhoan = editTextdangkytk.getText().toString();
                textdangkymatkhau = editTextdangkymk.getText().toString();
                Dataservice dataservice = APIService.getService();
                Call<String> callback = dataservice.DangKyAcc(textdangkytaikhoan, textdangkymatkhau);
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String ketquadangky = response.body();
                        if(ketquadangky.equals("OK")) {
                            Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });

    }

    private void AnhXa() {
        editTextdangkytk = findViewById(R.id.edittextdangkytaikhoan);
        editTextdangkymk = findViewById(R.id.edittextdangkymatkhau);

        buttondangkydongy = findViewById(R.id.buttondangkydongy);
        buttondangkyhuy = findViewById(R.id.buttondangkyhuy);
    }

    private void IntentLogin() {
        buttondangkyhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKyActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}