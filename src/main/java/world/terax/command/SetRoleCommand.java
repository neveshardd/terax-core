package world.terax.command;

import world.terax.Language;
import world.terax.menus.role.MenuSetRole;
import world.terax.nms.NMS;
import world.terax.player.Profile;
import world.terax.player.role.Role;
import world.terax.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetRoleCommand extends Commands{

    public SetRoleCommand() {
        super("setrole", "setgroup", "group", "role");
    }

    @Override
    public void perform(CommandSender sender, String label, String[] args) {
        Player player = null;
        boolean isPlayer = sender instanceof Player;
        if (isPlayer) {
            player = (Player) sender;
            if (!player.hasPermission("command.setgroup")) {
                player.sendMessage("§cVocê não tem permissão para executar este comando.");
                return;
            }
        }

        if (args.length == 0) {
            if (isPlayer) {
                player.sendMessage("Utilize /setrole [player] ou /setrole [player] [grupo]");
            } else {
                sender.sendMessage("Console precisa fornecer argumentos: /setrole [player] [grupo]");
            }
            return;
        }

        if (args.length == 1) {
            if (isPlayer) {
                new MenuSetRole(Profile.getProfile(player.getName()), args[0]);
            } else {
                sender.sendMessage("Console precisa fornecer o grupo: /setrole [player] [grupo]");
            }
            return;
        }

        // args.length >= 2
        if (Role.getRoleByName(args[1]) == null) {
            if (isPlayer) {
                player.sendMessage("§cUtilize apenas grupos válidos.");
            } else {
                sender.sendMessage("Grupo inválido.");
            }
            return;
        }

        // Executa o comando de mudança de role
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + args[0] + " parent set " + args[1]);

        String roleName = Role.getRoleByName(args[1]).getName();

        if (isPlayer) {
            player.sendMessage(Language.roles$set.replace("{player}", args[0]).replace("{role}", roleName));
        } else {
            sender.sendMessage("Grupo " + roleName + " definido para " + args[0]);
        }

        Bukkit.getOnlinePlayers().forEach(p ->
                NMS.sendTitle(p,
                        Language.roles$titles$header.replace("{player}", StringUtils.getFirstColor(roleName) + " " + args[0]),
                        Language.roles$titles$footer.replace("{role}", roleName))
        );

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target != null && target.isOnline()) {
            target.sendMessage(Language.roles$notification.replace("{role}", roleName));
        }
    }


}
