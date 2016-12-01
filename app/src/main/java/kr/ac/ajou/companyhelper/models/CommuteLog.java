package kr.ac.ajou.companyhelper.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import org.joda.time.DateTime;

/**
 * Created by huy on 2016. 12. 2..
 */
@Table(name = "CommuteLog")
public class CommuteLog extends Model {

  @Column(name = "commutedAt")
  private DateTime commutedAt;

  @Column(name = "employee", onDelete = Column.ForeignKeyAction.CASCADE)
  private Employee employee;
}
