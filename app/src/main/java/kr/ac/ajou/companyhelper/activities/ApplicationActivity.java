package kr.ac.ajou.companyhelper.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import kr.ac.ajou.companyhelper.models.Employee;
import kr.ac.ajou.companyhelper.models.daos.EmployeeDao;
import kr.ac.ajou.companyhelper.models.daos.WorksOnDao;

/**
 * Created by huy on 2016. 12. 2..
 */
public class ApplicationActivity extends AppCompatActivity {
  protected void setup() {
    Employee employee = EmployeeDao.findOrCreate();
    employee.getEmail();
    WorksOnDao.Dummy("Database");
    WorksOnDao.Dummy("DistributedSystem");
    WorksOnDao.Dummy("Spark");
    WorksOnDao.Dummy("MapReduce");
  }

  protected Employee currentEmployee() {
    return EmployeeDao.findOrCreate();
  }

  protected void startProjectListAcitivity() {
    Intent intent = new Intent(this, ProjectListActivity.class);
    startActivity(intent);
  }

  protected void startTodoListActivity() {
    Intent intent = new Intent(this, TodoListActivity.class);
    startActivity(intent);
  }
}
