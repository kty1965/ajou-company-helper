package kr.ac.ajou.companyhelper.serializers;

import com.activeandroid.serializer.TypeSerializer;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by huy on 2016. 12. 2..
 */
public class DateTimeSerializer extends TypeSerializer {

  @Override
  public Class<?> getDeserializedType() {
    return DateTime.class;
  }

  @Override
  public Class<?> getSerializedType() {
    return Long.class;
  }

  @Override
  public Object serialize(Object data) {
    if (data == null) {
      return null;
    }

    return ((DateTime) data).getMillis();
  }

  @Override
  public Object deserialize(Object data) {
    if (data == null) {
      return null;
    }

    return new DateTime((Long) data);
  }
}
