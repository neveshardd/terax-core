package world.terax.command;

import world.terax.utils.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static world.terax.Core.reply;

public class ReplyCommand extends Commands{

    public ReplyCommand() {
        super("r", "reply");
    }

    @Override
    public void perform(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas jogadores podem executar este comando.");
            return;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage("§cUtilize /tell [mensagem]");
            return;
        }
        Player target = reply.get(player);
        if (target == null) {
            player.sendMessage("§cVocê não tem ninguém para responder.");
            return;
        }
        if (!target.isOnline()) {
            player.sendMessage("§cUsuário não encontrado.");
            return;
        }
        reply.put(target, player);
        String msg = StringUtils.join(args, " ");
        if (player.hasPermission("tell.color")) {
            msg = msg.replace("&", "§");
        }
        target.sendMessage("§8Mensagem de " + player.getName() + ": §6" + msg);
        player.sendMessage("§8Mensagem para " + target.getName() + ": §6" + msg);
    }

}
