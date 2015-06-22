package jdbc;

import java.sql.Connection;

/**
 * Created by ssa on 2015-06-07 22:10
 */
public interface JdbcFacade {

    boolean executeDDL(String ddl);

    public String composeJdbcUrl();

    public Connection createConnection();

}
