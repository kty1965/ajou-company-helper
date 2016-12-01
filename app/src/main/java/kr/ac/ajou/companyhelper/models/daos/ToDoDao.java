package kr.ac.ajou.companyhelper.models.daos;

import kr.ac.ajou.companyhelper.models.ToDo;

/**
 * Created by huy on 2016. 12. 2..
 */
public class ToDoDao extends SqlDao<ToDo> {
  public ToDoDao() {
    super(ToDo.class);
  }
}
