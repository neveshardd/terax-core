# Terax-Core

Terax-Core é um sistema core abrangente projetado para uma rede de servidores Minecraft (servidores Spigot/Bukkit conectados via BungeeCord). Seu objetivo principal é fornecer funcionalidades essenciais de backend e recursos comuns para um servidor em grande escala, incluindo gerenciamento de dados de jogadores, comunicação entre servidores, mecânicas de jogo personalizadas e ferramentas administrativas.

## Funcionalidades

O Terax-Core oferece um conjunto rico de funcionalidades para alimentar uma rede robusta de Minecraft:

* **Perfis Persistentes de Jogadores**: Gerencie funções, preferências, estatísticas e dados de inventário dos jogadores em toda a rede.
* **Suporte a Múltiplos Bancos de Dados**: Integração perfeita com MySQL e MongoDB, utilizando HikariCP para gerenciamento eficiente de conexões.
* **Sistema de Party Cross-Server**: Jogadores podem formar e gerenciar grupos que persistem e funcionam em diferentes servidores da rede via BungeeCord.
* **Economia In-Game**: Um sistema completo para gerenciar moedas e dinheiro dos jogadores.
* **Sistemas de Conquistas e Boosters**: Acompanhe e recompense conquistas dos jogadores, e ofereça boosters globais ou pessoais.
* **Menus GUI Personalizados**: Menus interativos baseados em inventário para perfis de jogadores, seleção de servidores, entregas, estatísticas e mais.
* **Gerenciamento Avançado de NPCs e Hologramas**: Crie e gerencie NPCs personalizados e hologramas dinâmicos usando NMS e ProtocolLib.
* **Sistema de Jogadores Falsos (Bots)**: Implemente e gerencie jogadores artificiais para diversos fins dentro do jogo.
* **Balanceamento de Carga de Servidores**: Distribua jogadores entre servidores de jogo usando estratégias como Menor Conexão ou Maior Conexão.
* **Conjunto Completo de Comandos**: Uma ampla variedade de comandos in-game para staff (ex.: chat da staff, gerenciamento de jogadores) e jogadores (ex.: mensagens privadas, comandos de party, comandos de modo de jogo).
* **Estatísticas e Armazenamento de Dados por Modo de Jogo**: Gerenciamento dedicado de dados para modos populares como BedWars, SkyWars, TheBridge, Murder e BuildBattle.
* **Hotbars e Scoreboards Personalizados**: Configurações dinâmicas de hotbar e scoreboard adaptadas às preferências dos jogadores.
* **Visibilidade e Preferências do Jogador**: Permita que os jogadores personalizem suas configurações de visibilidade no jogo.
* **Integração NMS via Reflection**: Interação avançada com o código interno do Minecraft, especificamente para a versão `v1_8_R3`.
* **Integração com Discord**: Conecte eventos e comandos in-game com o Discord via JDA.
* **Expansão PlaceholderAPI**: Fornece conteúdo e informações dinâmicas através do PlaceholderAPI.

## Instalação

Para configurar o Terax-Core na sua rede de Minecraft, siga os seguintes passos:

### Pré-requisitos

* **Java Development Kit (JDK)**: Versão 8 ou superior.
* **Gradle**: Usado para construir o projeto (ou utilize o `gradlew` fornecido).
* **Servidor Minecraft**: Um servidor Spigot/Bukkit (ex.: PaperMC, Spigot) e um proxy BungeeCord/Waterfall.
* **Dependências Necessárias (Runtime)**:

  * [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/): Necessário para manipulação avançada de pacotes, NPCs e hologramas.
  * [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/): Necessário para placeholders dinâmicos de texto.
  * **JDA (Java Discord API)**: Se desejar integração com Discord, garanta que o JDA esteja corretamente vinculado ou incluído no plugin.
  * **Banco de Dados**: Acesso a um servidor MySQL ou MongoDB para armazenamento persistente de dados.

### Construindo o Plugin

1. **Clone o Repositório**:

   ```bash
   git clone https://github.com/neveshardd/terax-core.git
   cd terax-core
   ```

