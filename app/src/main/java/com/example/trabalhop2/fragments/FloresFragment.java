package com.example.trabalhop2.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalhop2.R;
import com.google.firebase.database.DatabaseReference;

public class FloresFragment extends Fragment {

    Button btnSalvar, btnCancelar;
    EditText etNomeFlor, etTipoFlor, etPrecoFlor;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FloresFragment() { }

    public static FloresFragment newInstance(String param1, String param2) {
        FloresFragment fragment = new FloresFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_flores, container, false);
        
        btnCancelar = view.findViewById(R.id.idCancelar_floresFragment);
        btnSalvar = view.findViewById(R.id.idSalvar_floresFragment);

        etNomeFlor = view.findViewById(R.id.idNomeDaFlor_floresFragment);
        etTipoFlor = view.findViewById(R.id.idTipo_floresFragment);
        etPrecoFlor = view.findViewById(R.id.idPreco_floresFragment);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarFlor();
                cancelarFlor();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelarFlor();
                Toast.makeText(getContext(), "Campos limpos.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void cancelarFlor() {
        etNomeFlor.setText("");
        etTipoFlor.setText("");
        etPrecoFlor.setText("");
    }

    private void salvarFlor(){
        try {

            Toast.makeText(getContext(), "Flor salva.", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getContext(), "Não foi possível salvar, preencha corretamente os campos e tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }
}