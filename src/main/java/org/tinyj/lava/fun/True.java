package org.tinyj.lava.fun;

import org.tinyj.lava.LavaPredicate;

import java.util.function.Predicate;

public final class True<X, E extends Exception>
    implements LavaPredicate<X, E>, Predicate<X> {

  public static final True<?, ?> TRUE = new True<>();

  @SuppressWarnings({"unchecked", "MethodNameSameAsClassName"})
  public static <X, E extends Exception> True<X, E>
  True() { return (True) TRUE; }

  private True() {}

  @Override
  public final boolean checkedTest(X x) { return true; }

  @Override
  public final boolean test(X x) { return true; }
}
