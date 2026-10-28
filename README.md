# 🛡️ Sistem_IDS: Sistema de Detecção de Intrusões Automotivas
[![Pipeline CI - Sistem_IDS](https://github.com/MarcioTdSJr/Sistem_IDS/actions/workflows/ci.yml/badge.svg)](https://github.com/MarcioTdSJr/Sistem_IDS/actions/workflows/ci.yml)

Projeto acadêmico desenvolvido para o curso de Ciência da Computação do CEUB, focando na segurança cibernética de redes veiculares (barramento CAN) e na aplicação de práticas de DevOps (Avaliação A1).

## 📌 Sobre o Projeto
O Sistem_IDS é uma prova de conceito que simula a comunicação interna de um veículo e monitora a rede em tempo real para detectar anomalias. O foco atual do motor de detecção é a identificação de ataques de **Negativa de Serviço (DoS - Denial of Service)**, interceptando picos volumétricos de pacotes injetados na rede.

## 🏗️ Arquitetura e Tecnologias
O sistema foi desenhado com uma arquitetura distribuída e executado em ambiente Windows, utilizando comunicação via Sockets UDP para simular o tráfego de rede:
*   **Java 17 (Motor IDS):** Responsável por escutar a porta de rede, calcular a volumetria de tráfego por segundo e disparar alertas críticos.
*   **Python 3 (Simulador):** Utiliza a biblioteca `python-can` para gerar frames de telemetria automotiva e simular o tráfego limpo.
*   **DevOps & CI/CD:** Repositório estruturado no padrão Trunk-Based (branch `main`). Utiliza **GitHub Actions** para compilação automatizada e execução de testes unitários a cada novo commit.

## 🚀 Como Executar a Simulação Local

Para ver o sistema de defesa em ação, é necessário executar os três componentes simultaneamente em terminais separados:

**1. Iniciar o Motor de Detecção (O Guarda)**
Navegue até a pasta `ids-engine`, compile e execute o código Java:
```bash
javac MotorIDS.java
java MotorIDS

2. Iniciar o Tráfego do Veículo (O Carro)
Em um novo terminal, inicie o tráfego limpo (1 pacote/segundo):
python trafic-sim/traffic-sim.py

3. Injetar o Ataque DoS (O Hacker)
Em um terceiro terminal, dispare o script malicioso para sobrecarregar a rede virtual:
python trafic-sim/ataque-dos.py
