package org.tinyj.lava;

import org.tinyj.lava.binder.*;
import org.tinyj.lava.fun.*;

import java.util.function.*;

import static java.util.Objects.requireNonNull;

/**
 * Utility class
 */
public class LavaFun {

  protected LavaFun() { assert false : "LavaFun is a utility class, static use only."; }

  public final static
  LavaRunnable<NoException> NO_OP = NoOp.NoOp();
  public final static
  LavaSupplier<?, NoException> NULL = Null.Null();
  public final static
  LavaFunction<Object, ?, NoException> IDENTITY = Identity.Identity();
  public final static
  LavaFunction<Object, String, ? extends RuntimeException> TO_STRING = ToString.ToString();
  public final static
  LavaBiFunction<Object, Object, ?, NoException> FIRST = First.First();
  public final static
  LavaBiFunction<Object, Object, ?, NoException> SECOND = Second.Second();
  public final static
  LavaPredicate<Object, NoException> TRUE = True.True();
  public final static
  LavaPredicate<Object, NoException> FALSE = False.False();
  public final static
  LavaPredicate<Object, NoException> IS_NULL = IsNull.IsNull();
  public final static
  LavaPredicate<Object, NoException> NON_NULL = NonNull.NonNull();
  public final static
  LavaBiPredicate<Object, Object, ? extends RuntimeException> EQUALS = Equals.Equals();

  /**
   * The no-operation, does absolutely nothing. This is equivalent to `() -> {}`
   */
  public static <E extends Exception> LavaRunnable<E>
  NoOp() { return NoOp.NoOp(); }

  /**
   * The constant supplier, always returns `value`. This is equivalent to `() -> value`
   */
  public static <R, E extends Exception> LavaSupplier<R, E>
  Constant(R constant) { return new Constant<>(constant); }

  /**
   * The `null` supplier, always returns `null`. This is equivalent to `() -> null`
   */
  public static <R, E extends Exception> LavaSupplier<R, E>
  Null() { return Null.Null(); }

  /**
   * The identity function, returns it's argument. This is equivalent to `x -> x`
   */
  public static <X extends R, R, E extends Exception> LavaFunction<X, R, E>
  Identity() { return Identity.Identity(); }

  /**
   * The stringify function, returns the string representation of its argument. This is equivalent to `x -> Objects.toString(x)`
   */
  public static <X, E extends Exception> LavaFunction<X, String, E>
  ToString() { return ToString.ToString(); }

  /**
   * Binary function returning its first argument. This is equivalent to `(x, y) -> x`
   */
  public static <X extends R, Y, R, E extends Exception> LavaBiFunction<X, Y, R, E>
  First() { return First.First(); }

  /**
   * Binary function returning its second argument. This is equivalent to `(x, y) -> y`
   */
  public static <X, Y extends R, R, E extends Exception> LavaBiFunction<X, Y, R, E>
  Second() { return Second.Second(); }

  /**
   * The _true_-predicate, returns `true` for all tested values. This is equivalent to `x -> true`
   */
  public static <X, E extends Exception> LavaPredicate<X, E>
  True() { return True.True(); }

  /**
   * The _false_-predicate, returns `false` for all tested values. This is equivalent to `x -> false`
   */
  public static <X, E extends Exception> LavaPredicate<X, E>
  False() { return False.False(); }

  /**
   * The _"is `null`?"_-predicate, returns true iff the tested value is `null`. This is equivalent to `x -> x == null`
   */
  public static <X, E extends Exception> LavaPredicate<X, E>
  IsNull() { return IsNull.IsNull(); }

  /**
   * The _"is not `null`?"_-predicate, returns true iff the tested value is not `null`. This is equivalent to `x -> x != null`
   */
  public static <X, E extends Exception> LavaPredicate<X, E>
  NonNull() { return NonNull.NonNull(); }

  /**
   * The _equals_ predicate, returns true iff arguments equals `reference`. This is equivalent to `x -> Objects.equals(x, reference)`
   */
  public static <X, Y, E extends Exception> LavaPredicate<X, E>
  IsEqualTo(Y y) { return new IsEqualTo<>(y); }

  /**
   * The _equals_ relation, returns true iff both arguments are equal. This is equivalent to `(x, y) -> Objects.equals(x, y)`
   */
  public static <X, Y, E extends Exception> LavaBiPredicate<X, Y, E>
  Equals() { return Equals.Equals(); }

  /**
   * Convenience method to ensure given lambda implements (#LavaRunnable).
   * This may be necessary e.g. when a method is overloaded to take either
   * a (#LavaRunnable) or a (#LavaSupplier) or a similar functional interface.
   * Writing `runnable(...)` is equivalent to `((LavaRunnable) ...)`
   */
  public static <E extends Exception> LavaRunnable<E>
  runnable(LavaRunnable<? extends E> runnable) { return LavaRunnable.castDown(runnable); }

