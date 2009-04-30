package liquibase.database.statement.generator;

import liquibase.database.Database;
import liquibase.database.DerbyDatabase;
import liquibase.database.FirebirdDatabase;
import liquibase.database.InformixDatabase;
import liquibase.database.MSSQLDatabase;
import liquibase.database.MySQLDatabase;
import liquibase.database.OracleDatabase;
import liquibase.database.PostgresDatabase;
import liquibase.database.SQLiteDatabase;
import liquibase.database.SybaseASADatabase;
import liquibase.database.SybaseDatabase;
import liquibase.database.statement.AddUniqueConstraintStatement;
import liquibase.database.statement.CreateTableStatement;
import liquibase.database.statement.NotNullConstraint;
import liquibase.database.statement.SqlStatement;
import liquibase.test.TestContext;

import org.junit.Test;

public class AddUniqueConstraintGeneratorTest extends AbstractSqlGeneratorTest {
    protected static final String TABLE_NAME = "AddUQTest";
    protected static final String COLUMN_NAME = "colToMakeUQ";
    protected static final String CONSTRAINT_NAME = "UQ_TEST";

    public AddUniqueConstraintGeneratorTest() {
        this(new AddUniqueConstraintGenerator());
    }

    public AddUniqueConstraintGeneratorTest(SqlGenerator generatorUnderTest) {
        super(generatorUnderTest);
    }

//    protected void setupDatabase(Database database) throws Exception {
//            dropAndCreateTable(new CreateTableStatement(null, TABLE_NAME)
//                    .addColumn("id", "int", new NotNullConstraint())
//                    .addColumn(COLUMN_NAME, "int", new NotNullConstraint()), database);
//
//            dropAndCreateTable(new CreateTableStatement(TestContext.ALT_SCHEMA, TABLE_NAME)
//                    .addColumn("id", "int", new NotNullConstraint())
//                    .addColumn(COLUMN_NAME, "int", new NotNullConstraint()), database);
//    }
//
//    protected SqlStatement createGeneratorUnderTest() {
//        return new AddUniqueConstraintStatement(null, null, null, null);
//    }

//    @Test
//    public void execute_noSchema() throws Exception {
//        new DatabaseTestTemplate().testOnAvailableDatabases(
//                new SqlStatementDatabaseTest(null, new AddUniqueConstraintStatement(null, TABLE_NAME, COLUMN_NAME, "uq_adduqtest")) {
//
//                    protected void preExecuteAssert(DatabaseSnapshot snapshot) {
//                        assertFalse(snapshot.getTable(TABLE_NAME).getColumn(COLUMN_NAME).isUnique());
//                    }
//
//                    protected void postExecuteAssert(DatabaseSnapshot snapshot) {
//                        //todo: enable snapshot and assertion when snapshot can check for unique constraints
//                        //snapshot = new DatabaseSnapshot(snapshot);
//                    	assertTrue(snapshot.getTable(TABLE_NAME).getColumn(COLUMN_NAME).isUnique());
//                    }
//                });
//    }
//
//    @Test
//    public void execute_withSchema() throws Exception {
//        new DatabaseTestTemplate().testOnAvailableDatabases(
//                new SqlStatementDatabaseTest(TestContext.ALT_SCHEMA, new AddUniqueConstraintStatement(TestContext.ALT_SCHEMA, TABLE_NAME, COLUMN_NAME, "uq_adduqtest")) {
//                    protected void preExecuteAssert(DatabaseSnapshot snapshot) {
//                        assertFalse(snapshot.getTable(TABLE_NAME).getColumn(COLUMN_NAME).isUnique());
//                    }
//
//                    protected void postExecuteAssert(DatabaseSnapshot snapshot) {
//                        //todo: enable snapshot and assertion when snapshot can check for unique constraints
//                snapshot = new DatabaseSnapshot(database, TestContext.ALT_SCHEMA);
//                assertTrue(snapshot.getTable(TABLE_NAME).getColumn(COLUMN_NAME).isUnique());
//                    }
//
//                });
//    }
//
//    @Test
//    public void execute_withTablespace() throws Exception {
//        new DatabaseTestTemplate().testOnAvailableDatabases(
//                new SqlStatementDatabaseTest(null, new AddUniqueConstraintStatement(null, TABLE_NAME, COLUMN_NAME, "uq_adduqtest").setTablespace(TestContext.ALT_TABLESPACE)) {
//                    protected void preExecuteAssert(DatabaseSnapshot snapshot) {
//                        assertFalse(snapshot.getTable(TABLE_NAME).getColumn(COLUMN_NAME).isUnique());
//                    }
//
//                    protected void postExecuteAssert(DatabaseSnapshot snapshot) {
//                        //todo: enable snapshot and assertion when snapshot can check for unique constraints
//                        // snapshot = new DatabaseSnapshot(database);
////                assertTrue(snapshot.getTable(TABLE_NAME).getColumn(COLUMN_NAME).isUnique());
//                    }
//                });
//    }

