package org.tinyj.lava.fun;

import org.tinyj.lava.LavaSupplier;

import java.util.function.Supplier;

public final class Null<R, E extends Exception>
    implements LavaSupplier<R, E>, Supplier<R> {

  public static final Null<?, ?> NULL = new Null<>();

  @SuppressWarnings({"unchecked", "MethodNameSameAsClassName"})
  public static <R, E extends Exception> Null<R, E>
  Null() { return (Null) NULL; }

  private Null() {}

  @Override
  public final R checkedGet() { return null; }

  @Override
  public final R get() { return null; }
}
