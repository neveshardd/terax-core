Claro! Aqui está a tradução fiel para o português, mantendo exatamente a mesma formatação e markdown:

---

# Terax-Core

Terax-Core é um plugin e módulo core abrangente projetado para redes de servidores Minecraft, direcionado especificamente para servidores Spigot/Paper e proxies BungeeCord/Waterfall. Ele fornece uma base robusta e compartilhada de recursos e sistemas essenciais necessários para uma experiência Minecraft moderna e interconectada, centralizando dados de jogadores, gerenciamento de rede e várias mecânicas dentro do jogo.

## Recursos

Terax-Core oferece uma ampla gama de recursos para melhorar e simplificar o gerenciamento de uma rede de servidores Minecraft:

* **Perfis de Jogadores Centralizados**: Gerencia estatísticas, preferências, conquistas, cargos e títulos dos jogadores em toda a rede.
* **Suporte a Múltiplos Bancos de Dados**: Integração com MySQL, MongoDB e HikariCP para armazenamento eficiente de dados, incluindo tabelas especializadas para vários minigames (ex.: BedWars, SkyWars, Murder, TheBridge).
* **Menus Dinâmicos no Jogo (GUI)**: Fornece interfaces gráficas interativas para gerenciamento de perfil, seleção de servidor, resgate de entregas e muito mais.
* **Sistema de Partidas em Rede**: Permite que jogadores formem e gerenciem grupos que persistem entre diferentes servidores Spigot/Paper conectados via BungeeCord.
* **Balanceamento Avançado de Carga de Servidores**: Implementa estratégias como "Menor Conexão" e "Maior Conexão" para distribuição eficiente de jogadores entre servidores de jogo.
* **Placar e Funcionalidades Personalizadas de Hotbar**: Oferece placares dinâmicos e hotbars customizáveis para uma experiência envolvente.
* **Sistema Abrangente de Conquistas**: Rastreia e recompensa conquistas dos jogadores em vários minigames.
* **Gerenciamento de Moedas do Jogo**: Gerencia duas moedas distintas: Cash e Coins.
* **Integração NMS (Native Minecraft Server)**: Inclui código específico para a versão 1.8.3 do Minecraft, permitindo entidades personalizadas (NPCs, Hologramas), manipulação avançada de rede e pathfinding.
* **Gerenciamento de NPCs/Jogadores Falsos**: Suporta NPCs personalizados com skins e animações.
* **API de Efeitos de Partículas**: Fornece uma API para gerar e gerenciar efeitos de partículas no jogo.
* **Sistema Extensivo de Comandos**: Uma ampla gama de comandos para jogadores e administradores.
* **Sistema de Entregas e Recompensas no Jogo**: Um sistema para distribuir e resgatar recompensas no jogo.
* **Gerenciamento de Cargos e Permissões**: Define e gerencia cargos de jogadores e permissões associadas.
* **Preferências do Jogador**: Permite que jogadores personalizem configurações como visibilidade de jogadores, mensagens privadas e efeitos de sangue e violência.
* **Integração com APIs/Plugins Externos**: Integração com PlaceholderAPI para textos dinâmicos, ProtocolLib para manipulação de pacotes, Mojang API para dados de jogadores e JDA para integração com Discord.
* **Ferramentas Robusta de Configuração e Log**: Ferramentas para gerenciamento de configurações e registro de eventos.
* **Utilitários de Reflexão**: Utilitários avançados para interação com código privado/protegido do servidor Minecraft.

## Instalação

Para instalar e implementar o Terax-Core em sua rede de servidores Minecraft, siga estes passos:

### Pré-requisitos

* **Java Development Kit (JDK)**: Versão 8 ou mais recente.
* **Gradle**: Para compilar o projeto.
* **Servidor Minecraft**: Um servidor Spigot ou Paper em execução (a versão 1.8.8 é provavelmente o alvo devido à integração com NMS 1.8.3).
* **Proxy BungeeCord/Waterfall**: Um servidor proxy BungeeCord ou Waterfall em execução.
* **ProtocolLib**: Necessário em servidores Spigot/Paper para manipulação avançada de pacotes e recursos de NPC/Holograma.
* **PlaceholderAPI**: Opcional, mas recomendado para placeholders dinâmicos.

