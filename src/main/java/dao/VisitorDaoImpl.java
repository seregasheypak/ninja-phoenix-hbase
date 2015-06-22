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
//        Result<Record> result = dslContext
//                .select()
//                .from(tableByName("customers").as("c"))
//                .join(tableByName("orders").as("o")).on("o.customer_id = c.id")
//                .where(fieldByName("o.amount").lessThan(amount))
//                .orderBy(fieldByName("c.name").asc())
//                .fetch();
        return Optional.ofNullable(dslContext()
                                    .select()
                                    .from(table("unique_site_visitor"))
                                    .where(field(name("visitorId")).equal(visitorId))
                                    .fetchOneInto(Visitor.class));
    }

    public boolean save(Visitor visitor){
        ///return dslContext().insertInto(table("unique_site_visitor")).values(visitor).execute() > 0;
        return dslContext().insertInto(table("unique_site_visitor"),
                                    field(name("visitorId")), field(name("siteId")), field(name("visitTs")))
                            .values(visitor.getVisitorId(), visitor.getSiteId(), visitor.getVisitTs())
                            .execute() > 0;

    }
}
