package com.sistemids;

public class PacoteCAN {
    private String idCan;
    private String dados;
    private long timestamp;

    public PacoteCAN(String idCan, String dados, long timestamp) {
        this.idCan = idCan;
        this.dados = dados;
        this.timestamp = timestamp;
    }

    public String getIdentificador() {
        return this.idCan;
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}