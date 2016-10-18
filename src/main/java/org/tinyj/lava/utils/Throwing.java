package org.tinyj.lava.utils;

import org.tinyj.lava.throwing.*;

import java.util.function.*;

public class Throwing {

  protected Throwing() { assert false : "Throwing is a utility class, static use only."; }

  public static <E extends Exception> ThrowingRunnable<E>
  runnable(ThrowingRunnable<? extends E> runnable) { return ThrowingRunnable.castDown(runnable); }

  public static <X, E extends Exception> ThrowingConsumer<X, E>
  consumer(ThrowingConsumer<? super X, ? extends E> consumer) { return ThrowingConsumer.castDown(consumer); }

  public static <X, Y, E extends Exception> ThrowingBiConsumer<X, Y, E>
  biConsumer(ThrowingBiConsumer<? super X, ? super Y, ? extends E> biConsumer) { return ThrowingBiConsumer.castDown(biConsumer); }

  public static <R, E extends Exception> ThrowingSupplier<R, E>
  supplier(ThrowingSupplier<? extends R, ? extends E> supplier) { return ThrowingSupplier.castDown(supplier); }

  public static <X, R, E extends Exception> ThrowingFunction<X, R, E>
  function(ThrowingFunction<? super X, ? extends R, ? extends E> function) { return ThrowingFunction.castDown(function); }

  public static <X, Y, R, E extends Exception> ThrowingBiFunction<X, Y, R, E>
  biFunction(ThrowingBiFunction<? super X, ? super Y, ? extends R, ? extends E> biFunction) { return ThrowingBiFunction.castDown(biFunction); }

  public static <X, E extends Exception> ThrowingPredicate<X, E>
  predicate(ThrowingPredicate<? super X, ? extends E> predicate) { return ThrowingPredicate.castDown(predicate); }

  public static <X, Y, E extends Exception> ThrowingBiPredicate<X, Y, E>
  biPredicate(ThrowingBiPredicate<? super X, ? super Y, ? extends E> biPredicate) { return ThrowingBiPredicate.castDown(biPredicate); }

  public static <X, E extends Exception> ThrowingFunction<X, X, E>
  unaryOp(ThrowingFunction<? super X, ? extends X, ? extends E> operator) { return ThrowingFunction.castDown(operator); }

  public static <X, E extends Exception> ThrowingBiFunction<X, X, X, E>
  binaryOp(ThrowingBiFunction<? super X, ? super X, ? extends X, ? extends E> operator) { return ThrowingBiFunction.castDown(operator); }

  public static <X, E extends Exception> ThrowingBiPredicate<X, X, E>
  relation(ThrowingBiPredicate<? super X, ? super X, ? extends E> relation) { return ThrowingBiPredicate.castDown(relation); }

  public static ThrowingRunnable<RuntimeException>
  throwing(Runnable runnable) { return runnable::run; }

  public static <X> ThrowingConsumer<X, RuntimeException>
  throwing(Consumer<? super X> consumer) { return consumer::accept; }

  public static <X, Y> ThrowingBiConsumer<X, Y, RuntimeException>
  throwing(BiConsumer<? super X, ? super Y> biConsumer) { return biConsumer::accept; }

  public static <R> ThrowingSupplier<R, RuntimeException>
  throwing(Supplier<? extends R> supplier) { return supplier::get; }

  public static <X, R> ThrowingFunction<X, R, RuntimeException>
  throwing(Function<? super X, ? extends R> function) { return function::apply; }

  public static <X, Y, R> ThrowingBiFunction<X, Y, R, RuntimeException>
  throwing(BiFunction<? super X, ? super Y, ? extends R> biFunction) { return biFunction::apply; }

  public static <X> ThrowingPredicate<X, RuntimeException>
  throwing(Predicate<? super X> predicate) { return predicate::test; }

  public static <X, Y> ThrowingBiPredicate<X, Y, RuntimeException>
  throwing(BiPredicate<? super X, ? super Y> biPredicate) { return biPredicate::test; }
}
