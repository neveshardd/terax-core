# Terax-Core

Terax-Core is a comprehensive core plugin and module designed for Minecraft server networks, specifically targeting Spigot/Paper servers and BungeeCord/Waterfall proxies. It provides a robust, shared foundation of essential features and systems necessary for a modern, interconnected Minecraft experience, centralizing player data, network management, and various in-game mechanics.

## Features

Terax-Core offers a wide array of features to enhance and streamline the management of a Minecraft server network:

*   **Centralized Player Profiles**: Manages player statistics, preferences, achievements, roles, and titles across the network.
*   **Multi-Database Support**: Integrates with MySQL, MongoDB, and HikariCP for efficient data storage, including specialized data tables for various minigames (e.g., BedWars, SkyWars, Murder, TheBridge).
*   **Dynamic In-Game Menus (GUI)**: Provides interactive graphical user interfaces for profile management, server selection, delivery claims, and more.
*   **Network-Wide Party System**: Allows players to form and manage parties that persist across different Spigot/Paper servers connected via BungeeCord.
*   **Advanced Server Load Balancing**: Implements strategies like "Least Connection" and "Most Connection" for efficient distribution of players across game servers.
*   **Custom Scoreboard and Hotbar Functionalities**: Offers dynamic scoreboards and customizable hotbars for an engaging player experience.
*   **Comprehensive Achievement System**: Tracks and rewards player achievements for various minigames.
*   **In-game Currency Management**: Manages two distinct currencies: Cash and Coins.
*   **NMS (Native Minecraft Server) Integration**: Includes version-specific code for Minecraft 1.8.3, enabling custom entities (NPCs, Holograms), advanced network handling, and pathfinding.
*   **Fake Player/NPC Management**: Supports custom NPCs with skin and animation capabilities.
*   **Particle Effects API**: Provides an API for generating and managing in-game particle effects.
*   **Extensive Command System**: A wide range of player and administrative commands for various functionalities.
*   **In-game Deliveries and Reward System**: A system for distributing and claiming in-game rewards.
*   **Role Management and Permissions**: Defines and manages player roles and associated permissions.
*   **Player Preferences**: Allows players to customize settings like player visibility, private messages, and blood & gore effects.
*   **Integration with External APIs/Plugins**: Hooks into PlaceholderAPI for dynamic text, ProtocolLib for packet manipulation, Mojang API for player data, and JDA for Discord integration.
*   **Robust Configuration and Logging Utilities**: Tools for managing plugin configurations and logging events.
*   **Reflection Utilities**: Advanced utilities for interacting with private/protected Minecraft server code.

## Installation

To install and deploy Terax-Core on your Minecraft server network, follow these steps:

### Prerequisites

*   **Java Development Kit (JDK)**: Version 8 or newer.
*   **Gradle**: For building the project.
*   **Minecraft Server**: A running Spigot or Paper server (version 1.8.8 is likely targeted given NMS 1.8.3 integration).
*   **BungeeCord/Waterfall Proxy**: A running BungeeCord or Waterfall proxy server.
*   **ProtocolLib**: Required on Spigot/Paper servers for advanced packet manipulation and NPC/Hologram features.
*   **PlaceholderAPI**: Optional, but recommended for dynamic text placeholders.

### Building from Source

1.  **Clone the repository**:
    ```bash
    git clone https://github.com/neveshardd/terax-core.git
    cd terax-core
    ```

2.  **Build the project using Gradle**:
    This command will compile the project and create a shaded JAR file containing all dependencies.
    ```bash
    ./gradlew shadowJar
    ```
    On Windows, use `gradlew.bat shadowJar`.

3.  **Locate the compiled JAR**:
    The compiled plugin JAR file, typically named `TeraxCore.jar`, will be found in the `build/libs/` directory.

### Deployment

1.  **Spigot/Paper Servers**:
    *   Place the `TeraxCore.jar` file into the `plugins/` folder of your Spigot/Paper servers.
    *   Ensure ProtocolLib and PlaceholderAPI (if used) are also in the `plugins/` folder.

2.  **BungeeCord/Waterfall Proxy**:
    *   Place the `TeraxCore.jar` file into the `modules/` folder of your BungeeCord/Waterfall proxy.

3.  **Configuration**:
    *   Start your servers. Terax-Core will generate default configuration files in the `plugins/TeraxCore/` directory (for Spigot/Paper) and `modules/TeraxCore/` directory (for BungeeCord).
    *   Carefully review and adjust `config.yml`, `bungee.yml`, `roles.yml`, `servers.yml`, `deliveries.yml`, `blacklist.txt`, and `utils.yml` to match your network's setup and preferences.

