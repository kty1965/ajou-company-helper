package kr.ac.ajou.companyhelper.models.daos;

import kr.ac.ajou.companyhelper.models.CommuteLog;

/**
 * Created by huy on 2016. 12. 2..
 */
public class CommuteLogDao extends SqlDao<CommuteLog> {
  public CommuteLogDao() {
    super(CommuteLog.class);
  }
}
