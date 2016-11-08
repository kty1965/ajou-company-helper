package kr.ac.ajou.companyhelper.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by huy on 2016. 11. 8..
 */
@Table(name = "WorksOn")
public class WorksOn extends Model {
  @Column(name = "employeeId", index = true, uniqueGroups = "employee_project", onUniqueConflicts = Column.ConflictAction.FAIL)
  private Long employeeId;
  @Column(name = "projectId", index = true, uniqueGroups = "employee_project", onUniqueConflicts = Column.ConflictAction.FAIL)
  private Long projectId;

  @Column(name = "employee", onDelete = Column.ForeignKeyAction.CASCADE)
  private Employee employee;

  @Column(name = "project", onDelete = Column.ForeignKeyAction.CASCADE)
  private Project project;

  public WorksOn() {

  }

  public WorksOn(Employee employee, Project project) {
    this.employee = employee;
    this.employeeId = employee.getId();
    this.project = project;
    this.projectId = project.getId();
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public Long getProjectId() {
    return projectId;
  }

  public Employee getEmployee() {
    return employee;
  }

  public Project getProject() {
    return project;
  }
}