2. **Build com Gradle**:
   Execute a tarefa do Gradle para compilar o projeto e criar o arquivo JAR.

   ```bash
   ./gradlew shadowJar
   ```

   No Windows, use `gradlew.bat shadowJar`.

3. **Localize o Arquivo JAR**:
   O JAR compilado estará no diretório `build/libs/`, normalmente com o nome `Core.jar`.

### Deploy

1. **Servidores Spigot/Bukkit**:

   * Coloque `Core.jar` na pasta `plugins/` do(s) seu(s) servidor(es) Spigot/Bukkit.
   * Certifique-se de que `ProtocolLib.jar` e `PlaceholderAPI.jar` também estejam na pasta `plugins/`.

2. **Proxy BungeeCord/Waterfall**:

   * Coloque `Core.jar` na pasta `plugins/` do proxy BungeeCord/Waterfall.

3. **Configuração**:

   * Inicie seus servidores e proxy uma vez para gerar os arquivos de configuração iniciais (ex.: `config.yml`, `bungee.yml`, `roles.yml`, `servers.yml`, `deliveries.yml`, `language.yml`) no diretório `plugins/TeraxCore/` tanto nos servidores Spigot/Bukkit quanto no proxy BungeeCord.
   * Edite esses arquivos para configurar conexões de banco de dados, lista de servidores, roles e outras configurações conforme a necessidade da sua rede.
   * Reinicie os servidores e proxy após realizar alterações na configuração.

## Uso

Uma vez que o Terax-Core esteja instalado e configurado, jogadores e staff podem interagir com suas funcionalidades:

### Comandos In-Game

* `/core`: Comando administrativo principal para gerenciamento do plugin.
* `/party [invite|accept|deny|leave|kick|chat]`: Gerenciar parties cross-server.
* `/tell <player> <message>` ou `/msg <player> <message>`: Enviar mensagens privadas para outros jogadores.
* `/reply <message>` ou `/r <message>`: Responder à última mensagem privada.
* `/staffchat <message>` ou `/sc <message>`: Comunicar-se com membros da staff online.
* `/fake [set|reset|list]`: Gerenciar jogadores falsos/bots.
* `/setrole <player> <role>`: Atribuir um role a um jogador.
* `/cash [add|remove|set] <player> <amount>`: Gerenciar dinheiro do jogador.
* `/coins [add|remove|set] <player> <amount>`: Gerenciar moedas do jogador.
* `/title [set|reset|list]`: Gerenciar títulos de jogadores.
* `/fly`: Alternar modo de voo.
* `/gamemode <mode>` ou `/gm <mode>`: Alterar o modo de jogo.
* `/invsee <player>`: Visualizar o inventário de outro jogador.
* `/warning <player> <reason>`: Aplicar avisos a jogadores.
* `/online`: Visualizar jogadores online (em toda a rede, se configurado).

### Menus GUI

Os jogadores podem acessar diversos menus interativos clicando em itens específicos ou usando comandos:

* `/profile`: Acesse o menu do perfil do jogador para gerenciar preferências, ver estatísticas, conquistas, boosters e títulos.
* `/servers`: Navegue e conecte-se aos servidores de jogo disponíveis na rede.
* `/deliveries`: Resgatar recompensas pendentes do sistema de entregas.
* `/statistics`: Visualizar estatísticas específicas de cada modo de jogo.
* `/preferences`: Personalizar configurações do jogador, como visibilidade e preferências de mensagens privadas.
* `/achievements`: Explorar e acompanhar o progresso de conquistas.
* `/boosters`: Ver boosters ativos na rede e seus boosters pessoais.
* `/titles`: Selecionar e gerenciar títulos in-game.

## Stack Tecnológica e Dependências

O Terax-Core é construído utilizando um conjunto robusto de tecnologias:

