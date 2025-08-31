package world.terax.libraries.npclib.npc;

import world.terax.libraries.npclib.api.EntityController;
import world.terax.libraries.npclib.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public abstract class AbstractEntityController implements EntityController {
  
  private Entity bukkitEntity;
  
  protected abstract Entity createEntity(Location location, NPC npc);
  
  @Override
  public void spawn(Location location, NPC npc) {
    this.bukkitEntity = createEntity(location, npc);
  }
  
  @Override
  public void remove() {
    if (this.bukkitEntity != null) {
      this.bukkitEntity.remove();
      this.bukkitEntity = null;
    }
  }
  
  @Override
  public Entity getBukkitEntity() {
    return this.bukkitEntity;
  }
}
