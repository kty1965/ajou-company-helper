package kr.ac.ajou.companyhelper.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import kr.ac.ajou.companyhelper.activities.project.ProjectAddActivity;
import kr.ac.ajou.companyhelper.activities.project.ProjectDetailActivity;
import kr.ac.ajou.companyhelper.activities.project.ProjectListActivity;
import kr.ac.ajou.companyhelper.activities.todo.TodoAddActivity;
import kr.ac.ajou.companyhelper.activities.todo.TodoDetailActivity;
import kr.ac.ajou.companyhelper.activities.todo.TodoListActivity;
import kr.ac.ajou.companyhelper.models.Employee;
import kr.ac.ajou.companyhelper.models.Project;
import kr.ac.ajou.companyhelper.models.ToDo;
import kr.ac.ajou.companyhelper.models.daos.EmployeeDao;
import kr.ac.ajou.companyhelper.models.daos.WorksOnDao;

/**
 * Created by huy on 2016. 12. 2..
 */
public class ApplicationActivity extends AppCompatActivity {
  protected final static int PROJECT_ADD = 0;
  protected final static int TODO_ADD = 0;

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

  protected void startToDoListActivity() {
    Intent intent = new Intent(this, TodoListActivity.class);
    startActivity(intent);
  }

  protected void startToDoAddActivity() {
    Intent intent = new Intent(this, TodoAddActivity.class);
    startActivityForResult(intent, TODO_ADD);
  }

  protected void startToDoDetailActivity(ToDo todo) {
    Intent intent = new Intent(this, TodoDetailActivity.class);
    intent.putExtra("title", todo.getTitle());
    intent.putExtra("content", todo.getContent());
    intent.putExtra("priority", todo.getPriority());
    intent.putExtra("state", todo.getState());
    intent.putExtra("project_name", todo.getProject().getName());
    startActivity(intent);
  }

  protected void startProjectAddActivity() {
    Intent intent = new Intent(this, ProjectAddActivity.class);
    startActivityForResult(intent, PROJECT_ADD);
  }

  protected void startProjectDetailActivity(Project project) {
    Intent intent = new Intent(this, ProjectDetailActivity.class);
    intent.putExtra("name", project.getName());
    intent.putExtra("description", project.getDescription());
    startActivity(intent);
  }
}
