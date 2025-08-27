package dev.slickcollections.kiwizin.cmd;

import dev.slickcollections.kiwizin.player.Profile;
import dev.slickcollections.kiwizin.titles.Title;
import dev.slickcollections.kiwizin.utils.enums.EnumSound;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.stream.Collectors;

public class TitleCommand extends Commands {

    public TitleCommand() {
        super("title", "titles");
    }

    @Override
    public void perform(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas jogadores podem utilizar este comando.");
            return;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("kcore.cmd.title")) {
            player.sendMessage("§cVocê não possui permissão para utilizar este comando.");
            return;
        }

        // Se não houver argumentos ou for "list" → listar titles disponíveis
        if (args.length == 0 || args[0].equalsIgnoreCase("list")) {
            String titles = Title.listTitles().stream()
                    .map(title -> title.getId() + " §7- §f" + title.getTitle())
                    .collect(Collectors.joining("\n"));

            player.sendMessage("§eTitles disponíveis:\n" + titles);
            player.sendMessage("§eUse /title <titleId> <player> para adicionar um title a um jogador.");
            return;
        }

        // Verifica se o comando recebeu os argumentos mínimos para setar o title
        if (args.length < 2) {
            player.sendMessage("§eUso correto: /title <titleId> <player>");
            return;
        }

        String titleId = args[0];
        String targetName = args[1];

        Title title = Title.getById(titleId);
        if (title == null) {
            EnumSound.VILLAGER_NO.play(player, 1.0F, 1.0F);
            player.sendMessage("§cTitle não encontrado: " + titleId);
            return;
        }

        Player targetPlayer = Bukkit.getPlayer(targetName);
        if (targetPlayer == null) {
            EnumSound.VILLAGER_NO.play(player, 1.0F, 1.0F);
            player.sendMessage("§cJogador não encontrado: " + targetName);
            return;
        }

        Profile targetProfile = Profile.getProfile(targetPlayer.getName());
        if (targetProfile == null) {
            player.sendMessage("§cProfile do jogador não encontrado.");
            return;
        }

        // Adiciona o title ao container do jogador
        title.give(targetProfile);

        EnumSound.ORB_PICKUP.play(player, 1.0F, 2.0F);
        player.sendMessage("§aTitle §e" + title.getTitle() + " §afoi adicionado para §e" + targetPlayer.getName());
        targetPlayer.sendMessage("§aVocê recebeu um novo title: §e" + title.getTitle());
    }
}
