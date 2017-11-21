package org.tinyj.lava.utils;

import org.tinyj.lava.*;
import org.tinyj.lava.binder.*;

import java.util.Objects;
import java.util.function.*;

import static java.util.Objects.requireNonNull;

/**
 * Utility class
 */
public class LavaFun {

  /** Static utility class, {@literal do not instantiate!} */
  protected LavaFun() { assert false : "LavaFun is a utility class, static use only."; }

  public final static
  LavaFunction<Object, ?, NoException> IDENTITY = x -> x;
  public final static
  LavaBiFunction<Object, Object, ?, NoException> FIRST = (x, y) -> x;
  public final static
  LavaBiFunction<Object, Object, ?, NoException> SECOND = (x, y) -> y;
  public final static
  LavaPredicate<Object, NoException> IS_NULL = Objects::isNull;
  public final static
  LavaPredicate<Object, NoException> NON_NULL = Objects::nonNull;
  public final static
  LavaBiPredicate<Object, Object, ? extends RuntimeException> EQUALS = Objects::equals;
  // IS_EQUAL_TO
  public final static
  LavaFunction<Object, String, ? extends RuntimeException> TO_STRING = x -> x == null ? null : x.toString();

  /**
   * The no-operation, does absolutely nothing. This is equivalent to
   * {@code (...) -> {}}. Can be used where ever a {@link Runnable},
   * {@link Consumer}, or {@link BiConsumer} is required.
   */
  @SuppressWarnings({"MethodNameSameAsClassName", "unchecked"})
  public static <X, Y, E extends Exception> NoOp<X, Y, E>
  NoOp() { return (NoOp) NoOp.NO_OP; }

  /**
   * The constant supplier, always returns {@code value}. This is equivalent to
   * {@code (...) -> value}. Can be used where ever a {@link Supplier},
   * {@link Function}, or {@link BiFunction} is required.
   */
  public static <X, Y, R, E extends Exception> Constant<X, Y, R, E>
  Constant(R value) { return new Constant<>(value); }

  /**
   * The {@code null} supplier, always returns {@code null}. This is equivalent
   * to {@code (...) -> null}. Can be used where ever a {@link Supplier},
   * {@link Function}, or {@link BiFunction} is required.
   */
  @SuppressWarnings("unchecked")
  public static <X, Y, R, E extends Exception> Constant<X, Y, R, E>
  Null() { return Constant(null); }

  /**
   * The identity function, returns it's argument. This is equivalent to
   * {@code x -> x}
   */
  @SuppressWarnings("unchecked")
  public static <X extends R, R, E extends Exception> LavaFunction<X, R, E>
  Identity() { return (LavaFunction) IDENTITY; }

  /**
   * The stringify function, returns the string representation of its argument.
   * This is equivalent to {@code x -> Objects.toString(x)}
   */
  @SuppressWarnings("unchecked")
  public static <X, E extends Exception> LavaFunction<X, String, E>
  ToString() { return (LavaFunction) TO_STRING; }

  /**
   * Binary function returning its first argument. This is equivalent to
   * {@code (x, y) -> x}
   */
  @SuppressWarnings("unchecked")
  public static <X extends R, Y, R, E extends Exception> LavaBiFunction<X, Y, R, E>
  First() { return (LavaBiFunction) FIRST; }

  /**
   * Binary function returning its second argument. This is equivalent to
   * {@code (x, y) -> y}
   */
  @SuppressWarnings("unchecked")
  public static <X, Y extends R, R, E extends Exception> LavaBiFunction<X, Y, R, E>
  Second() { return (LavaBiFunction) SECOND; }

  /**
   * The {@literal true}-predicate, returns {@code true} for all tested values.
   * This is equivalent to {@code (...) -> true}. Can be used where ever a
   * {@link BooleanSupplier}, {@link Predicate}, or {@link BiPredicate} is required.
   */
  public static <X, Y, E extends Exception> True<X, Y, E>
  True() {
    return (True) True.TRUE;
  }

  /**
   * The {@literal false}-predicate, returns {@code false} for all tested values.
   * This is equivalent to {@code (...) -> false}. Can be used where ever a
   * {@link BooleanSupplier}, {@link Predicate}, or {@link BiPredicate} is required.
   */
  public static <X, Y, E extends Exception> False<X, Y, E>
  False() {
    return (False) False.FALSE;
  }

