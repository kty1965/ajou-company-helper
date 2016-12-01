package kr.ac.ajou.companyhelper.models.daos;

import com.activeandroid.query.Select;
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
    Project project = new Select().from(Project.class).where("name = ?", name).executeSingle();
    if (project == null) {
      project = new Project(name);
      project.save();
    }
    return project;
  }
}
