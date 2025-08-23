# Terax-Core

![Java](https://img.shields.io/badge/Language-Java-orange.svg)
![Gradle](https://img.shields.io/badge/Build-Gradle-02303A.svg)
![Minecraft](https://img.shields.io/badge/Platform-Minecraft%20(Spigot%2FBungeeCord)-71C671.svg)
![License](https://img.shields.io/github/license/neveshardd/terax-core)

## Table of Contents

*   [About The Project](#about-the-project)
*   [Features](#features)
*   [Getting Started](#getting-started)
    *   [Prerequisites](#prerequisites)
    *   [Installation](#installation)
*   [Usage](#usage)
*   [Tech Stack](#tech-stack)
*   [Project Structure](#project-structure)
*   [Contributing](#contributing)
*   [License](#license)
*   [Contact](#contact)

## About The Project

Terax-Core is a comprehensive core system designed for a multi-server Minecraft network, supporting both BungeeCord proxy servers and Spigot/Bukkit game servers. Its primary purpose is to provide advanced player management, cross-server functionalities, sophisticated game mechanics, and essential network utilities. This project aims to centralize various aspects of a large Minecraft network, offering a robust and extensible foundation for server administrators.

## Features

Terax-Core offers a rich set of features to enhance and manage your Minecraft network:

*   **Centralized Player Profiles**: Store and manage player statistics, preferences, and data across the entire network.
*   **Cross-Server Party System**: Enable players to form parties, chat, and teleport together across different servers.
*   **Dynamic Server Load Balancing**: Implement intelligent load balancing with strategies like Least Connection and Most Connection to optimize server performance and player distribution.
*   **Interactive GUI Menus**: Provide intuitive graphical user interfaces for player profiles, server selection, and reward claims.
*   **Custom NPC Management**: Create and manage custom Non-Player Characters with configurable AI, skins, and interaction events.
*   **Hologram Display System**: Deploy dynamic hologram displays for in-game information, advertisements, and aesthetic enhancements.
*   **Virtual Economy System**: Integrate an in-game economy with support for cash and coins.
*   **Achievement Tracking and Rewards**: Implement a comprehensive achievement system with rewards for various game modes (e.g., BedWars, SkyWars, Murder, TheBridge, BuildBattle).
*   **Network and Personal Booster System**: Offer boosters to players to enhance their gameplay experience (e.g., XP boosts, coin multipliers).
*   **Daily/Timed Delivery System**: Provide a system for players to claim daily or timed rewards.
*   **Player Rank/Role Management**: Manage player roles and ranks with custom permissions and display prefixes/suffixes.
*   **Custom Player Titles System**: Allow players to unlock and select custom titles.
*   **Fake Player System**: Enable player visibility toggling and the creation of fake players for various purposes.
*   **Dynamic and Customizable Scoreboards**: Provide personalized and dynamic scoreboards for players.
*   **Database Abstraction Layer**: Support for both MySQL and MongoDB databases with HikariCP for connection pooling.
*   **Extensive NMS Reflection**: Utilize Net Minecraft Server (NMS) reflection for low-level server interactions and custom entity handling.
*   **ProtocolLib Integration**: Leverage ProtocolLib for advanced packet manipulation.
*   **PlaceholderAPI Expansion**: Seamless integration with PlaceholderAPI for dynamic text placeholders.
*   **Robust Command Handling**: Comprehensive command system for both Bukkit/Spigot and BungeeCord environments.
*   **Configurable Settings**: Easily customize plugin settings via YAML configuration files.

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

*   Java Development Kit (JDK) 8 or higher
*   Gradle (for building the project)
*   A running Minecraft server (Spigot/Paper or similar for Bukkit plugin, BungeeCord/Waterfall for BungeeCord plugin)
*   **Optional External Dependencies (if not shaded by build):**
    *   [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/) (for Spigot/Bukkit)
    *   [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) (for Spigot/Bukkit, if using placeholders)
*   **Database:** Access to a MySQL or MongoDB server for persistent data storage.

### Installation

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/neveshardd/terax-core.git
    cd terax-core
    ```

2.  **Build the project using Gradle:**
    ```bash
    ./gradlew shadowJar
    ```
    This command will compile the project and create a shaded JAR file (likely `TeraxCore.jar`) in the `build/libs/` directory, containing all necessary dependencies.

3.  **Deploy the plugin:**
    *   **For Spigot/Bukkit servers:** Copy the generated `TeraxCore.jar` from `build/libs/` into the `plugins/` folder of your Spigot/Bukkit server.
    *   **For BungeeCord servers:** Copy the generated `TeraxCore.jar` from `build/libs/` into the `plugins/` folder of your BungeeCord server.
    *   **External Dependencies:** If ProtocolLib and PlaceholderAPI are not shaded into `TeraxCore.jar` (check your `build.gradle` or the plugin's behavior), place their respective JARs into the `plugins/` folder of your Spigot/Bukkit servers as well.

4.  **Configure the plugin:**
    *   Start your Minecraft servers to generate the initial configuration files (e.g., `config.yml`, `servers.yml`, `roles.yml`, `deliveries.yml`, `utils.yml`) in the `plugins/Terax-Core/` directory.
    *   Edit these YAML files to suit your network's specific requirements, including database credentials, server lists, and feature settings.

5.  **Restart your servers:**
    Restart your Spigot/Bukkit and BungeeCord servers to apply the configuration changes and fully enable Terax-Core.

## Usage

Terax-Core operates as a core plugin across your Minecraft network, providing foundational services and features.

**Key functionalities include:**

*   **Player Management**: Players' profiles, statistics, and preferences are automatically managed and synchronized across the network.
*   **Cross-Server Parties**: Use the `/party` command (or its BungeeCord equivalent) to create, manage, and invite players to parties, enabling cross-server communication and teleportation.
*   **Virtual Economy**: Utilize `/cash` and `/coins` commands (if enabled) for managing the in-game currency.
*   **Player Visibility**: Players can toggle their visibility using commands, managed by the fake player system.
*   **Server Selection**: Access server selection menus (e.g., `/servers`) to navigate your network.
*   **Profile Menus**: Players can open their profile menu (e.g., `/profile`) to view statistics, achievements, boosters, titles, and preferences.
*   **Daily Deliveries**: Players can claim rewards via a deliveries menu (e.g., `/deliveries`).
*   **Admin Commands**: Administrators can use commands like `/core`, `/fake`, `/cash`, `/coins` (or their BungeeCord equivalents) to manage plugin settings, player data, and network features.

Refer to the generated configuration files for detailed customization options and command aliases.

## Tech Stack

Terax-Core is built using modern Java technologies and integrates with various Minecraft APIs and libraries:

*   **Language**: Java
*   **Build Automation**: Gradle
*   **Minecraft APIs**:
    *   Bukkit/Spigot API (for game servers)
    *   BungeeCord API (for proxy servers)
*   **Key Libraries & Frameworks**:
    *   [ProtocolLib](https://github.com/dmulloy2/ProtocolLib) - Minecraft packet manipulation
    *   [PlaceholderAPI](https://github.com/PlaceholderAPI/PlaceholderAPI) - Dynamic text placeholders
    *   [JSON Simple](https://code.google.com/archive/p/json-simple/) - JSON parsing and generation
    *   [HikariCP](https://github.com/brettwooldridge/HikariCP) - High-performance JDBC connection pool
    *   [MongoDB Java Driver](https://github.com/mongodb/mongo-java-driver) - MongoDB integration
    *   [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) - MySQL integration
    *   [Google Guava](https://github.com/google/guava) - Core Java utilities

## Project Structure

The project is organized into a logical structure to manage its extensive functionalities:

```
terax-core/
├── .gradle/                   # Gradle wrapper files and cache
├── build/                     # Compiled classes, resources, and final JARs
├── gradle/                    # Gradle wrapper files
├── libraries/                 # External JAR dependencies (e.g., ProtocolLib, PlaceholderAPI)
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── dev/slickcollections/kiwizin/  # Main source code package
│   │   │       ├── achievements/              # Achievement system
│   │   │       ├── booster/                   # Booster system
│   │   │       ├── bungee/                    # BungeeCord specific logic (commands, listeners, party)
│   │   │       ├── cash/                      # Virtual economy (cash/coins)
│   │   │       ├── cmd/                       # Bukkit/Spigot command handlers
│   │   │       ├── database/                  # Database abstraction (MySQL, MongoDB, HikariCP)
│   │   │       ├── deliveries/                # Daily/timed deliveries system
│   │   │       ├── hook/                      # Integrations with other plugins (PlaceholderAPI, ProtocolLib)
│   │   │       ├── libraries/                 # Custom libraries (NPCs, Holograms, Menus, Mojang API)
│   │   │       ├── listeners/                 # Bukkit/Spigot event listeners
│   │   │       ├── menus/                     # GUI menu system
│   │   │       ├── nms/                       # NMS (Net Minecraft Server) reflection for version compatibility
│   │   │       ├── party/                     # Cross-server party system
│   │   │       ├── player/                    # Player profile, roles, scoreboard, fake players
│   │   │       ├── plugin/                    # Plugin core utilities (config, logger)
│   │   │       ├── reflection/                # General reflection utilities
│   │   │       ├── servers/                   # Server management and load balancing
│   │   │       ├── titles/                    # Player titles system
│   │   │       └── utils/                     # General utility classes
│   │   └── resources/
│   │       ├── plugin.yml                     # Bukkit/Spigot plugin descriptor
│   │       ├── bungee.yml                     # BungeeCord plugin descriptor
│   │       ├── config.yml                     # Main plugin configuration
│   │       ├── roles.yml                      # Player roles configuration
│   │       ├── servers.yml                    # Server list for load balancing/menus
│   │       └── ...                            # Other configuration and resource files
├── .gitignore                 # Specifies intentionally untracked files
├── build.gradle               # Gradle build script
├── gradlew                    # Gradle wrapper script (Linux/macOS)
├── gradlew.bat                # Gradle wrapper script (Windows)
├── LICENSE                    # Project license
└── README.md                  # This README file
```

## Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also open an issue with the tag "enhancement".

1.  Fork the Project
2.  Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3.  Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4.  Push to the Branch (`git push origin feature/AmazingFeature`)
5.  Open a Pull Request

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Contact

neveshardd - [@neveshardd](https://github.com/neveshardd)
Project Link: [https://github.com/neveshardd/terax-core](https://github.com/neveshardd/terax-core)
