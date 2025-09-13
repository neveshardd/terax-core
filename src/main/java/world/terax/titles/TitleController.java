package world.terax.titles;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import world.terax.Core;
import world.terax.player.Profile;
import world.terax.reflection.Accessors;
import world.terax.reflection.MinecraftReflection;
import world.terax.reflection.acessors.FieldAccessor;

import java.lang.reflect.InvocationTargetException;

public class TitleController {

    private static final FieldAccessor<Integer> ENTITY_ID = Accessors.getField(
            MinecraftReflection.getEntityClass(), "entityCount", int.class);

    private final ProtocolManager protocol = ProtocolLibrary.getProtocolManager();

    private Player owner;
    private WrappedDataWatcher watcher;
    private boolean disabled = true;
    private final int entityId;
    private boolean spawned = false;
    private BukkitTask teleportTask;

    public TitleController(Player owner, String title) {
        this.owner = owner;
        this.watcher = new WrappedDataWatcher();

        watcher.setObject(0, (byte) 0x20); // invisível
        watcher.setObject(2, title);       // nome customizado
        watcher.setObject(3, (byte) 1);    // nome visível
        watcher.setObject(5, (byte) 1);    // marcador pequeno
        watcher.setObject(10, (byte) 1);   // braços visíveis (opcional)

        this.entityId = ENTITY_ID.get(null);
        ENTITY_ID.set(null, this.entityId + 1);
    }

    public void setName(String name) {
        boolean wasDisabled = "disabled".equals(this.watcher.getString(2));

        this.watcher.setObject(2, name);

        if (wasDisabled) {
            Profile.listProfiles().forEach(profile -> {
                Player player = profile.getPlayer();
                if (player != null && player.canSee(this.owner)) {
                    showToPlayer(player);
                }
            });
            return;
        }

        if ("disabled".equals(name)) {
            Profile.listProfiles().forEach(profile -> {
                Player player = profile.getPlayer();
                if (player != null && player.canSee(this.owner)) {
                    hideToPlayer(player);
                }
            });
            return;
        }

        Profile.listProfiles().forEach(profile -> {
            Player player = profile.getPlayer();
            if (player != null && player.canSee(this.owner)) {
                try {
                    PacketContainer metadataPacket = protocol.createPacket(PacketType.Play.Server.ENTITY_METADATA);
                    metadataPacket.getIntegers().write(0, this.entityId);
                    metadataPacket.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
                    protocol.sendServerPacket(player, metadataPacket);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void destroy() {
        disable();
        owner = null;
        watcher = null;
    }

    public void enable() {
        if (!disabled) return;
        disabled = false;
        Profile.listProfiles().forEach(profile -> {
            Player player = profile.getPlayer();
            if (player != null && player.canSee(owner)) showToPlayer(player);
        });

        // Inicia task automática para teleports
        if (teleportTask == null) {
            teleportTask = Bukkit.getScheduler().runTaskTimer(
                    Bukkit.getPluginManager().getPlugin(Core.getInstance().getName()),
                    this::teleportAllViewers,
                    1L, 1L // a cada 2 ticks (~0.1s)
            );
        }
    }

    public void disable() {
        if (disabled) return;
        Profile.listProfiles().forEach(profile -> {
            Player player = profile.getPlayer();
            if (player != null && player.canSee(owner)) hideToPlayer(player);
        });
        disabled = true;

        // Cancela task de teleports
        if (teleportTask != null) {
            teleportTask.cancel();
            teleportTask = null;
        }
    }

     // altura ajustável
    public void showToPlayer(Player player) {
        if (player.equals(this.owner) || disabled) return;

        try {
            Location loc = owner.getLocation().clone().add(0, 1.1, 0);
            int x = (int) Math.floor(loc.getX() * 32.0);
            int y = (int) Math.floor(loc.getY() * 32.0);
            int z = (int) Math.floor(loc.getZ() * 32.0);

            PacketContainer spawn = protocol.createPacket(PacketType.Play.Server.SPAWN_ENTITY_LIVING);
            spawn.getIntegers().write(0, this.entityId); // entity id
            spawn.getIntegers().write(1, 30);            // 30 = ArmorStand
            spawn.getIntegers().write(2, x);
            spawn.getIntegers().write(3, y);
            spawn.getIntegers().write(4, z);
            spawn.getDataWatcherModifier().write(0, watcher);

            protocol.sendServerPacket(player, spawn);
            spawned = true;

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void teleportAllViewers() {
        if (!spawned || owner == null || disabled) return;
        Profile.listProfiles().forEach(profile -> {
            Player viewer = profile.getPlayer();
            if (viewer != null && viewer.canSee(owner)) {
                teleportToPlayer(viewer);
            }
        });
    }

    public void teleportToPlayer(Player viewer) {
        try {
            Location loc = owner.getLocation().clone().add(0, 1.1, 0);
            int x = (int) Math.floor(loc.getX() * 32.0);
            int y = (int) Math.floor(loc.getY() * 32.0);
            int z = (int) Math.floor(loc.getZ() * 32.0);

            PacketContainer teleport = protocol.createPacket(PacketType.Play.Server.ENTITY_TELEPORT);
            teleport.getIntegers().write(0, this.entityId);
            teleport.getIntegers().write(1, x);
            teleport.getIntegers().write(2, y);
            teleport.getIntegers().write(3, z);
            teleport.getBytes().write(0, (byte) 0);
            teleport.getBytes().write(1, (byte) 0);

            protocol.sendServerPacket(viewer, teleport);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    void hideToPlayer(Player player) {
        if (player.equals(owner)) return;
        if (disabled) return;

        try {
            PacketContainer destroy = protocol.createPacket(PacketType.Play.Server.ENTITY_DESTROY);
            destroy.getIntegerArrays().write(0, new int[]{entityId});
            protocol.sendServerPacket(player, destroy);
            spawned = false;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Player getOwner() {
        return owner;
    }
}
