package kr.ac.ajou.companyhelper.models.daos;

import kr.ac.ajou.companyhelper.models.Employee;
import kr.ac.ajou.companyhelper.models.Project;
import kr.ac.ajou.companyhelper.models.ToDo;

/**
 * Created by huy on 2016. 12. 2..
 */
public class ToDoDao extends SqlDao<ToDo> {
  public ToDoDao() {
    super(ToDo.class);
  }

  public static ToDo create(
      String title,
      String content,
      String priority,
      Project project,
      Employee employee) {
    ToDo todo = new ToDo(priority, title, content, project, employee);
    todo.save();
    return todo;
  }
}