4.  **Restart Servers**:
    Restart all your Spigot/Paper and BungeeCord/Waterfall servers to apply the configuration changes and fully enable Terax-Core.

## Usage

Once installed and configured, Terax-Core provides a seamless experience for players and administrators alike.

### In-Game Commands

Terax-Core registers numerous commands for both general players and staff:

*   **Player Commands**:
    *   `/party`: Manage player parties (create, invite, leave, chat).
    *   `/tell <player> <message>` or `/w <player> <message>`: Send private messages.
    *   `/reply <message>` or `/r <message>`: Reply to the last private message.
    *   `/online`: View online player count.

*   **Administrative/Staff Commands**:
    *   `/staffchat <message>`: Communicate with other staff members.
    *   `/fake [name]`: Manage fake players/NPCs.
    *   `/setrole <player> <role>`: Assign roles to players.
    *   `/cash <player> [add|remove|set] <amount>`: Manage player cash.
    *   `/coins <player> [add|remove|set] <amount>`: Manage player coins.
    *   `/title <player> [set|remove] <title>`: Manage player titles.
    *   `/fly`: Toggle flight mode.
    *   `/gamemode [mode]`: Change game mode.
    *   `/invsee <player>`: View a player's inventory.
    *   `/warning <player> <reason>`: Issue warnings to players.
    *   `/core`: Main administrative command for plugin management.

### Graphical User Interfaces (GUIs)

Players can access various GUI menus for interacting with Terax-Core features:

*   `/profile`: Access your player profile, statistics, preferences, achievements, titles, and boosters.
*   `/servers`: View and connect to different game servers on the network.
*   `/deliveries`: Claim available in-game rewards.

## Tech Stack

Terax-Core is built using a robust set of technologies:

*   **Language**: Java
*   **Build Tool**: Gradle
*   **Minecraft APIs**:
    *   Bukkit/Spigot API
    *   BungeeCord API
    *   Native Minecraft Server (NMS) for Minecraft 1.8.3
*   **Key Libraries/Frameworks**:
    *   ProtocolLib (for advanced packet manipulation and custom entities)
    *   PlaceholderAPI (for dynamic text)
    *   HikariCP (JDBC connection pooling for MySQL)
    *   MongoDB Java Driver
    *   JSON Simple (for JSON parsing)
    *   JDA (Java Discord API)
    *   Google Guava
*   **Integrated/Vendored Libraries**:
    *   Custom Menu system
    *   Hologram library
    *   NPCLib (for advanced NPC management)
    *   Mojang API interaction (for player data)

## Project Structure

The project follows a standard Java/Gradle structure with specific directories for its various components:

```
terax-core/
├── .gradle/                  # Gradle internal files
├── build/                    # Compiled output and build artifacts
├── gradle/                   # Gradle wrapper files
├── libraries/                # External JAR dependencies (e.g., ProtocolLib, PlaceholderAPI, JDA)
├── src/
│   ├── main/
│   │   ├── java/             # Main Java source code
│   │   │   └── dev/
│   │   │       └── slickcollections/
│   │   │           └── kiwizin/ # Core logic, player management, database, NMS, etc.
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
│   │   └── resources/        # Plugin configuration files and descriptors
│   │       ├── plugin.yml    # Spigot/Bukkit plugin descriptor
│   │       ├── bungee.yml    # BungeeCord module descriptor
│   │       ├── config.yml    # Main plugin configuration
│   │       ├── roles.yml     # Role definitions
│   │       ├── servers.yml   # Server list for load balancing/selection
│   │       ├── deliveries.yml # Delivery reward configurations
│   │       ├── blacklist.txt # Placeholder for blacklisted names
│   │       └── utils.yml     # Utility configurations
├── build.gradle              # Gradle build configuration file
├── gradlew                   # Gradle wrapper script (Linux/macOS)
├── gradlew.bat               # Gradle wrapper script (Windows)
├── LICENSE                   # Project license file
└── README.md                 # This README file
```

## Contributing

Contributions to Terax-Core are welcome! If you find a bug, have a feature request, or want to improve the codebase, please feel free to:

1.  Fork the repository.
2.  Create a new branch for your feature or bug fix.
3.  Make your changes, ensuring code quality and adherence to existing coding styles.
4.  Submit a pull request with a clear description of your changes.

## License

This project is licensed under the terms specified in the `LICENSE` file in the root of the repository.
