package org.tinyj.lava.binder;

import org.tinyj.lava.LavaBiFunction;
import org.tinyj.lava.LavaFunction;
import org.tinyj.lava.LavaPredicate;
import org.tinyj.lava.LavaSupplier;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * Enable various forms of currying on (#LavaPredicate).
 *
 * To enable a fluent syntax binders wrapping the curried function are returned
 * where applicable. This introduces some overhead that might be an issue if
 * either the result is invoked many times or many results are produced. Use
 * `bound()` unwrap these results.
 *
 * @param <X> the type of the input to the predicate
 */
public class LavaPredicateBinder<X, E extends Exception>
    implements LavaPredicate<X, E> {

  protected final LavaPredicate<X, E> bound;

  public LavaPredicateBinder(LavaPredicate<? super X, ? extends E> bound) {
    requireNonNull(bound);
    this.bound = LavaPredicate.castDown(bound);
  }

  /**
   * Curry argument with `x`.
   */
  public LavaSupplier<Boolean, E>
  bind(X x) { return () -> bound.checkedTest(x); }

  /**
   * Link the argument to supplied value. `x` is invoked each time the
   * resulting (#LavaBooleanSupplier) is invoked and the results is supplied as
   * argument to the curried (#LavaPredicate).
   */
  public LavaSupplier<Boolean, E>
  linkTo(Supplier<? extends X> x) {
    requireNonNull(x);
    return () -> bound.checkedTest(x.get());
  }

  /**
   * Map the argument. `x` is invoked each time the resulting (#LavaPredicate) is
   * invoked and the result is supplied as argument to the curried (#LavaPredicate).
   */
  public <U> LavaPredicateBinder<U, ?>
  linkTo(LavaFunction<? super U, ? extends X, ?> x) {
    requireNonNull(x);
    return new LavaPredicateBinder<>(u -> bound.checkedTest(x.checkedApply(u)));
  }

  /**
   * Map the argument. `x` is invoked each time the resulting (#LavaBiPredicate) is
   * invoked and the result is supplied as argument to the curried (#LavaPredicate).
   */
  public <U, V> LavaBiPredicateBinder<U, V, ?>
  linkTo(LavaBiFunction<? super U, ? super V, ? extends X, ?> x) {
    requireNonNull(x);
    return new LavaBiPredicateBinder<>((u, v) -> bound.checkedTest(x.checkedApply(u, v)));
  }

  /**
   * @return the wrapped (#LavaPredicate)
   */
  public LavaPredicate<X, E>
  bound() { return bound; }

  @Override
  public boolean
  checkedTest(X x) throws E { return bound.checkedTest(x); }
}
