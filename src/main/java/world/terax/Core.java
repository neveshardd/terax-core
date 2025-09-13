package world.terax;

import com.comphenix.protocol.ProtocolLibrary;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import world.terax.achievements.Achievement;
import world.terax.booster.Booster;
import world.terax.command.Commands;
import world.terax.database.Database;
import world.terax.deliveries.Delivery;
import world.terax.hook.CoreExpansion;
import world.terax.hook.protocollib.FakeAdapter;
import world.terax.hook.protocollib.HologramAdapter;
import world.terax.hook.protocollib.NPCAdapter;
import world.terax.libraries.MinecraftVersion;
import world.terax.libraries.holograms.HologramLibrary;
import world.terax.libraries.npclib.NPCLibrary;
import world.terax.listeners.Listeners;
import world.terax.listeners.PluginMessageListener;
import world.terax.nms.NMS;
import world.terax.player.Profile;
import world.terax.player.fake.FakeManager;
import world.terax.player.role.Role;
import world.terax.plugin.Plugin;
import world.terax.servers.ServerItem;
import world.terax.titles.Title;
import world.terax.utils.queue.Queue;
import world.terax.utils.queue.QueuePlayer;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;

@SuppressWarnings("unchecked")
public class Core extends Plugin {
  
  public static final List<String> warnings = new ArrayList<>();
    public static HashMap<Player, Player> reply = new HashMap<>();
  public static final List<String> minigames = Arrays.asList("Sky Wars", "The Bridge", "Murder", "Bed Wars", "Build Battle");
  
  public static boolean validInit;
  public static String minigame = "";
  
  /**
   * Copyright (c) 2020-2021 SliceCollections
   * Uma edição de um plugin chamado mCore.
   * Criador: https://github.com/maxteer
   * Source Code (mCore): https://github.com/slicecollections/mCore
   */
  
  private static Core instance;
  private static Location lobby;
  
  public static Location getLobby() {
    return lobby;
  }
  
  public static void setLobby(Location location) {
    lobby = location;
  }
  
  public static Core getInstance() {
    return instance;
  }
  
  public static void sendServer(Profile profile, String name) {
    if (!Core.getInstance().isEnabled()) {
      return;
    }
    
    Player player = profile.getPlayer();
    if (Core.getInstance().getConfig("utils").getBoolean("queue")) {
      if (player != null) {
        player.closeInventory();
        Queue queue = player.hasPermission("permission.queue") ? Queue.VIP : Queue.MEMBER;
        QueuePlayer qp = queue.getQueuePlayer(player);
        if (qp != null) {
          if (qp.server.equalsIgnoreCase(name)) {
            qp.player.sendMessage("§cVocê já está na fila de conexão!");
          } else {
            qp.server = name;
          }
          return;
        }
        
        queue.queue(player, profile, name);
      }
    } else {
      if (player != null) {
        Bukkit.getScheduler().runTask(Core.getInstance(), () -> {
          if (player.isOnline()) {
            player.closeInventory();
            NMS.sendActionBar(player, "");
            player.sendMessage("§aConectando...");
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(name);
            player.sendPluginMessage(Core.getInstance(), "BungeeCord", out.toByteArray());
          }
        });
      }
    }
  }
  
  @Override
  public void start() {
    instance = this;
  }
  
  @Override
  public void load() {}
  
  @Override
  public void enable() {
    if (!NMS.setupNMS()) {
      this.setEnabled(false);
      this.getLogger().warning("Sua versao nao e compativel com o plugin, utilize a versao 1_8_R3 (Atual: " + MinecraftVersion.getCurrentVersion().getVersion() + ")");
      return;
    }
    
    saveDefaultConfig();
    lobby = Bukkit.getWorlds().get(0).getSpawnLocation();
    
    // Remover o spawn-protection-size
    if (Bukkit.getSpawnRadius() != 0) {
      Bukkit.setSpawnRadius(0);
    }
    
    if (!PlaceholderAPIPlugin.getInstance().getDescription().getVersion().equals("2.10.5")) {
      Bukkit.getConsoleSender().sendMessage(" \n §6§lAVISO IMPORTANTE\n \n §7Utilize a versão 2.10.5 do PlaceHolderAPI, você está utilizando a v" + PlaceholderAPIPlugin.getInstance().getDescription().getVersion() + "\n ");
      System.exit(0);
      return;
    }
    
    PlaceholderAPI.registerExpansion(new CoreExpansion());
    
    Database.setupDatabase(
        getConfig().getString("database.tipo"),
        getConfig().getString("database.mysql.host"),
        getConfig().getString("database.mysql.porta"),
        getConfig().getString("database.mysql.nome"),
        getConfig().getString("database.mysql.usuario"),
        getConfig().getString("database.mysql.senha"),
        getConfig().getBoolean("database.mysql.hikari", false),
        getConfig().getBoolean("database.mysql.mariadb", false),
        getConfig().getString("database.mongodb.url", "")
    );
    
    NPCLibrary.setupNPCs(this);
    HologramLibrary.setupHolograms(this);
    
    Role.setupRoles();
    Language.setupLanguage();
    FakeManager.setupFake();
    Title.setupTitles();
    Booster.setupBoosters();
    Delivery.setupDeliveries();
    ServerItem.setupServers();
    Achievement.setupAchievements();
    
    Commands.setupCommands();
    Listeners.setupListeners();
    
    ProtocolLibrary.getProtocolManager().addPacketListener(new FakeAdapter());
    ProtocolLibrary.getProtocolManager().addPacketListener(new NPCAdapter());
    ProtocolLibrary.getProtocolManager().addPacketListener(new HologramAdapter());
    
    getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    getServer().getMessenger().registerOutgoingPluginChannel(this, "Core");
    getServer().getMessenger().registerIncomingPluginChannel(this, "Core", new PluginMessageListener());
    
    validInit = true;
    this.getLogger().info("O plugin foi ativado.");
  }
  
  @Override
  public void disable() {
    if (validInit) {
      Bukkit.getOnlinePlayers().forEach(player -> {
        Profile profile = Profile.unloadProfile(player.getName());
        if (profile != null) {
          profile.saveSync();
          this.getLogger().info("Saved" + profile.getName());
          profile.destroy();
        }
      });
      Database.getInstance().close();
    }
  }

}
