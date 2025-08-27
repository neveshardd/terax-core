package dev.slickcollections.kiwizin.cmd;

import dev.slickcollections.kiwizin.Language;
import dev.slickcollections.kiwizin.menus.role.MenuSetRole;
import dev.slickcollections.kiwizin.nms.NMS;
import dev.slickcollections.kiwizin.player.Profile;
import dev.slickcollections.kiwizin.player.role.Role;
import dev.slickcollections.kiwizin.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetRoleCommand extends Commands{

    public SetRoleCommand() {
        super("setrole", "setgroup");
    }

    @Override
    public void perform(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas jogadores podem utilizar este comando.");
            return;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("kutils.cmd.setgroup")) {
            player.sendMessage("§cVocê não tem permissão para executar este comando.");
            return;
        }
        if (args.length == 0) {
            player.sendMessage("Utilize /setrole [player] ou /setrole [player] [grupo]");
            return;
        }
        if (args.length == 1) {
            new MenuSetRole(Profile.getProfile(player.getName()), args[0]);
        } else {
            if (Role.getRoleByName(args[1]) == null) {
                player.sendMessage("§cUtilize apenas grupos válidos.");
                return;
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + args[0] + " parent set " + args[1]);
            player.sendMessage(Language.roles$set.replace("{player}", args[0]).replace("{role}", Role.getRoleByName(args[1]).getName()));
            Bukkit.getOnlinePlayers().forEach(playerr -> NMS.sendTitle(playerr, Language.roles$titles$header.replace("{player}", StringUtils.getFirstColor(Role.getRoleByName(args[1]).getName()) + " " + args[0]), Language.roles$titles$footer.replace("{role}", Role.getRoleByName(args[1]).getName())));
            if (Bukkit.getPlayerExact(args[0]) != null) {
                if (Bukkit.getPlayerExact(args[0]).isOnline()) {
                    Bukkit.getPlayerExact(args[0]).sendMessage(Language.roles$notification.replace("{role}", Role.getRoleByName(args[1]).getName()));
                }
            }
        }
    }

}
