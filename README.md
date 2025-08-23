# Terax-Core

![Java](https://img.shields.io/badge/Language-Java-orange.svg)
![Gradle](https://img.shields.io/badge/Build-Gradle-02303A.svg)
![Minecraft](https://img.shields.io/badge/Platform-Minecraft%20(Spigot%2FBungeeCord)-71C671.svg)
![License](https://img.shields.io/github/license/neveshardd/terax-core)

## Índice

*   [Sobre o Projeto](#sobre-o-projeto)
*   [Funcionalidades](#funcionalidades)
*   [Começando](#começando)
    *   [Pré-requisitos](#pré-requisitos)
    *   [Instalação](#instalação)
*   [Uso](#uso)
*   [Tecnologias](#tecnologias)
*   [Estrutura do Projeto](#estrutura-do-projeto)
*   [Contribuindo](#contribuindo)
*   [Licença](#licença)
*   [Contato](#contato)

## Sobre o Projeto

Terax-Core é um sistema core abrangente projetado para uma rede de múltiplos servidores Minecraft, suportando tanto servidores proxy BungeeCord quanto servidores de jogo Spigot/Bukkit. Seu principal objetivo é fornecer gerenciamento avançado de jogadores, funcionalidades cross-server, mecânicas sofisticadas de jogo e utilitários essenciais para a rede. Este projeto busca centralizar vários aspectos de uma grande rede de Minecraft, oferecendo uma base robusta e extensível para administradores de servidores.

## Funcionalidades

Terax-Core oferece um conjunto rico de funcionalidades para aprimorar e gerenciar sua rede Minecraft:

*   **Perfis Centralizados de Jogadores**: Armazene e gerencie estatísticas, preferências e dados de jogadores em toda a rede.
*   **Sistema de Party Cross-Server**: Permite que jogadores formem grupos, conversem e se teletransportem juntos entre diferentes servidores.
*   **Balanceamento Dinâmico de Servidores**: Implementa balanceamento inteligente de carga com estratégias como Menos Conexões e Mais Conexões para otimizar o desempenho dos servidores e a distribuição de jogadores.
*   **Menus Interativos GUI**: Fornece interfaces gráficas intuitivas para perfis de jogadores, seleção de servidores e resgate de recompensas.
*   **Gerenciamento de NPCs Customizados**: Crie e gerencie NPCs customizados com IA configurável, skins e eventos de interação.
*   **Sistema de Hologramas**: Implante hologramas dinâmicos para informações no jogo, anúncios e melhorias estéticas.
*   **Sistema de Economia Virtual**: Integre uma economia no jogo com suporte a cash e coins.
*   **Rastreamento de Conquistas e Recompensas**: Implemente um sistema completo de conquistas com recompensas para vários modos de jogo (ex.: BedWars, SkyWars, Murder, TheBridge, BuildBattle).
*   **Sistema de Boosters de Rede e Pessoais**: Ofereça boosters para jogadores melhorarem sua experiência (ex.: bônus de XP, multiplicadores de coins).
*   **Sistema de Entregas Diárias/Temporizadas**: Forneça recompensas diárias ou temporizadas para jogadores.
*   **Gerenciamento de Cargos/Ranks**: Administre cargos e ranks de jogadores com permissões customizadas e prefixos/sufixos exibidos.
*   **Sistema de Títulos Personalizados**: Permite que jogadores desbloqueiem e escolham títulos customizados.
*   **Sistema de Jogadores Falsos**: Habilite alternância de visibilidade de jogadores e criação de jogadores falsos para diferentes finalidades.
*   **Scoreboards Dinâmicos e Customizáveis**: Forneça scoreboards personalizados e dinâmicos para jogadores.
*   **Camada de Abstração de Banco de Dados**: Suporte a bancos de dados MySQL e MongoDB com HikariCP para pooling de conexões.
*   **Extenso Uso de NMS Reflection**: Utiliza Net Minecraft Server (NMS) reflection para interações de baixo nível e manipulação de entidades customizadas.
*   **Integração com ProtocolLib**: Aproveita o ProtocolLib para manipulação avançada de pacotes.
*   **Expansão com PlaceholderAPI**: Integração com PlaceholderAPI para placeholders dinâmicos.
*   **Gerenciamento Robusto de Comandos**: Sistema abrangente de comandos para ambientes Bukkit/Spigot e BungeeCord.
*   **Configurações Customizáveis**: Personalize facilmente as configurações via arquivos YAML.

## Começando

Para obter uma cópia local em execução, siga estes passos simples.

### Pré-requisitos

*   Java Development Kit (JDK) 8 ou superior
*   Gradle (para compilar o projeto)
*   Um servidor Minecraft em execução (Spigot/Paper ou similar para plugin Bukkit, BungeeCord/Waterfall para plugin BungeeCord)
*   **Dependências Externas Opcionais (se não incluídas no build):**
    *   [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/) (para Spigot/Bukkit)
    *   [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) (para Spigot/Bukkit, se usar placeholders)
*   **Banco de Dados:** Acesso a um servidor MySQL ou MongoDB para armazenamento persistente de dados.

### Instalação

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/neveshardd/terax-core.git
    cd terax-core
    ```

2.  **Compile o projeto usando Gradle:**
    ```bash
    ./gradlew shadowJar
    ```
    Este comando irá compilar o projeto e criar um JAR sombreado (provavelmente `TeraxCore.jar`) no diretório `build/libs/`, contendo todas as dependências necessárias.

3.  **Implemente o plugin:**
    *   **Para servidores Spigot/Bukkit:** Copie o `TeraxCore.jar` de `build/libs/` para a pasta `plugins/` do seu servidor Spigot/Bukkit.
    *   **Para servidores BungeeCord:** Copie o `TeraxCore.jar` de `build/libs/` para a pasta `plugins/` do seu servidor BungeeCord.
    *   **Dependências Externas:** Se ProtocolLib e PlaceholderAPI não estiverem incluídos dentro de `TeraxCore.jar` (verifique no `build.gradle` ou no comportamento do plugin), coloque seus respectivos JARs também na pasta `plugins/` dos servidores Spigot/Bukkit.

4.  **Configure o plugin:**
    *   Inicie seus servidores Minecraft para gerar os arquivos de configuração iniciais (ex.: `config.yml`, `servers.yml`, `roles.yml`, `deliveries.yml`, `utils.yml`) no diretório `plugins/Terax-Core/`.
    *   Edite esses arquivos YAML para atender aos requisitos específicos da sua rede, incluindo credenciais de banco de dados, lista de servidores e configurações de funcionalidades.

5.  **Reinicie seus servidores:**
    Reinicie seus servidores Spigot/Bukkit e BungeeCord para aplicar as mudanças de configuração e habilitar completamente o Terax-Core.

## Uso

Terax-Core funciona como um plugin core em toda a sua rede Minecraft, fornecendo serviços e funcionalidades fundamentais.

**Principais funcionalidades incluem:**

*   **Gerenciamento de Jogadores**: Perfis, estatísticas e preferências dos jogadores são automaticamente gerenciados e sincronizados pela rede.
*   **Parties Cross-Server**: Use o comando `/party` (ou equivalente no BungeeCord) para criar, gerenciar e convidar jogadores, permitindo comunicação e teleporte entre servidores.
*   **Economia Virtual**: Utilize os comandos `/cash` e `/coins` (se habilitados) para gerenciar a moeda do jogo.
*   **Visibilidade de Jogadores**: Jogadores podem alternar sua visibilidade através de comandos, geridos pelo sistema de jogadores falsos.
*   **Seleção de Servidores**: Acesse menus de seleção (ex.: `/servers`) para navegar pela rede.
*   **Menus de Perfil**: Jogadores podem abrir seus perfis (ex.: `/profile`) para visualizar estatísticas, conquistas, boosters, títulos e preferências.
*   **Entregas Diárias**: Jogadores podem resgatar recompensas via o menu de entregas (ex.: `/deliveries`).
*   **Comandos de Admin**: Administradores podem usar comandos como `/core`, `/fake`, `/cash`, `/coins` (ou equivalentes no BungeeCord) para gerenciar configurações, dados de jogadores e funcionalidades da rede.

Consulte os arquivos de configuração gerados para opções detalhadas de personalização e aliases de comandos.

## Tecnologias

Terax-Core é construído com tecnologias modernas em Java e integra várias APIs e bibliotecas do Minecraft:

*   **Linguagem**: Java
*   **Automação de Build**: Gradle
*   **APIs do Minecraft**:
    *   Bukkit/Spigot API (para servidores de jogo)
    *   BungeeCord API (para servidores proxy)
*   **Principais Bibliotecas & Frameworks**:
    *   [ProtocolLib](https://github.com/dmulloy2/ProtocolLib) - Manipulação de pacotes Minecraft
    *   [PlaceholderAPI](https://github.com/PlaceholderAPI/PlaceholderAPI) - Placeholders dinâmicos
    *   [JSON Simple](https://code.google.com/archive/p/json-simple/) - Parsing e geração de JSON
    *   [HikariCP](https://github.com/brettwooldridge/HikariCP) - Pool de conexões JDBC de alta performance
    *   [MongoDB Java Driver](https://github.com/mongodb/mongo-java-driver) - Integração com MongoDB
    *   [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) - Integração com MySQL
    *   [Google Guava](https://github.com/google/guava) - Utilitários essenciais para Java

## Estrutura do Projeto

O projeto é organizado em uma estrutura lógica para gerenciar suas funcionalidades extensas:

````

terax-core/
├── .gradle/                   # Arquivos do wrapper e cache do Gradle
├── build/                     # Classes compiladas, recursos e JARs finais
├── gradle/                    # Arquivos do wrapper do Gradle
├── libraries/                 # Dependências JAR externas (ex.: ProtocolLib, PlaceholderAPI)
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── dev/slickcollections/kiwizin/  # Pacote principal do código-fonte
│   │   │       ├── achievements/              # Sistema de conquistas
│   │   │       ├── booster/                   # Sistema de boosters
│   │   │       ├── bungee/                    # Lógica específica para BungeeCord (comandos, listeners, party)
│   │   │       ├── cash/                      # Economia virtual (cash/coins)
│   │   │       ├── cmd/                       # Handlers de comandos Bukkit/Spigot
│   │   │       ├── database/                  # Abstração de banco de dados (MySQL, MongoDB, HikariCP)
│   │   │       ├── deliveries/                # Sistema de entregas diárias/temporizadas
│   │   │       ├── hook/                      # Integrações com outros plugins (PlaceholderAPI, ProtocolLib)
│   │   │       ├── libraries/                 # Bibliotecas customizadas (NPCs, Hologramas, Menus, Mojang API)
│   │   │       ├── listeners/                 # Listeners de eventos Bukkit/Spigot
│   │   │       ├── menus/                     # Sistema de menus GUI
│   │   │       ├── nms/                       # NMS reflection para compatibilidade de versões
│   │   │       ├── party/                     # Sistema de party cross-server
│   │   │       ├── player/                    # Perfil de jogador, roles, scoreboard, fake players
│   │   │       ├── plugin/                    # Utilitários centrais do plugin (config, logger)
│   │   │       ├── reflection/                # Utilitários gerais de reflection
│   │   │       ├── servers/                   # Gerenciamento de servidores e balanceamento
│   │   │       ├── titles/                    # Sistema de títulos de jogadores
│   │   │       └── utils/                     # Classes utilitárias gerais
│   │   └── resources/
│   │       ├── plugin.yml                     # Descriptor do plugin Bukkit/Spigot
│   │       ├── bungee.yml                     # Descriptor do plugin BungeeCord
│   │       ├── config.yml                     # Configuração principal do plugin
│   │       ├── roles.yml                      # Configuração de roles de jogadores
│   │       ├── servers.yml                    # Lista de servidores para balanceamento/menus
│   │       └── ...                            # Outros arquivos de configuração e recursos
├── .gitignore                 # Especifica arquivos não versionados
├── build.gradle               # Script de build Gradle
├── gradlew                    # Script wrapper Gradle (Linux/macOS)
├── gradlew\.bat                # Script wrapper Gradle (Windows)
├── LICENSE                    # Licença do projeto
└── README.md                  # Este arquivo README
````

## Contribuindo

Contribuições são o que tornam a comunidade open-source um lugar incrível para aprender, inspirar e criar. Qualquer contribuição que você fizer será **muito bem-vinda**.

Se você tiver uma sugestão para melhorar este projeto, faça um fork do repositório e crie um pull request. Você também pode abrir uma issue com a tag "enhancement".

1.  Faça um Fork do Projeto
2.  Crie sua Branch de Funcionalidade (`git checkout -b feature/NovaFuncionalidade`)
3.  Commit suas alterações (`git commit -m 'Adiciona NovaFuncionalidade'`)
4.  Push para a Branch (`git push origin feature/NovaFuncionalidade`)
5.  Abra um Pull Request

## Licença

Distribuído sob a licença MIT. Veja `LICENSE` para mais informações.

## Contato

neveshardd - [@neveshardd](https://github.com/neveshardd)  
Link do Projeto: [https://github.com/neveshardd/terax-core](https://github.com/neveshardd/terax-core)
