package org.tinyj.lava.binder;

import org.tinyj.lava.LavaBiFunction;
import org.tinyj.lava.LavaFunction;
import org.tinyj.lava.LavaSupplier;

import static java.util.Objects.requireNonNull;

/**
 * Enable various forms of currying on {@link LavaBiFunction}.
 * <p>
 * To enable a fluent syntax binders wrapping the curried function are returned
 * where applicable. This introduces some overhead that might be an issue if
 * either the result is invoked many times or many results are produced. Use
 * {@code bound()} unwrap these results.
 *
 * @param <X> the type of the first argument to the function
 * @param <Y> the type of the second argument to the function
 * @param <R> the type of the result of the function
 */
public class LavaBiFunctionBinder<X, Y, R, E extends Exception>
    implements LavaBiFunction<X, Y, R, E> {

  protected final LavaBiFunction<X, Y, R, E> bound;

  public LavaBiFunctionBinder(LavaBiFunction<? super X, ? super Y, ? extends R, ? extends E> bound) {
    requireNonNull(bound);
    this.bound = LavaBiFunction.castDown(bound);
  }

  /**
   * Flip the arguments.
   */
  public LavaBiFunction<Y, X, R, E>
  flip() { return (y, x) -> bound.checkedApply(x, y); }

  /**
   * Curries both arguments.
   */
  public LavaSupplier<R, E>
  bind(X x, Y y) { return () -> bound.checkedApply(x, y);}

  /**
   * Curry the first argument.
   */
  public LavaFunctionBinder<Y, R, E>
  bindFirst(X x) { return new LavaFunctionBinder<>(y -> bound.checkedApply(x, y)); }

  /**
   * Curry the second argument.
   */
  public LavaFunctionBinder<X, R, E>
  bindSecond(Y y) { return new LavaFunctionBinder<>(x -> bound.checkedApply(x, y)); }

  /**
   * Link both arguments to supplied values. {@code x} and {@code y} are invoked each
   * time the resulting {@link LavaSupplier} is invoked and the results are supplied as
   * arguments to the bound {@link LavaBiFunction}.
   */
  public LavaSupplier<R, ?>
  link(LavaSupplier<? extends X, ?> x, LavaSupplier<? extends Y, ?> y) {
    requireNonNull(x);
    requireNonNull(y);
    return () -> bound.checkedApply(x.checkedGet(), y.checkedGet());
  }

  /**
   * Map both arguments. {@code x} and {@code y} are invoked each time the resulting
   * {@link LavaBiFunction} is invoked and the results are supplied as arguments to the
   * bound {@link LavaBiFunction}.
   */
  public <U, V> LavaBiFunctionBinder<U, V, R, ?>
  link(LavaFunction<? super U, ? extends X, ?> x, LavaFunction<? super V, ? extends Y, ?> y) {
    requireNonNull(x);
    requireNonNull(y);
    return new LavaBiFunctionBinder<>((u, v) -> bound.checkedApply(x.checkedApply(u), y.checkedApply(v)));
  }

  /**
   * Link the first argument to supplied value. {@code x} is invoked each time the
   * resulting {@link LavaFunction} is invoked and the results is supplied as first
   * argument to the bound {@link LavaBiFunction}.
   */
  public LavaFunctionBinder<Y, R, ?>
  linkFirst(LavaSupplier<? extends X, ?> x) {
    requireNonNull(x);
    return new LavaFunctionBinder<>(y -> bound.checkedApply(x.checkedGet(), y));
  }

  /**
   * Map the first argument. {@code x} is invoked each time the resulting
   * {@link LavaBiFunction} is invoked and the result is supplied as first argument
   * to the bound {@link LavaBiFunction}.
   */
  public <U> LavaBiFunctionBinder<U, Y, R, ?>
  linkFirst(LavaFunction<? super U, ? extends X, ?> x) {
    requireNonNull(x);
    return new LavaBiFunctionBinder<>((u, y) -> bound.checkedApply(x.checkedApply(u), y));
  }

  /**
   * Link the second argument to supplied value. {@code y} is invoked each time the
   * resulting {@link LavaFunction} is invoked and the results is supplied as second
   * argument to the bound {@link LavaBiFunction}.
   */
  public LavaFunctionBinder<X, R, ?>
  linkSecond(LavaSupplier<? extends Y, ?> y) {
    requireNonNull(y);
    return new LavaFunctionBinder<>(x -> bound.checkedApply(x, y.checkedGet()));
  }

  /**
   * Map the second argument. {@code y} is invoked each time the resulting
   * {@link LavaBiFunction} is invoked and the result is supplied as second argument
   * to the bound {@link LavaBiFunction}.
   */
  public <V> LavaBiFunctionBinder<X, V, R, ?>
  linkSecond(LavaFunction<? super V, ? extends Y, ?> y) {
    requireNonNull(y);
    return new LavaBiFunctionBinder<>((x, v) -> bound.checkedApply(x, y.checkedApply(v)));
  }

  /**
   * @return the wrapped {@link LavaBiFunction}
   */
  public LavaBiFunction<X, Y, R, E>
  bound() { return bound; }

  @Override
  public R
  checkedApply(X x, Y y) throws E { return bound.checkedApply(x, y); }
}
