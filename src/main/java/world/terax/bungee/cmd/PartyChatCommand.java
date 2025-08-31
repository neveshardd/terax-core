package world.terax.bungee.cmd;

import world.terax.bungee.party.BungeeParty;
import world.terax.bungee.party.BungeePartyManager;
import world.terax.player.role.Role;
import world.terax.utils.StringUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PartyChatCommand extends Commands {
  
  public PartyChatCommand() {
    super("p");
  }
  
  @Override
  public void perform(CommandSender sender, String[] args) {
    if (!(sender instanceof ProxiedPlayer)) {
      sender.sendMessage(new TextComponent("§cApenas jogadores podem utilizar este comando."));
      return;
    }
    
    ProxiedPlayer player = (ProxiedPlayer) sender;
    if (args.length == 0) {
      player.sendMessage(new TextComponent("§cUtilize /p [mensagem] para conversar com a sua Party."));
      return;
    }
    
    BungeeParty party = BungeePartyManager.getMemberParty(player.getName());
    if (party == null) {
      player.sendMessage(new TextComponent("§cVocê não pertence a uma Party."));
      return;
    }
    
    party.broadcast("§d[Party] " + Role.getPrefixed(player.getName()) + "§f: " + StringUtils.join(args, " "));
  }
}
