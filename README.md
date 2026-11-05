# 🛡️ Sistem_IDS: Sistema de Detecção de Intrusões Automotivas
[![Pipeline CI - Sistem IDS](https://github.com/MarcioTdSJr/Sistem_IDS/actions/workflows/ci.yml/badge.svg)](https://github.com/MarcioTdSJr/Sistem_IDS/actions/workflows/ci.yml)

Projeto acadêmico desenvolvido para o curso de Ciência da Computação do CEUB, focando na segurança cibernética de redes veiculares (barramento CAN) e na aplicação de práticas de DevOps (Avaliações A1 e A2).

## 📌 Sobre o Projeto
O Sistem_IDS é uma prova de conceito que simula a comunicação interna de um veículo e monitora a rede em tempo real para detectar anomalias. O foco atual do motor de detecção é a identificação de ataques de **Negativa de Serviço (DoS - Denial of Service)**, interceptando picos volumétricos de pacotes injetados na rede.

## 🏗️ Arquitetura e Tecnologias
O sistema foi desenhado com uma arquitetura distribuída e executado em ambiente Windows/Linux, utilizando comunicação via Sockets UDP para simular o tráfego de rede:
* **Java 17 (Motor IDS):** Responsável por escutar a porta de rede, calcular a volumetria de tráfego por segundo e disparar alertas críticos.
* **Python 3 (Simulador):** Utiliza a biblioteca `python-can` para gerar frames de telemetria automotiva e simular o tráfego limpo e os ataques.
* **DevOps & CI/CD:** Repositório estruturado no padrão Trunk-Based (branch `main`). Utiliza **GitHub Actions** e **Docker** para compilação automatizada, execução de testes unitários e entrega contínua a cada novo commit.

## ⚙️ Pipeline CI/CD configurada com GitHub Actions e Maven
A automação do repositório (`.github/workflows/ci.yml`) é disparada a cada *push* ou *pull request* na branch `main` e executa as seguintes etapas:
* **Integração Contínua (CI):** Compilação do motor Java com Apache Maven, execução de testes unitários automatizados com JUnit 5 e validação de sintaxe dos scripts Python.
* **Entrega Contínua (CD):** Build das imagens Docker (`Dockerfile` e `docker-compose.yml`), aplicação de tags de versão e publicação automatizada no registro de containers.

## 🚀 Estratégia de CI/CD, Versionamento e Rollback

### 🔐 Gestão Segura de Configurações e Segredos
A esteira de CI/CD utiliza o mecanismo nativo de gestão de segredos do GitHub Actions (`secrets.GITHUB_TOKEN`), garantindo que nenhuma senha ou chave de API fique exposta em texto plano no código-fonte.

### 🏷️ Versionamento Semântico (SemVer)
As imagens Docker do projeto seguem o padrão de versionamento semântico `MAJOR.MINOR.PATCH` (ex: `v1.0.0`):
* **MAJOR:** Alterações incompatíveis na arquitetura da rede CAN.
* **MINOR:** Novas regras de detecção de anomalias no motor Java.
* **PATCH:** Correções de bugs e refatorações leves.

### 🔄 Procedimento Documentado de Rollback
Em caso de falha crítica ou falso-positivo detectado em ambiente de produção após o deploy de uma nova versão:

1. **Identificação da Versão Estável:** Identifique a última tag válida do container (ex: `v1.0.0`).
2. **Reversão de Imagem no Docker Compose:** Altere a tag no arquivo `docker-compose.yml` para a versão anterior:
```yaml
services:
  motor-ids:
    image: ghcr.io/marciotdsjr/sistem_ids:v1.0.0
```
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

