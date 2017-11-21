package org.tinyj.lava.binder;

import org.tinyj.lava.utils.JavaFun;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * Enable various forms of currying on Java's {@link Function}.
 * <p>
 * To enable a fluent syntax binders wrapping the curried function are returned
 * where applicable. This introduces some overhead that might be an issue if
 * either the result is invoked many times or many results are produced. Use
 * {@code bound()} to unwrap results.
 *
 * @param <X> the type of the input to the function
 * @param <R> the type of the result of the function
 */
public class JavaFunctionBinder<X, R>
    implements Function<X, R> {

  protected final Function<X, R> bound;

  public JavaFunctionBinder(Function<? super X, ? extends R> bound) {
    requireNonNull(bound);
    this.bound = JavaFun.castDown(bound);
  }

  /**
   * Curry argument with {@code x}.
   */
  public Supplier<R>
  bind(X x) { return () -> bound.apply(x); }

  /**
   * Link the argument to supplied value. {@code x} is invoked each time the
   * resulting {@link Supplier} is invoked and the results is supplied as argument
   * to the curried {@link Function}.
   */
  public Supplier<R>
  linkTo(Supplier<? extends X> x) {
    requireNonNull(x);
    return () -> bound.apply(x.get());
  }

  /**
   * Map the argument. {@code x} is invoked each time the resulting {@link Function} is
   * invoked and the result is supplied as argument to the curried {@link Function}.
   */
  public <U> JavaFunctionBinder<U, R>
  linkTo(Function<? super U, ? extends X> x) {
    requireNonNull(x);
    return new JavaFunctionBinder<>(u -> bound.apply(x.apply(u)));
  }

  /**
   * Map the argument. {@code x} is invoked each time the resulting {@link BiFunction} is
   * invoked and the result is supplied as argument to the curried {@link Function}.
   */
  public <U, V> JavaBiFunctionBinder<U, V, R>
  linkTo(BiFunction<? super U, ? super V, ? extends X> x) {
    requireNonNull(x);
    return new JavaBiFunctionBinder<>((u, v) -> bound.apply(x.apply(u, v)));
  }

  /**
   * @return the wrapped {@link Function}
   */
  public Function<X, R>
  bound() { return bound; }

  public <U extends X, V> BiFunction<U, V, R>
  applyFirst() { return ((u, v) -> bound.apply(u)); }

  public <U, V extends X> BiFunction<U, V, R>
  applySecond() { return ((u, v) -> bound.apply(v)); }

  @Override
  public <U> JavaFunctionBinder<U, R>
  compose(Function<? super U, ? extends X> before) { return new JavaFunctionBinder<>(bound.compose(before)); }

  @Override
  public <V> JavaFunctionBinder<X, V>
  andThen(Function<? super R, ? extends V> after) { return new JavaFunctionBinder<>(bound.andThen(after)); }

  @Override
  public R
  apply(X x) { return bound.apply(x); }
}
