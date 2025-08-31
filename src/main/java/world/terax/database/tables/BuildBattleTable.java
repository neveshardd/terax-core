package world.terax.database.tables;

import world.terax.database.Database;
import world.terax.database.HikariDatabase;
import world.terax.database.MySQLDatabase;
import world.terax.database.data.DataContainer;
import world.terax.database.data.DataTable;
import world.terax.database.data.interfaces.DataTableInfo;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

@DataTableInfo(
    name = "CoreBuildBattle",
    create = "CREATE TABLE IF NOT EXISTS `CoreBuildBattle` (`name` VARCHAR(32), `wins` LONG, `games` LONG, `points` LONG, " +
        "`monthlywins` LONG, `monthlygames` LONG, `monthlypoints` LONG, `month` TEXT, `coins` DOUBLE, `lastmap` LONG, `cosmetics` TEXT, `selected` TEXT, PRIMARY KEY(`name`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;",
    select = "SELECT * FROM `CoreBuildBattle` WHERE LOWER(`name`) = ?",
    insert = "INSERT INTO `CoreBuildBattle` VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
    update = "UPDATE `CoreBuildBattle` SET `wins` = ?, `games` = ?, `points` = ?, `monthlywins` = ?, `monthlygames` = ?, `monthlypoints` = ?, `month` = ?, `coins` = ?, `lastmap` = ?, `cosmetics` = ?, `selected` = ? WHERE LOWER(`name`) = ?"
)
public class BuildBattleTable extends DataTable {
  
  @Override
  public void init(Database database) {
    if (database instanceof MySQLDatabase) {
      if (((MySQLDatabase) database).query("SHOW COLUMNS FROM `CoreBuildBattle` LIKE 'lastmap'") == null) {
        ((MySQLDatabase) database).execute(
            "ALTER TABLE `CoreBuildBattle` ADD `lastmap` LONG DEFAULT 0 AFTER `coins`");
      }
    } else if (database instanceof HikariDatabase) {
      if (((HikariDatabase) database).query("SHOW COLUMNS FROM `CoreBuildBattle` LIKE 'lastmap'") == null) {
        ((HikariDatabase) database).execute(
            "ALTER TABLE `CoreBuildBattle` ADD `lastmap` LONG DEFAULT 0 AFTER `coins`");
      }
    }
  }
  
  public Map<String, DataContainer> getDefaultValues() {
    Map<String, DataContainer> defaultValues = new LinkedHashMap<>();
    defaultValues.put("wins", new DataContainer(0L));
    defaultValues.put("games", new DataContainer(0L));
    defaultValues.put("points", new DataContainer(0L));
    for (String key : new String[]{"wins", "games",
        "points"}) {
      defaultValues.put("monthly" + key, new DataContainer(0L));
    }
    defaultValues.put("month", new DataContainer((Calendar.getInstance().get(Calendar.MONTH) + 1) + "/" +
        Calendar.getInstance().get(Calendar.YEAR)));
    defaultValues.put("coins", new DataContainer(0.0D));
    defaultValues.put("lastmap", new DataContainer(0L));
    defaultValues.put("cosmetics", new DataContainer("{}"));
    defaultValues.put("selected", new DataContainer("{}"));
    return defaultValues;
  }
}
