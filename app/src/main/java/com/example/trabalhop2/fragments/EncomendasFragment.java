package com.example.trabalhop2.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabalhop2.R;
import com.example.trabalhop2.adapterRecycle.EncomendaAdapter;
import com.example.trabalhop2.models.Encomenda;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

public class EncomendasFragment extends Fragment {

    EditText etCliente, etEndereco, etFlor, etTipoFlor;
    TextView tvPago;
    Button btnSalvar, btnCancelar, btnPagoSim, btnPagoNao;
    List<Encomenda> dados;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    EncomendaAdapter encomendaAdapter;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

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

        btnSalvar = view.findViewById(R.id.idSalvar_encomendasFragment);
        btnCancelar = view.findViewById(R.id.idCancelar_encomendasFragment);
        btnPagoSim = view.findViewById(R.id.idSim_encomendasFragment);
        btnPagoNao = view.findViewById(R.id.idNao_encomendasFragment);

        etCliente = view.findViewById(R.id.idCliente_encomendasFragment);
        etEndereco = view.findViewById(R.id.idEndereco_encomendasFragment);
        etFlor = view.findViewById(R.id.idFlor_encomendasFragment);
        etTipoFlor = view.findViewById(R.id.idTipoFlor_encomendasFragment);
        tvPago = view.findViewById(R.id.idPago_encomendasFragment);

        recyclerView = view.findViewById(R.id.idRecyclerView_encomendasFragment);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        dados = new LinkedList();
        encomendaAdapter = new EncomendaAdapter(dados);
        recyclerView.setAdapter(encomendaAdapter);

        cancelarEncomenda();

        listarDados();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarEncomenda();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelarEncomenda();
                Toast.makeText(getContext(), "Campos limpos.", Toast.LENGTH_SHORT).show();
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
            Encomenda encomenda = new Encomenda();
            encomenda.setCliente(etCliente.getText().toString());
            encomenda.setEndereco(etEndereco.getText().toString());
            encomenda.setFlor(etFlor.getText().toString());
            encomenda.setTipoFlor(etTipoFlor.getText().toString());
            encomenda.setPago(tvPago.getText().toString());
            if(encomenda.getCliente().isEmpty() || encomenda.getEndereco().isEmpty() || encomenda.getFlor().isEmpty() || encomenda.getTipoFlor().isEmpty() || encomenda.getPago().isEmpty()){
                Toast.makeText(getContext(), "Preencha todos os campos e tente de novo.", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(), "Encomenda salva.", Toast.LENGTH_SHORT).show();
                databaseReference.child("encomendas").push().setValue(encomenda);
                cancelarEncomenda();
            }
        }catch (Exception e){
            Toast.makeText(getContext(), "Algo deu errado.", Toast.LENGTH_SHORT).show();
        }
    }

    private void sim(){ tvPago.setText("Sim"); }

    private void nao(){ tvPago.setText("Não"); }

    private void listarDados(){
        DatabaseReference encomendas = databaseReference.child("encomendas");
        Query query = encomendas.orderByKey();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    dados.clear();
                    for (DataSnapshot item : snapshot.getChildren()) {
                        Encomenda encomenda = item.getValue(Encomenda.class);
                        dados.add(encomenda);
                    }
                    encomendaAdapter.notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(getContext(), "Erro: " + e, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Erro: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}