package dev.slickcollections.kiwizin.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OnlineCommand extends Commands{

    public OnlineCommand() {
        super("online");
    }

    @Override
    public void perform(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas jogadores podem executar este comando.");
            return;
        }
        Player player = (Player) sender;

        if (Bukkit.getOnlinePlayers().size() < 2) {
            player.sendMessage("§aSomente Você está online neste servidor.");
            return;
        }
        player.sendMessage("§aHá §e" + Bukkit.getOnlinePlayers().size() + " §ajogadores no seu servidor atual!");
    }

}
