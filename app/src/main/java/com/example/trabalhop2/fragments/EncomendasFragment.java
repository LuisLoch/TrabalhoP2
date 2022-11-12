package com.example.trabalhop2.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabalhop2.R;
import com.google.firebase.database.DatabaseReference;

public class EncomendasFragment extends Fragment {

    EditText etCliente, etEndereco, etFlor, etTipoFlor;
    TextView tvPago;
    Button btnSalvar, btnCancelar, btnPagoSim, btnPagoNao;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public EncomendasFragment() { }

    public static EncomendasFragment newInstance(String param1, String param2) {
        EncomendasFragment fragment = new EncomendasFragment();
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
        View view = inflater.inflate(R.layout.fragment_encomendas, container, false);

        etCliente = view.findViewById(R.id.idCliente_encomendasFragment);
        etEndereco = view.findViewById(R.id.idEndereco_encomendasFragment);
        etFlor = view.findViewById(R.id.idFlor_encomendasFragment);
        etTipoFlor = view.findViewById(R.id.idTipoFlor_encomendasFragment);
        tvPago = view.findViewById(R.id.idPago_encomendasFragment);

        btnSalvar = view.findViewById(R.id.idSalvar_encomendasFragment);
        btnCancelar = view.findViewById(R.id.idCancelar_encomendasFragment);
        btnPagoSim = view.findViewById(R.id.idSim_encomendasFragment);
        btnPagoNao = view.findViewById(R.id.idNao_encomendasFragment);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarEncomenda();
                cancelarEncomenda();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelarEncomenda();
            }
        });

        btnPagoSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sim();
            }
        });

        btnPagoNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nao();
            }
        });

        return view;
    }

    private void cancelarEncomenda() {
        etCliente.setText("");
        etEndereco.setText("");
        etFlor.setText("");
        etTipoFlor.setText("");
        tvPago.setText("");
    }

    private void salvarEncomenda(){
        try {

            Toast.makeText(getContext(), "Encomenda salva.", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getContext(), "Não foi possível salvar, preencha corretamente os campos e tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }

    private void sim(){ tvPago.setText("Sim"); }

    private void nao(){ tvPago.setText("Não"); }
}