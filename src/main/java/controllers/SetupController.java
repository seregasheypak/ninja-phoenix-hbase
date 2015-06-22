package controllers;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import jdbc.JdbcFacade;
import lombok.SneakyThrows;
import ninja.Result;
import ninja.Results;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by ssa on 2015-06-14 22:43
 */

@Singleton
public class SetupController {

    private static final Logger LOG = LoggerFactory.getLogger(SetupController.class);

    @Inject
    JdbcFacade jdbcFacade;

    @SneakyThrows
    public Result setup(){
        boolean result = false;
        for(String ddlFile : Arrays.asList("unique_site_visitor")) {
            String ddl = readDDL(ddlFile + ".ddl");
            result = jdbcFacade.executeDDL(ddl);
            LOG.debug("Executed {}. Result {}", ddl, result);
        }
        return  Results
                .ok()
                .json()
                .render(ImmutableMap.builder().put("result", String.valueOf(result)).build());
    }

    @SneakyThrows
    private String readDDL(String ddlFileName){
        return IOUtils.readLines(this.getClass().getClassLoader().getResourceAsStream(ddlFileName))
                .stream().collect(Collectors.joining("\n"));
    }

}
