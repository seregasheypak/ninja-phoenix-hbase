package jdbc;

import com.google.inject.Inject;
import lombok.SneakyThrows;
import ninja.utils.NinjaProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by ssa on 2015-06-07 22:10
 */
public class JdbcFacadeImpl implements JdbcFacade{

    @Inject
    NinjaProperties ninjaProperties;

    private final static Logger LOG = LoggerFactory.getLogger(JdbcFacadeImpl.class);

    @SneakyThrows
    public boolean executeDDL(String ddl){
        LOG.debug("Going to execute: {}", ddl);
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            conn = createConnection();
            preparedStatement = conn.prepareStatement(ddl);
            result = preparedStatement.execute();
        }finally{
            close(preparedStatement);
            close(conn);
        }

        return result;
    }

    public String composeJdbcUrl(){
        LOG.info("ninjaProperties: "+ ninjaProperties);
        return "jdbc:phoenix:" + ninjaProperties.get("hbase.zookeeper.quorum") + ":" +
                                 ninjaProperties.get("hbase.zookeeper.property.clientPort");
    }

    @SneakyThrows
    public Connection createConnection(){
        Connection connection = DriverManager.getConnection(composeJdbcUrl());
        connection.setAutoCommit(true);
        return connection;
    }

    private void close(Connection connection){
        try{
            if(connection == null || connection.isClosed()){
                return;
            }
        }catch (Exception e){
            LOG.warn("Error while closing ["+Connection.class.getSimpleName()+"]", e);
        }
    }

    private void close(PreparedStatement preparedStatement){
        try{
            if(preparedStatement == null || preparedStatement.isClosed()){
                return;
            }
            preparedStatement.close();
        }catch (Exception e){
            LOG.warn("Error while closing ["+PreparedStatement.class.getSimpleName()+"]", e);
        }
    }
}
