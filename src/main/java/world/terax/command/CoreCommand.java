package world.terax.command;

import world.terax.Core;
import world.terax.database.Database;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoreCommand extends Commands {

    public CoreCommand() {
        super("core", "c");
    }

    @Override
    public void perform(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas jogadores podem utilizar este comando.");
            return;
        }

        Player player = (Player) sender;

        // Se o jogador não tem permissão
        if (!player.hasPermission("core.admin")) {
            player.sendMessage("§6Core §bv" + Core.getInstance().getDescription().getVersion() + " §7Criado por §6neveshardd§7.");
            return;
        }

        // Se nenhum argumento for passado
        if (args.length == 0) {
            player.sendMessage(
                            "§6/core converter §f- §7Converter seu Banco de Dados.\n "
            );
            return;
        }

        // Com argumentos
        String action = args[0];

        if (action.equalsIgnoreCase("converter")) {
            player.sendMessage("§fBanco de Dados: " + Database.getInstance().getClass().getSimpleName().replace("Database", ""));
            Database.getInstance().convertDatabase(player);
        } else {
            player.sendMessage(
                    "§cSubcomando desconhecido!\n" +
                            "§6/core atualizar §f- §7Atualizar o Core.\n" +
                            "§6/core converter §f- §7Converter seu Banco de Dados.\n "
            );
        }
    }
}
