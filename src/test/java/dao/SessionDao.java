package dao;

import tables.Session;

public interface SessionDao {

    void addSession(Session session);

    int returnLastAddedId();
}
