package world.terax.command;

import world.terax.nms.NMS;
import world.terax.utils.StringUtils;
import world.terax.utils.enums.EnumSound;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarningCommand extends Commands{

    public WarningCommand() {
        super("aviso", "bc", "alert");
    }


    @Override
    public void perform(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas jogadores podem executar este comando.");
            return;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("command.aviso")) {
            player.sendMessage("§cVocê não tem permissão para executar este comando.");
            return;
        }
        if (args.length == 0) {
            player.sendMessage("§cUtilize /aviso [mensagem]");
            return;
        }
        Bukkit.getOnlinePlayers().forEach(playerr -> {
            EnumSound.ORB_PICKUP.play(playerr, 1.0F, 1.0F);
            NMS.sendTitle(playerr, "§6§lAVISO", StringUtils.formatColors(StringUtils.join(args, " ").replace("&", "")));
            playerr.sendMessage("");
            playerr.sendMessage("§6§lAVISO");
            playerr.sendMessage("");
            playerr.sendMessage(" " + StringUtils.join(args, " ").replace("&", "§"));
            playerr.sendMessage("");
        });
    }

}
