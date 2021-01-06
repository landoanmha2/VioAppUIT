package com.example.demotruyen.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import com.example.demotruyen.Model.Truyen;
import com.example.demotruyen.R;
import com.example.demotruyen.Service.APIService;
import com.example.demotruyen.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocChuongActivity3 extends AppCompatActivity {

    //Khai báo biến
    Truyen truyen;

    Toolbar toolbarchuong;
    TextView textViewnoidungchuong;
    ImageView imageViewSetting;

    Button buttonMono, buttonSerif, buttonCasual;
    SeekBar seekBarSize;
    int seekBarValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_chuong3);

        anhXa();
        GetDataFromInten();
        LoadChuong(truyen.getLinkTruyen()); //Gán dữ liệu chương vào
        //Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hàm quay về
        setSupportActionBar(toolbarchuong);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(truyen.getTenChuong());
        toolbarchuong.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        settingFontAndSize();
    }

    private void settingFontAndSize() {
        imageViewSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DocChuongActivity3.this, "Bạn đã click", Toast.LENGTH_SHORT).show();
                Dialog dialogsetting = new Dialog(DocChuongActivity3.this);
                dialogsetting.setTitle("Tùy chỉnh cấu hình");
                dialogsetting.setCancelable(true);
                dialogsetting.setContentView(R.layout.dialog_setting);

                buttonMono = dialogsetting.findViewById(R.id.buttonMonospace);
                buttonSerif = dialogsetting.findViewById(R.id.buttonSerif);
                buttonCasual = dialogsetting.findViewById(R.id.buttonCasual);
                seekBarSize = dialogsetting.findViewById(R.id.seekBarSize);

                btnGabriela();
                btnSerif();
                btnCasual();
                changeSize();

                Window window = dialogsetting.getWindow();
                window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                dialogsetting.show();

            }
        });
    }

    private void btnGabriela() {
        buttonMono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface typefaceMono = ResourcesCompat.getFont(DocChuongActivity3.this, R.font.gabriela);
                textViewnoidungchuong.setTypeface(typefaceMono);
            }
        });
    }

    private void btnSerif() {
        buttonSerif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface typefaceSerif = ResourcesCompat.getFont(DocChuongActivity3.this, R.font.serifcomic);
                textViewnoidungchuong.setTypeface(typefaceSerif);
            }
        });
    }

    private void btnCasual() {
        buttonCasual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface typefaceCasual = ResourcesCompat.getFont(DocChuongActivity3.this, R.font.doncasual);
                textViewnoidungchuong.setTypeface(typefaceCasual);
            }
        });
    }

    private void changeSize() {
        seekBarSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewnoidungchuong.setTextSize(seekBarValue);
            }
        });
    }

    private void LoadChuong(String linkchuong) {
        this.textViewnoidungchuong.setText(linkchuong);
    }

    private void GetDataFromInten() {
        Intent intent = getIntent();
        //mangchuongacti3.clear();
        if (intent != null) {
            if (intent.hasExtra("docchuong")) {
                truyen = (Truyen) intent.getSerializableExtra("docchuong");
                Toast.makeText(this, truyen.getTenTruyen(), Toast.LENGTH_SHORT).show();
                //mangchuongacti3.add(truyen);
            }
        }
    }
    private void anhXa() {
        toolbarchuong = findViewById(R.id.toolbarchuong);
        textViewnoidungchuong = findViewById(R.id.textviewnoidungchuong);
        imageViewSetting = (ImageView) findViewById(R.id.imageViewSetting);
    }
}

