import can
import time
import socket # Adicionamos a biblioteca de rede

# Configura o Socket UDP (O nosso cabo virtual)
IP_LOCAL = "127.0.0.1"
PORTA_IDS = 5000
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

bus = can.interface.Bus(interface='virtual', channel='vcan0', receive_own_messages=True)

print("Iniciando a simulação do veículo (Pressione Ctrl+C para parar)...")

try:
    while True:
        # Criando o pacote CAN
        msg = can.Message(
            arbitration_id=0x123,
            data=[0x01, 0x05, 0xA2, 0x00, 0x00, 0x00, 0x00, 0x00],
            is_extended_id=False
        )
        
        bus.send(msg)
        
        # Transforma a mensagem em texto e envia para o Java via UDP
        mensagem_texto = str(msg)
        sock.sendto(mensagem_texto.encode(), (IP_LOCAL, PORTA_IDS))
        
        print(f"Mensagem enviada para o IDS: {msg}")
        time.sleep(1)

except KeyboardInterrupt:
    print("\nSimulação encerrada.")