package world.terax.menus;

import world.terax.Core;
import world.terax.libraries.menu.PlayerMenu;
import world.terax.menus.profile.*;
import world.terax.player.Profile;
import world.terax.player.role.Role;
import world.terax.utils.BukkitUtils;
import world.terax.utils.StringUtils;
import world.terax.utils.enums.EnumSound;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MenuProfile extends PlayerMenu {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("d 'de' MMMM. yyyy 'às' HH:mm",
            Locale.forLanguageTag("pt-BR"));

    public MenuProfile(Profile profile) {
        super(profile.getPlayer(), "Meu Perfil", 6);

        this.setItem(13, BukkitUtils.putProfileOnSkull(this.player, BukkitUtils.deserializeItemStack(
                "SKULL_ITEM:3 : 1 : nome>&aInformações Pessoais : desc>&fRank: " + Role.getRoleByName(profile.getDataContainer("CoreProfile", "role").getAsString())
                        .getName() + "\n&fCash: &b" + StringUtils.formatNumber(profile.getStats("CoreProfile", "cash")) + "\n \n&fCadastrado: &7" + SDF.format(profile.getDataContainer("CoreProfile", "created").getAsLong()) + "\n&fÚltimo acesso: &7" + SDF
                        .format(profile.getDataContainer("CoreProfile", "lastlogin").getAsLong()))));

        this.setItem(29, BukkitUtils.deserializeItemStack(
                "357 : 1 : nome>&aAmigos : desc>&7Em nosso servidor existe um sistema\n&7de &6Desafios &7que se consiste em missões\n&7de realização única que lhe garante\n&7vários prêmios vitalícios.\n \n&8Os Prêmios variam entre títulos, coins,\n&8ícones de prestígio e outros cosméticos.\n \n&eClique para ver os desafios!"));

        this.setItem(30, BukkitUtils.deserializeItemStack(
                "PAPER : 1 : nome>&aEstatísticas : desc>&7Visualize as suas estatísticas de\n&7cada Minigame do nosso servidor.\n \n&eClique para ver as estatísticas!"));

        this.setItem(31, BukkitUtils.deserializeItemStack(
                "REDSTONE_COMPARATOR : 1 : nome>&aPreferências : desc>&7Em nosso servidor você pode personalizar\n&7sua experiência de jogo por completo.\n&7Personalize várias opções únicas como\n&7você desejar!\n \n&8As opções incluem ativar ou desativar as\n&8mensagens privadas, os jogadores e outros.\n \n&eClique para personalizar as opções!"));

        this.setItem(32, BukkitUtils.deserializeItemStack(
                "175 : 1 : nome>&aMedalhas : desc>&7Em nosso servidor existe um sistema\n&7de &6Desafios &7que se consiste em missões\n&7de realização única que lhe garante\n&7vários prêmios vitalícios.\n \n&8Os Prêmios variam entre títulos, coins,\n&8ícones de prestígio e outros cosméticos.\n \n&eClique para ver os desafios!"));

        this.setItem(33, BukkitUtils.deserializeItemStack(
                "389 : 1 : nome>&aSkins : desc>&7Em nosso servidor existe um sistema\n&7de &6Desafios &7que se consiste em missões\n&7de realização única que lhe garante\n&7vários prêmios vitalícios.\n \n&8Os Prêmios variam entre títulos, coins,\n&8ícones de prestígio e outros cosméticos.\n \n&eClique para ver os desafios!"));

        this.setItem(39, BukkitUtils.deserializeItemStack(
                "GOLD_INGOT : 1 : nome>&aDesafios : desc>&7Em nosso servidor existe um sistema\n&7de &6Desafios &7que se consiste em missões\n&7de realização única que lhe garante\n&7vários prêmios vitalícios.\n \n&8Os Prêmios variam entre títulos, coins,\n&8ícones de prestígio e outros cosméticos.\n \n&eClique para ver os desafios!"));

        this.setItem(40, BukkitUtils.deserializeItemStack(
                "POTION:8232 : 1 : esconder>tudo : nome>&aMultiplicadores de Coins : desc>&7Em nosso servidor existe um sistema\n&7de &6Multiplicadores de Coins &7que afetam\n&7a quantia de &6Coins &7ganhos nas partidas.\n \n&8Os Multiplicadores podem variar de\n&8pessoais ou gerais, podendo beneficiar\n&8você e até mesmo os outros jogadores.\n \n&eClique para ver seus multiplicadores!"));

        this.setItem(41, BukkitUtils.deserializeItemStack(
                "MAP : 1 : esconder>tudo : nome>&aTítulos : desc>&7Esbanje estilo com um título exclusivo\n&7que ficará acima da sua cabeça para\n&7os outros jogadores.\n \n&8Lembrando que você não verá o título,\n&8apenas os outros jogadores.\n \n&eClique para selecionar um título!"));

        this.register(Core.getInstance());
        this.open();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent evt) {
        if (evt.getInventory().equals(this.getInventory())) {
            evt.setCancelled(true);

            if (evt.getWhoClicked().equals(this.player)) {
                Profile profile = Profile.getProfile(this.player.getName());
                if (profile == null) {
                    this.player.closeInventory();
                    return;
                }

                if (evt.getClickedInventory() != null && evt.getClickedInventory().equals(this.getInventory())) {
                    ItemStack item = evt.getCurrentItem();

                    if (item != null && item.getType() != Material.AIR) {
                        if (evt.getSlot() == 13) {
                            EnumSound.ITEM_PICKUP.play(this.player, 0.5F, 2.0F);
                        } else if (evt.getSlot() == 30) {
                            EnumSound.CLICK.play(this.player, 0.5F, 2.0F);
                            new MenuStatistics(profile);
                        } else if (evt.getSlot() == 31) {
                            EnumSound.CLICK.play(this.player, 0.5F, 2.0F);
                            new MenuPreferences(profile);
                        } else if (evt.getSlot() == 41) {
                            EnumSound.CLICK.play(this.player, 0.5F, 2.0F);
                            new MenuTitles(profile);
                        } else if (evt.getSlot() == 40) {
                            EnumSound.CLICK.play(this.player, 0.5F, 2.0F);
                            new MenuBoosters(profile);
                        } else if (evt.getSlot() == 39) {
                            EnumSound.CLICK.play(this.player, 0.5F, 2.0F);
                            new MenuAchievements(profile);
                        }
                    }
                }
            }
        }
    }

    public void cancel() {
        HandlerList.unregisterAll(this);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent evt) {
        if (evt.getPlayer().equals(this.player)) {
            this.cancel();
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent evt) {
        if (evt.getPlayer().equals(this.player) && evt.getInventory().equals(this.getInventory())) {
            this.cancel();
        }
    }
}
