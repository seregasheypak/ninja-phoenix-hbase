package dao;

import com.google.inject.Inject;
import jdbc.JdbcFacade;
import org.jooq.DSLContext;
import org.jooq.conf.RenderNameStyle;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;

/**
 * Created by ssa on 2015-06-19 15:09
 */
public class BaseJooqDao {

    @Inject
    JdbcFacade jdbcFacade;

    protected DSLContext dslContext(){
        return DSL.using(jdbcFacade.createConnection(), new Settings().withRenderNameStyle(RenderNameStyle.AS_IS));
    }

    public JdbcFacade getJdbcFacade(){
        return jdbcFacade;
    }
}
