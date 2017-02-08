package org.tinyj.lava.binder;

import org.tinyj.lava.JavaFun;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * Enable various forms of currying on Java's (#Function).
 *
 * To enable a fluent syntax binders wrapping the curried function are returned
 * where applicable. This introduces some overhead that might be an issue if
 * either the result is invoked many times or many results are produced. Use
 * `bound()` unwrap these results.
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
   * Curry argument with `x`.
   */
  public Supplier<R>
  bind(X x) { return () -> bound.apply(x); }

  /**
   * Link the argument to supplied value. `x` is invoked each time the
   * resulting (#Supplier) is invoked and the results is supplied as argument
   * to the curried (#Function).
   */
  public Supplier<R>
  linkTo(Supplier<? extends X> x) {
    requireNonNull(x);
    return () -> bound.apply(x.get());
  }

  /**
   * Map the argument. `x` is invoked each time the resulting (#Function) is
   * invoked and the result is supplied as argument to the curried (#Function).
   */
  public <U> JavaFunctionBinder<U, R>
  linkTo(Function<? super U, ? extends X> x) {
    requireNonNull(x);
    return new JavaFunctionBinder<>(u -> bound.apply(x.apply(u)));
  }

  /**
   * Map the argument. `x` is invoked each time the resulting (#BiFunction) is
   * invoked and the result is supplied as argument to the curried (#Function).
   */
  public <U, V> JavaBiFunctionBinder<U, V, R>
  linkTo(BiFunction<? super U, ? super V, ? extends X> x) {
    requireNonNull(x);
    return new JavaBiFunctionBinder<>((u, v) -> bound.apply(x.apply(u, v)));
  }

  /**
   * @return the wrapped (#Function)
   */
  public Function<X, R>
  bound() { return bound; }

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