    @Override
    protected SqlStatement createSampleSqlStatement() {
        return new AddUniqueConstraintStatement(null, null, null, null);
    }

    @Override
    protected SqlStatement[] setupStatements() {
        return new SqlStatement[]{new CreateTableStatement(null, TABLE_NAME)
                .addColumn("id", "int", new NotNullConstraint())
                .addColumn(COLUMN_NAME, "int", new NotNullConstraint())
        };
    }

    @Override
    protected boolean shouldBeImplementation(Database database) {
        return !(database instanceof SQLiteDatabase)
                && !(database instanceof MSSQLDatabase)
                && !(database instanceof SybaseDatabase)
                && !(database instanceof SybaseASADatabase)
                ;
    }

    @Test
    public void execute_noSchema() throws Exception {
        SqlStatement statement = new AddUniqueConstraintStatement(null, TABLE_NAME, COLUMN_NAME, CONSTRAINT_NAME);
        testSqlOnAllExcept("alter table [adduqtest] add constraint [uq_test] unique ([coltomakeuq])", statement
                , MySQLDatabase.class, InformixDatabase.class, OracleDatabase.class, PostgresDatabase.class, DerbyDatabase.class);
        testSqlOn("alter table `adduqtest` add constraint `uq_test` unique (`coltomakeuq`)", statement, MySQLDatabase.class);
        testSqlOn("alter table adduqtest add constraint unique (coltomakeuq) constraint uq_test", statement, InformixDatabase.class);
        testSqlOn("alter table adduqtest add constraint uq_test unique (coltomakeuq)", statement, OracleDatabase.class);
        testSqlOn("alter table \"adduqtest\" add constraint uq_test unique (\"coltomakeuq\")", statement, PostgresDatabase.class);
        testSqlOn("alter table adduqtest add constraint uq_test unique (coltomakeuq)", statement, DerbyDatabase.class);
    }

    @Test
    public void execute_noConstraintName() throws Exception {
        SqlStatement statement = new AddUniqueConstraintStatement(null, TABLE_NAME, COLUMN_NAME, null);
//		testSqlOnAllExcept("alter table [adduqtest] add constraint [uq_test]", statement
//				, MySQLDatabase.class, InformixDatabase.class, OracleDatabase.class, PostgresDatabase.class, DerbyDatabase.class);
//		testSqlOn("alter table `adduqtest` add constraint `null` unique (`coltomakeuq`)", statement, MySQLDatabase.class);
//		testSqlOn("alter table adduqtest add constraint unique (coltomakeuq) constraint uq_test", statement, InformixDatabase.class);
//		testSqlOn("alter table adduqtest add constraint uq_test unique (coltomakeuq)", statement, OracleDatabase.class);
//		testSqlOn("alter table \"adduqtest\" add constraint uq_test unique (\"coltomakeuq\")", statement, PostgresDatabase.class);
//		testSqlOn("alter table adduqtest add constraint uq_test unique (coltomakeuq)", statement, DerbyDatabase.class);
    }

    @Test
    public void execute_withSchema() throws Exception {
        SqlStatement statement = new AddUniqueConstraintStatement(TestContext.ALT_SCHEMA, TABLE_NAME, COLUMN_NAME, CONSTRAINT_NAME);

        testSqlOnAllExcept("alter table liquibaseb.[adduqtest] add constraint [uq_test] unique ([coltomakeuq])", statement
                , MySQLDatabase.class, InformixDatabase.class, OracleDatabase.class, PostgresDatabase.class, DerbyDatabase.class
                // FIXME seems like FirebirdDatabase does not support schema attribute. Check it!
                , FirebirdDatabase.class
        );

        // FIXME Syntax for mysql is correct, but exception "Table 'liquibaseb.adduqtest' doesn't exist" is thrown
// 		testSqlOn("alter table `liquibaseb`.`adduqtest` add constraint `uq_test` unique (`coltomakeuq`)", statement, MySQLDatabase.class);
        testSqlOn("alter table liquibaseb.adduqtest add constraint unique (coltomakeuq) constraint uq_test", statement, InformixDatabase.class);
        testSqlOn("alter table liquibaseb.adduqtest add constraint uq_test unique (coltomakeuq)", statement, OracleDatabase.class);
        testSqlOn("alter table liquibaseb.\"adduqtest\" add constraint uq_test unique (\"coltomakeuq\")", statement, PostgresDatabase.class);
        testSqlOn("alter table liquibaseb.adduqtest add constraint uq_test unique (coltomakeuq)", statement, DerbyDatabase.class);
    }

}
