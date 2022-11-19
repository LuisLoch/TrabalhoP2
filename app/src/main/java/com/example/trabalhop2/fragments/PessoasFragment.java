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
import com.example.trabalhop2.adapterRecycle.PessoaAdapter;
import com.example.trabalhop2.models.Pessoa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

public class PessoasFragment extends Fragment {

    EditText etNomePessoa, etEnderecoPessoa, etCpfPessoa;
    Button btnSalvar, btnCancelar;
    List<Pessoa> dados;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    PessoaAdapter pessoaAdapter;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PessoasFragment() { }

    public static PessoasFragment newInstance(String param1, String param2) {
        PessoasFragment fragment = new PessoasFragment();
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
        View view = inflater.inflate(R.layout.fragment_pessoas, container, false);

        btnSalvar = view.findViewById(R.id.idSalvar_pessoasFragment);
        btnCancelar = view.findViewById(R.id.idCancelar_pessoasFragment);

        etNomePessoa = view.findViewById(R.id.idNome_pessoasFragment);
        etEnderecoPessoa = view.findViewById(R.id.idEndereco_pessoasFragment);
        etCpfPessoa = view.findViewById(R.id.idCpf_pessoasFragment);

        recyclerView = view.findViewById(R.id.idRecyclerView_pessoasFragment);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        dados = new LinkedList();
        pessoaAdapter = new PessoaAdapter(dados);
        recyclerView.setAdapter(pessoaAdapter);

        cancelarPessoa();

        listarDados();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarPessoa();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelarPessoa();
                Toast.makeText(getContext(), "Campos limpos.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void cancelarPessoa() {
        etNomePessoa.setText("");
        etEnderecoPessoa.setText("");
        etCpfPessoa.setText("");
    }

    private void salvarPessoa(){
        try {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(etNomePessoa.getText().toString());
            pessoa.setCpf(etCpfPessoa.getText().toString());
            pessoa.setEndereco(etEnderecoPessoa.getText().toString());
            if(pessoa.getNome().isEmpty() || pessoa.getCpf().isEmpty() || pessoa.getEndereco().isEmpty()){
                Toast.makeText(getContext(), "Preencha todos os campos e tente de novo.", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(), "Cliente salvo.", Toast.LENGTH_SHORT).show();
                databaseReference.child("pessoas").push().setValue(pessoa);
                cancelarPessoa();
            }
        }catch (Exception e){
            Toast.makeText(getContext(), "Algo deu errado.", Toast.LENGTH_SHORT).show();
        }
    }

    private void listarDados(){
        DatabaseReference pessoas = databaseReference.child("pessoas");
        Query query = pessoas.orderByChild("nome");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    dados.clear();
                    for (DataSnapshot item : snapshot.getChildren()) {
                        Pessoa pessoa = item.getValue(Pessoa.class);
                        dados.add(pessoa);
                    }
                    pessoaAdapter.notifyDataSetChanged();
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