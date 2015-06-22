package dao;

import models.Visitor;

import java.util.Optional;

/**
 * Created by ssa on 2015-06-19 15:07
 */
public interface VisitorDao {

    boolean save(Visitor visitor);

    Optional<Visitor> find(String visitorId);
}
