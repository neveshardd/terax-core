# Terax Core

**Terax Core** é o plugin base da rede **Terax**, desenvolvido em **Java 8** com **Gradle** para Spigot/Paper 1.8.8+ e compatível com redes BungeeCord/Waterfall.
Ele fornece sistemas centrais, cache, API e integrações para construção de servidores Minecraft escaláveis e modulares.

---

## 📦 Estrutura do Projeto

* **Gradle Build** → Automação da compilação e dependências.
* **Bibliotecas Locais**:

  * `PlaceholderAPI` → Suporte a placeholders customizados.
  * `ProtocolLib` → Manipulação de pacotes NMS.
  * `Waterfall` → Proxy da rede.
* **Módulos Core**:

  * Integração com MySQL.
  * Sistema de cache para entidades (players, roles, configs, etc).
  * API modular para expansão futura.

---

## 🚀 Tecnologias Utilizadas

* **Java 8**
* **Spigot/Paper 1.8.8**
* **Waterfall / BungeeCord**
* **Gradle**
* **Lombok**
* **MySQL**
* **ProtocolLib**
* **PlaceholderAPI**

---

## ⚙️ Requisitos

* **Java 8**
* **Servidor Spigot/Paper 1.8.8+**
* **MySQL 5.7+ ou MariaDB**
* Plugins necessários:

  * [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/)
  * [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/)

---

## 🔧 Compilação

Clone o repositório e rode o Gradle:

```bash
git clone https://github.com/seu-repo/terax-core.git
cd terax-core
./gradlew build
```

O plugin compilado ficará em:

```
build/libs/terax-core.jar
```

---

## 📚 Funcionalidades

* ✅ **Sistema de Roles** (prefixo, sufixo, prioridade, cor, etc).
* ✅ **Cache de jogadores** (primeiro/último login, roles, medalhas, skins).
* ✅ **Cache de configs** (centralizado, com MySQL).
* ✅ **Integração PlaceholderAPI** (placeholders como `%role_full%`, `%last_login%`, etc).
* ✅ **Integração com ProtocolLib** para NMS e pacotes.
* ✅ **Compatibilidade com redes BungeeCord/Waterfall**.

---

## ⌨️ Comandos

* `/tag` → Lista as roles do jogador.
* `/setrole <player> <role>` → Define o role de um jogador.
* `/lobby` → Teleporta para o lobby configurado.

---

## 🛠️ Contribuição

1. Faça um **fork** do projeto.
2. Crie uma branch: `git checkout -b minha-feature`.
3. Commit suas mudanças: `git commit -m 'Adicionei nova feature'`.
4. Push: `git push origin minha-feature`.
5. Abra um **Pull Request**.

---

## 📜 Licença

Este projeto está licenciado sob a **MIT License**. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.

---

👉 Quer que eu adicione também exemplos de uso da **API do plugin** (como registrar uma role, criar um placeholder ou manipular cache), para deixar o README ainda mais didático?
