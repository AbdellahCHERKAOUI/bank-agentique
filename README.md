# E-Bank Microservices with AI Agent

An e-banking platform built with a **microservices architecture** (Spring Boot / Spring Cloud) and an **AI assistant** (Spring AI + MCP) that can query and manage customers through natural language. The assistant is reachable from **Discord** and **Telegram**.

## Architecture

| Service | Role |
|---|---|
| `customer-service` | Manages customers (JPA) and exposes its operations as **MCP tools** |
| `ebank-service` | Business service, calls `customer-service` via **OpenFeign** with **Resilience4j** circuit breaker |
| `ebank-bot` | AI agent (Spring AI `ChatClient` + chat memory + MCP client) and chat integrations |
| Discovery / Config | Service registry and centralized configuration (Spring Cloud) |

## Features

- Customer management (create, list, search)
- Inter-service communication with **OpenFeign**
- Fault tolerance with **Resilience4j** (circuit breaker + fallback)
- **MCP server** exposing tools (`@McpTool`) to the LLM
- **AI agent** with per-user conversation memory
- Chatbot interfaces: **Discord** (JDA) and **Telegram**

## Tech stack

- Java, Spring Boot, Spring Cloud (Eureka, Config, OpenFeign)
- Spring Data JPA
- Resilience4j
- Spring AI (ChatClient, ChatMemory, MCP client/server)
- JDA (Discord) and TelegramBots (Telegram)
- Maven

## Getting started

### Prerequisites

- JDK 21+
- Maven
- An LLM provider configured for Spring AI (OpenAI, Ollama, ...)
- A Discord bot token and/or a Telegram bot token

### Run

1. Start the discovery/config services.
2. Start `customer-service` (MCP server).
3. Start `ebank-service`.
4. Start `ebank-bot`.

### Configuration

Never commit secrets. Use environment variables:

```properties
discord.token=${DISCORD_TOKEN}
telegram.bot.token=${TELEGRAM_TOKEN}
spring.ai.mcp.client.streamable-http.connections.customer.url=http://localhost:8056
```