  /**
   * Convenience method to ensure given lambda implements (#LavaConsumer).
   * This may be necessary e.g. when a method is overloaded to take either
   * a (#LavaConsumer) or a (#LavaFunction) or a similar functional interface.
   * Writing `consumer(...)` is equivalent to `((LavaConsumer<X>) ...)`,
   * where `X` is inferred from the context and the given lambda expression.
   */
  public static <X, E extends Exception> LavaConsumer<X, E>
  consumer(LavaConsumer<? super X, ? extends E> consumer) { return LavaConsumer.castDown(consumer); }

  /**
   * Convenience method to ensure given lambda implements (#LavaBiConsumer).
   * This may be necessary e.g. when a method is overloaded to take either
   * a (#LavaBiConsumer) or a (#LavaBiFunction) or a similar functional interface.
   * Writing `biConsumer(...)` is equivalent to `((LavaBiConsumer<X, Y>) ...)`,
   * where `X` and `Y` are inferred from the context and the given lambda expression.
   */
  public static <X, Y, E extends Exception> LavaBiConsumer<X, Y, E>
  biConsumer(LavaBiConsumer<? super X, ? super Y, ? extends E> biConsumer) { return LavaBiConsumer.castDown(biConsumer); }

  /**
   * Convenience method to ensure given lambda implements (#LavaSupplier).
   * This may be necessary e.g. when a method is overloaded to take either
   * a (#LavaRunnable) or a (#LavaSupplier) or a similar functional interface.
   * Writing `supplier(...)` is equivalent to `((LavaSupplier<R>) ...)`,
   * where `R` is inferred from the context and the given lambda expression.
   */
  public static <R, E extends Exception> LavaSupplier<R, E>
  supplier(LavaSupplier<? extends R, ? extends E> supplier) { return LavaSupplier.castDown(supplier); }

  /**
   * Convenience method to ensure given lambda implements (#LavaFunction).
   * This may be necessary e.g. when a method is overloaded to take either
   * a (#LavaFunction) or a (#LavaConsumer) or a similar functional interface.
   * Writing `function(...)` is equivalent to `((LavaFunction<X, R>) ...)`,
   * where `X` and `R` are inferred from the context and the given lambda expression.
   */
  public static <X, R, E extends Exception> LavaFunction<X, R, E>
  function(LavaFunction<? super X, ? extends R, ? extends E> function) { return LavaFunction.castDown(function); }

  /**
   * Convenience method to ensure given lambda implements (#LavaBiFunction).
   * This may be necessary e.g. when a method is overloaded to take either
   * a (#LavaBiFunction) or a (#LavaBiConsumer) or a similar functional interface.
   * Writing `biFunction(...)` is equivalent to `((LavaBiFunction<X, Y, R>) ...)`,
   * where `X`, `Y` and `R` are inferred from the context and the given lambda expression.
   */
  public static <X, Y, R, E extends Exception> LavaBiFunction<X, Y, R, E>
  biFunction(LavaBiFunction<? super X, ? super Y, ? extends R, ? extends E> biFunction) { return LavaBiFunction.castDown(biFunction); }

  /**
   * Convenience method to ensure given lambda implements (#LavaPredicate).
   * This may be necessary e.g. when a method is overloaded to take either
   * a (#LavaPredicate) or a (#LavaFunction) or a similar functional interface.
   * Writing `predicate(...)` is equivalent to `((LavaPredicate<X>) ...)`,
   * where `X` is inferred from the context and the given lambda expression.
   */
  public static <X, E extends Exception> LavaPredicate<X, E>
  predicate(LavaPredicate<? super X, ? extends E> predicate) { return LavaPredicate.castDown(predicate); }

  /**
   * Convenience method to ensure given lambda implements (#LavaBiPredicate).
   * This may be necessary e.g. when a method is overloaded to take either
   * a (#LavaBiPredicate) or a (#LavaBiFunction) or a similar functional interface.
   * Writing `biPredicate(...)` is equivalent to `((LavaBiPredicate<X, Y>) ...)`,
   * where `X`, `Y` are inferred from the context and the given lambda expression.
   */
  public static <X, Y, E extends Exception> LavaBiPredicate<X, Y, E>
  biPredicate(LavaBiPredicate<? super X, ? super Y, ? extends E> biPredicate) { return LavaBiPredicate.castDown(biPredicate); }

  /**
   * Convenience method to ensure given (#LavaFunction) lambda fulfills the
   * criteria of an unary operation, i.e. argument and result type match.
   */
  public static <X, E extends Exception> LavaFunction<X, X, E>
  unaryOp(LavaFunction<? super X, ? extends X, ? extends E> operator) { return LavaFunction.castDown(operator); }

