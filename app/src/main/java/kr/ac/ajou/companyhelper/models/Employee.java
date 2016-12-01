package kr.ac.ajou.companyhelper.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huy on 2016. 11. 8..
 */
@Table(name = "Employee")
public class Employee extends Model {
  @Column(name = "email", index = true, unique = true) private String email;
  @Column(name = "name") private String name;
  @Column(name = "password") private String password;

  public Employee() {

  }
  public Employee(String email, String name, String password) {
    this.email = email;
    this.name = name;
    this.password = password;
  }

  public List<WorksOn> getWorksOn() {
    return getMany(WorksOn.class, "employee");
  }

  public List<Project> getProjects() {
    List<WorksOn> worksOn= getWorksOn();
    ArrayList<Project> projects = new ArrayList<>();
    for (WorksOn work : worksOn) {
      projects.add(work.getProject());
    }

    return projects;
  }

  public List<ToDo> getToDos() {
    return getMany(ToDo.class, "employee");
  }

  public List<CommuteLog> getCommuteLogs() {
    return getMany(CommuteLog.class, "employee");
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
