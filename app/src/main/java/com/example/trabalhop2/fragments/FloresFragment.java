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
import android.widget.Toast;

import com.example.trabalhop2.R;
import com.example.trabalhop2.adapterRecycle.FlorAdapter;
import com.example.trabalhop2.models.Flor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

public class FloresFragment extends Fragment {

    EditText etNomeFlor, etTipoFlor, etPrecoFlor;
    Button btnSalvar, btnCancelar;
    List<Flor> dados;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    FlorAdapter florAdapter;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


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

        recyclerView = view.findViewById(R.id.idRecyclerView_floresFragment);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        dados = new LinkedList();
        florAdapter = new FlorAdapter(dados);
        recyclerView.setAdapter(florAdapter);

        cancelarFlor();

        listarDados();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarFlor();
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
            Flor flor = new Flor();
            flor.setNome(etNomeFlor.getText().toString());
            flor.setTipo(etTipoFlor.getText().toString());
            flor.setPreco(Float.parseFloat(etPrecoFlor.getText().toString().replaceAll(",", ".")));
            if(flor.getNome().isEmpty() || flor.getTipo().isEmpty() || flor.getPreco().toString().isEmpty()){
                Toast.makeText(getContext(), "Preencha todos os campos e tente de novo.", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(), "Flor salva.", Toast.LENGTH_SHORT).show();
                databaseReference.child("flores").push().setValue(flor);
                cancelarFlor();
            }
        }catch (Exception e){
            Toast.makeText(getContext(), "Algo deu errado.", Toast.LENGTH_SHORT).show();
        }
    }

    private void listarDados(){
        DatabaseReference flores = databaseReference.child("flores");
        Query query = flores.orderByChild("nome");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    dados.clear();
                    for (DataSnapshot item : snapshot.getChildren()) {
                        Flor flor = item.getValue(Flor.class);
                        dados.add(flor);
                    }
                    florAdapter.notifyDataSetChanged();
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