  /**
   * Convenience method to ensure given (#LavaBiFunction) lambda fulfills the
   * criteria of an binary operation, i.e. argument and result types match.
   */
  public static <X, E extends Exception> LavaBiFunction<X, X, X, E>
  binaryOp(LavaBiFunction<? super X, ? super X, ? extends X, ? extends E> operator) { return LavaBiFunction.castDown(operator); }

  /**
   * Convenience method to ensure given (#LavaBiPredicate) lambda fulfills the
   * criteria of an binary relation, i.e. argument types match.
   */
  public static <X, E extends Exception> LavaBiPredicate<X, X, E>
  relation(LavaBiPredicate<? super X, ? super X, ? extends E> relation) { return LavaBiPredicate.castDown(relation); }

  /**
   * Start currying `bound`. See (#LavaConsumerBinder).
   */
  public static <X, E extends Exception> LavaConsumerBinder<X, E>
  bind(LavaConsumer<? super X, ? extends E> bound) { return new LavaConsumerBinder<>(bound); }

  /**
   * Start currying `bound`. See (#LavaBiConsumerBinder).
   */
  public static <X, Y, E extends Exception> LavaBiConsumerBinder<X, Y, E>
  bind(LavaBiConsumer<? super X, ? super Y, ? extends E> bound) { return new LavaBiConsumerBinder<>(bound); }

  /**
   * Start currying `bound`. See (#LavaFunctionBinder).
   */
  public static <X, R, E extends Exception> LavaFunctionBinder<X, R, E>
  bind(LavaFunction<? super X, ? extends R, ? extends E> bound) { return new LavaFunctionBinder<>(bound); }

  /**
   * Start currying `bound`. See (#LavaBiFunctionBinder).
   */
  public static <X, Y, R, E extends Exception> LavaBiFunctionBinder<X, Y, R, E>
  bind(LavaBiFunction<? super X, ? super Y, ? extends R, ? extends E> bound) { return new LavaBiFunctionBinder<>(bound); }

  /**
   * Start currying `bound`. See (#LavaPredicateBinder).
   */
  public static <X, E extends Exception> LavaPredicate<X, E>
  bind(LavaPredicate<? super X, ? extends E> bound) { return new LavaPredicateBinder<>(bound); }

  /**
   * Start currying `bound`. See (#LavaBiPredicateBinder).
   */
  public static <X, Y, E extends Exception> LavaBiPredicate<X, Y, E>
  bind(LavaBiPredicate<? super X, ? super Y, ? extends E> bound) { return new LavaBiPredicateBinder<>(bound); }

  /**
   * Extend a given (#LavaRunnable) to a (#LavaConsumer) ignoring its argument.
   */
  public <E extends Exception> LavaConsumer<Object, E>
  toConsumer(LavaRunnable<? extends E> runnable) {
    requireNonNull(runnable);
    return o -> runnable.checkedRun();
  }

  /**
   * Extend a (#LavaRunnable) to a (#LavaBiConsumer) ignoring its arguments.
   */
  public <E extends Exception> LavaBiConsumer<Object, Object, E>
  toBiConsumer(LavaRunnable<? extends E> runnable) {
    requireNonNull(runnable);
    return (o1, o2) -> runnable.checkedRun();
  }

  /**
   * Extend a (#LavaConsumer) to a (#LavaBiConsumer) ignoring its second argument.
   */
  public <X, E extends Exception> LavaBiConsumer<X, Object, E>
  toBiConsumer(LavaConsumer<? super X, ? extends E> consumer) {
    requireNonNull(consumer);
    return (x, o) -> consumer.checkedAccept(x);
  }

  /**
   * Extend a (#LavaSupplier) to a (#LavaFunction) ignoring its argument.
   */
  public <R, E extends Exception> LavaFunction<Object, R, E>
  toFunction(LavaSupplier<? extends R, ? extends E> supplier) {
    requireNonNull(supplier);
    return o -> supplier.checkedGet();
  }

  /**
   * Extend a (#LavaSupplier) to a (#LavaBiFunction) ignoring its arguments.
   */
  public <R, E extends Exception> LavaBiFunction<Object, Object, R, E>
  toBiFunction(LavaSupplier<? extends R, ? extends E> supplier) {
    requireNonNull(supplier);
    return (o1, o2) -> supplier.checkedGet();
  }

  /**
   * Extend a (#LavaFunction) to a (#LavaBiFunction) ignoring its second argument.
   */
  public <X, R, E extends Exception> LavaBiFunction<X, Object, R, E>
  toBiFunction(LavaFunction<? super X, ? extends R, ? extends E> function) {
    requireNonNull(function);
    return (x, o) -> function.checkedApply(x);
  }

