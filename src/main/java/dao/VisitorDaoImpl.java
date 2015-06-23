package dao;

import models.Visitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static org.jooq.impl.DSL.*;

/**
 * Created by ssa on 2015-06-19 15:07
 */
public class VisitorDaoImpl extends BaseJooqDao implements VisitorDao{

    private static final Logger LOG = LoggerFactory.getLogger(VisitorDaoImpl.class);

    public Optional<Visitor> find(String visitorId){
        LOG.debug("List all records from table unique_site_visitor");
        dslContext().select(field(name("visitorId")), field(name("siteId")), field(name("visitTs")))
                .from(table("unique_site_visitor"))
                .fetch()
                .forEach(record -> LOG.debug("record: {}", record));

        return Optional.ofNullable(dslContext()
                                    .select()
                                    .from(table("unique_site_visitor"))
                                    .where(field(name("visitorId")).equal(visitorId))
                                    .fetchOneInto(Visitor.class));
    }

    public boolean save(Visitor visitor){
        String sql = dslContext().insertInto(table("unique_site_visitor"),
                field(name("visitorId")), field(name("siteId")), field(name("visitTs")))
                .values(inline(visitor.getVisitorId()), inline(visitor.getSiteId()), inline(visitor.getVisitTs()))
                .getSQL().replace("insert", "upsert");
        LOG.debug("Executing upsert: {}", sql );
        Connection con = getJdbcFacade().createConnection();
        try {
            int result = getJdbcFacade().createConnection().prepareStatement("upsert into unique_site_visitor (visitorId, siteId, visitTs) values ('xxxyyyzzz', 1, 2)").executeUpdate();
            LOG.debug("SAVE DUMMY: {}", result);
            con.commit();
            con.close();
            LOG.debug("SELECTING!!! => ");
             con = getJdbcFacade().createConnection();;
            ResultSet resultSet = getJdbcFacade().createConnection().prepareStatement("select visitorId from unique_site_visitor").executeQuery();
            while(resultSet.next()){
                LOG.debug("resultSet visitorId ::: {}", resultSet.getString("visitorId"));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dslContext().query(sql).execute() > 0;
    }
}
