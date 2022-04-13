package dao;

import tables.Status;

public interface StatusDao {

    void addStatus(Status status);

    void deleteStatusId(int id);

    void deleteAllStatuses();

    int returnLastAddedId();

}
