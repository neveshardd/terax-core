package dev.slickcollections.kiwizin.cmd;

import dev.slickcollections.kiwizin.nms.NMS;
import dev.slickcollections.kiwizin.player.role.Role;
import dev.slickcollections.kiwizin.utils.StringUtils;
import dev.slickcollections.kiwizin.utils.enums.EnumSound;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StaffChatCommand extends Commands{

    public static List<Player> STAFFS = new ArrayList<>();

    public StaffChatCommand() {
        super("sc", "staffchat");
    }

    @Override
    public void perform(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas jogadores podem executar este comando.");
            return;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("kutils.cmd.staffchat")) {
            player.sendMessage("§cVocê não tem permissão para executar este comando.");
            return;
        }
        if (args.length == 0) {
            if (STAFFS.contains(player)) {
                player.sendMessage("§cAgora tudo que você escrever no chat não aparecerá no chat staff.");
                STAFFS.remove(player);
            } else {
                player.sendMessage("§aAgora tudo que você escrever no chat aparecerá no chat staff.");
                STAFFS.add(player);
            }
            return;
        }

        Bukkit.getOnlinePlayers().stream().filter(playerx -> playerx.hasPermission("kutils.cmd.staffchat")).forEach(playerx -> {
            NMS.sendActionBar(playerx, "§eHá uma nova mensagem no chat da staff!");
            EnumSound.ORB_PICKUP.play(playerx, 1.0F, 1.0F);

            playerx.sendMessage("§6[StaffChat] " + Role.getPrefixed(player.getName(), true) + "§f: " + StringUtils.join(args, "").replace("&", "§"));
        });
    }

}
