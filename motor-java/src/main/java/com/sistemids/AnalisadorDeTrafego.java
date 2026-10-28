package com.sistemids;

public class AnalisadorDeTrafego {
    private int limitePacotesPorSegundo;
    private int contadorDePacotes;
    private long tempoInicial;

    public AnalisadorDeTrafego(int limitePacotesPorSegundo) {
        this.limitePacotesPorSegundo = limitePacotesPorSegundo;
        this.contadorDePacotes = 0;
        this.tempoInicial = System.currentTimeMillis();
    }

    public void analisarFrequencia(PacoteCAN pacote) {
        long agora = pacote.getTimestamp();
        // Se passou mais de 1 segundo (1000 ms), reinicia a janela de contagem
        if (agora - tempoInicial > 1000) {
            contadorDePacotes = 0;
            tempoInicial = agora;
        }
        contadorDePacotes++;
    }

    public boolean verificarAtaqueDoS() {
        return contadorDePacotes > limitePacotesPorSegundo;
    }
}