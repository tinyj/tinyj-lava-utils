package org.tinyj.lava.utils;

import org.tinyj.lava.LavaBiPredicate;
import org.tinyj.lava.LavaCondition;
import org.tinyj.lava.LavaPredicate;
import org.tinyj.lava.NoException;

import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

import static org.tinyj.lava.utils.LavaFun.True;

/**
 * The {@literal false}-predicate, returns {@code false} for all tested values.
 * This is equivalent to {@code (...) -> false}. Can be used where ever a
 * {@link BooleanSupplier}, {@link Predicate}, or {@link BiPredicate} is required.
 */
public final class False<X, Y, E extends Exception>
    implements BooleanSupplier, Predicate<X>, BiPredicate<X, Y>,
    LavaCondition<E>, LavaPredicate<X, E>, LavaBiPredicate<X, Y, E> {

  static final False FALSE = new False();

  private False() {}

  @Override
  public boolean test(X x, Y y) { return false; }

  @Override
  public boolean getAsBoolean() { return false; }

  @Override
  public boolean test(X x) { return false; }

  @Override
  public boolean checkedTest(X x, Y y) throws NoException { return false; }

  @Override
  public boolean checkedTest() throws NoException { return false; }

  @Override
  public boolean checkedTest(X x) throws NoException { return false; }

  @Override
  public True<X, Y, E> negate() { return True(); }

  @Override
  public Predicate<X> and(Predicate<? super X> other) { return this; }

  @Override
  public BiPredicate<X, Y> and(BiPredicate<? super X, ? super Y> other) { return this; }

  @Override
  public Predicate<X> or(Predicate<? super X> other) { return JavaFun.castDown(other); }

  @Override
  public BiPredicate<X, Y> or(BiPredicate<? super X, ? super Y> other) { return JavaFun.castDown(other); }
}
