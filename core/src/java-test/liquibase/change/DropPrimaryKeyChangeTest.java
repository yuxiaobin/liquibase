package liquibase.change;

import liquibase.database.MockDatabase;
import liquibase.database.statement.DropPrimaryKeyStatement;
import liquibase.database.statement.SqlStatement;
import static org.junit.Assert.*;
import org.junit.Test;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;

public class DropPrimaryKeyChangeTest extends AbstractChangeTest {
        @Test
    public void getRefactoringName() throws Exception {
        assertEquals("Drop Primary Key", new DropPrimaryKeyChange().getChangeMetaData().getDescription());
    }

    @Test
    public void generateStatement() throws Exception {
        DropPrimaryKeyChange change = new DropPrimaryKeyChange();
        change.setSchemaName("SCHEMA_NAME");
        change.setTableName("TABLE_NAME");
        change.setConstraintName("PK_NAME");

        SqlStatement[] sqlStatements = change.generateStatements(new MockDatabase());
        assertEquals(1, sqlStatements.length);
        assertTrue(sqlStatements[0] instanceof DropPrimaryKeyStatement);
        assertEquals("SCHEMA_NAME", ((DropPrimaryKeyStatement) sqlStatements[0]).getSchemaName());
        assertEquals("TABLE_NAME", ((DropPrimaryKeyStatement) sqlStatements[0]).getTableName());
        assertEquals("PK_NAME", ((DropPrimaryKeyStatement) sqlStatements[0]).getConstraintName());
    }

    @Test
    public void getConfirmationMessage() throws Exception {
        DropPrimaryKeyChange change = new DropPrimaryKeyChange();
        change.setSchemaName("SCHEMA_NAME");
        change.setTableName("TABLE_NAME");
        change.setConstraintName("PK_NAME");
        assertEquals("Primary key dropped from TABLE_NAME", change.getConfirmationMessage());

    }

}
