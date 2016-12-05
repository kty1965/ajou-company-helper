package kr.ac.ajou.companyhelper.models.daos;

import kr.ac.ajou.companyhelper.models.CommuteLog;
import kr.ac.ajou.companyhelper.models.Employee;
import org.joda.time.DateTime;

/**
 * Created by huy on 2016. 12. 2..
 */
public class CommuteLogDao extends SqlDao<CommuteLog> {
  public CommuteLogDao() {
    super(CommuteLog.class);
  }

  public static CommuteLog create(Employee employee, DateTime createdAt) {
    CommuteLog commuteLog = new CommuteLog(createdAt, employee);
    commuteLog.save();
    return commuteLog;
  }
}
