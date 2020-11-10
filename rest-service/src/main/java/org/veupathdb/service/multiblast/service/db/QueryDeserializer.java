package org.veupathdb.service.multiblast.service.db;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

import io.vulpine.lib.query.util.RowParser;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class QueryDeserializer
{
  private static final String
    ERR_METH_PARAMS = "The @DbColumn annotation can only be used on methods taking exactly 1 parameter."
    + " Error on %s.%s.",
    ERR_NO_DB_COL   = "The @DbColumn `name` value cannot be blank. Error on %s.%s";

  @NotNull
  public <T> RowParser<T> deserialize(@NotNull T object) {
    return rs -> {
      var getters = checkMethods(object);

      for (var ref : getters) {
        if (ref.converter != null) {
          var tmp = rs.getObject(ref.name);
          ref.method.invoke(ref.obj, ref.converter.convert(tmp));
        } else {
          ref.method.invoke(ref.obj, rs.getObject(ref.name, ref.inType));
        }
      }

      return object;
    };
  }

  @NotNull
  public <T> RowParser<T> deserialize(@NotNull Class<T> clz) {
    return rs -> deserialize(clz.getConstructor().newInstance()).parse(rs);
  }

  @Target({ METHOD })
  @Retention(RUNTIME)
  public @interface DbColumn {
    String name();
    Converter converter = null;
  }

  @FunctionalInterface
  public interface Converter {
    /**
     * Convert takes the given object and parses or converts it into the type
     * expected by the setter.
     *
     * @param in Raw object from a {@link java.sql.ResultSet#getObject(String)}
     *           call.
     *
     * @return The converted value.
     */
    Object convert(Object in);
  }

  private List<MethodRef> checkMethods(Object obj) throws Exception {
    var clz     = obj.getClass();
    var methods = clz.getMethods();
    var getters = new ArrayList<MethodRef>(methods.length);

    for (var meth : methods) {
      var ann = meth.getAnnotation(DbColumn.class);
      if (ann == null)
        continue;

      if (meth.getParameterCount() != 1)
        throw new Exception(String.format(ERR_METH_PARAMS, clz.getSimpleName(), meth.getName()));

      if (ann.name().isBlank())
        throw new Exception(String.format(ERR_NO_DB_COL, clz.getSimpleName(), meth.getName()));

      getters.add(new MethodRef(meth, meth.getParameterTypes()[0], ann.name(), obj, ann.converter));
    }

    return getters;
  }
}

class MethodRef {
  final Method method;
  final Class<?> inType;
  final String name;
  final Object obj;
  final QueryDeserializer.Converter converter;

  MethodRef(
    @NotNull Method method,
    @NotNull Class<?> inType,
    @NotNull String name,
    @NotNull Object obj,
    QueryDeserializer.Converter converter
  ) {
    this.method    = method;
    this.inType    = inType;
    this.name      = name;
    this.obj       = obj;
    this.converter = converter;
  }
}
