package org.tinyj.lava.binder;

import org.tinyj.lava.JavaFun;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * Enable various forms of currying on Java's (#BiFunction).
 *
 * To enable a fluent syntax binders wrapping the curried function are returned
 * where applicable. This introduces some overhead that might be an issue if
 * either the result is invoked many times or many results are produced. Use
 * `bound()` unwrap these results.
 *
 * @param <X> the type of the first argument to the function
 * @param <Y> the type of the second argument to the function
 * @param <R> the type of the result of the function
 */
public class JavaBiFunctionBinder<X, Y, R>
    implements BiFunction<X, Y, R> {

  protected final BiFunction<X, Y, R> bound;

  public JavaBiFunctionBinder(BiFunction<? super X, ? super Y, ? extends R> bound) {
    requireNonNull(bound);
    this.bound = JavaFun.castDown(bound);
  }

  /**
   * Flip the arguments.
   */
  public BiFunction<Y, X, R>
  flip() { return (y, x) -> bound.apply(x, y); }

  /**
   * Curries both arguments.
   */
  public Supplier<R>
  bind(X x, Y y) { return () -> bound.apply(x, y);}

  /**
   * Curry the first argument.
   */
  public JavaFunctionBinder<Y, R>
  bindFirst(X x) { return new JavaFunctionBinder<>(y -> bound.apply(x, y)); }

  /**
   * Curry the second argument.
   */
  public JavaFunctionBinder<X, R>
  bindSecond(Y y) { return new JavaFunctionBinder<>(x -> bound.apply(x, y)); }

  /**
   * Link both arguments to supplied values. `x` and `y` are invoked each
   * time the resulting (#Supplier) is invoked and the results are supplied as
   * arguments to the bound (#BiFunction).
   */
  public Supplier<R>
  link(Supplier<? extends X> x, Supplier<? extends Y> y) {
    requireNonNull(x);
    requireNonNull(y);
    return () -> bound.apply(x.get(), y.get());
  }

  /**
   * Map both arguments. `x` and `y` are invoked each time the resulting
   * (#BiFunction) is invoked and the results are supplied as arguments to the
   * bound (#BiFunction).
   */
  public <U, V> JavaBiFunctionBinder<U, V, R>
  link(Function<? super U, ? extends X> x, Function<? super V, ? extends Y> y) {
    requireNonNull(x);
    requireNonNull(y);
    return new JavaBiFunctionBinder<>((u, v) -> bound.apply(x.apply(u), y.apply(v)));
  }

  /**
   * Link the first argument to supplied value. `x` is invoked each time the
   * resulting (#Function) is invoked and the results is supplied as first
   * argument to the bound (#BiFunction).
   */
  public JavaFunctionBinder<Y, R>
  linkFirst(Supplier<? extends X> x) {
    requireNonNull(x);
    return new JavaFunctionBinder<>(y -> bound.apply(x.get(), y));
  }

  /**
   * Map the first argument. `x` is invoked each time the resulting
   * (#BiFunction) is invoked and the result is supplied as first argument
   * to the bound (#BiFunction).
   */
  public <U> JavaBiFunctionBinder<U, Y, R>
  linkFirst(Function<? super U, ? extends X> x) {
    requireNonNull(x);
    return new JavaBiFunctionBinder<>((u, y) -> bound.apply(x.apply(u), y));
  }

  /**
   * Link the second argument to supplied value. `y` is invoked each time the
   * resulting (#Function) is invoked and the results is supplied as second
   * argument to the bound (#BiFunction).
   */
  public JavaFunctionBinder<X, R>
  linkSecond(Supplier<? extends Y> y) {
    requireNonNull(y);
    return new JavaFunctionBinder<>(x -> bound.apply(x, y.get()));
  }

  /**
   * Map the second argument. `y` is invoked each time the resulting
   * (#BiFunction) is invoked and the result is supplied as second argument
   * to the bound (#BiFunction).
   */
  public <V> JavaBiFunctionBinder<X, V, R>
  linkSecond(Function<? super V, ? extends Y> y) {
    requireNonNull(y);
    return new JavaBiFunctionBinder<>((x, v) -> bound.apply(x, y.apply(v)));
  }

  /**
   * @return the wrapped (#BiFunction)
   */
  public BiFunction<X, Y, R>
  bound() { return bound; }

  @Override
  public <V> JavaBiFunctionBinder<X, Y, V>
  andThen(Function<? super R, ? extends V> after) { return new JavaBiFunctionBinder<>(bound.andThen(after)); }

  @Override
  public R
  apply(X x, Y y) { return bound.apply(x, y); }
}
