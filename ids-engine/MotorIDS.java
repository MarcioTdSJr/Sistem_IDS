import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class MotorIDS {
    public static void main(String[] args) {
        int porta = 5000;
        int limitePacotesPorSegundo = 50; // Limite que define o ataque

        try (DatagramSocket socket = new DatagramSocket(porta)) {
            System.out.println("=========================================");
            System.out.println("🛡️ Motor IDS Iniciado com Sucesso!");
            System.out.println("📡 Monitorando anomalias na porta " + porta + "...");
            System.out.println("=========================================\n");

            byte[] buffer = new byte[1024];
            
            // Variáveis para controlar o tempo e a contagem
            long tempoJanela = System.currentTimeMillis();
            int contadorPacotes = 0;

            while (true) {
                DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);
                socket.receive(pacote);
                
                // Converte a mensagem recebida para texto
                String mensagemCAN = new String(pacote.getData(), 0, pacote.getLength());
                
                // Ignora a impressão de todo pacote normal para não poluir a tela
                contadorPacotes++;
                
                long tempoAtual = System.currentTimeMillis();
                
                // Se passou 1 segundo (1000 milissegundos) desde a última checagem...
                if (tempoAtual - tempoJanela >= 1000) {
                    
                    // Lógica de Detecção!
                    if (contadorPacotes > limitePacotesPorSegundo) {
                        System.out.println("🚨 [ALERTA CRÍTICO] Ataque DoS Detectado! Tráfego: " + contadorPacotes + " pacotes/s");
                        System.out.println("   -> Último payload suspeito: " + mensagemCAN);
                    } else {
                        System.out.println("✅ Status: Normal. Tráfego: " + contadorPacotes + " pacotes/s");
                    }
                    
                    // Zera o contador e reinicia o cronômetro para o próximo segundo
                    contadorPacotes = 0;
                    tempoJanela = tempoAtual;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao iniciar o IDS: " + e.getMessage());
        }
    }
}