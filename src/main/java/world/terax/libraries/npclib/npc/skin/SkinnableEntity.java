package world.terax.libraries.npclib.npc.skin;

import world.terax.libraries.npclib.api.npc.NPC;
import org.bukkit.entity.Player;

public interface SkinnableEntity {
  
  NPC getNPC();
  
  Player getEntity();
  
  SkinPacketTracker getSkinTracker();
  
  Skin getSkin();
  
  void setSkin(Skin skin);
  
  void setSkinFlags(byte flags);
}
