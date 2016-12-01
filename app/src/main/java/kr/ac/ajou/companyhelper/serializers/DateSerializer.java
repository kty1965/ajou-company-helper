package kr.ac.ajou.companyhelper.serializers;

import com.activeandroid.serializer.TypeSerializer;

import java.util.Date;

/**
 * Created by huy on 2016. 12. 2..
 */
final public class DateSerializer extends TypeSerializer {
  @Override
  public Class<?> getDeserializedType() {
    return Date.class;
  }

  @Override
  public Class<?> getSerializedType() {
    return Long.class;
  }

  @Override
  public Long serialize(Object data) {
    if (data == null) {
      return null;
    }

    return ((Date) data).getTime();
  }

  @Override
  public Date deserialize(Object data) {
    if (data == null) {
      return null;
    }

    return new Date((Long) data);
  }
}
