package org.tinyj.lava.binder;

import org.tinyj.lava.utils.JavaFun;

import java.util.function.*;

import static java.util.Objects.requireNonNull;

/**
 * Enable various forms of currying on Java's {@link Consumer}.
 * <p>
 * To enable a fluent syntax binders wrapping the curried function are returned
 * where applicable. This introduces some overhead that might be an issue if
 * either the result is invoked many times or many results are produced. Use
 * {@code bound()} to unwrap results.
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
   * Curry argument with {@code x}.
   */
  public Runnable
  bind(X x) { return () -> bound.accept(x); }

  /**
   * Link the argument to supplied value. {@code x} is invoked each time the
   * resulting {@link Runnable} is invoked and the results is supplied as argument
   * to the curried {@link Consumer}.
   */
  public Runnable
  linkTo(Supplier<? extends X> x) {
    requireNonNull(x);
    return () -> bound.accept(x.get());
  }

  /**
   * Map the argument. {@code x} is invoked each time the resulting {@link Consumer} is
   * invoked and the result is supplied as argument to the curried {@link Consumer}.
   */
  public <U> JavaConsumerBinder<U>
  linkTo(Function<? super U, ? extends X> x) {
    requireNonNull(x);
    return new JavaConsumerBinder<>(u -> bound.accept(x.apply(u)));
  }

  /**
   * Map the argument. {@code x} is invoked each time the resulting {@link BiConsumer} is
   * invoked and the result is supplied as argument to the curried {@link Consumer}.
   */
  public <U, V> JavaBiConsumerBinder<U, V>
  linkTo(BiFunction<? super U, ? super V, ? extends X> x) {
    requireNonNull(x);
    return new JavaBiConsumerBinder<>((u, v) -> bound.accept(x.apply(u, v)));
  }

  /**
   * @return the wrapped {@link Consumer}
   */
  public Consumer<X>
  bound() { return bound; }

  public <U extends X, V> BiConsumer<U, V>
  acceptFirst() { return ((u, v) -> bound.accept(u)); }

  public <U, V extends X> BiConsumer<U, V>
  acceptSecond() { return ((u, v) -> bound.accept(v)); }

  @Override
  public Consumer<X> andThen(Consumer<? super X> after) { return new JavaConsumerBinder<>(bound.andThen(after)); }

  @Override
  public void accept(X x) { bound.accept(x); }
}
