package dev.slickcollections.kiwizin.bungee.cmd;

import dev.slickcollections.kiwizin.player.role.Role;
import dev.slickcollections.kiwizin.utils.StringUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import static dev.slickcollections.kiwizin.bungee.Bungee.tell;

public class ReplyCommand extends Commands{

    public ReplyCommand() {
        super("r", "reply");
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(TextComponent.fromLegacyText("§cApenas jogadores podem utilizar este comando."));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (args.length == 0) {
            player.sendMessage(TextComponent.fromLegacyText("§cUtilize /r [mensagem]"));
            return;
        }
        if (!tell.containsKey(player)) {
            player.sendMessage(TextComponent.fromLegacyText("§cVocê não tem ninguém para responder."));
            return;
        }
        ProxiedPlayer tKL = tell.get(player);
        String msg = StringUtils.join(args, " ");
        if (player.hasPermission("kutils.tell.color")) {
            msg = StringUtils.formatColors(msg);
        }
        if (tKL == null || !tKL.isConnected()) {
            player.sendMessage(TextComponent.fromLegacyText("§cUsuário não encontrado."));
            return;
        }
        tell.put(tKL, player);
        tell.put(player, tKL);

        tKL.sendMessage(TextComponent.fromLegacyText("§8Mensagem de "
                + Role.getPrefixed(player.getName()) + "§8: §6" + msg));
        player.sendMessage(TextComponent.fromLegacyText("§8Mensagem para "
                + Role.getPrefixed(tKL.getName()) + "§8: §6" + msg));
    }

}
