package org.tinyj.lava.utils;

import java.util.function.BiFunction;
import java.util.function.Function;

@FunctionalInterface
interface FunctionOrBiFunction<X, Y, R> extends Function<X, R>, BiFunction<X, Y, R> {
  @Override
  default R apply(X x, Y y) {
    return apply(x);
  }

  @Override
  default <S> FunctionOrBiFunction<X, Y, S> andThen(Function<? super R, ? extends S> after) {
    return x -> after.apply(this.apply(x));
  }
}
