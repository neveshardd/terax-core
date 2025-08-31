package world.terax.titles;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import world.terax.player.Profile;
import world.terax.reflection.Accessors;
import world.terax.reflection.MinecraftReflection;
import world.terax.reflection.acessors.FieldAccessor;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class TitleController {

    private static final FieldAccessor<Integer> ENTITY_ID = Accessors.getField(
            MinecraftReflection.getEntityClass(), "entityCount", int.class);

    private final ProtocolManager protocol = ProtocolLibrary.getProtocolManager();

    private Player owner;
    private WrappedDataWatcher watcher;
    private boolean disabled = true;
    private final int entityId;

    public TitleController(Player owner, String title) {
        this.owner = owner;
        this.watcher = new WrappedDataWatcher();

        // 1.8.8 não usa Registry, usa IDs diretamente
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

        // Atualiza diretamente pelo índice
        this.watcher.setObject(2, name);

        if (wasDisabled) {
            // Antes estava "disabled", agora ativo → mostrar novamente
            Profile.listProfiles().forEach(profile -> {
                Player player = profile.getPlayer();
                if (player != null && player.canSee(this.owner)) {
                    showToPlayer(player);
                }
            });
            return;
        }

        if ("disabled".equals(name)) {
            // Se o nome for "disabled" → esconder
            Profile.listProfiles().forEach(profile -> {
                Player player = profile.getPlayer();
                if (player != null && player.canSee(this.owner)) {
                    hideToPlayer(player);
                }
            });
            return;
        }

        // Atualiza metadata para todos os players visíveis
        Profile.listProfiles().forEach(profile -> {
            Player player = profile.getPlayer();
            if (player != null && player.canSee(this.owner)) {
                try {
                    PacketContainer metadataPacket = protocol.createPacket(PacketType.Play.Server.ENTITY_METADATA);
                    metadataPacket.getIntegers().write(0, this.entityId); // entity id
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
    }

    public void disable() {
        if (disabled) return;
        Profile.listProfiles().forEach(profile -> {
            Player player = profile.getPlayer();
            if (player != null && player.canSee(owner)) hideToPlayer(player);
        });
        disabled = true;
    }

    public void showToPlayer(Player player) {
        if (player.equals(this.owner) || disabled) return;

        try {
            double baseX = owner.getLocation().getX();
            double baseY = owner.getLocation().getY() * 32.0;
            double baseZ = owner.getLocation().getZ();

            for (int i = 0; i < 5; i++) { // envia várias vezes para reforçar a atualização
                // Spawn invisível ArmorStand
                PacketContainer spawn = protocol.createPacket(PacketType.Play.Server.SPAWN_ENTITY_LIVING);
                spawn.getIntegers().write(0, this.entityId); // entity id
                spawn.getIntegers().write(1, 30);            // 30 = ArmorStand
                spawn.getIntegers().write(2, (int) baseX);
                spawn.getIntegers().write(3, (int) baseY); // posição acima do displayname
                spawn.getIntegers().write(4, (int) baseZ);
                spawn.getDataWatcherModifier().write(0, watcher);

                // Montar o ArmorStand no player (passenger)
                PacketContainer attach = protocol.createPacket(PacketType.Play.Server.ATTACH_ENTITY);
                attach.getIntegers().write(0, 0);                  // nenhum leash
                attach.getIntegers().write(1, this.entityId);     // ArmorStand
                attach.getIntegers().write(2, owner.getEntityId()); // veículo = player

                protocol.sendServerPacket(player, spawn);
                protocol.sendServerPacket(player, attach);
            }

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
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Player getOwner() {
        return owner;
    }
}
