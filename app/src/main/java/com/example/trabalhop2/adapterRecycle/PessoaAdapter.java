package com.example.trabalhop2.adapterRecycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhop2.R;
import com.example.trabalhop2.models.Pessoa;

import java.util.List;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.PessoaHolder> {

    List<Pessoa> dados;

    public PessoaAdapter(List<Pessoa> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public PessoaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linhapessoa, parent, false);
        return new PessoaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PessoaHolder pessoaHolder, int position) {
        pessoaHolder.nome.setText(dados.get(position).getNome());
        pessoaHolder.cpf.setText(dados.get(position).getCpf().toString());
        pessoaHolder.endereco.setText(dados.get(position).getEndereco());
    }

    @Override
    public int getItemCount() { return dados.size(); }

    public class PessoaHolder extends RecyclerView.ViewHolder {
        public TextView nome, cpf, endereco;
        public PessoaHolder(@NonNull View itemView){
            super((itemView));
            nome = itemView.findViewById(R.id.idNome_linhapessoa);
            cpf = itemView.findViewById(R.id.idCpf_linhapessoa);
            endereco = itemView.findViewById(R.id.idEndereco_linhapessoa);
        }
    }
}
