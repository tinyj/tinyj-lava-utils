package org.tinyj.lava.binder;

import org.tinyj.lava.LavaBiConsumer;
import org.tinyj.lava.LavaFunction;
import org.tinyj.lava.LavaRunnable;
import org.tinyj.lava.LavaSupplier;

import static java.util.Objects.requireNonNull;

/**
 * Enable various forms of currying on (#LavaBiConsumer).
 *
 * To enable a fluent syntax binders wrapping the curried function are returned
 * where applicable. This introduces some overhead that might be an issue if
 * either the result is invoked many times or many results are produced. Use
 * `bound()` unwrap these results.
 *
 * @param <X> the type of the first argument to the operation
 * @param <Y> the type of the second argument to the operation
 */
public class LavaBiConsumerBinder<X, Y, E extends Exception>
    implements LavaBiConsumer<X, Y, E> {

  protected final LavaBiConsumer<X, Y, E> bound;

  public LavaBiConsumerBinder(LavaBiConsumer<? super X, ? super Y, ? extends E> bound) {
    requireNonNull(bound);
    this.bound = LavaBiConsumer.castDown(bound);
  }

  /**
   * Flip the arguments.
   */
  public LavaBiConsumerBinder<Y, X, E>
  flip() { return new LavaBiConsumerBinder<>((y, x) -> bound.checkedAccept(x, y)); }

  /**
   * Curries both arguments.
   */
  public LavaRunnable<E>
  bind(X x, Y y) { return () -> bound.checkedAccept(x, y); }

  /**
   * Curry the first argument.
   */
  public LavaConsumerBinder<Y, E>
  bindFirst(X x) { return new LavaConsumerBinder<>(y -> bound.checkedAccept(x, y)); }

  /**
   * Curry the second argument.
   */
  public LavaConsumerBinder<X, E>
  bindSecond(Y y) { return new LavaConsumerBinder<>(x -> bound.checkedAccept(x, y)); }

  /**
   * Link both arguments to supplied values. `x` and `y` are invoked each
   * time the resulting (#LavaRunnable) is invoked and the results are supplied as
   * arguments to the bound (#LavaBiConsumer).
   */
  public LavaRunnable<?>
  link(LavaSupplier<? extends X, ?> x, LavaSupplier<? extends Y, ?> y) {
    requireNonNull(x);
    requireNonNull(y);
    return () -> bound.checkedAccept(x.checkedGet(), y.checkedGet());
  }

  /**
   * Map both arguments. `x` and `y` are invoked each time the resulting
   * (#LavaBiConsumer) is invoked and the results are supplied as arguments to the
   * bound (#LavaBiConsumer).
   */
  public <U, V> LavaBiConsumerBinder<U, V, ?>
  link(LavaFunction<? super U, ? extends X, ?> x, LavaFunction<? super V, ? extends Y, ?> y) {
    requireNonNull(x);
    requireNonNull(y);
    return new LavaBiConsumerBinder<>((u, v) -> bound.checkedAccept(x.checkedApply(u), y.checkedApply(v)));
  }

  /**
   * Link the first argument to supplied value. `x` is invoked each time the
   * resulting (#LavaConsumer) is invoked and the results is supplied as first
   * argument to the bound (#LavaBiConsumer).
   */
  public LavaConsumerBinder<Y, ?>
  linkFirst(LavaSupplier<? extends X, ?> x) {
    requireNonNull(x);
    return new LavaConsumerBinder<>(y -> bound.checkedAccept(x.checkedGet(), y));
  }

  /**
   * Map the first argument. `x` is invoked each time the resulting
   * (#LavaBiConsumer) is invoked and the result is supplied as first argument
   * to the bound (#LavaBiConsumer).
   */
  public <U> LavaBiConsumerBinder<U, Y, ?>
  linkFirst(LavaFunction<? super U, ? extends X, ?> x) {
    requireNonNull(x);
    return new LavaBiConsumerBinder<>((u, y) -> bound.checkedAccept(x.checkedApply(u), y));
  }

  /**
   * Link the second argument to supplied value. `y` is invoked each time the
   * resulting (#LavaConsumer) is invoked and the results is supplied as second
   * argument to the bound (#LavaBiConsumer).
   */
  public LavaConsumerBinder<X, ?>
  linkSecond(LavaSupplier<? extends Y, ?> y) {
    requireNonNull(y);
    return new LavaConsumerBinder<>(x -> bound.checkedAccept(x, y.checkedGet()));
  }

  /**
   * Map the second argument. `y` is invoked each time the resulting
   * (#LavaBiConsumer) is invoked and the result is supplied as second argument
   * to the bound (#LavaBiConsumer).
   */
  public <V> LavaBiConsumerBinder<X, V, ?>
  linkSecond(LavaFunction<? super V, ? extends Y, ?> y) {
    requireNonNull(y);
    return new LavaBiConsumerBinder<>((x, v) -> bound.checkedAccept(x, y.checkedApply(v)));
  }

  /**
   * @return the wrapped (#LavaBiConsumer)
   */
  public LavaBiConsumer<X, Y, E>
  bound() {
    return bound;
  }

  @Override
  public void checkedAccept(X x, Y y) throws E { bound.checkedAccept(x, y); }
}
