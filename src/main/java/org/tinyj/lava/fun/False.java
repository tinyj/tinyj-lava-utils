package org.tinyj.lava.fun;

import org.tinyj.lava.LavaPredicate;

import java.util.function.Predicate;

public final class False<X, E extends Exception>
    implements LavaPredicate<X, E>, Predicate<X> {

  public static final False<?, ?> FALSE = new False<>();

  @SuppressWarnings({"unchecked", "MethodNameSameAsClassName"})
  public static <X, E extends Exception> False<X, E>
  False() { return (False) FALSE; }

  private False() {}

  @Override
  public final boolean checkedTest(X x) { return false; }

  @Override
  public final boolean test(X x) { return false; }
}
