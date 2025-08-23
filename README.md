# Terax-Core

Terax-Core is a comprehensive core library and plugin suite designed for Minecraft networks, supporting both Spigot/Bukkit servers and BungeeCord proxies. It provides essential features for player management, game-mode specific statistics, and advanced server interactions, aiming to be the backbone for a robust and dynamic Minecraft server network.

## Features

This project offers a rich set of functionalities to enhance the player experience and simplify server management:

*   **Player Profile System**: Persistent data storage for player statistics, preferences, achievements, titles, and boosters.
*   **Interactive GUI Menus**: User-friendly in-game menus for various player interactions, including profiles, server selection, and achievements.
*   **Cross-Server Party System**: Allows players to form parties that persist and function across different servers in the network.
*   **In-Game Economy & Rewards**: A robust currency system (cash/coins) and a flexible delivery system for scheduled or triggered in-game rewards.
*   **Dynamic Server Load Balancing**: Intelligent server selection and load balancing to distribute players efficiently and ensure optimal performance.
*   **Custom NPCs & Holograms**: Create interactive Non-Player Characters and floating text holograms for engaging lobbies and informational displays.
*   **Fake Player Management**: Tools for simulating server population or assigning roles in minigames using fake players.
*   **Extensible Achievement System**: A flexible system to track and reward player achievements, with support for game-specific achievements (e.g., BedWars, SkyWars, Murder, TheBridge).
*   **Role-Based System**: Comprehensive role management for player permissions, display names, and chat prefixes.
*   **Database Integration**: Seamless support for both MySQL and MongoDB databases for scalable and persistent data storage.
*   **Advanced Server Interaction**: Utilizes NMS (Net Minecraft Server) reflection and ProtocolLib for deep customization and interaction with server internals.
*   **BungeeCord Integration**: Network-wide features and communication capabilities facilitated by BungeeCord.
*   **Utility & Management**: Includes logger, configuration management, and general utility classes.
*   **PlaceholderAPI Expansion**: Provides custom placeholders for dynamic text integration with PlaceholderAPI.

## Installation

To set up Terax-Core on your Minecraft network, follow these steps:

### Prerequisites

*   **Java Development Kit (JDK)**: Version 8 or higher.
*   **Git**: For cloning the repository.
*   **Minecraft Server**: A Spigot/Bukkit compatible server (e.g., PaperMC, Spigot) running.
*   **Minecraft Proxy (Optional but Recommended)**: A BungeeCord or Waterfall proxy server if you intend to use cross-server features.

### Building the Project

1.  **Clone the Repository**:
    ```bash
    git clone https://github.com/neveshardd/terax-core.git
    cd terax-core
    ```

2.  **Build with Gradle**:
    Execute the Gradle `shadowJar` task to compile the project and bundle all dependencies into a single JAR file.
    ```bash
    ./gradlew shadowJar
    ```
    (On Windows, use `gradlew.bat shadowJar`)

    The compiled plugin JAR (`TeraxCore.jar`) will be located in the `build/libs/` directory.

### Deployment

1.  **Spigot/Bukkit Servers**:
    *   Place the `TeraxCore.jar` file into the `plugins/` folder of your Spigot/Bukkit server.
    *   **Required Dependencies**:
        *   [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/): Download and place this plugin into your Spigot/Bukkit `plugins/` folder.
        *   [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/): Download and place this plugin into your Spigot/Bukkit `plugins/` folder.

2.  **BungeeCord/Waterfall Proxy (for network features)**:
    *   If you are utilizing BungeeCord features, also place the `TeraxCore.jar` file into the `plugins/` folder of your BungeeCord/Waterfall proxy server.

3.  **Restart Servers**: Restart both your Spigot/Bukkit servers and BungeeCord proxy (if applicable) to load the plugin and generate configuration files.

### Configuration

After the first run, configuration files will be generated in `plugins/TeraxCore/` on both your Spigot/Bukkit servers and BungeeCord proxy.

*   **`config.yml`**: Main plugin settings, database credentials (MySQL, MongoDB).
*   **`roles.yml`**: Define player roles, permissions, and display settings.
*   **`servers.yml`**: Configure server groups and load balancing options.
*   **`deliveries.yml`**: Set up in-game rewards and their delivery conditions.
*   **`bungee.yml`**: BungeeCord specific settings.

Edit these files to customize Terax-Core to your network's needs.

## Usage

Terax-Core provides a wide array of commands and interactive menus accessible both in-game and through the console.

### In-Game Commands (Spigot/Bukkit)

Players and administrators can interact with Terax-Core using various commands:

*   `/core`: Main administrative command for plugin management.
*   `/party`: Manage cross-server parties (create, invite, leave, chat).
*   `/cash <player> <add|remove|set> <amount>`: Manage player cash/economy.
*   `/coins <player> <add|remove|set> <amount>`: Manage player coins/economy.
*   `/fake <player> <join|leave|toggle>`: Manage fake players for server population.

