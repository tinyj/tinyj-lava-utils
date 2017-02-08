package org.tinyj.lava.binder;

import org.tinyj.lava.JavaFun;

import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * Enable various forms of currying on Java's (#BiPredicate).
 *
 * To enable a fluent syntax binders wrapping the curried function are returned
 * where applicable. This introduces some overhead that might be an issue if
 * either the result is invoked many times or many results are produced. Use
 * `bound()` unwrap these results.
 *
 * @param <X> the type of the first argument to the predicate
 * @param <Y> the type of the second argument the predicate
 */
public class JavaBiPredicateBinder<X, Y>
    implements BiPredicate<X, Y> {

  protected final BiPredicate<X, Y> bound;

  public JavaBiPredicateBinder(BiPredicate<? super X, ? super Y> bound) {
    requireNonNull(bound);
    this.bound = JavaFun.castDown(bound);
  }

  /**
   * Flip the arguments.
   */
  public BiPredicate<Y, X>
  flip() { return (y, x) -> bound.test(x, y); }

  /**
   * Curries both arguments.
   */
  public BooleanSupplier
  bind(X x, Y y) { return () -> bound.test(x, y);}

  /**
   * Curry the first argument.
   */
  public JavaPredicateBinder<Y>
  bindFirst(X x) { return new JavaPredicateBinder<>(y -> bound.test(x, y)); }

  /**
   * Curry the second argument.
   */
  public JavaPredicateBinder<X>
  bindSecond(Y y) { return new JavaPredicateBinder<>(x -> bound.test(x, y)); }

  /**
   * Link both arguments to supplied values. `x` and `y` are invoked each
   * time the resulting (#BooleanSupplier) is invoked and the results are
   * supplied as arguments to the bound (#BiPredicate).
   */
  public BooleanSupplier
  link(Supplier<? extends X> x, Supplier<? extends Y> y) {
    requireNonNull(x);
    requireNonNull(y);
    return () -> bound.test(x.get(), y.get());
  }

  /**
   * Map both arguments. `x` and `y` are invoked each time the resulting
   * (#BiPredicate) is invoked and the results are supplied as arguments to the
   * bound (#BiPredicate).
   */
  public <U, V> JavaBiPredicateBinder<U, V>
  link(Function<? super U, ? extends X> x, Function<? super V, ? extends Y> y) {
    requireNonNull(x);
    requireNonNull(y);
    return new JavaBiPredicateBinder<>((u, v) -> bound.test(x.apply(u), y.apply(v)));
  }

  /**
   * Link the first argument to supplied value. `x` is invoked each time the
   * resulting (#Predicate) is invoked and the results is supplied as first
   * argument to the bound (#BiPredicate).
   */
  public JavaPredicateBinder<Y>
  linkFirst(Supplier<? extends X> x) {
    requireNonNull(x);
    return new JavaPredicateBinder<>(y -> bound.test(x.get(), y));
  }

  /**
   * Map the first argument. `x` is invoked each time the resulting
   * (#BiPredicate) is invoked and the result is supplied as first argument
   * to the bound (#BiPredicate).
   */
  public <U> JavaBiPredicateBinder<U, Y>
  linkFirst(Function<? super U, ? extends X> x) {
    requireNonNull(x);
    return new JavaBiPredicateBinder<>((u, y) -> bound.test(x.apply(u), y));
  }

  /**
   * Link the second argument to supplied value. `y` is invoked each time the
   * resulting (#Predicate) is invoked and the results is supplied as second
   * argument to the bound (#BiPredicate).
   */
  public JavaPredicateBinder<X>
  linkSecond(Supplier<? extends Y> y) {
    requireNonNull(y);
    return new JavaPredicateBinder<>(x -> bound.test(x, y.get()));
  }

  /**
   * Map the second argument. `y` is invoked each time the resulting
   * (#BiPredicate) is invoked and the result is supplied as second argument
   * to the bound (#BiPredicate).
   */
  public <V> JavaBiPredicateBinder<X, V>
  linkSecond(Function<? super V, ? extends Y> y) {
    requireNonNull(y);
    return new JavaBiPredicateBinder<>((x, v) -> bound.test(x, y.apply(v)));
  }

  /**
   * @return the wrapped (#BiPredicate)
   */
  public BiPredicate<X, Y>
  bound() { return bound; }

  @Override
  public boolean
  test(X x, Y y) { return bound.test(x, y); }
}