  /**
   * The "is {@code null}?"-predicate, returns true iff the tested value is
   * {@code null}. This is equivalent to {@code x -> x == null}
   */
  @SuppressWarnings("unchecked")
  public static <X, E extends Exception> LavaPredicate<X, E>
  IsNull() { return (LavaPredicate) IS_NULL; }

  /**
   * The "is not {@code null}?"-predicate, returns true iff the tested value is
   * not {@code null}. This is equivalent to {@code x -> x != null}
   */
  @SuppressWarnings("unchecked")
  public static <X, E extends Exception> LavaPredicate<X, E>
  NonNull() { return (LavaPredicate) NON_NULL; }

  /**
   * The {@literal equals} predicate, returns true iff arguments equals
   * {@code reference}. This is equivalent to {@code x -> Objects.equals(x, reference)}
   */
  public static <X, Y, E extends Exception> LavaPredicate<X, E>
  IsEqualTo(Y reference) { return x -> Objects.equals(x, reference); }

  /**
   * The {@literal equals} relation, returns true iff both arguments are equal.
   * This is equivalent to {@code (x, y) -> Objects.equals(x, y)}
   */
  @SuppressWarnings("unchecked")
  public static <X, Y, E extends Exception> LavaBiPredicate<X, Y, E>
  Equals() { return (LavaBiPredicate) EQUALS; }

  /**
   * @param exception to throw
   * @return Returns a runnable that always throws
   */
  public static <X, Y, R, E extends Exception> LavaRunnable<E>
  Throw(E exception) { return () -> { throw exception; }; }

  /**
   * Convenience method to ensure given lambda implements {@link LavaRunnable}.
   * This may be necessary e.g. when a method is overloaded to take either
   * a {@link LavaRunnable} or a {@link LavaSupplier} or a similar functional interface.
   * Writing {@code runnable(...)} is equivalent to {@code ((LavaRunnable) ...)}
   */
  public static <E extends Exception> LavaRunnable<E>
  runnable(LavaRunnable<? extends E> runnable) { return LavaRunnable.castDown(runnable); }

  /**
   * Convenience method to ensure given lambda implements {@link LavaConsumer}.
   * This may be necessary e.g. when a method is overloaded to take either
   * a {@link LavaConsumer} or a {@link LavaFunction} or a similar functional interface.
   * Writing {@code consumer(...)} is equivalent to {@code ((LavaConsumer<X>) ...)},
   * where {@code X} is inferred from the context and the given lambda expression.
   */
  public static <X, E extends Exception> LavaConsumer<X, E>
  consumer(LavaConsumer<? super X, ? extends E> consumer) { return LavaConsumer.castDown(consumer); }

  /**
   * Convenience method to ensure given lambda implements {@link LavaBiConsumer}.
   * This may be necessary e.g. when a method is overloaded to take either
   * a {@link LavaBiConsumer} or a {@link LavaBiFunction} or a similar functional interface.
   * Writing {@code biConsumer(...)} is equivalent to {@code ((LavaBiConsumer<X, Y, E>) ...)},
   * where {@code X} and {@code Y} are inferred from the context and the given lambda expression.
   */
  public static <X, Y, E extends Exception> LavaBiConsumer<X, Y, E>
  biConsumer(LavaBiConsumer<? super X, ? super Y, ? extends E> biConsumer) { return LavaBiConsumer.castDown(biConsumer); }

  /**
   * Convenience method to ensure given lambda implements {@link LavaSupplier}.
   * This may be necessary e.g. when a method is overloaded to take either
   * a {@link LavaRunnable} or a {@link LavaSupplier} or a similar functional interface.
   * Writing {@code supplier(...)} is equivalent to {@code ((LavaSupplier<R, E>) ...)},
   * where {@code R} is inferred from the context and the given lambda expression.
   */
  public static <R, E extends Exception> LavaSupplier<R, E>
  supplier(LavaSupplier<? extends R, ? extends E> supplier) { return LavaSupplier.castDown(supplier); }

  /**
   * Convenience method to ensure given lambda implements {@link LavaFunction}.
   * This may be necessary e.g. when a method is overloaded to take either
   * a {@link LavaFunction} or a {@link LavaConsumer} or a similar functional interface.
   * Writing {@code function(...)} is equivalent to {@code ((LavaFunction<X, R, E>) ...)},
   * where {@code X} and {@code R} are inferred from the context and the given lambda expression.
   */
  public static <X, R, E extends Exception> LavaFunction<X, R, E>
  function(LavaFunction<? super X, ? extends R, ? extends E> function) { return LavaFunction.castDown(function); }

