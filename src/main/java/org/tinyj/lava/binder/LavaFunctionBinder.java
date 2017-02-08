package org.tinyj.lava.binder;

import org.tinyj.lava.LavaBiFunction;
import org.tinyj.lava.LavaFunction;
import org.tinyj.lava.LavaSupplier;

import static java.util.Objects.requireNonNull;

/**
 * Enable various forms of currying on (#LavaFunction).
 *
 * To enable a fluent syntax binders wrapping the curried function are returned
 * where applicable. This introduces some overhead that might be an issue if
 * either the result is invoked many times or many results are produced. Use
 * `bound()` unwrap these results.
 *
 * @param <X> the type of the input to the function
 * @param <R> the type of the result of the function
 */
public class LavaFunctionBinder<X, R, E extends Exception>
    implements LavaFunction<X, R, E> {

  protected final LavaFunction<X, R, E> bound;

  public LavaFunctionBinder(LavaFunction<? super X, ? extends R, ? extends E> bound) {
    requireNonNull(bound);
    this.bound = LavaFunction.castDown(bound);
  }

  /**
   * Curry argument with `x`.
   */
  public LavaSupplier<R, E>
  bind(X x) { return () -> bound.checkedApply(x); }

  /**
   * Link the argument to supplied value. `x` is invoked each time the
   * resulting (#LavaSupplier) is invoked and the results is supplied as argument
   * to the curried (#LavaFunction).
   */
  public LavaSupplier<R, ?>
  linkTo(LavaSupplier<? extends X, ?> x) {
    requireNonNull(x);
    return () -> bound.checkedApply(x.checkedGet());
  }

  /**
   * Map the argument. `x` is invoked each time the resulting (#LavaFunction) is
   * invoked and the result is supplied as argument to the curried (#LavaFunction).
   */
  public <U> LavaFunctionBinder<U, R, ?>
  linkTo(LavaFunction<? super U, ? extends X, ?> x) {
    requireNonNull(x);
    return new LavaFunctionBinder<>(u -> bound.checkedApply(x.checkedApply(u)));
  }

  /**
   * Map the argument. `x` is invoked each time the resulting (#LavaBiFunction) is
   * invoked and the result is supplied as argument to the curried (#LavaFunction).
   */
  public <U, V> LavaBiFunctionBinder<U, V, R, ?>
  linkTo(LavaBiFunction<? super U, ? super V, ? extends X, ?> x) {
    requireNonNull(x);
    return new LavaBiFunctionBinder<>((u, v) -> bound.checkedApply(x.checkedApply(u, v)));
  }

  /**
   * @return the wrapped (#LavaFunction)
   */
  public LavaFunction<X, R, E>
  bound() { return bound; }

  @Override
  public R
  checkedApply(X x) throws E { return bound.checkedApply(x); }
}
