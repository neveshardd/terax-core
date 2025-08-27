package dev.slickcollections.kiwizin.cmd;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand extends Commands{

    public GamemodeCommand() {
        super("gamemode", "gm");
    }

    @Override
    public void perform(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas jogadores podem utilizar este comando.");
            return;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("kutils.cmd.gm") || !player.hasPermission("kutils.cmd.gamemode")) {
            player.sendMessage("§cVocê não possui permissão para utilizar este comando.");
            return;
        }

        if (args.length == 0) {
            player.sendMessage("§cUtilize /gamemode [modo] [player]");
            return;
        }

        if (args.length == 1) {
            if (args[0].equals("0") || args[0].equalsIgnoreCase("survival")) {
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage("§aAlterado para modo §7Survival§a.");
            } else if (args[0].equals("1") || args[0].equalsIgnoreCase("creative")) {
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage("§aAlterado para modo §7Criativo§a.");
            } else if (args[0].equals("2") || args[0].equalsIgnoreCase("adventure")) {
                player.setGameMode(GameMode.ADVENTURE);
                player.sendMessage("§aAlterado para modo §7Aventura§a.");
            } else if (args[0].equals("3") || args[0].equalsIgnoreCase("spectator")) {
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage("§aAlterado para modo §7Espectador§a.");
            } else {
                player.sendMessage("§cUtilize /gamemode [modo] [player]");
            }
        } else {
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null || !target.isOnline()) {
                player.sendMessage("§cUsuário não encontrado.");
                return;
            }
            if (args[0].equals("0") || args[0].equalsIgnoreCase("survival")) {
                target.setGameMode(GameMode.SURVIVAL);
                target.sendMessage("§aAlterado para modo §7Sobrevivência §apor §f" + player.getName() + "§a.");
                player.sendMessage("§aVocê alterou o modo de §f" + target.getName() + "§a para §7Sobrevivência§a.");
            } else if (args[0].equals("1") || args[0].equalsIgnoreCase("creative")) {
                target.setGameMode(GameMode.CREATIVE);
                target.sendMessage("§aAlterado para modo §7Criativo §apor §f" + player.getName() + "§a.");
                player.sendMessage("§aVocê alterou o modo de §f" + target.getName() + "§a para §7Criativo§a.");
            } else if (args[0].equals("2") || args[0].equalsIgnoreCase("adventure")) {
                target.setGameMode(GameMode.ADVENTURE);
                target.sendMessage("§aAlterado para modo §7Aventura §apor §f" + player.getName() + "§a.");
                player.sendMessage("§aVocê alterou o modo de §f" + target.getName() + "§a para §7Aventura§a.");
            } else if (args[0].equals("3") || args[0].equalsIgnoreCase("spectator")) {
                target.setGameMode(GameMode.SPECTATOR);
                target.sendMessage("§aAlterado para modo §7Espectador §apor §f" + player.getName() + "§a.");
                player.sendMessage("§aVocê alterou o modo de §f" + target.getName() + "§a para §7Espectador§a.");
            } else {
                player.sendMessage("§cUtilize /gamemode [modo] [player]");
            }
        }
    }

}
