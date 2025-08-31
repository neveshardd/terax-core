package world.terax.bungee.cmd;

import world.terax.bungee.Bungee;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class FakeResetCommand extends Commands {
  
  public FakeResetCommand() {
    super("faker");
  }
  
  @Override
  public void perform(CommandSender sender, String[] args) {
    if (!(sender instanceof ProxiedPlayer)) {
      sender.sendMessage(new TextComponent("§cApenas jogadores podem utilizar este comando."));
      return;
    }
    
    ProxiedPlayer player = (ProxiedPlayer) sender;
    if (!player.hasPermission("command.fake")) {
      player.sendMessage(new TextComponent("§cVocê não possui permissão para utilizar este comando."));
      return;
    }
    
    if (!Bungee.isFake(player.getName())) {
      player.sendMessage(new TextComponent("§cVocê não está utilizando um nickname falso."));
      return;
    }
    
    Bungee.removeFake(player);
  }
}
