package jdbc;

import com.google.inject.Inject;
import lombok.SneakyThrows;
import ninja.logging.LogbackConfigurator;
import ninja.utils.NinjaProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by ssa on 2015-06-07 22:10
 */
public class JdbcFacade {

    @Inject
    NinjaProperties ninjaProperties;

    private final static Logger LOG = LoggerFactory.getLogger(LogbackConfigurator.class);

    @SneakyThrows
    public boolean executeDDL(String ddl){
        LOG.debug("Going to execute: {}", ddl);
        Connection conn = DriverManager.getConnection(composeJdbcUrl());
        PreparedStatement preparedStatement = conn.prepareStatement(ddl);
        return preparedStatement.execute();
    }

    private String composeJdbcUrl(){
        return "jdbc:phoenix:" + ninjaProperties.get("hbase.zookeeper.quorum") + ":" +
                                 ninjaProperties.get("hbase.zookeeper.property.clientPort");
    }
}
