package com.example.trabalhop2.models;

public class Encomenda {
    String cliente, pago, flor, tipoFlor;
    Endereco endereco;

    public String getCliente() { return cliente; }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getFlor() { return flor; }

    public void setFlor(String flor) { this.flor = flor; }

    public String getTipoFlor() { return tipoFlor; }

    public void setTipoFlor(String tipoFlor) { this.tipoFlor = tipoFlor; }
}
