package org.tinyj.lava.binder;

import org.tinyj.lava.LavaBiPredicate;
import org.tinyj.lava.utils.JavaFun;

import java.util.function.*;

import static java.util.Objects.requireNonNull;

/**
 * Enable various forms of currying on Java's {@link BiPredicate}.
 * <p>
 * To enable a fluent syntax binders wrapping the curried function are returned
 * where applicable. This introduces some overhead that might be an issue if
 * either the result is invoked many times or many results are produced. Use
 * {@code bound()} to unwrap results.
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
   * Link both arguments to supplied values. {@code x} and {@code y} are invoked each
   * time the resulting {@link BooleanSupplier} is invoked and the results are
   * supplied as arguments to the bound {@link BiPredicate}.
   */
  public BooleanSupplier
  link(Supplier<? extends X> x, Supplier<? extends Y> y) {
    requireNonNull(x);
    requireNonNull(y);
    return () -> bound.test(x.get(), y.get());
  }

  /**
   * Map both arguments. {@code x} and {@code y} are invoked each time the resulting
   * {@link BiPredicate} is invoked and the results are supplied as arguments to the
   * bound {@link BiPredicate}.
   */
  public <U, V> JavaBiPredicateBinder<U, V>
  link(Function<? super U, ? extends X> x, Function<? super V, ? extends Y> y) {
    requireNonNull(x);
    requireNonNull(y);
    return new JavaBiPredicateBinder<>((u, v) -> bound.test(x.apply(u), y.apply(v)));
  }

  /**
   * Link the first argument to supplied value. {@code x} is invoked each time the
   * resulting {@link Predicate} is invoked and the results is supplied as first
   * argument to the bound {@link BiPredicate}.
   */
  public JavaPredicateBinder<Y>
  linkFirst(Supplier<? extends X> x) {
    requireNonNull(x);
    return new JavaPredicateBinder<>(y -> bound.test(x.get(), y));
  }

  /**
   * Map the first argument. {@code x} is invoked each time the resulting
   * {@link BiPredicate} is invoked and the result is supplied as first argument
   * to the bound {@link BiPredicate}.
   */
  public <U> JavaBiPredicateBinder<U, Y>
  linkFirst(Function<? super U, ? extends X> x) {
    requireNonNull(x);
    return new JavaBiPredicateBinder<>((u, y) -> bound.test(x.apply(u), y));
  }

  /**
   * Link the second argument to supplied value. {@code y} is invoked each time the
   * resulting {@link Predicate} is invoked and the results is supplied as second
   * argument to the bound {@link BiPredicate}.
   */
  public JavaPredicateBinder<X>
  linkSecond(Supplier<? extends Y> y) {
    requireNonNull(y);
    return new JavaPredicateBinder<>(x -> bound.test(x, y.get()));
  }

  /**
   * Map the second argument. {@code y} is invoked each time the resulting
   * {@link BiPredicate} is invoked and the result is supplied as second argument
   * to the bound {@link BiPredicate}.
   */
  public <V> JavaBiPredicateBinder<X, V>
  linkSecond(Function<? super V, ? extends Y> y) {
    requireNonNull(y);
    return new JavaBiPredicateBinder<>((x, v) -> bound.test(x, y.apply(v)));
  }

  /**
   * @return the wrapped {@link BiPredicate}
   */
  public BiPredicate<X, Y>
  bound() { return bound; }

  @Override
  public boolean
  test(X x, Y y) { return bound.test(x, y); }
}
