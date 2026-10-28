package com.sistemids;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnalisadorDeTrafegoTest {

    @Test
    public void devePermitirTrafegoNormalAbaixoDoLimite() {
        // Define um limite seguro de 10 pacotes por segundo
        AnalisadorDeTrafego analisador = new AnalisadorDeTrafego(10);
        long tempoAtual = System.currentTimeMillis();

        // Simula o envio de apenas 5 pacotes no mesmo segundo (Tráfego Padrão)
        for (int i = 0; i < 5; i++) {
            PacoteCAN pacote = new PacoteCAN("0x123", "DADOS_NORMAIS", tempoAtual);
            analisador.analisarFrequencia(pacote);
        }

        // Verifica se o sistema NÃO acusou ataque falso (deve retornar false)
        assertFalse(analisador.verificarAtaqueDoS(), "Tráfego normal não deve disparar alerta DoS");
    }

    @Test
    public void deveDetectarAtaqueDoSQuandoUltrapassarLimite() {
        // Define um limite seguro de 10 pacotes por segundo
        AnalisadorDeTrafego analisador = new AnalisadorDeTrafego(10);
        long tempoAtual = System.currentTimeMillis();

        // Simula um ataque injetando 15 pacotes no mesmo segundo (Acima do limite)
        for (int i = 0; i < 15; i++) {
            PacoteCAN pacoteMalicioso = new PacoteCAN("0x000", "FLOOD_DOS", tempoAtual);
            analisador.analisarFrequencia(pacoteMalicioso);
        }

        // Verifica se o IDS detectou o ataque (deve retornar true)
        assertTrue(analisador.verificarAtaqueDoS(), "O IDS deveria ter detectado o ataque DoS!");
    }
}