package org.tinyj.lava.binder;

import org.tinyj.lava.JavaFun;

import java.util.function.*;

import static java.util.Objects.requireNonNull;

/**
 * Enable various forms of currying on Java's (#Predicate).
 *
 * To enable a fluent syntax binders wrapping the curried function are returned
 * where applicable. This introduces some overhead that might be an issue if
 * either the result is invoked many times or many results are produced. Use
 * `bound()` unwrap these results.
 *
 * @param <X> the type of the input to the predicate
 */
public class JavaPredicateBinder<X>
    implements Predicate<X> {

  protected final Predicate<X> bound;

  public JavaPredicateBinder(Predicate<? super X> bound) {
    requireNonNull(bound);
    this.bound = JavaFun.castDown(bound);
  }

  /**
   * Curry argument with `x`.
   */
  public BooleanSupplier
  bind(X x) { return () -> bound.test(x); }

  /**
   * Link the argument to supplied value. `x` is invoked each time the
   * resulting (#BooleanSupplier) is invoked and the results is supplied as
   * argument to the curried (#Predicate).
   */
  public BooleanSupplier
  linkTo(Supplier<? extends X> x) {
    requireNonNull(x);
    return () -> bound.test(x.get());
  }

  /**
   * Map the argument. `x` is invoked each time the resulting (#Predicate) is
   * invoked and the result is supplied as argument to the curried (#Predicate).
   */
  public <U> JavaPredicateBinder<U>
  linkTo(Function<? super U, ? extends X> x) {
    requireNonNull(x);
    return new JavaPredicateBinder<>(u -> bound.test(x.apply(u)));
  }

  /**
   * Map the argument. `x` is invoked each time the resulting (#BiPredicate) is
   * invoked and the result is supplied as argument to the curried (#Predicate).
   */
  public <U, V> JavaBiPredicateBinder<U, V>
  linkTo(BiFunction<? super U, ? super V, ? extends X> x) {
    requireNonNull(x);
    return new JavaBiPredicateBinder<>((u, v) -> bound.test(x.apply(u, v)));
  }

  /**
   * @return the wrapped (#Predicate)
   */
  public Predicate<X>
  bound() { return bound; }

  @Override
  public boolean
  test(X x) { return bound.test(x); }
}
