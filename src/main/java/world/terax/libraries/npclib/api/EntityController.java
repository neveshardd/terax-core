package world.terax.libraries.npclib.api;

import world.terax.libraries.npclib.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public interface EntityController {
  
  void spawn(Location location, NPC npc);
  
  void remove();
  
  Entity getBukkitEntity();
}