  /**
   * Convenience method to ensure given lambda implements {@link LavaBiFunction}.
   * This may be necessary e.g. when a method is overloaded to take either
   * a {@link LavaBiFunction} or a {@link LavaBiConsumer} or a similar functional interface.
   * Writing {@code biFunction(...)} is equivalent to {@code ((LavaBiFunction<X, Y, R, E>) ...)},
   * where {@code X}, {@code Y} and {@code R} are inferred from the context and the given lambda expression.
   */
  public static <X, Y, R, E extends Exception> LavaBiFunction<X, Y, R, E>
  biFunction(LavaBiFunction<? super X, ? super Y, ? extends R, ? extends E> biFunction) { return LavaBiFunction.castDown(biFunction); }

  /**
   * Convenience method to ensure given lambda implements {@link LavaCondition}.
   * This may be necessary e.g. when a method is overloaded to take either
   * a {@link LavaCondition} or a {@link LavaSupplier} or a similar functional interface.
   * Writing {@code condition(...)} is equivalent to {@code ((LavaCondition<E>) ...)}.
   */
  public static <E extends Exception> LavaCondition<E>
  condition(LavaCondition<? extends E> condition) { return LavaCondition.castDown(condition); }

  /**
   * Convenience method to ensure given lambda implements {@link LavaPredicate}.
   * This may be necessary e.g. when a method is overloaded to take either
   * a {@link LavaPredicate} or a {@link LavaFunction} or a similar functional interface.
   * Writing {@code predicate(...)} is equivalent to {@code ((LavaPredicate<X>) ...)},
   * where {@code X} is inferred from the context and the given lambda expression.
   */
  public static <X, E extends Exception> LavaPredicate<X, E>
  predicate(LavaPredicate<? super X, ? extends E> predicate) { return LavaPredicate.castDown(predicate); }

  /**
   * Convenience method to ensure given lambda implements {@link LavaBiPredicate}.
   * This may be necessary e.g. when a method is overloaded to take either
   * a {@link LavaBiPredicate} or a {@link LavaBiFunction} or a similar functional interface.
   * Writing {@code biPredicate(...)} is equivalent to {@code ((LavaBiPredicate<X, Y>) ...)},
   * where {@code X}, {@code Y} are inferred from the context and the given lambda expression.
   */
  public static <X, Y, E extends Exception> LavaBiPredicate<X, Y, E>
  biPredicate(LavaBiPredicate<? super X, ? super Y, ? extends E> biPredicate) { return LavaBiPredicate.castDown(biPredicate); }

  /**
   * Convenience method to ensure given {@link LavaFunction} lambda fulfills the
   * criteria of an unary operation, i.e. argument and result type match.
   */
  public static <X, E extends Exception> LavaFunction<X, X, E>
  unaryOp(LavaFunction<? super X, ? extends X, ? extends E> operator) { return LavaFunction.castDown(operator); }

  /**
   * Convenience method to ensure given {@link LavaBiFunction} lambda fulfills the
   * criteria of an binary operation, i.e. argument and result types match.
   */
  public static <X, E extends Exception> LavaBiFunction<X, X, X, E>
  binaryOp(LavaBiFunction<? super X, ? super X, ? extends X, ? extends E> operator) { return LavaBiFunction.castDown(operator); }

  /**
   * Convenience method to ensure given {@link LavaBiPredicate} lambda fulfills the
   * criteria of an binary relation, i.e. argument types match.
   */
  public static <X, E extends Exception> LavaBiPredicate<X, X, E>
  relation(LavaBiPredicate<? super X, ? super X, ? extends E> relation) { return LavaBiPredicate.castDown(relation); }

  /**
   * Start currying {@code bound}.
   *
   * @see LavaConsumerBinder
   */
  public static <X, E extends Exception> LavaConsumerBinder<X, E>
  bind(LavaConsumer<? super X, ? extends E> bound) { return new LavaConsumerBinder<>(bound); }

