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

public class PessoasFragment extends Fragment {

    EditText etNomePessoa, etEnderecoPessoa, etCpfPessoa;
    Button btnSalvar, btnCancelar;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;

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

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarPessoa();
                cancelarPessoa();
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

            Toast.makeText(getContext(), "Cliente salvo.", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getContext(), "Não foi possível salvar, preencha corretamente os campos e tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }
}