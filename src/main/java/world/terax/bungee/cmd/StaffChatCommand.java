package world.terax.bungee.cmd;

import world.terax.player.role.Role;
import world.terax.utils.StringUtils;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;

public class StaffChatCommand extends Commands{

    public static ArrayList<ProxiedPlayer> STAFFS = new ArrayList<>();
    public StaffChatCommand() {
        super("sc", "staffchat");
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent("§cApenas jogadores podem utilizar este comando."));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (!player.hasPermission("command.staffchat")) {
            player.sendMessage(new TextComponent("§cVocê não tem permissão para executar este comando."));
            return;
        }
        if (args.length == 0) {
            if (!STAFFS.contains(player)) {
                STAFFS.add(player);
                sender.sendMessage(new TextComponent("§aTudo que você digitar no chat sairá no chatstaff."));
            } else {
                STAFFS.remove(player);
                sender.sendMessage(new TextComponent("§aTudo que você digitar no chat não sairá no chatstaff."));
            }
            return;
        }
        String msg = StringUtils.join(args, " ");
        BungeeCord.getInstance().getPlayers().stream().filter(playerx -> playerx.hasPermission("command.staffchat")).forEach(playerx -> {
            playerx.sendMessage(new TextComponent("§6[StaffChat] "
                    + Role.getPrefixed(playerx.getName()) + "§f: " + StringUtils.formatColors(msg)));
        });
    }

}
