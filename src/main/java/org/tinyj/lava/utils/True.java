package org.tinyj.lava.utils;

import org.tinyj.lava.LavaBiPredicate;
import org.tinyj.lava.LavaCondition;
import org.tinyj.lava.LavaPredicate;
import org.tinyj.lava.NoException;

import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

import static org.tinyj.lava.utils.LavaFun.False;

/**
 * The {@literal true}-predicate, returns {@code true} for all tested values.
 * This is equivalent to {@code (...) -> true}. Can be used where ever a
 * {@link BooleanSupplier}, {@link Predicate}, or {@link BiPredicate} is required.
 */
public final class True<X, Y, E extends Exception>
    implements BooleanSupplier, Predicate<X>, BiPredicate<X, Y>,
    LavaCondition<E>, LavaPredicate<X, E>, LavaBiPredicate<X, Y, E> {

  static final True TRUE = new True();

  private True() {}

  @Override
  public boolean test(X x, Y y) { return true; }

  @Override
  public boolean getAsBoolean() { return true; }

  @Override
  public boolean test(X x) { return true; }

  @Override
  public boolean checkedTest(X x, Y y) throws NoException { return true; }

  @Override
  public boolean checkedTest() throws NoException { return true; }

  @Override
  public boolean checkedTest(X x) throws NoException { return true; }

  @Override
  public False<X, Y, E> negate() { return False(); }

  @Override
  public Predicate<X> and(Predicate<? super X> other) { return JavaFun.castDown(other); }

  @Override
  public BiPredicate<X, Y> and(BiPredicate<? super X, ? super Y> other) { return JavaFun.castDown(other); }

  @Override
  public Predicate<X> or(Predicate<? super X> other) { return this; }

  @Override
  public BiPredicate<X, Y> or(BiPredicate<? super X, ? super Y> other) { return this; }
}
