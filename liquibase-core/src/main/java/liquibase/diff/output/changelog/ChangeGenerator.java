package liquibase.diff.output.changelog;

import liquibase.database.Database;
import liquibase.structure.DatabaseObject;

public interface ChangeGenerator {

    final int PRIORITY_NONE = -1;
    final int PRIORITY_DEFAULT = 1;
    final int PRIORITY_DATABASE = 5;
    final int PRIORITY_ADDITIONAL = 50;

    int getPriority(Class<? extends DatabaseObject> objectType, Database database);

}
