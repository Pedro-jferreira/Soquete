# 🕒 Aplicação Cliente-Servidor - Consulta de Hora por Região

### 📌 Objetivo
Desenvolver uma aplicação cliente-servidor onde o **cliente** envia uma **região geográfica** (ex: `"America/Sao_Paulo"`) e o **servidor** retorna a **hora local** da região fornecida.

A aplicação possui **três versões** distintas, variando o protocolo de comunicação e o suporte a múltiplos clientes.

---

### ✅ Requisitos
- O cliente envia uma string com o nome de uma **região geográfica** (timezone).
- O servidor processa a solicitação e responde com a **hora local** da região fornecida.
- Utilização da classe `java.time.ZonedDateTime` para manipulação de data e hora com fuso horário.

---

### 🚀 Versões do Projeto
Cada versão está separada em uma pasta/projeto distinto:

#### 📁 1. UDP - User Datagram Protocol
- Comunicação entre cliente e servidor via **UDP**.
- Envio e recebimento de mensagens usando `DatagramSocket` e `DatagramPacket`.
- Comunicação sem conexão persistente (**sem estado**).

#### 📁 2. TCP - Transmission Control Protocol (Simples)
- Comunicação entre cliente e servidor via **TCP**.
- O servidor atende **um cliente por vez** de forma sequencial.
- Utiliza `ServerSocket` e `Socket`.

#### 📁 3. TCP - Multithread
- Comunicação via **TCP**.
- O servidor é capaz de atender **múltiplas requisições simultaneamente**, usando `Thread`.
- Cada cliente conectado é tratado em uma `Thread` separada.

---

### 💻 Exemplo de Uso
**Entrada do Cliente:**
Digite a região geográfica (Ex: America/Sao_Paulo): America/Sao_Paulo

**Resposta do Servidor:**
Hora local em America/Sao_Paulo: 2025-03-24 16:30:45 -03:00

**Ou, se a região for inválida:**
Erro: Região 'Invalid/Region' inválida!



---

### 🛠 Tecnologias Utilizadas
- **Java 17**
- `java.net.*` para sockets
- `java.time.ZonedDateTime` e `java.time.format.DateTimeFormatter`
- Threads para a versão multithread

---

### ▶ Como Executar
1. Compile os arquivos de cada versão:
```bash
javac TimeServer.java TimeClient.java
2.Execute o servidor:
java TimeServer
3.Em outro terminal, execute o cliente:
java TimeClient
```

---

### 👨‍💻 Autor
**Pedro Ferreira**

