package world.terax.player.fake;

import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedSignedProperty;
import net.md_5.bungee.api.chat.*;
import world.terax.Core;
import world.terax.libraries.profile.Mojang;
import world.terax.player.role.Role;
import world.terax.plugin.config.Config;
import world.terax.utils.BukkitUtils;
import world.terax.utils.StringUtils;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FakeManager {
  
  public static final String STEVE =
      "eyJ0aW1lc3RhbXAiOjE1ODcxNTAzMTc3MjAsInByb2ZpbGVJZCI6IjRkNzA0ODZmNTA5MjRkMzM4NmJiZmM5YzEyYmFiNGFlIiwicHJvZmlsZU5hbWUiOiJzaXJGYWJpb3pzY2hlIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8xYTRhZjcxODQ1NWQ0YWFiNTI4ZTdhNjFmODZmYTI1ZTZhMzY5ZDE3NjhkY2IxM2Y3ZGYzMTlhNzEzZWI4MTBiIn19fQ==:syZ2Mt1vQeEjh/t8RGbv810mcfTrhQvnwEV7iLCd+5udVeroTa5NjoUehgswacTML3k/KxHZHaq4o6LmACHwsj/ivstW4PWc2RmVn+CcOoDKI3ytEm70LvGz0wAaTVKkrXHSw/RbEX/b7g7oQ8F67rzpiZ1+Z3TKaxbgZ9vgBQZQdwRJjVML2keI0669a9a1lWq3V/VIKFZc1rMJGzETMB2QL7JVTpQFOH/zXJGA+hJS5bRol+JG3LZTX93+DililM1e8KEjKDS496DYhMAr6AfTUfirLAN1Jv+WW70DzIpeKKXWR5ZeI+9qf48+IvjG8DhRBVFwwKP34DADbLhuebrolF/UyBIB9sABmozYdfit9uIywWW9+KYgpl2EtFXHG7CltIcNkbBbOdZy0Qzq62Tx6z/EK2acKn4oscFMqrobtioh5cA/BCRb9V4wh0fy5qx6DYHyRBdzLcQUfb6DkDx1uyNJ7R5mO44b79pSo8gdd9VvMryn/+KaJu2UvyCrMVUtOOzoIh4nCMc9wXOFW3jZ7ZTo4J6c28ouL98rVQSAImEd/P017uGvWIT+hgkdXnacVG895Y6ilXqJToyvf1JUQb4dgry0WTv6UTAjNgrm5a8mZx9OryLuI2obas97LCon1rydcNXnBtjUk0TUzdrvIa5zNstYZPchUb+FSnU=";
  public static final String ALEX =
      "eyJ0aW1lc3RhbXAiOjE1ODcxMzkyMDU4MzUsInByb2ZpbGVJZCI6Ijc1MTQ0NDgxOTFlNjQ1NDY4Yzk3MzlhNmUzOTU3YmViIiwicHJvZmlsZU5hbWUiOiJUaGFua3NNb2phbmciLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNiNjBhMWY2ZDU2MmY1MmFhZWJiZjE0MzRmMWRlMTQ3OTMzYTNhZmZlMGU3NjRmYTQ5ZWEwNTc1MzY2MjNjZDMiLCJtZXRhZGF0YSI6eyJtb2RlbCI6InNsaW0ifX19fQ==:W60UUuAYlWfLFt5Ay3Lvd/CGUbKuuU8+HTtN/cZLhc0BC22XNgbY1btTite7ZtBUGiZyFOhYqQi+LxVWrdjKEAdHCSYWpCRMFhB1m0zEfu78yg4XMcFmd1v7y9ZfS45b3pLAJ463YyjDaT64kkeUkP6BUmgsTA2iIWvM33k6Tj3OAM39kypFSuH+UEpkx603XtxratD+pBjUCUvWyj2DMxwnwclP/uACyh0ZVrI7rC5xJn4jSura+5J2/j6Z/I7lMBBGLESt7+pGn/3/kArDE/1RShOvm5eYKqrTMRfK4n3yd1U1DRsMzxkU2AdlCrv1swT4o+Cq8zMI97CF/xyqk8z2L98HKlzLjtvXIE6ogljyHc9YsfU9XhHwZ7SKXRNkmHswOgYIQCSa1RdLHtlVjN9UdUyUoQIIO2AWPzdKseKJJhXwqKJ7lzfAtStErRzDjmjr7ld/5tFd3TTQZ8yiq3D6aRLRUnOMTr7kFOycPOPhOeZQlTjJ6SH3PWFsdtMMQsGzb2vSukkXvJXFVUM0TcwRZlqT5MFHyKBBPprIt0wVN6MmSKc8m5kdk7ZBU2ICDs/9Cd/fyzAIRDu3Kzm7egbAVK9zc1kXwGzowUkGGy1XvZxyRS5jF1zu6KzVgaXOGcrOLH4z/OHzxvbyW22/UwahWGN7MD4j37iJ7gjZDrk=";
  
  private static final Config CONFIG = Core.getInstance().getConfig("utils");
  private static final Pattern REAL_PATTERN = Pattern.compile("(?i)corefakereal:\\w*"), NOT_CHANGE_PATTERN = Pattern.compile("(?i)corenotchange:\\w*");
  
  public static Map<String, String> fakeNames = new HashMap<>();
  public static Map<String, Role> fakeRoles = new HashMap<>();
  public static Map<String, String> fakeSkins = new HashMap<>();
  
  private static TextComponent FAKE_ROLES;
  private static TextComponent FAKE_SKINS;
  
  private static List<String> randoms;
  private static Boolean bungeeSide;

    public static void setupFake() {
        if (CONFIG.get("fake.role") instanceof String) {
            CONFIG.set("fake.role", Arrays.asList(CONFIG.getString("fake.role", "")));
        }

        // Menu de cargos
        FAKE_ROLES = new TextComponent("");
        FAKE_ROLES.addExtra(new TextComponent("§5§lALTERAR NICKNAME\n \n§0Escolha o cargo que gostaria de utilizar enquanto está disfarçado:\n "));

        for (String roleName : CONFIG.getStringList("fake.role")) {
            Role role = Role.getRoleByName(roleName);
            if (role != null) {
                TextComponent component = new TextComponent("\n §0▪ " + role.getName());
                component.setHoverEvent(new HoverEvent(
                        HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder("§7Seu nickname será exibido como '" + role.getPrefix() + "Nickname'§7.").create()
                ));
                component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/fake " + roleName));
                FAKE_ROLES.addExtra(component);
            }
        }

        // Menu de skins
        FAKE_SKINS = new TextComponent("");
        FAKE_SKINS.addExtra(new TextComponent("§5§lALTERAR NICKNAME\n \n§0Enquanto disfarçado, sua skin será alterada para ajudar a te camuflar.\n \n§0Escolha sua skin:\n "));

        TextComponent STEVE = new TextComponent("\n §0▪ §7Steve");
        STEVE.setHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("§7Você irá obter a aparência de Steve.").create()
        ));
        STEVE.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/fake {role} steve"));

        TextComponent ALEX = new TextComponent("\n §0▪ §7Alex");
        ALEX.setHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("§7Você irá obter a aparência da Alex.").create()
        ));
        ALEX.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/fake {role} alex"));

        TextComponent YOU = new TextComponent("\n §0▪ §7Você");
        YOU.setHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("§7Você irá obter a sua aparência.").create()
        ));
        YOU.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/fake {role} you"));

        // Adiciona os botões na ordem que preferir
        FAKE_SKINS.addExtra(STEVE);
        FAKE_SKINS.addExtra(YOU);
        FAKE_SKINS.addExtra(ALEX);
    }


    public static void sendRole(Player player) {
    ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
    BookMeta meta = (BookMeta) book.getItemMeta();
    meta.setAuthor("Nys");
    meta.setTitle("Escolher cargo");
    book.setItemMeta(meta);
    book = BukkitUtils.setNBTList(book, "pages", Collections.singletonList(ComponentSerializer.toString(FAKE_ROLES)));
    BukkitUtils.openBook(player, book);
  }
  
  public static void sendSkin(Player player, String role) {
    ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
    BookMeta meta = (BookMeta) book.getItemMeta();
    meta.setAuthor("Nys");
    meta.setTitle("Escolher cargo");
    book.setItemMeta(meta);
    book = BukkitUtils.setNBTList(book, "pages", Collections.singletonList(ComponentSerializer.toString(FAKE_SKINS).replace("{role}", role)));
    BukkitUtils.openBook(player, book);
  }
  
  public static void applyFake(Player player, String fakeName, String role, String skin) {
    if (!isBungeeSide()) {
      player.kickPlayer(StringUtils.formatColors(CONFIG.getString("fake.kick-apply", "")).replace("\\n", "\n"));
    }
    fakeNames.put(player.getName(), fakeName);
    fakeRoles.put(player.getName(), Role.getRoleByName(role));
    fakeSkins.put(player.getName(), skin);
  }
  
  public static void removeFake(Player player) {
    if (!isBungeeSide()) {
      player.kickPlayer(StringUtils.formatColors(CONFIG.getString("fake.kick-remove", "")).replace("\\n", "\n"));
    }
    fakeNames.remove(player.getName());
    fakeRoles.remove(player.getName());
    fakeSkins.remove(player.getName());
  }
  
  public static String getCurrent(String playerName) {
    return isFake(playerName) ? getFake(playerName) : playerName;
  }
  
  public static String getFake(String playerName) {
    return fakeNames.get(playerName);
  }
  
  public static Role getRole(String playerName) {
    return fakeRoles.getOrDefault(playerName, Role.getLastRole());
  }
  
  public static String getSkin(String playerName) {
    return fakeSkins.getOrDefault(playerName, STEVE);
  }
  
  public static boolean isFake(String playerName) {
    return fakeNames.containsKey(playerName);
  }
  
  public static boolean isUsable(String name) {
    return !fakeNames.containsKey(name) && !fakeNames.containsValue(name) && Bukkit.getPlayer(name) == null;
  }
  
  public static List<String> listNicked() {
    return new ArrayList<>(fakeNames.keySet());
  }
  
  public static List<String> getRandomNicks() {
    if (randoms == null) {
      randoms = CONFIG.getStringList("fake.randoms");
    }
    
    return randoms;
  }
  
  public static boolean isFakeRole(String roleName) {
    return CONFIG.getStringList("fake.role").stream().anyMatch(role -> role.equalsIgnoreCase(roleName));
  }
  
  public static boolean isBungeeSide() {
    if (bungeeSide == null) {
      bungeeSide = CONFIG.getBoolean("bungeecord");
    }
    
    return bungeeSide;
  }
  
  public static String replaceNickedChanges(String original) {
    String replaced = original;
    for (String name : listNicked()) {
      Matcher matcher = Pattern.compile("(?i)" + name).matcher(replaced);
      
      while (matcher.find()) {
        replaced = replaced.replaceFirst(Pattern.quote(matcher.group()), Matcher.quoteReplacement("corenotchange:" + name));
      }
    }
    
    return replaced;
  }
  
  public static String replaceNickedPlayers(String original, boolean toFake) {
    String replaced = original;
    List<String> backup = new ArrayList<>();
    for (String name : listNicked()) {
      Matcher matcher = NOT_CHANGE_PATTERN.matcher(replaced);
      while (matcher.find()) {
        String found = matcher.group();
        backup.add(found.replace("corenotchange:", ""));
        replaced = replaced.replaceFirst(Pattern.quote(found), Matcher.quoteReplacement("corenotchange:" + (backup.size() - 1)));
      }
      
      matcher = Pattern.compile("(?i)" + (toFake ? name : getFake(name))).matcher(replaced);
      while (matcher.find()) {
        replaced = replaced.replaceFirst(Pattern.quote(matcher.group()), Matcher.quoteReplacement(toFake ? getFake(name) : name));
      }
    }
    
    Matcher matcher = REAL_PATTERN.matcher(replaced);
    while (matcher.find()) {
      String found = matcher.group();
      replaced = replaced.replaceFirst(Pattern.quote(found), Matcher.quoteReplacement(
          fakeNames.entrySet().stream().filter(entry -> entry.getValue().equals(found.replace("corefakereal:", ""))).map(Map.Entry::getKey).findFirst().orElse("")));
    }
    
    matcher = NOT_CHANGE_PATTERN.matcher(replaced);
    while (matcher.find()) {
      String found = matcher.group();
      replaced = replaced.replaceFirst(Pattern.quote(matcher.group()), Matcher.quoteReplacement(backup.get(Integer.parseInt(found.replace("corenotchange:", "")))));
    }
    
    backup.clear();
    return replaced;
  }
  
  public static WrappedGameProfile cloneProfile(WrappedGameProfile profile) {
    WrappedGameProfile gameProfile = profile.withName(getFake(profile.getName()));
    gameProfile.getProperties().clear();
    
    try {
      String id = Mojang.getUUID(gameProfile.getName());
      if (id != null) {
        String textures = Mojang.getSkinProperty(id);
        if (textures != null) {
          gameProfile.getProperties().put("textures", new WrappedSignedProperty(textures.split(" : ")[0], textures.split(" : ")[1], textures.split(" : ")[2]));
        }
      }
    } catch (Exception ignore) {
    }
    
    return gameProfile;
  }
}