  /**
   * Extend a (#LavaBooleanSupplier) to a (#LavaPredicate) ignoring its argument.
   */
  public <E extends Exception> LavaPredicate<Object, E>
  toPredicate(LavaBooleanSupplier<? extends E> supplier) {
    requireNonNull(supplier);
    return o -> supplier.checkedGetAsBoolean();
  }

  /**
   * Extend a (#LavaBooleanSupplier) to a (#LavaBiPredicate) ignoring its arguments.
   */
  public <E extends Exception> LavaBiPredicate<Object, Object, E>
  toBiPredicate(LavaBooleanSupplier<? extends E> supplier) {
    requireNonNull(supplier);
    return (o1, o2) -> supplier.checkedGetAsBoolean();
  }

  /**
   * Extend a (#LavaPredicate) to a (#LavaBiPredicate) ignoring its second argument.
   */
  public <X, E extends Exception> LavaBiPredicate<X, Object, E>
  toBiPredicate(LavaPredicate<? super X, ? extends E> predicate) {
    requireNonNull(predicate);
    return (x, o) -> predicate.checkedTest(x);
  }

  /**
   * Flip arguments of a (#LavaBiConsumer)
   */
  public static <X, Y, E extends Exception> LavaBiConsumer<Y, X, E>
  flipArguments(LavaBiConsumer<X, Y, E> biConsumer) {
    requireNonNull(biConsumer);
    return (y, x) -> biConsumer.checkedAccept(x, y);
  }

  /**
   * Flip arguments of a (#LavaBiFunction)
   */
  public static <X, Y, R, E extends Exception> LavaBiFunction<Y, X, R, E>
  flipArguments(LavaBiFunction<X, Y, R, E> biFunction) {
    requireNonNull(biFunction);
    return (y, x) -> biFunction.checkedApply(x, y);
  }

  /**
   * Flip arguments of a (#LavaBiPredicate)
   */
  public static <X, Y, E extends Exception> LavaBiPredicate<Y, X, E>
  flipArguments(LavaBiPredicate<X, Y, E> biPredicate) {
    requireNonNull(biPredicate);
    return (y, x) -> biPredicate.checkedTest(x, y);
  }

  /**
   * Turn a (#LavaRunnable) declaring (#RuntimeException) as exception limit into a (#Runnable).
   */
  public static Runnable
  toJavaFun(LavaRunnable<? extends RuntimeException> runnable) {
    requireNonNull(runnable);
    return runnable::checkedRun;
  }

  /**
   * Turn a (#LavaConsumer) declaring (#RuntimeException) as exception limit into a (#Consumer).
   */
  public static <X> Consumer<X>
  toJavaFun(LavaConsumer<? super X, ? extends RuntimeException> consumer) {
    requireNonNull(consumer);
    return consumer::checkedAccept;
  }

  /**
   * Turn a (#LavaBiConsumer) declaring (#RuntimeException) as exception limit into a (#BiConsumer).
   */
  public static <X, Y> BiConsumer<X, Y>
  toJavaFun(LavaBiConsumer<? super X, ? super Y, ? extends RuntimeException> biConsumer) {
    requireNonNull(biConsumer);
    return biConsumer::checkedAccept;
  }

  /**
   * Turn a (#LavaSupplier) declaring (#RuntimeException) as exception limit into a (#Supplier).
   */
  public static <R> Supplier<R>
  toJavaFun(LavaSupplier<? extends R, ? extends RuntimeException> supplier) {
    requireNonNull(supplier);
    return supplier::checkedGet;
  }

  /**
   * Turn a (#LavaFunction) declaring (#RuntimeException) as exception limit into a (#Function).
   */
  public static <X, R> Function<X, R>
  toJavaFun(LavaFunction<? super X, ? extends R, ? extends RuntimeException> function) {
    requireNonNull(function);
    return function::checkedApply;
  }

  /**
   * Turn a (#LavaBiFunction) declaring (#RuntimeException) as exception limit into a (#BiFunction).
   */
  public static <X, Y, R> BiFunction<X, Y, R>
  toJavaFun(LavaBiFunction<? super X, ? super Y, ? extends R, ? extends RuntimeException> biFunction) {
    requireNonNull(biFunction);
    return biFunction::checkedApply;
  }

  /**
   * Turn a (#LavaPredicate) declaring (#RuntimeException) as exception limit into a (#Predicate).
   */
  public static <X> Predicate<X>
  toJavaFun(LavaPredicate<? super X, ? extends RuntimeException> predicate) {
    requireNonNull(predicate);
    return predicate::checkedTest;
  }

  /**
   * Turn a (#LavaBiPredicate) declaring (#RuntimeException) as exception limit into a (#BiPredicate).
   */
  public static <X, Y> BiPredicate<X, Y>
  toJavaFun(LavaBiPredicate<? super X, ? super Y, ? extends RuntimeException> biPredicate) {
    requireNonNull(biPredicate);
    return biPredicate::checkedTest;
  }
}
