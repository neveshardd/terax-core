package world.terax.menus.role;

import world.terax.Core;
import world.terax.Language;
import world.terax.libraries.menu.PagedPlayerMenu;
import world.terax.nms.NMS;
import world.terax.player.Profile;
import world.terax.player.role.Role;
import world.terax.plugin.config.Config;
import world.terax.utils.BukkitUtils;
import world.terax.utils.StringUtils;
import world.terax.utils.enums.EnumSound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuSetRole extends PagedPlayerMenu {

    private static final Config CONFIG = Core.getInstance().getConfig("roles");

    private Map<String, Role> roles = new HashMap<>();
    private final String target;

    public MenuSetRole(Profile profile, String target) {
        super(profile.getPlayer(),
                StringUtils.formatColors(CONFIG.getString("title", "").replace("{player}", target)),
                CONFIG.getBoolean("rows_auto") ? (Role.listRoles().size() / 7) + 3 : CONFIG.getInt("rows")
        );

        this.previousPage = (this.rows * 9) - 9;
        this.nextPage = (this.rows * 9) - 1;
        this.target = target;
        this.onlySlots(10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34);

        List<ItemStack> items = new ArrayList<>();

        for (Role role : Role.listRoles()) {
            // Nome colorido com &
            String roleNameColored = role.getRole() != null
                    ? StringUtils.formatColors(Role.getColored(role.getRole().getName()))
                    : StringUtils.formatColors(Role.getColored(role.getName()));
            String roleNamePlain = StringUtils.stripColors(roleNameColored);

            // ItemStack do icon
            String iconStr = role.getIcon() != null ? role.getIcon() : "STONE:1";
            ItemStack icon = BukkitUtils.deserializeItemStack(
                    StringUtils.formatColors(iconStr)
                            .replace("{role}", roleNameColored)
                            .replace("{player}", StringUtils.formatColors(target))
            );

            // Setando display name colorido
            ItemMeta meta = icon.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(roleNameColored);
                icon.setItemMeta(meta);
            }

            items.add(icon);
            this.roles.put(roleNamePlain, role); // mapeia pelo nome sem cor
        }

        this.setItems(items);
        items.clear();

        this.register(Core.getInstance());
        this.open();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent evt) {
        if (!evt.getInventory().equals(this.getCurrentInventory())) return;

        evt.setCancelled(true);
        if (!evt.getWhoClicked().equals(this.player)) return;
        if (evt.getClickedInventory() == null || !evt.getClickedInventory().equals(this.getCurrentInventory())) return;

        ItemStack item = evt.getCurrentItem();
        if (item == null || item.getType() == Material.AIR) return;

        // Navegação de páginas
        if (evt.getSlot() == this.previousPage) {
            EnumSound.CLICK.play(this.player, 0.5F, 2.0F);
            this.openPrevious();
            return;
        } else if (evt.getSlot() == this.nextPage) {
            EnumSound.CLICK.play(this.player, 0.5F, 2.0F);
            this.openNext();
            return;
        }

        // Busca role pelo display name sem cores
        Role role = null;
        if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
            String displayNamePlain = StringUtils.stripColors(item.getItemMeta().getDisplayName());
            role = this.roles.get(displayNamePlain);
        }

        if (role != null) {
            // Gera comando automaticamente se não estiver definido
            String roleNameCommand = role.getRole() != null
                    ? role.getRole().getName().toLowerCase()
                    : role.getName().toLowerCase();

            String cmd = role.getCmd();
            if (cmd == null || cmd.isEmpty()) {
                cmd = "lp user " + target + " group set " + roleNameCommand;
            } else {
                cmd = cmd.replace("{player}", target);
            }

            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);

            // Envia títulos para todos os jogadores
            final Role finalRole = role; // variável final para lambda
            Bukkit.getOnlinePlayers().forEach(playerr ->
                    NMS.sendTitle(playerr,
                            StringUtils.formatColors(Language.roles$titles$header.replace("{player}", target)),
                            StringUtils.formatColors(Language.roles$titles$footer.replace("{role}", finalRole.getRole().getName()))
                    )
            );

            // Mensagem para quem setou a role
            player.sendMessage(StringUtils.formatColors(
                    Language.roles$set.replace("{player}", target).replace("{role}", role.getRole().getName())
            ));

            // Mensagem para o jogador que recebeu a role
            if (Bukkit.getPlayerExact(target) != null && Bukkit.getPlayerExact(target).isOnline()) {
                Bukkit.getPlayerExact(target).sendMessage(StringUtils.formatColors(
                        Language.roles$notification.replace("{role}", role.getRole().getName())
                ));
            }

            player.closeInventory();
        }
    }

    public void cancel() {
        HandlerList.unregisterAll(this);
        if (this.roles != null) this.roles.clear();
        this.roles = null;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent evt) {
        if (evt.getPlayer().equals(this.player)) this.cancel();
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent evt) {
        if (evt.getPlayer().equals(this.player) && evt.getInventory().equals(this.getCurrentInventory())) {
            this.cancel();
        }
    }
}
