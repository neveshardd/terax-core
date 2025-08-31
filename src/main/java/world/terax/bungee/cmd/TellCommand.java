package world.terax.bungee.cmd;

import world.terax.player.role.Role;
import world.terax.utils.StringUtils;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import static world.terax.bungee.Bungee.tell;

public class TellCommand extends Commands{

    public TellCommand() {
        super("tell", "whisper");
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent("§cApenas jogadores podem utilizar este comando."));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (args.length < 2) {
            player.sendMessage(new TextComponent("§cUtilize /tell [jogador] [mensagem]"));
            return;
        }
        ProxiedPlayer tKL = BungeeCord.getInstance().getPlayer(args[0]);
        if (player == tKL) {
            player.sendMessage(new TextComponent("§cVocê não pode mandar mensagens privadas para sí mesmo."));
            return;
        }
        String msg = StringUtils.join(args, 1, " ");
        if (player.hasPermission("tell.color")) {
            msg = StringUtils.formatColors(msg);
        }
        if (tKL == null || !tKL.isConnected()) {
            player.sendMessage(new TextComponent("§cUsuário não encontrado."));
            return;
        }
        tell.put(tKL, player);
        tell.put(player, tKL);

        tKL.sendMessage(new TextComponent("§8Mensagem de "
                + Role.getPrefixed(player.getName()) + "§8: §6" + msg));
        player.sendMessage(new TextComponent("§8Mensagem para "
                + Role.getPrefixed(tKL.getName()) + "§8: §6" + msg));
    }

}
