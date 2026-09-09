public class TesteIDS {
    public static void main(String[] args) {
        System.out.println("Iniciando bateria de testes do IDS...");
        int limiteDoS = 50;

        // Cenario 1: Tráfego Normal (15 pacotes)
        boolean trancadoNormal = detectarAtaque(15, limiteDoS);
        if (trancadoNormal) {
            throw new RuntimeException("FALHA: Tráfego normal foi classificado como ataque!");
        }
        System.out.println("✅ Teste 1 (Tráfego Normal): Passou");

        // Cenario 2: Ataque DoS (85.000 pacotes)
        boolean trancadoAtaque = detectarAtaque(85000, limiteDoS);
        if (!trancadoAtaque) {
            throw new RuntimeException("FALHA: Ataque DoS massivo não foi detectado!");
        }
        System.out.println("✅ Teste 2 (Ataque DoS): Passou");

        System.out.println("🎉 Todos os testes passaram com sucesso!");
    }

    // A lógica de negócio isolada para podermos testar
    public static boolean detectarAtaque(int quantidadePacotes, int limite) {
        return quantidadePacotes > limite;
    }
}