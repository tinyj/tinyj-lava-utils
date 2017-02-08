package org.tinyj.lava.binder;

import org.tinyj.lava.JavaFun;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * Enable various forms of currying on Java's (#BiConsumer).
 *
 * To enable a fluent syntax binders wrapping the curried function are returned
 * where applicable. This introduces some overhead that might be an issue if
 * either the result is invoked many times or many results are produced. Use
 * `bound()` unwrap these results.
 *
 * @param <X> the type of the first argument to the operation
 * @param <Y> the type of the second argument to the operation
 */
public class JavaBiConsumerBinder<X, Y>
    implements BiConsumer<X, Y> {

  protected final BiConsumer<X, Y> bound;

  public JavaBiConsumerBinder(BiConsumer<? super X, ? super Y> bound) {
    requireNonNull(bound);
    this.bound = JavaFun.castDown(bound);
  }

  /**
   * Flip the arguments.
   */
  public JavaBiConsumerBinder<Y, X>
  flip() { return new JavaBiConsumerBinder<>((y, x) -> bound.accept(x, y)); }

  /**
   * Curries both arguments.
   */
  public Runnable
  bind(X x, Y y) { return () -> bound.accept(x, y); }

  /**
   * Curry the first argument.
   */
  public JavaConsumerBinder<Y>
  bindFirst(X x) { return new JavaConsumerBinder<>(y -> bound.accept(x, y)); }

  /**
   * Curry the second argument.
   */
  public JavaConsumerBinder<X>
  bindSecond(Y y) { return new JavaConsumerBinder<>(x -> bound.accept(x, y)); }

  /**
   * Link both arguments to supplied values. `x` and `y` are invoked each
   * time the resulting (#Runnable) is invoked and the results are supplied as
   * arguments to the bound (#BiConsumer).
   */
  public Runnable
  link(Supplier<? extends X> x, Supplier<? extends Y> y) {
    requireNonNull(x);
    requireNonNull(y);
    return () -> bound.accept(x.get(), y.get());
  }

  /**
   * Map both arguments. `x` and `y` are invoked each time the resulting
   * (#BiConsumer) is invoked and the results are supplied as arguments to the
   * bound (#BiConsumer).
   */
  public <U, V> JavaBiConsumerBinder<U, V>
  link(Function<? super U, ? extends X> x, Function<? super V, ? extends Y> y) {
    requireNonNull(x);
    requireNonNull(y);
    return new JavaBiConsumerBinder<>((u, v) -> bound.accept(x.apply(u), y.apply(v)));
  }

  /**
   * Link the first argument to supplied value. `x` is invoked each time the
   * resulting (#Consumer) is invoked and the results is supplied as first
   * argument to the bound (#BiConsumer).
   */
  public JavaConsumerBinder<Y>
  linkFirst(Supplier<? extends X> x) {
    requireNonNull(x);
    return new JavaConsumerBinder<>(y -> bound.accept(x.get(), y));
  }

  /**
   * Map the first argument. `x` is invoked each time the resulting
   * (#BiConsumer) is invoked and the result is supplied as first argument
   * to the bound (#BiConsumer).
   */
  public <U> JavaBiConsumerBinder<U, Y>
  linkFirst(Function<? super U, ? extends X> x) {
    requireNonNull(x);
    return new JavaBiConsumerBinder<>((u, y) -> bound.accept(x.apply(u), y));
  }

  /**
   * Link the second argument to supplied value. `y` is invoked each time the
   * resulting (#Consumer) is invoked and the results is supplied as second
   * argument to the bound (#BiConsumer).
   */
  public JavaConsumerBinder<X>
  linkSecond(Supplier<? extends Y> y) {
    requireNonNull(y);
    return new JavaConsumerBinder<>(x -> bound.accept(x, y.get()));
  }

  /**
   * Map the second argument. `y` is invoked each time the resulting
   * (#BiConsumer) is invoked and the result is supplied as second argument
   * to the bound (#BiConsumer).
   */
  public <V> JavaBiConsumerBinder<X, V>
  linkSecond(Function<? super V, ? extends Y> y) {
    requireNonNull(y);
    return new JavaBiConsumerBinder<>((x, v) -> bound.accept(x, y.apply(v)));
  }

  /**
   * @return the wrapped (#BiConsumer)
   */
  public BiConsumer<X, Y>
  bound() { return bound; }

  @Override
  public JavaBiConsumerBinder<X, Y> andThen(BiConsumer<? super X, ? super Y> after) {
    return new JavaBiConsumerBinder<>(bound.andThen(after));
  }

  @Override
  public void accept(X x, Y y) { bound.accept(x, y); }
}
