package world.terax.command;

import world.terax.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static world.terax.Core.reply;

public class TellCommand extends Commands{

    public TellCommand() {
        super("tell");
    }

    @Override
    public void perform(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas jogadores podem executar este comando.");
            return;
        }
        Player player = (Player) sender;
        if (args.length < 2) {
            player.sendMessage("§cUtilize /tell [player] [mensagem]");
            return;
        }
        if (args[0].equals(player.getName())) {
            player.sendMessage("§cVocê não pode mandar mensagens privadas para sí mesmo.");
            return;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null || !target.isOnline()) {
            player.sendMessage("§cUsuário não encontrado.");
            return;
        }
        reply.put(player, target);
        reply.put(target, player);
        String msg = StringUtils.join(args, 1, " ");
        if (player.hasPermission("tell.color")) {
            msg = msg.replace("&", "§");
        }
        target.sendMessage("§8Mensagem de " + player.getName() + ": §6" + msg);
        player.sendMessage("§8Mensagem para " + target.getName() + ": §6" + msg);
    }

}
