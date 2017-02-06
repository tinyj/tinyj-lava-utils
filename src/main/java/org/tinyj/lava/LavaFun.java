package org.tinyj.lava;

import org.tinyj.lava.binder.LavaBiConsumerBinder;
import org.tinyj.lava.binder.LavaBiFunctionBinder;
import org.tinyj.lava.binder.LavaConsumerBinder;
import org.tinyj.lava.binder.LavaFunctionBinder;
import org.tinyj.lava.fun.*;

import java.util.function.*;

import static java.util.Objects.requireNonNull;

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

  public static <E extends Exception> LavaRunnable<E>
  NoOp() { return NoOp.NoOp(); }

  public static <R, E extends Exception> LavaSupplier<R, E>
  Constant(R constant) { return new Constant<>(constant); }

  public static <R, E extends Exception> LavaSupplier<R, E>
  Null() { return Null.Null(); }

  public static <X extends R, R, E extends Exception> LavaFunction<X, R, E>
  Identity() { return Identity.Identity(); }

  public static <X, E extends Exception> LavaFunction<X, String, E>
  ToString() { return ToString.ToString(); }

  public static <X extends R, Y, R, E extends Exception> LavaBiFunction<X, Y, R, E>
  First() { return First.First(); }

  public static <X, Y extends R, R, E extends Exception> LavaBiFunction<X, Y, R, E>
  Second() { return Second.Second(); }

  public static <X, E extends Exception> LavaPredicate<X, E>
  True() { return True.True(); }

  public static <X, E extends Exception> LavaPredicate<X, E>
  False() { return False.False(); }

  public static <X, E extends Exception> LavaPredicate<X, E>
  IsNull() { return IsNull.IsNull(); }

  public static <X, E extends Exception> LavaPredicate<X, E>
  NonNull() { return NonNull.NonNull(); }

  public static <X, Y, E extends Exception> LavaPredicate<X, E>
  IsEqualTo(Y y) { return new IsEqualTo<>(y); }

  public static <X, Y, E extends Exception> LavaBiPredicate<X, Y, E>
  Equals() { return Equals.Equals(); }

  public static <E extends Exception> LavaRunnable<E>
  runnable(LavaRunnable<? extends E> runnable) { return LavaRunnable.castDown(runnable); }

  public static <X, E extends Exception> LavaConsumer<X, E>
  consumer(LavaConsumer<? super X, ? extends E> consumer) { return LavaConsumer.castDown(consumer); }

  public static <X, Y, E extends Exception> LavaBiConsumer<X, Y, E>
  biConsumer(LavaBiConsumer<? super X, ? super Y, ? extends E> biConsumer) { return LavaBiConsumer.castDown(biConsumer); }

  public static <R, E extends Exception> LavaSupplier<R, E>
  supplier(LavaSupplier<? extends R, ? extends E> supplier) { return LavaSupplier.castDown(supplier); }

  public static <X, R, E extends Exception> LavaFunction<X, R, E>
  function(LavaFunction<? super X, ? extends R, ? extends E> function) { return LavaFunction.castDown(function); }

  public static <X, Y, R, E extends Exception> LavaBiFunction<X, Y, R, E>
  biFunction(LavaBiFunction<? super X, ? super Y, ? extends R, ? extends E> biFunction) { return LavaBiFunction.castDown(biFunction); }

  public static <X, E extends Exception> LavaPredicate<X, E>
  predicate(LavaPredicate<? super X, ? extends E> predicate) { return LavaPredicate.castDown(predicate); }

  public static <X, Y, E extends Exception> LavaBiPredicate<X, Y, E>
  biPredicate(LavaBiPredicate<? super X, ? super Y, ? extends E> biPredicate) { return LavaBiPredicate.castDown(biPredicate); }

  public static <X, E extends Exception> LavaFunction<X, X, E>
  unaryOp(LavaFunction<? super X, ? extends X, ? extends E> operator) { return LavaFunction.castDown(operator); }

  public static <X, E extends Exception> LavaBiFunction<X, X, X, E>
  binaryOp(LavaBiFunction<? super X, ? super X, ? extends X, ? extends E> operator) { return LavaBiFunction.castDown(operator); }

  public static <X, E extends Exception> LavaBiPredicate<X, X, E>
  relation(LavaBiPredicate<? super X, ? super X, ? extends E> relation) { return LavaBiPredicate.castDown(relation); }

  public static <X, E extends Exception> LavaConsumerBinder<X, E>
  bind(LavaConsumer<? super X, ? extends E> bound) { return new LavaConsumerBinder<>(bound); }

  public static <X, Y, E extends Exception> LavaBiConsumerBinder<X, Y, E>
  bind(LavaBiConsumer<? super X, ? super Y, ? extends E> bound) { return new LavaBiConsumerBinder<>(bound); }

  public static <X, R, E extends Exception> LavaFunctionBinder<X, R, E>
  bind(LavaFunction<? super X, ? extends R, ? extends E> bound) { return new LavaFunctionBinder<>(bound); }

  public static <X, Y, R, E extends Exception> LavaBiFunctionBinder<X, Y, R, E>
  bind(LavaBiFunction<? super X, ? super Y, ? extends R, ? extends E> bound) { return new LavaBiFunctionBinder<>(bound); }

  public <E extends Exception> LavaConsumer<Object, E>
  toConsumer(LavaRunnable<? extends E> runnable) {
    requireNonNull(runnable);
    return o -> runnable.checkedRun();
  }

  public <E extends Exception> LavaBiConsumer<Object, Object, E>
  toBiConsumer(LavaRunnable<? extends E> runnable) {
    requireNonNull(runnable);
    return (o1, o2) -> runnable.checkedRun();
  }

  public <X, E extends Exception> LavaBiConsumer<X, Object, E>
  toBiConsumer(LavaConsumer<? super X, ? extends E> consumer) {
    requireNonNull(consumer);
    return (x, o) -> consumer.checkedAccept(x);
  }

  public <R, E extends Exception> LavaFunction<Object, R, E>
  toFunction(LavaSupplier<? extends R, ? extends E> supplier) {
    requireNonNull(supplier);
    return o -> supplier.checkedGet();
  }

  public <R, E extends Exception> LavaBiFunction<Object, Object, R, E>
  toBiFunction(LavaSupplier<? extends R, ? extends E> supplier) {
    requireNonNull(supplier);
    return (o1, o2) -> supplier.checkedGet();
  }

  public <X, R, E extends Exception> LavaBiFunction<X, Object, R, E>
  toBiFunction(LavaFunction<? super X, ? extends R, ? extends E> function) {
    requireNonNull(function);
    return (x, o) -> function.checkedApply(x);
  }

  public <X, E extends Exception> LavaBiPredicate<X, Object, E>
  toBiPredicate(LavaPredicate<? super X, ? extends E> predicate) {
    requireNonNull(predicate);
    return (x, o) -> predicate.checkedTest(x);
  }

  public static Runnable
  toJavaFun(LavaRunnable<? extends RuntimeException> runnable) {
    requireNonNull(runnable);
    return runnable::checkedRun;
  }

  public static <X> Consumer<X>
  toJavaFun(LavaConsumer<? super X, ? extends RuntimeException> consumer) {
    requireNonNull(consumer);
    return consumer::checkedAccept;
  }

  public static <X, Y> BiConsumer<X, Y>
  toJavaFun(LavaBiConsumer<? super X, ? super Y, ? extends RuntimeException> biConsumer) {
    requireNonNull(biConsumer);
    return biConsumer::checkedAccept;
  }

  public static <R> Supplier<R>
  toJavaFun(LavaSupplier<? extends R, ? extends RuntimeException> supplier) {
    requireNonNull(supplier);
    return supplier::checkedGet;
  }

  public static <X, R> Function<X, R>
  toJavaFun(LavaFunction<? super X, ? extends R, ? extends RuntimeException> function) {
    requireNonNull(function);
    return function::checkedApply;
  }

  public static <X, Y, R> BiFunction<X, Y, R>
  toJavaFun(LavaBiFunction<? super X, ? super Y, ? extends R, ? extends RuntimeException> biFunction) {
    requireNonNull(biFunction);
    return biFunction::checkedApply;
  }

  public static <X> Predicate<X>
  toJavaFun(LavaPredicate<? super X, ? extends RuntimeException> predicate) {
    requireNonNull(predicate);
    return predicate::checkedTest;
  }

  public static <X, Y> BiPredicate<X, Y>
  toJavaFun(LavaBiPredicate<? super X, ? super Y, ? extends RuntimeException> biPredicate) {
    requireNonNull(biPredicate);
    return biPredicate::checkedTest;
  }

  public static <X, Y, E extends Exception> LavaBiConsumer<Y, X, E>
  flipArguments(LavaBiConsumer<X, Y, E> biConsumer) {
    requireNonNull(biConsumer);
    return (y, x) -> biConsumer.checkedAccept(x, y);
  }

  public static <X, Y, R, E extends Exception> LavaBiFunction<Y, X, R, E>
  flipArguments(LavaBiFunction<X, Y, R, E> biFunction) {
    requireNonNull(biFunction);
    return (y, x) -> biFunction.checkedApply(x, y);
  }

}
