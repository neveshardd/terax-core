package world.terax;

import world.terax.plugin.config.Config;
import world.terax.plugin.config.Writer;
import world.terax.plugin.logger.Logger;
import world.terax.utils.StringUtils;
import org.bukkit.Bukkit;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Language {

    public static String roles$default = "§7Membro";
    public static String roles$set = "§aVocê setou o grupo de {player} para {role}§a.";
    public static String roles$notification = "\n§aAgora você é {role}§a! Caso seu grupo\n§anão tenha atualizado basta relogar.\n \n";
    public static String roles$titles$header = "{player}";
    public static String roles$titles$footer = "§fTornou-se {role}";

    public static final Logger LOGGER = ((Logger) Core.getInstance().getLogger())
            .getModule("LANGUAGE");
    private static final Config CONFIG = Core.getInstance().getConfig("language");

    public static void setupLanguage() {
        boolean save = false;
        Writer writer = Core.getInstance().getWriter(CONFIG.getFile(),
                "Core - Criado por neveshardd\nVersão da configuração: " + Core.getInstance()
                        .getDescription().getVersion());
        for (Field field : Language.class.getDeclaredFields()) {
            if (field.getName().contains("$") && !Modifier.isFinal(field.getModifiers())) {
                String nativeName = field.getName().replace("$", ".").replace("_", "-");

                try {
                    Object value;
                    Writer.YamlEntryInfo entryInfo = field.getAnnotation(Writer.YamlEntryInfo.class);

                    if (CONFIG.contains(nativeName)) {
                        value = CONFIG.get(nativeName);
                        if (value instanceof String) {
                            value = StringUtils.formatColors((String) value).replace("\\n", "\n");
                        } else if (value instanceof List) {
                            List l = (List) value;
                            List<Object> list = new ArrayList<>(l.size());
                            for (Object v : l) {
                                if (v instanceof String) {
                                    list.add(StringUtils.formatColors((String) v).replace("\\n", "\n"));
                                } else {
                                    list.add(v);
                                }
                            }

                            value = list;
                        }

                        field.set(null, value);
                        writer.set(nativeName, new Writer.YamlEntry(
                                new Object[]{entryInfo == null ? "" : entryInfo.annotation(),
                                        CONFIG.get(nativeName)}));
                    } else {
                        value = field.get(null);
                        if (value instanceof String) {
                            value = StringUtils.deformatColors((String) value).replace("\n", "\\n");
                        } else if (value instanceof List) {
                            List l = (List) value;
                            List<Object> list = new ArrayList<>(l.size());
                            for (Object v : l) {
                                if (v instanceof String) {
                                    list.add(StringUtils.deformatColors((String) v).replace("\n", "\\n"));
                                } else {
                                    list.add(v);
                                }
                            }

                            value = list;
                        }

                        save = true;
                        writer.set(nativeName, new Writer.YamlEntry(
                                new Object[]{entryInfo == null ? "" : entryInfo.annotation(), value}));
                    }
                } catch (ReflectiveOperationException e) {
                    LOGGER.log(Level.WARNING, "Unexpected error on settings file: ", e);
                }
            }
        }

        if (save) {
            writer.write();
            CONFIG.reload();
            Bukkit.getScheduler().scheduleSyncDelayedTask(Core.getInstance(),
                    () -> LOGGER.info("A config §6language.yml §afoi modificada ou criada."));
        }
    }

}
