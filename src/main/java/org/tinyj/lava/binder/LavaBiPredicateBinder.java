package org.tinyj.lava.binder;

import org.tinyj.lava.LavaBiPredicate;
import org.tinyj.lava.LavaBooleanSupplier;
import org.tinyj.lava.LavaFunction;
import org.tinyj.lava.LavaSupplier;

import static java.util.Objects.requireNonNull;

/**
 * Enable various forms of currying on (#LavaBiPredicate).
 *
 * To enable a fluent syntax binders wrapping the curried function are returned
 * where applicable. This introduces some overhead that might be an issue if
 * either the result is invoked many times or many results are produced. Use
 * `bound()` unwrap these results.
 *
 * @param <X> the type of the first argument to the predicate
 * @param <Y> the type of the second argument the predicate
 */
public class LavaBiPredicateBinder<X, Y, E extends Exception>
    implements LavaBiPredicate<X, Y, E> {

  protected final LavaBiPredicate<X, Y, E> bound;

  public LavaBiPredicateBinder(LavaBiPredicate<? super X, ? super Y, ? extends E> bound) {
    requireNonNull(bound);
    this.bound = LavaBiPredicate.castDown(bound);
  }

  /**
   * Flip the arguments.
   */
  public LavaBiPredicate<Y, X, E>
  flip() { return (y, x) -> bound.checkedTest(x, y); }

  /**
   * Curries both arguments.
   */
  public LavaBooleanSupplier<E>
  bind(X x, Y y) { return () -> bound.checkedTest(x, y);}

  /**
   * Curry the first argument.
   */
  public LavaPredicateBinder<Y, E>
  bindFirst(X x) { return new LavaPredicateBinder<>(y -> bound.checkedTest(x, y)); }

  /**
   * Curry the second argument.
   */
  public LavaPredicateBinder<X, E>
  bindSecond(Y y) { return new LavaPredicateBinder<>(x -> bound.checkedTest(x, y)); }

  /**
   * Link both arguments to supplied values. `x` and `y` are invoked each
   * time the resulting (#LavaBooleanSupplier) is invoked and the results are
   * supplied as arguments to the bound (#LavaBiPredicate).
   */
  public LavaBooleanSupplier<?>
  link(LavaSupplier<? extends X, ?> x, LavaSupplier<? extends Y, ?> y) {
    requireNonNull(x);
    requireNonNull(y);
    return () -> bound.checkedTest(x.checkedGet(), y.checkedGet());
  }

  /**
   * Map both arguments. `x` and `y` are invoked each time the resulting
   * (#LavaBiPredicate) is invoked and the results are supplied as arguments to the
   * bound (#LavaBiPredicate).
   */
  public <U, V> LavaBiPredicateBinder<U, V, ?>
  link(LavaFunction<? super U, ? extends X, ?> x, LavaFunction<? super V, ? extends Y, ?> y) {
    requireNonNull(x);
    requireNonNull(y);
    return new LavaBiPredicateBinder<>((u, v) -> bound.checkedTest(x.checkedApply(u), y.checkedApply(v)));
  }

  /**
   * Link the first argument to supplied value. `x` is invoked each time the
   * resulting (#LavaPredicate) is invoked and the results is supplied as first
   * argument to the bound (#LavaBiPredicate).
   */
  public LavaPredicateBinder<Y, ?>
  linkFirst(LavaSupplier<? extends X, ?> x) {
    requireNonNull(x);
    return new LavaPredicateBinder<>(y -> bound.checkedTest(x.checkedGet(), y));
  }

  /**
   * Map the first argument. `x` is invoked each time the resulting
   * (#LavaBiPredicate) is invoked and the result is supplied as first argument
   * to the bound (#LavaBiPredicate).
   */
  public <U> LavaBiPredicateBinder<U, Y, ?>
  linkFirst(LavaFunction<? super U, ? extends X, ?> x) {
    requireNonNull(x);
    return new LavaBiPredicateBinder<>((u, y) -> bound.checkedTest(x.checkedApply(u), y));
  }

  /**
   * Link the second argument to supplied value. `y` is invoked each time the
   * resulting (#LavaPredicate) is invoked and the results is supplied as second
   * argument to the bound (#LavaBiPredicate).
   */
  public LavaPredicateBinder<X, ?>
  linkSecond(LavaSupplier<? extends Y, ?> y) {
    requireNonNull(y);
    return new LavaPredicateBinder<>(x -> bound.checkedTest(x, y.checkedGet()));
  }

  /**
   * Map the second argument. `y` is invoked each time the resulting
   * (#LavaBiPredicate) is invoked and the result is supplied as second argument
   * to the bound (#LavaBiPredicate).
   */
  public <V> LavaBiPredicateBinder<X, V, ?>
  linkSecond(LavaFunction<? super V, ? extends Y, ?> y) {
    requireNonNull(y);
    return new LavaBiPredicateBinder<>((x, v) -> bound.checkedTest(x, y.checkedApply(v)));
  }

  /**
   * @return the wrapped (#LavaBiPredicate)
   */
  public LavaBiPredicate<X, Y, E>
  bound() { return bound; }

  @Override
  public boolean
  checkedTest(X x, Y y) throws E { return bound.checkedTest(x, y); }
}
