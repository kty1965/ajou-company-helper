package kr.ac.ajou.companyhelper.models.daos;

import android.database.sqlite.SQLiteConstraintException;
import kr.ac.ajou.companyhelper.models.Employee;
import kr.ac.ajou.companyhelper.models.Project;
import kr.ac.ajou.companyhelper.models.WorksOn;

/**
 * Created by huy on 2016. 11. 8..
 */
public class WorksOnDao {
  public static boolean Dummy(String projectName) {
    try {
      WorksOn worksOn = new WorksOn(EmployeeDao.findOrCreate(), ProjectDao.Dummy(projectName));
      worksOn.save();
    } catch (SQLiteConstraintException exception) {
      return false;
    }
    return true;
  }
}
