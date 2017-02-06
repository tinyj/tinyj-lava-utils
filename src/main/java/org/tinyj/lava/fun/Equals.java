package org.tinyj.lava.fun;

import org.tinyj.lava.LavaBiPredicate;

import java.util.Objects;
import java.util.function.BiPredicate;

public final class Equals<X, Y, E extends Exception>
    implements LavaBiPredicate<X, Y, E>, BiPredicate<X, Y> {

  public static final Equals<?, ?, ?> EQUALS = new Equals<>();

  @SuppressWarnings({"unchecked", "MethodNameSameAsClassName"})
  public static <X, Y, E extends Exception> Equals<X, Y, E>
  Equals() { return (Equals) EQUALS; }

  private Equals() {}

  @Override
  public final boolean checkedTest(X x, Y y) { return Objects.equals(x, y); }

  @Override
  public final boolean test(X x, Y y) { return Objects.equals(x, y); }
}
