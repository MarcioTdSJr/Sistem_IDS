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
## 🐳 Como Executar o Ambiente (Docker)

Com o projeto containerizado, não é necessário instalar o Java ou o Python manualmente na máquina. Toda a arquitetura (Motor IDS e Simulador de Tráfego) é orquestrada via **Docker Compose**:

1. **Subir o ambiente completo (Build + Execução):**
```
Bash
docker compose up --build
```
2. **Acompanhar os logs de detecção em tempo real (em outro terminal, caso rode com -d):**
```
Bash
docker compose logs -f
```
3. **Encerrar a simulação e remover os containers:**
```
Bash
docker compose down
```
