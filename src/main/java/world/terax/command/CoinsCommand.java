package world.terax.command;

import world.terax.player.Profile;
import world.terax.utils.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsCommand extends Commands {
  
  public CoinsCommand() {
    super("coins");
  }
  
  @Override
  public void perform(CommandSender sender, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player) sender;
      Profile profile = Profile.getProfile(player.getName());
      player.sendMessage("\n§eSeus coins:");
      
      for (String name : new String[]{"Bed Wars", "Murder", "The Bridge", "Sky Wars"}) {
        player.sendMessage(" §8▪ §f" + name + " §7" + StringUtils
            .formatNumber(profile.getCoins("Core" + name.replace(" ", ""))));
      }
      
      player.sendMessage("\n");
    } else {
      sender.sendMessage("§cApenas jogadores podem utilizar este comando.");
    }
  }
}