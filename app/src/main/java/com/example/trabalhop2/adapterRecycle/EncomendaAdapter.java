package com.example.trabalhop2.adapterRecycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhop2.R;
import com.example.trabalhop2.models.Encomenda;

import java.util.List;

public class EncomendaAdapter extends RecyclerView.Adapter<EncomendaAdapter.EncomendaHolder> {
    List<Encomenda> dados;

    public EncomendaAdapter(List<Encomenda> dados){
        this.dados = dados;
    }

    @NonNull
    @Override
    public EncomendaAdapter.EncomendaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linhaencomenda, parent, false);
        return new EncomendaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EncomendaHolder encomendaHolder, int position) {
            encomendaHolder.cliente.setText("Cliente: "+dados.get(position).getCliente());
            encomendaHolder.endereco.setText(dados.get(position).getEndereco().getLogradouro() + ", " + dados.get(position).getEndereco().getBairro() + ", " + dados.get(position).getEndereco().getComplemento());
            encomendaHolder.flor.setText("Flor: "+dados.get(position).getFlor());
            encomendaHolder.tipoFlor.setText("Espécie: "+dados.get(position).getTipoFlor());
            encomendaHolder.pago.setText("Pago: "+dados.get(position).getPago());
    }

    @Override
    public int getItemCount() { return dados.size(); }

    public class EncomendaHolder extends RecyclerView.ViewHolder{
        public TextView cliente, endereco, pago, flor, tipoFlor;
        public EncomendaHolder(@NonNull View itemView) {
            super(itemView);
            cliente = itemView.findViewById(R.id.idCliente_linhaencomenda);
            endereco = itemView.findViewById(R.id.idEndereco_linhaencomenda);
            pago = itemView.findViewById(R.id.idPago_linhaencomenda);
            flor = itemView.findViewById(R.id.idNomeFlor_linhaencomenda);
            tipoFlor = itemView.findViewById(R.id.idTipoFlor_linhaencomenda);
        }
    }
}
