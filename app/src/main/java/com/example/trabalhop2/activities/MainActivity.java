package com.example.trabalhop2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.trabalhop2.R;
import com.example.trabalhop2.fragments.EncomendasFragment;
import com.example.trabalhop2.fragments.FloresFragment;
import com.example.trabalhop2.fragments.PessoasFragment;

public class MainActivity extends AppCompatActivity {
    Button btnFlores, btnClientes, btnEncomendas;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFlores = findViewById(R.id.idBtnFlores_main);
        btnClientes = findViewById(R.id.idBtnClientes_main);
        btnEncomendas = findViewById(R.id.idBtnEncomendas_main);
        frameLayout = findViewById(R.id.idFrame_main);

        btnFlores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragmentFlores(view);
            }
        });
        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragmentClientes(view);
            }
        });
        btnEncomendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragmentEncomendas(view);
            }
        });
    }

    public void setFragmentFlores(View view){
        getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), new FloresFragment()).commit();
    }

    public void setFragmentClientes(View view){
        getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), new PessoasFragment()).commit();
    }

    public void setFragmentEncomendas(View view){
        getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(), new EncomendasFragment()).commit();
    }
}