package world.terax.player.role;

import world.terax.Core;
import world.terax.Manager;
import world.terax.database.Database;
import world.terax.database.cache.RoleCache;
import world.terax.plugin.config.Config;
import world.terax.utils.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Role {

    private static final List<Role> ROLES = new ArrayList<>();
    private final int id;
    private final String name;
    private String prefix;
    private final String permission;
    private final boolean alwaysVisible;
    private final boolean broadcast;
    private final String icon;
    private final String cmd;

    public static void setupRoles() {
        if (!new File("plugins/" + Core.getInstance().getDescription().getName() + "/roles.yml").exists()) {
            Core.getInstance().saveResource("roles.yml", false);
        }

        Config config = Core.getInstance().getConfig("roles");
        for (String key : config.getSection("roles").getKeys(false)) {
            listRoles().add(new Role(
                            config.getString("roles." + key + ".name", "§7Membro"),
                            config.getString("roles." + key + ".prefix", "§7"),
                            config.getString("roles." + key + ".permission", ""),
                            config.getBoolean("roles." + key + ".alwaysvisible", false),
                            config.getBoolean("roles." + key + ".broadcast", true),
                            config.getString("roles." + key + ".icon", "STONE:1"),
                            config.getString("roles." + key + ".cmd", "")
                    )
            );

        }
    }

    public Role(String name, String prefix, String permission, boolean alwaysVisible, boolean broadcast, String icon, String cmd) {
        this.id = ROLES.size();
        this.name = StringUtils.formatColors(name != null ? name : "§7Membro");
        this.prefix = StringUtils.formatColors(prefix != null ? prefix : "§7");
        this.permission = permission != null ? permission : "";
        this.alwaysVisible = alwaysVisible;
        this.broadcast = broadcast;
        this.icon = icon != null ? icon : "STONE:1";
        this.cmd = cmd != null ? cmd : "";
    }


    public static String getPrefixed(String name) {
        return getPrefixed(name, false);
    }

    public static String getColored(String name) {
        return getColored(name, false);
    }

    public static String getPrefixed(String name, boolean removeFake) {
        return getTaggedName(name, false, removeFake);
    }

    public static String getColored(String name, boolean removeFake) {
        return getTaggedName(name, true, removeFake);
    }

    private static String getTaggedName(String name, boolean onlyColor, boolean removeFake) {
        String prefix = "&7";
        if (!removeFake && Manager.isFake(name)) {
            prefix = Manager.getFakeRole(name).getPrefix();
            if (onlyColor) {
                prefix = StringUtils.getLastColor(prefix);
            }

            return prefix + Manager.getFake(name);
        }

        Object target = Manager.getPlayer(name);
        if (target != null) {
            prefix = getPlayerRole(target, true).getPrefix();
            if (onlyColor) {
                prefix = StringUtils.getLastColor(prefix);
            }
            return prefix + name;
        }

        String rs = RoleCache.isPresent(name) ? RoleCache.get(name) : Database.getInstance().getRankAndName(name);
        if (rs != null) {
            prefix = getRoleByName(rs.split(" : ")[0]).getPrefix();
            if (onlyColor) {
                prefix = StringUtils.getLastColor(prefix);
            }
            name = rs.split(" : ")[1];
            if (!removeFake && Manager.isFake(name)) {
                name = Manager.getFake(name);
            }
            return prefix + name;
        }

        return prefix + name;
    }

    public static Role getRoleByName(String name) {
        for (Role role : ROLES) {
            if (StringUtils.stripColors(role.getName()).equalsIgnoreCase(name)) {
                return role;
            }
        }

        return ROLES.get(ROLES.size() - 1);
    }

    public static Role getRoleByPermission(String permission) {
        for (Role role : ROLES) {
            if (role.getPermission().equals(permission)) {
                return role;
            }
        }

        return null;
    }

    public Role getRoleByPrefix() {
        for (Role role : Role.listRoles()) {
            if (role.getPrefix().equals(prefix)) {
                return role;
            }
        }

        return null;
    }

    public String getCmd() {
        return cmd;
    }

    public String getIcon() {
        return icon;
    }

    public Role getRole() {
        return getRoleByPrefix();
    }

    public static Role getPlayerRole(Object player) {
        return getPlayerRole(player, false);
    }

    public static Role getPlayerRole(Object player, boolean removeFake) {
        if (!removeFake && Manager.isFake(Manager.getName(player))) {
            return Manager.getFakeRole(Manager.getName(player));
        }

        for (Role role : ROLES) {
            if (role.has(player)) {
                return role;
            }
        }

        return getLastRole();
    }

    public static Role getLastRole() {
        return ROLES.get(ROLES.size() - 1);
    }

    public static List<Role> listRoles() {
        return ROLES;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getPermission() {
        return this.permission;
    }

    public boolean isDefault() {
        return this.permission.isEmpty();
    }

    public boolean isAlwaysVisible() {
        return this.alwaysVisible;
    }

    public boolean isBroadcast() {
        return this.broadcast;
    }

    public boolean has(Object player) {
        return this.isDefault() || Manager.hasPermission(player, this.permission);
    }
}