### Compilando a Partir do Código-Fonte

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/neveshardd/terax-core.git
   cd terax-core
   ```

2. **Compile o projeto usando Gradle**:
   Este comando compilará o projeto e criará um arquivo JAR sombreado contendo todas as dependências.

   ```bash
   ./gradlew shadowJar
   ```

   No Windows, use `gradlew.bat shadowJar`.

3. **Localize o JAR compilado**:
   O arquivo JAR compilado do plugin, normalmente chamado `TeraxCore.jar`, será encontrado no diretório `build/libs/`.

### Implantação

1. **Servidores Spigot/Paper**:

   * Coloque o arquivo `TeraxCore.jar` na pasta `plugins/` de seus servidores Spigot/Paper.
   * Certifique-se de que ProtocolLib e PlaceholderAPI (se usados) também estejam na pasta `plugins/`.

2. **Proxy BungeeCord/Waterfall**:

   * Coloque o arquivo `TeraxCore.jar` na pasta `modules/` do seu proxy BungeeCord/Waterfall.

3. **Configuração**:

   * Inicie seus servidores. O Terax-Core irá gerar arquivos de configuração padrão nos diretórios `plugins/TeraxCore/` (para Spigot/Paper) e `modules/TeraxCore/` (para BungeeCord).
   * Revise e ajuste cuidadosamente `config.yml`, `bungee.yml`, `roles.yml`, `servers.yml`, `deliveries.yml`, `blacklist.txt` e `utils.yml` para corresponder à configuração e preferências da sua rede.

4. **Reinicie os Servidores**:
   Reinicie todos os seus servidores Spigot/Paper e BungeeCord/Waterfall para aplicar as mudanças de configuração e habilitar totalmente o Terax-Core.

## Uso

Uma vez instalado e configurado, o Terax-Core fornece uma experiência fluida tanto para jogadores quanto para administradores.

### Comandos no Jogo

O Terax-Core registra diversos comandos tanto para jogadores comuns quanto para a staff:

* **Comandos de Jogadores**:

  * `/party`: Gerenciar grupos de jogadores (criar, convidar, sair, conversar).
  * `/tell <jogador> <mensagem>` ou `/w <jogador> <mensagem>`: Enviar mensagens privadas.
  * `/reply <mensagem>` ou `/r <mensagem>`: Responder à última mensagem privada.
  * `/online`: Ver a contagem de jogadores online.

* **Comandos Administrativos/Staff**:

  * `/staffchat <mensagem>`: Comunicar-se com outros membros da staff.
  * `/fake [nome]`: Gerenciar jogadores falsos/NPCs.
  * `/setrole <jogador> <cargo>`: Atribuir cargos a jogadores.
  * `/cash <jogador> [add|remove|set] <quantia>`: Gerenciar cash do jogador.
  * `/coins <jogador> [add|remove|set] <quantia>`: Gerenciar coins do jogador.
  * `/title <jogador> [set|remove] <título>`: Gerenciar títulos do jogador.
  * `/fly`: Alternar modo de voo.
  * `/gamemode [modo]`: Alterar modo de jogo.
  * `/invsee <jogador>`: Ver inventário de um jogador.
  * `/warning <jogador> <razão>`: Emitir advertências para jogadores.
  * `/core`: Comando principal administrativo para gerenciamento do plugin.

### Interfaces Gráficas (GUIs)

Jogadores podem acessar vários menus GUI para interagir com os recursos do Terax-Core:

* `/profile`: Acessar seu perfil de jogador, estatísticas, preferências, conquistas, títulos e boosters.
* `/servers`: Ver e conectar-se a diferentes servidores da rede.
* `/deliveries`: Resgatar recompensas disponíveis no jogo.

## Stack Tecnológica

Terax-Core é construído usando um conjunto robusto de tecnologias:

* **Linguagem**: Java
* **Ferramenta de Build**: Gradle
* **APIs do Minecraft**:

  * Bukkit/Spigot API
  * BungeeCord API
  * Native Minecraft Server (NMS) para Minecraft 1.8.3
* **Bibliotecas/Frameworks Principais**:

  * ProtocolLib (para manipulação avançada de pacotes e entidades personalizadas)
  * PlaceholderAPI (para textos dinâmicos)
  * HikariCP (pool de conexões JDBC para MySQL)
  * MongoDB Java Driver
  * JSON Simple (para parsing de JSON)
  * JDA (Java Discord API)
  * Google Guava
* **Bibliotecas Integradas/Empacotadas**:

  * Sistema de Menu personalizado
  * Biblioteca de Hologramas
  * NPCLib (para gerenciamento avançado de NPCs)
  * Interação com API Mojang (para dados de jogadores)

## Estrutura do Projeto

O projeto segue uma estrutura padrão de Java/Gradle com diretórios específicos para seus vários componentes:

```
terax-core/
├── .gradle/                  # Arquivos internos do Gradle
├── build/                    # Saída compilada e artefatos de build
├── gradle/                   # Arquivos do wrapper Gradle
├── libraries/                # Dependências JAR externas (ex.: ProtocolLib, PlaceholderAPI, JDA)
├── src/
│   ├── main/
│   │   ├── java/             # Código-fonte principal em Java
│   │   │   └── dev/
│   │   │       └── slickcollections/
│   │   │           └── kiwizin/ # Lógica central, gerenciamento de jogadores, banco de dados, NMS, etc.
│   │   │               ├── achievements/
│   │   │               ├── bungee/
│   │   │               ├── cmd/
│   │   │               ├── database/
│   │   │               ├── deliveries/
│   │   │               ├── hook/
│   │   │               ├── libraries/
│   │   │               ├── listeners/
│   │   │               ├── menus/
│   │   │               ├── nms/
│   │   │               ├── party/
│   │   │               ├── player/
│   │   │               ├── plugin/
│   │   │               ├── reflection/
│   │   │               ├── servers/
│   │   │               ├── titles/
│   │   │               └── utils/
│   │   └── resources/        # Arquivos de configuração do plugin e descritores
│   │       ├── plugin.yml    # Descritor do plugin Spigot/Bukkit
│   │       ├── bungee.yml    # Descritor do módulo BungeeCord
│   │       ├── config.yml    # Configuração principal do plugin
│   │       ├── roles.yml     # Definições de cargos
│   │       ├── servers.yml   # Lista de servidores para balanceamento/seleção
│   │       ├── deliveries.yml # Configurações de recompensas/entregas
│   │       ├── blacklist.txt # Placeholder para nomes bloqueados
│   │       └── utils.yml     # Configurações utilitárias
├── build.gradle              # Arquivo de configuração do Gradle
├── gradlew                   # Script do wrapper Gradle (Linux/macOS)
├── gradlew.bat               # Script do wrapper Gradle (Windows)
├── LICENSE                   # Arquivo de licença do projeto
└── README.md                 # Este arquivo README
```

## Contribuindo

Contribuições para o Terax-Core são bem-vindas! Se você encontrar um bug, tiver uma sugestão de recurso ou quiser melhorar o código, sinta-se à vontade para:

1. Fazer um fork do repositório.
2. Criar uma nova branch para sua funcionalidade ou correção.
3. Fazer suas alterações, garantindo qualidade de código e aderência ao estilo existente.
4. Enviar um pull request com uma descrição clara das mudanças.

## Licença

Este projeto é licenciado sob os termos especificados no arquivo `LICENSE` na raiz do repositório.

---

Quer que eu também **traduza o arquivo LICENSE (Apache 2.0)** para português (uso informativo) e deixe junto ao README?
