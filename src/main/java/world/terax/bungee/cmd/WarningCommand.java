package world.terax.bungee.cmd;

import world.terax.player.role.Role;
import world.terax.utils.StringUtils;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class WarningCommand extends Commands{

    public WarningCommand() {
        super("aviso", "bc");
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent("§cApenas jogadores podem utilizar este comando."));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (!player.hasPermission("command.warning")) {
            player.sendMessage(new TextComponent("§cVocê não tem permissão para executar este comando."));
            return;
        }
        if (args.length == 0) {
            player.sendMessage(new TextComponent("§cUtilize /aviso [mensagem]"));
            return;
        }
        String msg = StringUtils.join(args, " ");
        BungeeCord.getInstance().getPlayers().forEach(ppr -> {
            ppr.sendMessage(new TextComponent(""));
            ppr.sendMessage(new TextComponent(" " + Role.getPrefixed(player.getName()) + "§f: " + StringUtils.formatColors(msg)));
            ppr.sendMessage(new TextComponent(""));

        });
    }

}
