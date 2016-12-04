package kr.ac.ajou.companyhelper.models.daos;

import com.activeandroid.query.Select;
import kr.ac.ajou.companyhelper.models.Employee;
import kr.ac.ajou.companyhelper.models.Project;

import java.util.List;

/**
 * Created by huy on 2016. 11. 8..
 */
public class ProjectDao extends SqlDao<Project> {
  public ProjectDao() {
    super(Project.class);
  }

  public static Project Dummy (String name) {
    return Dummy(name, "");
  }

  public static Project Dummy (String name, String description) {
    Project project = new Select().from(Project.class).where("name = ?", name).executeSingle();
    if (project == null) {
      project = new Project(name, description);
      project.save();
    }
    return project;
  }

  public static Project createWithWorksOn(String name, String description, Employee employee) {
    Project project = new Project(name, description);
    project.save();
    WorksOnDao.create(employee, project);
    return project;
  }

  public static Project findByName(String name) {
    Project project = new Select().from(Project.class).where("name = ?", name).executeSingle();
    return project;
  }
}