  /**
   * Start currying {@code bound}.
   *
   * @see LavaBiConsumerBinder
   */
  public static <X, Y, E extends Exception> LavaBiConsumerBinder<X, Y, E>
  bind(LavaBiConsumer<? super X, ? super Y, ? extends E> bound) { return new LavaBiConsumerBinder<>(bound); }

  /**
   * Start currying {@code bound}.
   *
   * @see LavaFunctionBinder
   */
  public static <X, R, E extends Exception> LavaFunctionBinder<X, R, E>
  bind(LavaFunction<? super X, ? extends R, ? extends E> bound) { return new LavaFunctionBinder<>(bound); }

  /**
   * Start currying {@code bound}.
   *
   * @see LavaBiFunctionBinder
   */
  public static <X, Y, R, E extends Exception> LavaBiFunctionBinder<X, Y, R, E>
  bind(LavaBiFunction<? super X, ? super Y, ? extends R, ? extends E> bound) { return new LavaBiFunctionBinder<>(bound); }

  /**
   * Start currying {@code bound}.
   *
   * @see LavaPredicateBinder
   */
  public static <X, E extends Exception> LavaPredicate<X, E>
  bind(LavaPredicate<? super X, ? extends E> bound) { return new LavaPredicateBinder<>(bound); }

  /**
   * Start currying {@code bound}.
   *
   * @see LavaBiPredicateBinder
   */
  public static <X, Y, E extends Exception> LavaBiPredicate<X, Y, E>
  bind(LavaBiPredicate<? super X, ? super Y, ? extends E> bound) { return new LavaBiPredicateBinder<>(bound); }

  /**
   * Extend a given {@link LavaRunnable} to a {@link LavaConsumer} ignoring its argument.
   */
  public <E extends Exception> LavaConsumer<Object, E>
  toConsumer(LavaRunnable<? extends E> runnable) {
    requireNonNull(runnable);
    return o -> runnable.checkedRun();
  }

  /**
   * Extend a {@link LavaRunnable} to a {@link LavaBiConsumer} ignoring its arguments.
   */
  public <E extends Exception> LavaBiConsumer<Object, Object, E>
  toBiConsumer(LavaRunnable<? extends E> runnable) {
    requireNonNull(runnable);
    return (o1, o2) -> runnable.checkedRun();
  }

  /**
   * Extend a {@link LavaConsumer} to a {@link LavaBiConsumer} ignoring its second argument.
   */
  public <X, E extends Exception> LavaBiConsumer<X, Object, E>
  toBiConsumer(LavaConsumer<? super X, ? extends E> consumer) {
    requireNonNull(consumer);
    return (x, o) -> consumer.checkedAccept(x);
  }

  /**
   * Extend a {@link LavaSupplier} to a {@link LavaFunction} ignoring its argument.
   */
  public <R, E extends Exception> LavaFunction<Object, R, E>
  toFunction(LavaSupplier<? extends R, ? extends E> supplier) {
    requireNonNull(supplier);
    return o -> supplier.checkedGet();
  }

  /**
   * Extend a {@link LavaSupplier} to a {@link LavaBiFunction} ignoring its arguments.
   */
  public <R, E extends Exception> LavaBiFunction<Object, Object, R, E>
  toBiFunction(LavaSupplier<? extends R, ? extends E> supplier) {
    requireNonNull(supplier);
    return (o1, o2) -> supplier.checkedGet();
  }

  /**
   * Extend a {@link LavaFunction} to a {@link LavaBiFunction} ignoring its second argument.
   */
  public <X, R, E extends Exception> LavaBiFunction<X, Object, R, E>
  toBiFunction(LavaFunction<? super X, ? extends R, ? extends E> function) {
    requireNonNull(function);
    return (x, o) -> function.checkedApply(x);
  }

  /**
   * Extend a {@link LavaCondition} to a {@link LavaPredicate} ignoring its argument.
   */
  public <E extends Exception> LavaPredicate<Object, E>
  toPredicate(LavaCondition<? extends E> supplier) {
    requireNonNull(supplier);
    return o -> supplier.checkedTest();
  }

  /**
   * Extend a {@link LavaCondition} to a {@link LavaBiPredicate} ignoring its arguments.
   */
  public <E extends Exception> LavaBiPredicate<Object, Object, E>
  toBiPredicate(LavaCondition<? extends E> supplier) {
    requireNonNull(supplier);
    return (o1, o2) -> supplier.checkedTest();
  }

