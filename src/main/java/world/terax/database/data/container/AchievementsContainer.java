package world.terax.database.data.container;

import world.terax.achievements.Achievement;
import world.terax.database.data.DataContainer;
import world.terax.database.data.interfaces.AbstractContainer;
import org.json.simple.JSONArray;

@SuppressWarnings("unchecked")
public class AchievementsContainer extends AbstractContainer {
  
  public AchievementsContainer(DataContainer dataContainer) {
    super(dataContainer);
  }
  
  public void complete(Achievement achievement) {
    JSONArray achievements = this.dataContainer.getAsJsonArray();
    achievements.add(achievement.getId());
    this.dataContainer.set(achievements.toString());
    achievements.clear();
  }
  
  public boolean isCompleted(Achievement achievement) {
    return this.dataContainer.getAsJsonArray().contains(achievement.getId());
  }
}
