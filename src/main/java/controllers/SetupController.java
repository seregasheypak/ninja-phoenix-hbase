package controllers;

import jdbc.JdbcFacade;
import lombok.SneakyThrows;
import ninja.Result;
import org.apache.commons.io.IOUtils;

import java.util.stream.Collectors;

/**
 * Created by ssa on 2015-06-07 21:22
 */
public class SetupController {

    @SneakyThrows
    public Result setup(){
        String ddl = readDDL("unique_visitor.ddl");
        new JdbcFacade().executeDDL(ddl);
        return new Result(Result.SC_200_OK);
    }

    @SneakyThrows
    private String readDDL(String ddlFileName){
        return IOUtils.readLines(this.getClass().getClassLoader().getResourceAsStream(ddlFileName))
                .stream().collect(Collectors.joining("\n"));
    }
}