### Interactive Menus (Spigot/Bukkit)

Players can access various GUI menus for profiles and server navigation:

*   `/profile`: Access your player profile to view statistics, achievements, preferences, and titles.
*   `/servers`: Open a menu to browse and connect to available game servers.
*   `/deliveries`: Claim pending in-game rewards.

### BungeeCord Commands

For network-wide features, specific commands are available on the BungeeCord proxy:

*   `/party`: BungeeCord version of the party command, enabling cross-server party management.
*   `/partychat`: Toggle party chat.
*   `/fake <player> <join|leave|toggle>`: BungeeCord command for managing fake players across the network.
*   `/fakelist`: View a list of active fake players.
*   `/fakereset`: Reset all fake players.

## Tech Stack and Dependencies

Terax-Core is built primarily with Java and leverages a variety of frameworks and libraries to deliver its extensive functionality.

*   **Language**: Java
*   **Build Automation**: Gradle
*   **Minecraft APIs**:
    *   Spigot/Bukkit API (for server-side logic)
    *   BungeeCord API (for proxy server logic, implied by Waterfall dependency)
*   **Key Libraries & Frameworks**:
    *   **ProtocolLib**: For advanced packet manipulation and custom client-server interactions.
    *   **PlaceholderAPI**: For dynamic text placeholders across the network.
    *   **HikariCP**: High-performance JDBC connection pool for database connections.
    *   **MongoDB Java Driver**: For integrating with MongoDB NoSQL databases.
    *   **MySQL Connector/J**: For integrating with MySQL relational databases.
    *   **JSON Simple**: A simple Java toolkit for JSON parsing and generation.
    *   **Google Guava**: Google's core libraries for Java, providing utility functions.
*   **Bundled Libraries (found in `libraries` directory)**:
    *   `ProtocolLib-4.5.0.jar`
    *   `waterfall-1.21-598.jar` (A BungeeCord fork, indicating BungeeCord API usage)
    *   `PlaceholderAPI-2.10.5.jar`

## Project Structure

The project follows a standard Gradle-based Java project structure, organized for clarity and maintainability:

```
terax-core/
├── gradle/                     # Gradle wrapper files
├── src/
│   ├── main/
│   │   ├── java/               # Main Java source code (dev.slickcollections.kiwizin.*)
│   │   │   ├── achievements/   # Achievement system and types (BedWars, SkyWars, Murder, TheBridge)
│   │   │   ├── booster/        # Network-wide and player-specific boosters
│   │   │   ├── bungee/         # BungeeCord specific logic (commands, listeners, party)
│   │   │   ├── cash/           # In-game economy/cash management
│   │   │   ├── cmd/            # Command handlers for both Bukkit/Spigot and BungeeCord
│   │   │   ├── database/       # Database integration (MySQL, MongoDB, HikariCP, data containers)
│   │   │   ├── deliveries/     # Scheduled or triggered in-game reward system
│   │   │   ├── game/           # Core game state and team management (e.g., for minigames)
│   │   │   ├── hook/           # Integrations with external plugins (ProtocolLib, PlaceholderAPI)
│   │   │   ├── libraries/      # Custom libraries (NPCs, Holograms, Menus, Mojang API integration)
│   │   │   ├── listeners/      # Event listeners for various in-game and network events
│   │   │   ├── menus/          # Interactive GUI menu system (profile, servers, achievements, etc.)
│   │   │   ├── nms/            # NMS (Net Minecraft Server) abstractions for version compatibility (v1_8_R3)
│   │   │   ├── party/          # Cross-server party functionality
│   │   │   ├── player/         # Player profiles, roles, fake players, preferences, hotbars, scoreboards
│   │   │   ├── plugin/         # Core plugin utilities (logger, configuration management)
│   │   │   ├── reflection/     # Reflection utilities for accessing Minecraft server internals dynamically
│   │   │   ├── servers/        # Server balancing and management (balancer, server items)
│   │   │   ├── titles/         # Player title system
│   │   │   └── utils/          # General helper utilities (Bukkit, time, strings, particles, queues)
│   │   └── resources/          # Plugin metadata (plugin.yml, bungee.yml) and default configuration files
├── build.gradle                # Gradle build script for project configuration and dependencies
├── gradlew                     # Gradle wrapper script (for Linux/macOS)
├── gradlew.bat                 # Gradle wrapper script (for Windows)
├── LICENSE                     # Project license file
└── README.md                   # This README file
```

## Contributing

We welcome contributions to Terax-Core! If you have suggestions, bug reports, or want to contribute code, please feel free to:

1.  Fork the repository.
2.  Create a new branch for your feature or bug fix.
3.  Make your changes and ensure they adhere to the existing code style.
4.  Submit a pull request with a clear description of your changes.

## License

This project is licensed under the [LICENSE](LICENSE) file. Please review it for more details.
