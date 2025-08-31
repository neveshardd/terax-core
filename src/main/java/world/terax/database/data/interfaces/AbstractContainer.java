package world.terax.database.data.interfaces;

import world.terax.database.data.DataContainer;

public abstract class AbstractContainer {
  
  protected DataContainer dataContainer;
  
  public AbstractContainer(DataContainer dataContainer) {
    this.dataContainer = dataContainer;
  }
  
  public void gc() {
    this.dataContainer = null;
  }
}