* **Linguagem**: Java
* **Plataformas**: Minecraft (servidores Spigot/Bukkit, proxies BungeeCord/Waterfall)
* **APIs Principais**:

  * Bukkit API
  * Spigot API
  * BungeeCord API
  * Waterfall API
  * NMS (Net Minecraft Server, especificamente `v1_8_R3` para interações específicas)
* **Bibliotecas/Frameworks Externos**:

  * **ProtocolLib**: Para manipulação avançada de pacotes e renderização de entidades personalizadas.
  * **PlaceholderAPI**: Para textos dinâmicos e integração com outros plugins.
  * **Java Discord API (JDA)**: Para integração com o Discord.
  * **HikariCP**: Pool de conexões JDBC de alta performance para bancos de dados.
  * **MongoDB Java Driver**: Para interação com bancos de dados MongoDB.
  * **MySQL Connector/J**: Para interação com bancos de dados MySQL.
  * \*\*Google


Guava\*\*: Conjunto de bibliotecas essenciais do Google para Java.
\*   **JSON Simple**: Biblioteca leve para parsing e geração de JSON.

* **Automação de Build**: Gradle
* **Bancos de Dados**: MySQL, MongoDB

## Estrutura do Projeto

O projeto é organizado em pacotes lógicos para manter modularidade e clareza:

```
terax-core/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── world/terax/             (Lógica principal do plugin, managers, classes core)
│   │   │       ├── achievements/        (Lógica do sistema de conquistas e tipos)
│   │   │       ├── booster/             (Sistema de boosters para rede e pessoal)
│   │   │       ├── bungee/              (Listeners, comandos e integração com party no BungeeCord)
│   │   │       ├── cash/                (Gerenciamento de economia: cash e coins)
│   │   │       ├── command/             (Comandos in-game Bukkit/Spigot)
│   │   │       ├── database/            (Camada de abstração de banco, tabelas, containers de dados, cache)
│   │   │       ├── deliveries/          (Sistema de entregas de recompensas)
│   │   │       ├── game/                (Estruturas de dados e estados específicos de jogos)
│   │   │       ├── hook/                (Integração com plugins externos como ProtocolLib, PlaceholderAPI)
│   │   │       ├── libraries/           (Bibliotecas utilitárias para menus, hologramas, NPCLib, Mojang API)
│   │   │       ├── listeners/           (Event listeners Bukkit/Spigot)
│   │   │       ├── menus/               (Implementações de menus GUI para diversos recursos)
│   │   │       ├── nms/                 (Integração com Net Minecraft Server para código específico de versão)
│   │   │       ├── party/               (Lógica do sistema de parties in-game)
│   │   │       ├── player/              (Gerenciamento de perfil do jogador, fake players, hotbars, scoreboards)
│   │   │       ├── plugin/              (Utilitários do plugin, configuração e logging)
│   │   │       ├── reflection/          (Utilitários de reflection Java, especialmente para NMS)
│   │   │       ├── servers/             (Gerenciamento de servidores, balanceamento de carga e ping)
│   │   │       ├── titles/              (Sistema de títulos de jogadores)
│   │   │       └── utils/               (Classes utilitárias gerais: string, tempo, partículas, validação, etc.)
│   │   └── resources/
│   │       ├── plugin.yml               (Metadados do plugin Bukkit/Spigot)
│   │       ├── bungee.yml               (Metadados do plugin BungeeCord)
│   │       ├── config.yml               (Configuração principal do plugin)
│   │       ├── roles.yml                (Definições de roles e permissões)
│   │       ├── servers.yml              (Lista de servidores e configuração de load balancer)
│   │       ├── deliveries.yml           (Configurações de recompensas de entregas)
│   │       └── language.yml             (Strings de localização e idioma)
├── build.gradle                       (Configuração de build do Gradle)
├── libraries/                         (JARs de terceiros pré-compilados usados na compilação)
├── LICENSE                            (Arquivo de licença do projeto)
└── README.md                          (Este arquivo README)
```

## Contribuindo

Contribuições são bem-vindas! Se você tiver sugestões de melhorias, correções de bugs ou novos recursos, abra uma issue ou envie um pull request.

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.
