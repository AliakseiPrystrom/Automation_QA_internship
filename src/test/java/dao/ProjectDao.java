package dao;

import tables.Project;


public interface ProjectDao {

    void addProject(Project project);

    void deleteProject(int id);

    void deleteAllProjects();

    int returnLastAddedId();


}
