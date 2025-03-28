# 🚀 Desafio Técnico Objective - Como Executar a Aplicação

Este documento explica como configurar e executar a API de Gestão Bancária.

## 📌 Pré-requisitos
Antes de iniciar, certifique-se de ter instalado:
- **Java 17 ou superior**
- **Spring Boot**
- **Maven**
- **Git** (opcional, para clonar o repositório)
- **Postman / Insomnia** (opcional, para testar a API)

## ⚙️ Configuração e Execução
### 1️⃣ Compilar e Rodar a Aplicação
```bash
mvn spring-boot:run
```
A API estará rodando em **http://localhost:8080**.

### 2️⃣ Testar os Endpoints
Você pode usar **Postman** ou **Insomnia** para testar os endpoints. Alternativamente, utilize **cURL**:

🔹 **Criar uma conta:**
```bash
curl -X POST "http://localhost:8080/conta" -H "Content-Type: application/json" -d '{"numeroConta": 12345, "saldo": 1500.75}'
```

🔹 **Listar todas as contas:**
```bash
curl -X GET "http://localhost:8080/conta"
```

🔹 **Buscar uma conta específica:**
```bash
curl -X GET "http://localhost:8080/conta/12345"
```

## ✅ Rodar Testes
```bash
mvn test
```
Isso executará todos os testes unitários do projeto.

## 📜 Licença
Este projeto é apenas para fins de aprendizado e não possui uma licença específica.

---
Desenvolvido por Sávio Soares Sousa.
