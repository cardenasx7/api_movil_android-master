package com.example.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.app.remote.APIUtils;

public class SelectServiceActivity extends AppCompatActivity {

    Button btnPhp, btnJava, btnPython, btnNodeJs, btnCs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_service);
        chargeWidgets();
    }

    public void selectService(View view) {
        switch (view.getId()) {
            case R.id.btnPhpLumen:
                break;
            case R.id.btnJavaSpringBoot:
                break;
            case R.id.btnPythonFlask:
                break;
            //case R.id.btnCsAspNet:
            //    break;
            case R.id.btnNodejsExpress:
                break;
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void asd(View view) {

    }

    public void chargeWidgets() {
        btnPhp = findViewById(R.id.btnPhpLumen);
        btnJava = findViewById(R.id.btnJavaSpringBoot);
        btnPython = findViewById(R.id.btnPythonFlask);
        btnNodeJs = findViewById(R.id.btnNodejsExpress);
       // btnCs = findViewById(R.id.btnCsAspNet);
    }
}