  /**
   * Extend a {@link LavaPredicate} to a {@link LavaBiPredicate} ignoring its second argument.
   */
  public <X, E extends Exception> LavaBiPredicate<X, Object, E>
  toBiPredicate(LavaPredicate<? super X, ? extends E> predicate) {
    requireNonNull(predicate);
    return (x, o) -> predicate.checkedTest(x);
  }

  /**
   * Flip arguments of a {@link LavaBiConsumer}
   */
  public static <X, Y, E extends Exception> LavaBiConsumer<Y, X, E>
  flipArguments(LavaBiConsumer<X, Y, E> biConsumer) {
    requireNonNull(biConsumer);
    return (y, x) -> biConsumer.checkedAccept(x, y);
  }

  /**
   * Flip arguments of a {@link LavaBiFunction}
   */
  public static <X, Y, R, E extends Exception> LavaBiFunction<Y, X, R, E>
  flipArguments(LavaBiFunction<X, Y, R, E> biFunction) {
    requireNonNull(biFunction);
    return (y, x) -> biFunction.checkedApply(x, y);
  }

  /**
   * Flip arguments of a {@link LavaBiPredicate}
   */
  public static <X, Y, E extends Exception> LavaBiPredicate<Y, X, E>
  flipArguments(LavaBiPredicate<X, Y, E> biPredicate) {
    requireNonNull(biPredicate);
    return (y, x) -> biPredicate.checkedTest(x, y);
  }

  /**
   * Turn a {@link LavaRunnable} declaring {@link RuntimeException} as exception limit into a {@link Runnable}.
   */
  public static Runnable
  toJavaFun(LavaRunnable<? extends RuntimeException> runnable) {
    requireNonNull(runnable);
    return runnable::checkedRun;
  }

  /**
   * Turn a {@link LavaConsumer} declaring {@link RuntimeException} as exception limit into a {@link Consumer}.
   */
  public static <X> Consumer<X>
  toJavaFun(LavaConsumer<? super X, ? extends RuntimeException> consumer) {
    requireNonNull(consumer);
    return consumer::checkedAccept;
  }

  /**
   * Turn a {@link LavaBiConsumer} declaring {@link RuntimeException} as exception limit into a {@link BiConsumer}.
   */
  public static <X, Y> BiConsumer<X, Y>
  toJavaFun(LavaBiConsumer<? super X, ? super Y, ? extends RuntimeException> biConsumer) {
    requireNonNull(biConsumer);
    return biConsumer::checkedAccept;
  }

  /**
   * Turn a {@link LavaSupplier} declaring {@link RuntimeException} as exception limit into a {@link Supplier}.
   */
  public static <R> Supplier<R>
  toJavaFun(LavaSupplier<? extends R, ? extends RuntimeException> supplier) {
    requireNonNull(supplier);
    return supplier::checkedGet;
  }

  /**
   * Turn a {@link LavaFunction} declaring {@link RuntimeException} as exception limit into a {@link Function}.
   */
  public static <X, R> Function<X, R>
  toJavaFun(LavaFunction<? super X, ? extends R, ? extends RuntimeException> function) {
    requireNonNull(function);
    return function::checkedApply;
  }

  /**
   * Turn a {@link LavaBiFunction} declaring {@link RuntimeException} as exception limit into a {@link BiFunction}.
   */
  public static <X, Y, R> BiFunction<X, Y, R>
  toJavaFun(LavaBiFunction<? super X, ? super Y, ? extends R, ? extends RuntimeException> biFunction) {
    requireNonNull(biFunction);
    return biFunction::checkedApply;
  }

  /**
   * Turn a {@link LavaPredicate} declaring {@link RuntimeException} as exception limit into a {@link Predicate}.
   */
  public static <X> Predicate<X>
  toJavaFun(LavaPredicate<? super X, ? extends RuntimeException> predicate) {
    requireNonNull(predicate);
    return predicate::checkedTest;
  }

  /**
   * Turn a {@link LavaBiPredicate} declaring {@link RuntimeException} as exception limit into a {@link BiPredicate}.
   */
  public static <X, Y> BiPredicate<X, Y>
  toJavaFun(LavaBiPredicate<? super X, ? super Y, ? extends RuntimeException> biPredicate) {
    requireNonNull(biPredicate);
    return biPredicate::checkedTest;
  }
}
