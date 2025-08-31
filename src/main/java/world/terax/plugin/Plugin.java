package world.terax.plugin;

import world.terax.plugin.config.FileUtils;
import world.terax.plugin.config.Config;
import world.terax.plugin.config.Writer;
import world.terax.plugin.logger.Logger;
import world.terax.reflection.Accessors;
import world.terax.reflection.acessors.FieldAccessor;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public abstract class Plugin extends JavaPlugin {
  
  private static final FieldAccessor<PluginLogger> LOGGER_ACCESSOR = Accessors.getField(JavaPlugin.class, "logger", PluginLogger.class);
  private final FileUtils fileUtils;
  
  public Plugin() {
    this.fileUtils = new FileUtils(this);
    LOGGER_ACCESSOR.set(this, new Logger(this));
    
    this.start();
  }
  
  public abstract void start();
  
  public abstract void load();
  
  public abstract void enable();
  
  public abstract void disable();
  
  @Override
  public void onLoad() {
    this.load();
  }
  
  @Override
  public void onEnable() {
    this.enable();
  }
  
  @Override
  public void onDisable() {
    this.disable();
  }
  
  public Config getConfig(String name) {
    return this.getConfig("", name);
  }
  
  public Config getConfig(String path, String name) {
    return Config.getConfig(this, "plugins/" + this.getName() + "/" + path, name);
  }
  
  public Writer getWriter(File file) {
    return this.getWriter(file, "");
  }
  
  public Writer getWriter(File file, String header) {
    return new Writer((Logger) this.getLogger(), file, header);
  }
  
  public FileUtils getFileUtils() {
    return this.fileUtils;
  }
}
