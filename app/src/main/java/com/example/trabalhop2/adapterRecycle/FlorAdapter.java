package com.example.trabalhop2.adapterRecycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhop2.R;
import com.example.trabalhop2.models.Flor;

import java.text.DecimalFormat;
import java.util.List;

public class FlorAdapter extends RecyclerView.Adapter<FlorAdapter.FlorHolder> {
    List<Flor> dados;

    public FlorAdapter(List<Flor> dados){
        this.dados = dados;
    }

    @NonNull
    @Override
    public FlorAdapter.FlorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linhaflor, parent, false);
        return new FlorHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlorHolder florHolder, int position) {
        florHolder.nome.setText("Nome: "+dados.get(position).getNome());
        florHolder.tipo.setText("Tipo: "+dados.get(position).getTipo());
        DecimalFormat df = new DecimalFormat("#0.00");
        float valor;
        valor = Float.parseFloat(dados.get(position).getPreco().toString());
        valor = Float.parseFloat(df.format(valor));
        florHolder.preco.setText("R$ "+valor);
    }

    @Override
    public int getItemCount() { return dados.size(); }

    public class FlorHolder extends RecyclerView.ViewHolder{
        public TextView nome, tipo, preco;
        public FlorHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.idNome_linhaflor);
            tipo = itemView.findViewById(R.id.idTipo_linhaflor);
            preco = itemView.findViewById(R.id.idPreco_linhaflor);
        }
    }
}
