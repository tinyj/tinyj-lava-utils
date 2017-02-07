package org.tinyj.lava.binder;

import org.tinyj.lava.JavaFun;

import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class JavaBiPredicateBinder<X, Y>
    implements BiPredicate<X, Y> {

  protected final BiPredicate<X, Y> bound;

  public JavaBiPredicateBinder(BiPredicate<? super X, ? super Y> bound) {
    requireNonNull(bound);
    this.bound = JavaFun.castDown(bound);
  }

  public BiPredicate<Y, X>
  flip() { return (y, x) -> bound.test(x, y); }

  public BooleanSupplier
  bind(X x, Y y) { return () -> bound.test(x, y);}

  public JavaPredicateBinder<Y>
  bindFirst(X x) { return new JavaPredicateBinder<>(y -> bound.test(x, y)); }

  public JavaPredicateBinder<X>
  bindSecond(Y y) { return new JavaPredicateBinder<>(x -> bound.test(x, y)); }

  public BooleanSupplier
  link(Supplier<? extends X> x, Supplier<? extends Y> y) {
    requireNonNull(x);
    requireNonNull(y);
    return () -> bound.test(x.get(), y.get());
  }

  public <U, V> JavaBiPredicateBinder<U, V>
  link(Function<? super U, ? extends X> x, Function<? super V, ? extends Y> y) {
    requireNonNull(x);
    requireNonNull(y);
    return new JavaBiPredicateBinder<>((u, v) -> bound.test(x.apply(u), y.apply(v)));
  }

  public JavaPredicateBinder<Y>
  linkFirst(Supplier<? extends X> x) {
    requireNonNull(x);
    return new JavaPredicateBinder<>(y -> bound.test(x.get(), y));
  }

  public <U> JavaBiPredicateBinder<U, Y>
  linkFirst(Function<? super U, ? extends X> x) {
    requireNonNull(x);
    return new JavaBiPredicateBinder<>((u, y) -> bound.test(x.apply(u), y));
  }

  public JavaPredicateBinder<X>
  linkSecond(Supplier<? extends Y> y) {
    requireNonNull(y);
    return new JavaPredicateBinder<>(x -> bound.test(x, y.get()));
  }

  public <V> JavaBiPredicateBinder<X, V>
  linkSecond(Function<? super V, ? extends Y> y) {
    requireNonNull(y);
    return new JavaBiPredicateBinder<>((x, v) -> bound.test(x, y.apply(v)));
  }

  @Override
  public boolean
  test(X x, Y y) { return bound.test(x, y); }

  public BiPredicate<X, Y>
  bound() { return bound; }
}
