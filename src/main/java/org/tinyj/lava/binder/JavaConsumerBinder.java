package org.tinyj.lava.binder;

import org.tinyj.lava.JavaFun;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * Enable various forms of currying on Java's (#Consumer).
 *
 * To enable a fluent syntax binders wrapping the curried function are returned
 * where applicable. This introduces some overhead that might be an issue if
 * either the result is invoked many times or many results are produced. Use
 * `bound()` unwrap these results.
 *
 * @param <X> the type of the argument to the operation
 */
public class JavaConsumerBinder<X>
    implements Consumer<X> {

  protected final Consumer<X> bound;

  public JavaConsumerBinder(Consumer<? super X> bound) {
    requireNonNull(bound);
    this.bound = JavaFun.castDown(bound);
  }

  /**
   * Curry argument with `x`.
   */
  public Runnable
  bind(X x) { return () -> bound.accept(x); }

  /**
   * Link the argument to supplied value. `x` is invoked each time the
   * resulting (#Runnable) is invoked and the results is supplied as argument
   * to the curried (#Consumer).
   */
  public Runnable
  linkTo(Supplier<? extends X> x) {
    requireNonNull(x);
    return () -> bound.accept(x.get());
  }

  /**
   * Map the argument. `x` is invoked each time the resulting (#Consumer) is
   * invoked and the result is supplied as argument to the curried (#Consumer).
   */
  public <U> JavaConsumerBinder<U>
  linkTo(Function<? super U, ? extends X> x) {
    requireNonNull(x);
    return new JavaConsumerBinder<>(u -> bound.accept(x.apply(u)));
  }

  /**
   * Map the argument. `x` is invoked each time the resulting (#BiConsumer) is
   * invoked and the result is supplied as argument to the curried (#Consumer).
   */
  public <U, V> JavaBiConsumerBinder<U, V>
  linkTo(BiFunction<? super U, ? super V, ? extends X> x) {
    requireNonNull(x);
    return new JavaBiConsumerBinder<>((u, v) -> bound.accept(x.apply(u, v)));
  }

  /**
   * @return the wrapped (#Consumer)
   */
  public Consumer<X>
  bound() { return bound; }

  @Override
  public Consumer<X> andThen(Consumer<? super X> after) { return new JavaConsumerBinder<>(bound.andThen(after)); }

  @Override
  public void accept(X x) { bound.accept(x); }
}
