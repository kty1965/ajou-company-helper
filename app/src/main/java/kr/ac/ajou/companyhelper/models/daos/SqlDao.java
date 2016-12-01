package kr.ac.ajou.companyhelper.models.daos;

import com.activeandroid.Model;
import com.activeandroid.query.Select;
import kr.ac.ajou.companyhelper.models.Project;

import java.util.List;

/**
 * Created by huy on 2016. 11. 24..
 */
public class SqlDao<T extends Model> {
  private final Class<T> type;

  public SqlDao(Class<T> type) {
    this.type = type;
  }

  public List<T> getAll() {
    return new Select().from(type).execute();
  }
}
