package dao;

import models.Visitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.jooq.impl.DSL.*;

/**
 * Created by ssa on 2015-06-19 15:07
 */
public class VisitorDaoImpl extends BaseJooqDao implements VisitorDao{

    private static final Logger LOG = LoggerFactory.getLogger(VisitorDaoImpl.class);

    public Optional<Visitor> find(String visitorId){
        return Optional.ofNullable(dslContext()
                                    .select(field(name("visitorId")), field(name("siteId")), field(name("visitTs")))
                                    .from(table("unique_site_visitor"))
                                    .where(field(name("visitorId")).equal(visitorId))
                                    .fetchOneInto(Visitor.class));
    }

    public boolean save(Visitor visitor){
        String sql = dslContext().insertInto(table("unique_site_visitor"),
                field(name("visitorId")), field(name("siteId")), field(name("visitTs")))
                .values(inline(visitor.getVisitorId()), inline(visitor.getSiteId()), inline(visitor.getVisitTs()))
                .getSQL().replace("insert", "upsert");
        return dslContext().query(sql).execute() > 0;
    }
}
