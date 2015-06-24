package controllers;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.VisitorDao;
import models.Visitor;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ssa on 2015-06-14 22:43
 */

@Singleton
public class VisitorController {

    private static final Logger LOG = LoggerFactory.getLogger(VisitorController.class);

    @Inject
    VisitorDao visitorDao;

    public Result save(Visitor visitor){
        LOG.debug("Saving: {}", visitor);
        boolean result = visitorDao.save(visitor);
        return  Results
                .ok()
                .json()
                .render(new ImmutableMap.Builder<String, String>().put("result", String.valueOf(result)).build());
    }

    public Result find( @Param("id") String id){
        LOG.debug("find: {}", id);
        return Results
                .ok()
                .json()
                .render(visitorDao.find(id).orElse(new Visitor()));
    }
}
