package kr.ac.ajou.companyhelper.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by huy on 2016. 12. 2..
 */
@Table(name = "ToDo")
public class ToDo extends Model {
  @Column(name = "priority") private String priority;
  @Column(name = "title") private String title;
  @Column(name = "content") private String content;
  @Column(name = "state") private String state;

  @Column(name = "employee", onDelete = Column.ForeignKeyAction.CASCADE)
  private Employee employee;

  @Column(name = "project", onDelete = Column.ForeignKeyAction.CASCADE)
  private Project project;

  public ToDo() {
    this.state = "none";
  }

  public ToDo(String priority, String title, String content, Project project, Employee employee) {
    this.priority = priority;
    this.title = title;
    this.content = content;
    this.employee = employee;
    this.project = project;
    this.state = "none";
  }

  public String getPriority() {
    return priority;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public String getState() {
    return state;
  }

  public Employee getEmployee() {
    return employee;
  }

  public Project getProject() {
    return project;
  }
}
