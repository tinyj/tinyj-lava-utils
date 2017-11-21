org.tinyj.lava.utils
====================

Functor prototypes, argument binder and static utility methods for tinyj-lava
and Java™ functors.


## org.tinyj.lava.utils

### Constant<X, Y, R, E>
_[(src)](src/main/java/org/tinyj/lava/utils/Constant.java)_  

A constant, always returns `value`. This is equivalent to
`(...) -> value`. Can be used where ever a [`Supplier`],
[`Function`], or [`BiFunction`] is required.


**`Constant(R value)`** _(contructor)_  

**`apply(X x, Y y)`**
⇒ *`R`*  

**`apply(X x)`**
⇒ *`R`*  

**`get()`**
⇒ *`R`*  

**`checkedApply(X x, Y y)`**
⇒ *`R`*  

**`checkedApply(X x)`**
⇒ *`R`*  

**`checkedGet()`**
⇒ *`R`*  


### False<X, Y, E>
_[(src)](src/main/java/org/tinyj/lava/utils/False.java)_  

The _false_-predicate, returns `false` for all tested values.
This is equivalent to `(...) -> false`. Can be used where ever a
[`BooleanSupplier`], [`Predicate`], or [`BiPredicate`] is required.


***`FALSE`***
⇒ *`False`*  

**`test(X x, Y y)`**
⇒ *`boolean`*  

**`getAsBoolean()`**
⇒ *`boolean`*  

**`test(X x)`**
⇒ *`boolean`*  

**`checkedTest(X x, Y y)`**
⇒ *`boolean`*  

**`checkedTest()`**
⇒ *`boolean`*  

**`checkedTest(X x)`**
⇒ *`boolean`*  

**`negate()`**
⇒ *`True<X, Y, E>`*  

**`and(Predicate<? super X> other)`**
⇒ *`Predicate<X>`*  

**`and(BiPredicate<? super X, ? super Y> other)`**
⇒ *`BiPredicate<X, Y>`*  

**`or(Predicate<? super X> other)`**
⇒ *`Predicate<X>`*  

**`or(BiPredicate<? super X, ? super Y> other)`**
⇒ *`BiPredicate<X, Y>`*  


### FunctionOrBiFunction<X, Y, R>
_[(src)](src/main/java/org/tinyj/lava/utils/FunctionOrBiFunction.java)_
| _(interface)_  

**`apply(X x, Y y)`**
⇒ *`R`*  

**`andThen(Function<? super R, ? extends S> after)`**
⇒ *`FunctionOrBiFunction<X, Y, S>`*  


### JavaFun
_[(src)](src/main/java/org/tinyj/lava/utils/JavaFun.java)_  

Utility class


**`JavaFun()`** _(contructor)_  

Static utility class, _do not instantiate!_


***`IDENTITY`***
⇒ *`Function<Object, ?>`*  

***`FIRST`***
⇒ *`BiFunction<Object, Object, ?>`*  

***`SECOND`***
⇒ *`BiFunction<Object, Object, ?>`*  

***`IS_NULL`***
⇒ *`Predicate<Object>`*  

***`NON_NULL`***
⇒ *`Predicate<Object>`*  

***`EQUALS`***
⇒ *`BiPredicate<Object, Object>`*  

***`TO_STRING`***
⇒ *`Function<Object, String>`*  

***`NoOp()`***
⇒ *`NoOp<X, Y, E>`*  

The no-operation, does absolutely nothing. This is equivalent to
`(...) -> {}`. Can be used where ever a [`Runnable`],
[`Consumer`], or [`BiConsumer`] is required.


***`Constant(R value)`***
⇒ *`Constant<X, Y, R, E>`*  

A constant, always returns `value`. This is equivalent to
`(...) -> value`. Can be used where ever a [`Supplier`],
[`Function`], or [`BiFunction`] is required.


***`Null()`***
⇒ *`Constant<X, Y, R, E>`*  

The `null` supplier, always returns `null`. This is equivalent
to `(...) -> null`. Can be used where ever a [`Supplier`],
[`Function`], or [`BiFunction`] is required.


***`Identity()`***
⇒ *`Function<X, R>`*  

The identity function, returns it's argument. This is equivalent to
`x -> x`.


***`ToString()`***
⇒ *`Function<X, String>`*  

The stringify function, returns the string representation of its argument.
This is equivalent to `x -> Objects.toString(x)`.


***`First()`***
⇒ *`BiFunction<X, Y, R>`*  

Binary function returning its first argument. This is equivalent to
`(x, y) -> x`.


***`Second()`***
⇒ *`BiFunction<X, Y, R>`*  

Binary function returning its second argument. This is equivalent to
`(x, y) -> y`.


***`True()`***
⇒ *`True<X, Y, E>`*  

The _true_-predicate, returns `true` for all tested values.
This is equivalent to `(...) -> true`. Can be used where ever a
[`BooleanSupplier`], [`Predicate`], or [`BiPredicate`] is required.


***`False()`***
⇒ *`False<X, Y, E>`*  

The _false_-predicate, returns `false` for all tested values.
This is equivalent to `(...) -> false`. Can be used where ever a
[`BooleanSupplier`], [`Predicate`], or [`BiPredicate`] is required.


***`IsNull()`***
⇒ *`Predicate<X>`*  

The "is `null`?"-predicate, returns true iff the tested value is
`null`. This is equivalent to `x -> x == null`.


***`NonNull()`***
⇒ *`Predicate<X>`*  

The "is not `null`?"-predicate, returns true iff the tested value is
not `null`. This is equivalent to `x -> x != null`.


***`IsEqualTo(Y reference)`***
⇒ *`Predicate<X>`*  

The _equals_ predicate, returns true iff arguments equals
`reference`. This is equivalent to
`x -> Objects.equals(x, reference)`.


***`Equals()`***
⇒ *`BiPredicate<X, Y>`*  

The _equals_ relation, returns true iff both arguments are equal.
This is equivalent to `(x, y) -> Objects.equals(x, y)`.


***`runnable(Runnable runnable)`***
⇒ *`Runnable`*  

Convenience method to ensure given lambda implements [`Runnable`].
This may be necessary e.g. when a method is overloaded to take either
a [`Runnable`] or a [`Supplier`] or a similar functional interface.
Writing `runnable(...)` is equivalent to `((Runnable) ...)`.


***`consumer(Consumer<? super X> consumer)`***
⇒ *`Consumer<X>`*  

Convenience method to ensure given lambda implements [`Consumer`].
This may be necessary e.g. when a method is overloaded to take either
a [`Consumer`] or a [`Function`] or a similar functional interface.
Writing `consumer(...)` is equivalent to `((Consumer<X>) ...)`,
where `X` is inferred from the context and the given lambda expression.


***`biConsumer(BiConsumer<? super X, ? super Y> biConsumer)`***
⇒ *`BiConsumer<X, Y>`*  

Convenience method to ensure given lambda implements [`BiConsumer`].
This may be necessary e.g. when a method is overloaded to take either
a [`BiConsumer`] or a [`BiFunction`] or a similar functional interface.
Writing `biConsumer(...)` is equivalent to `((BiConsumer<X, Y>) ...)`,
where `X` and `Y` are inferred from the context and the given lambda expression.


***`supplier(Supplier<? extends R> supplier)`***
⇒ *`Supplier<R>`*  

Convenience method to ensure given lambda implements [`Supplier`].
This may be necessary e.g. when a method is overloaded to take either
a [`Runnable`] or a [`Supplier`] or a similar functional interface.
Writing `supplier(...)` is equivalent to `((Supplier<R>) ...)`,
where `R` is inferred from the context and the given lambda expression.


***`function(Function<? super X, ? extends R> function)`***
⇒ *`Function<X, R>`*  

Convenience method to ensure given lambda implements [`Function`].
This may be necessary e.g. when a method is overloaded to take either
a [`Function`] or a [`Consumer`] or a similar functional interface.
Writing `function(...)` is equivalent to `((Function<X, R>) ...)`,
where `X` and `R` are inferred from the context and the given lambda expression.


***`biFunction(BiFunction<? super X, ? super Y, ? extends R> biFunction)`***
⇒ *`BiFunction<X, Y, R>`*  

Convenience method to ensure given lambda implements [`BiFunction`].
This may be necessary e.g. when a method is overloaded to take either
a [`BiFunction`] or a [`BiConsumer`] or a similar functional interface.
Writing `biFunction(...)` is equivalent to `((BiFunction<X, Y, R>) ...)`,
where `X`, `Y` and `R` are inferred from the context and the given lambda expression.


***`predicate(Predicate<? super X> predicate)`***
⇒ *`Predicate<X>`*  

Convenience method to ensure given lambda implements [`Predicate`].
This may be necessary e.g. when a method is overloaded to take either
a [`Predicate`] or a [`Function`] or a similar functional interface.
Writing `predicate(...)` is equivalent to `((Predicate<X>) ...)`,
where `X` is inferred from the context and the given lambda expression.


***`biPredicate(BiPredicate<? super X, ? super Y> biPredicate)`***
⇒ *`BiPredicate<X, Y>`*  

Convenience method to ensure given lambda implements [`BiPredicate`].
This may be necessary e.g. when a method is overloaded to take either
a [`BiPredicate`] or a [`BiFunction`] or a similar functional interface.
Writing `biPredicate(...)` is equivalent to `((BiPredicate<X, Y>) ...)`,
where `X`, `Y` are inferred from the context and the given lambda expression.


***`unaryOp(Function<? super X, ? extends X> operator)`***
⇒ *`Function<X, X>`*  

Convenience method to ensure given [`Function`] lambda fulfills the
criteria of an unary operation, i.e. argument and result type match.


***`binaryOp(BiFunction<? super X, ? super X, ? extends X> operator)`***
⇒ *`BiFunction<X, X, X>`*  

Convenience method to ensure given [`BiFunction`] lambda fulfills the
criteria of an binary operation, i.e. argument and result types match.


***`relation(BiPredicate<? super X, ? super X> relation)`***
⇒ *`BiPredicate<X, X>`*  

Convenience method to ensure given [`BiPredicate`] lambda fulfills the
criteria of an binary relation, i.e. argument types match.


***`bind(Consumer<? super X> bound)`***
⇒ *`JavaConsumerBinder<X>`*  

Start currying `bound`.

See also: 
- [`JavaConsumerBinder`]


***`bind(BiConsumer<? super X, ? super Y> bound)`***
⇒ *`JavaBiConsumerBinder<X, Y>`*  

Start currying `bound`.

See also: 
- [`JavaBiConsumerBinder`]


***`bind(Function<? super X, ? extends R> bound)`***
⇒ *`JavaFunctionBinder<X, R>`*  

Start currying `bound`.

See also: 
- [`JavaFunctionBinder`]


***`bind(BiFunction<? super X, ? super Y, ? extends R> bound)`***
⇒ *`JavaBiFunctionBinder<X, Y, R>`*  

Start currying `bound`.

See also: 
- [`JavaBiFunctionBinder`]


***`bind(Predicate<? super X> bound)`***
⇒ *`JavaPredicateBinder<X>`*  

Start currying `bound`.

See also: 
- [`JavaPredicateBinder`]


***`bind(BiPredicate<? super X, ? super Y> bound)`***
⇒ *`JavaBiPredicateBinder<X, Y>`*  

Start currying `bound`.

See also: 
- [`JavaBiPredicateBinder`]


***`toConsumer(Runnable runnable)`***
⇒ *`Consumer<Object>`*  

Extend a given [`Runnable`] to a [`Consumer`] ignoring its argument.


***`toBiConsumer(Runnable runnable)`***
⇒ *`BiConsumer<Object, Object>`*  

Extend a [`Runnable`] to a [`BiConsumer`] ignoring its arguments.


***`toBiConsumer(Consumer<? super X> consumer)`***
⇒ *`BiConsumer<X, Object>`*  

Extend a [`Consumer`] to a [`BiConsumer`] ignoring its second argument.


***`toFunction(Supplier<? extends R> supplier)`***
⇒ *`Function<Object, R>`*  

Extend a [`Supplier`] to a [`Function`] ignoring its argument.


***`toBiFunction(Supplier<? extends R> supplier)`***
⇒ *`BiFunction<Object, Object, R>`*  

Extend a [`Supplier`] to a [`BiFunction`] ignoring its arguments.


***`toBiFunction(Function<? super X, ? extends R> function)`***
⇒ *`BiFunction<X, Y, R>`*  

Extend a [`Function`] to a [`BiFunction`] ignoring its second argument.


***`toPredicate(BooleanSupplier supplier)`***
⇒ *`Predicate<Object>`*  

Extend a [`BooleanSupplier`] to a [`Predicate`] ignoring its argument.


***`toBiPredicate(BooleanSupplier supplier)`***
⇒ *`BiPredicate<Object, Object>`*  

Extend a [`BooleanSupplier`] to a [`BiPredicate`] ignoring its arguments.


***`toBiPredicate(Predicate<? super X> predicate)`***
⇒ *`BiPredicate<X, Object>`*  

Extend a [`Predicate`] to a [`BiPredicate`] ignoring its second argument.


***`flipArguments(BiConsumer<X, Y> biConsumer)`***
⇒ *`BiConsumer<Y, X>`*  

Flip arguments of a [`BiConsumer`]


***`flipArguments(BiFunction<X, Y, R> biFunction)`***
⇒ *`BiFunction<Y, X, R>`*  

Flip arguments of a [`BiFunction`]


***`flipArguments(BiPredicate<X, Y> biPredicate)`***
⇒ *`BiPredicate<Y, X>`*  

Flip arguments of a [`BiPredicate`]


***`toLavaFun(Runnable runnable)`***
⇒ *`LavaRunnable<RuntimeException>`*  

Turn a [`Runnable`] into a [`LavaRunnable`] declaring [`RuntimeException`] as exception limit.


***`toLavaFun(Consumer<? super X> consumer)`***
⇒ *`LavaConsumer<X, RuntimeException>`*  

Turn a [`Consumer`] into a [`LavaConsumer`] declaring [`RuntimeException`] as exception limit.


***`toLavaFun(BiConsumer<? super X, ? super Y> biConsumer)`***
⇒ *`LavaBiConsumer<X, Y, RuntimeException>`*  

Turn a [`BiConsumer`] into a [`LavaBiConsumer`] declaring [`RuntimeException`] as exception limit.


***`toLavaFun(Supplier<? extends R> supplier)`***
⇒ *`LavaSupplier<R, RuntimeException>`*  

Turn a [`Supplier`] into a [`LavaSupplier`] declaring [`RuntimeException`] as exception limit.


***`toLavaFun(Function<? super X, ? extends R> function)`***
⇒ *`LavaFunction<X, R, RuntimeException>`*  

Turn a [`Function`] into a [`LavaFunction`] declaring [`RuntimeException`] as exception limit.


***`toLavaFun(BiFunction<? super X, ? super Y, ? extends R> biFunction)`***
⇒ *`LavaBiFunction<X, Y, R, RuntimeException>`*  

Turn a [`BiFunction`] into a [`LavaBiFunction`] declaring [`RuntimeException`] as exception limit.


***`toLavaFun(Predicate<? super X> predicate)`***
⇒ *`LavaPredicate<X, RuntimeException>`*  

Turn a [`Predicate`] into a [`LavaPredicate`] declaring [`RuntimeException`] as exception limit.


***`toLavaFun(BiPredicate<? super X, ? super Y> biPredicate)`***
⇒ *`LavaBiPredicate<X, Y, RuntimeException>`*  

Turn a [`BiPredicate`] into a [`LavaBiPredicate`] declaring [`RuntimeException`] as exception limit.


***`castDown(Consumer<? super X> consumer)`***
⇒ *`Consumer<X>`* *(`consumer` casted to the compatible parametrization [`Consumer`]`<X, E>`)*  
`X`: accepted input type  
`consumer`: consumer to adopt  

Safely casts between different [`Consumer`] parametrization.


***`castDown(BiConsumer<? super X, ? super Y> biConsumer)`***
⇒ *`BiConsumer<X, Y>`* *(`biConsumer` casted to the compatible parametrization [`BiConsumer`]`<X, Y, E>`)*  
`X`: first accepted input type  
`Y`: second accepted input type  
`biConsumer`: bi-consumer to adopt  

Safely casts between different [`BiConsumer`] parametrization.


***`castDown(Supplier<? extends R> supplier)`***
⇒ *`Supplier<R>`* *(`supplier` casted to the compatible parametrization [`Supplier`]`<R, E>`)*  
`R`: required result type  
`supplier`: supplier to adopt  

Safely casts between different [`Supplier`] parametrization.


***`castDown(Function<? super X, ? extends R> function)`***
⇒ *`Function<X, R>`* *(`function` casted to the compatible parametrization [`Function`]`<X, R, E>`)*  
`X`: accepted argument type  
`R`: required result type  
`function`: function to adopt  

Safely casts between different [`Function`] parametrization.


***`castDown(BiFunction<? super X, ? super Y, ? extends R> biFunction)`***
⇒ *`BiFunction<X, Y, R>`* *(`biFunction` casted to the compatible parametrization [`BiFunction`]`<X, Y, R, E>`)*  
`X`: first accepted argument type  
`Y`: second accepted argument type  
`R`: required result type  
`biFunction`: bi-function to adopt  

Safely casts between different [`BiFunction`] parametrization.


***`castDown(Predicate<? super X> predicate)`***
⇒ *`Predicate<X>`* *(`predicate` casted to the compatible parametrization [`Predicate`]`<X, E>`)*  
`X`: accepted argument type  
`predicate`: predicate to adopt  

Safely casts between different [`Predicate`] parametrization.


***`castDown(BiPredicate<? super X, ? super Y> biPredicate)`***
⇒ *`BiPredicate<X, Y>`* *(`biPredicate` casted to the compatible parametrization [`BiPredicate`]`<X, E>`)*  
`X`: first accepted argument type  
`Y`: second accepted argument type  
`biPredicate`: bi-predicate to adopt  

Safely casts between different [`BiPredicate`] parametrization.



### LavaFun
_[(src)](src/main/java/org/tinyj/lava/utils/LavaFun.java)_  

Utility class


**`LavaFun()`** _(contructor)_  

Static utility class, _do not instantiate!_


***`IDENTITY`***
⇒ *`LavaFunction<Object, ?, NoException>`*  

***`FIRST`***
⇒ *`LavaBiFunction<Object, Object, ?, NoException>`*  

***`SECOND`***
⇒ *`LavaBiFunction<Object, Object, ?, NoException>`*  

***`IS_NULL`***
⇒ *`LavaPredicate<Object, NoException>`*  

***`NON_NULL`***
⇒ *`LavaPredicate<Object, NoException>`*  

***`EQUALS`***
⇒ *`LavaBiPredicate<Object, Object, ? extends java.lang.RuntimeException>`*  

***`TO_STRING`***
⇒ *`LavaFunction<Object, String, ? extends java.lang.RuntimeException>`*  

***`NoOp()`***
⇒ *`NoOp<X, Y, E>`*  

The no-operation, does absolutely nothing. This is equivalent to
`(...) -> {}`. Can be used where ever a [`Runnable`],
[`Consumer`], or [`BiConsumer`] is required.


***`Constant(R value)`***
⇒ *`Constant<X, Y, R, E>`*  

The constant supplier, always returns `value`. This is equivalent to
`(...) -> value`. Can be used where ever a [`Supplier`],
[`Function`], or [`BiFunction`] is required.


***`Null()`***
⇒ *`Constant<X, Y, R, E>`*  

The `null` supplier, always returns `null`. This is equivalent
to `(...) -> null`. Can be used where ever a [`Supplier`],
[`Function`], or [`BiFunction`] is required.


***`Identity()`***
⇒ *`LavaFunction<X, R, E>`*  

The identity function, returns it's argument. This is equivalent to
`x -> x`


***`ToString()`***
⇒ *`LavaFunction<X, String, E>`*  

The stringify function, returns the string representation of its argument.
This is equivalent to `x -> Objects.toString(x)`


***`First()`***
⇒ *`LavaBiFunction<X, Y, R, E>`*  

Binary function returning its first argument. This is equivalent to
`(x, y) -> x`


***`Second()`***
⇒ *`LavaBiFunction<X, Y, R, E>`*  

Binary function returning its second argument. This is equivalent to
`(x, y) -> y`


***`True()`***
⇒ *`True<X, Y, E>`*  

The _true_-predicate, returns `true` for all tested values.
This is equivalent to `(...) -> true`. Can be used where ever a
[`BooleanSupplier`], [`Predicate`], or [`BiPredicate`] is required.


***`False()`***
⇒ *`False<X, Y, E>`*  

The _false_-predicate, returns `false` for all tested values.
This is equivalent to `(...) -> false`. Can be used where ever a
[`BooleanSupplier`], [`Predicate`], or [`BiPredicate`] is required.


***`IsNull()`***
⇒ *`LavaPredicate<X, E>`*  

The "is `null`?"-predicate, returns true iff the tested value is
`null`. This is equivalent to `x -> x == null`


***`NonNull()`***
⇒ *`LavaPredicate<X, E>`*  

The "is not `null`?"-predicate, returns true iff the tested value is
not `null`. This is equivalent to `x -> x != null`


***`IsEqualTo(Y reference)`***
⇒ *`LavaPredicate<X, E>`*  

The _equals_ predicate, returns true iff arguments equals
`reference`. This is equivalent to `x -> Objects.equals(x, reference)`


***`Equals()`***
⇒ *`LavaBiPredicate<X, Y, E>`*  

The _equals_ relation, returns true iff both arguments are equal.
This is equivalent to `(x, y) -> Objects.equals(x, y)`


***`Throw(E exception)`***
⇒ *`LavaRunnable<E>`* *(Returns a runnable that always throws)*  
`exception`: to throw  

***`runnable(LavaRunnable<? extends E> runnable)`***
⇒ *`LavaRunnable<E>`*  

Convenience method to ensure given lambda implements [`LavaRunnable`].
This may be necessary e.g. when a method is overloaded to take either
a [`LavaRunnable`] or a [`LavaSupplier`] or a similar functional interface.
Writing `runnable(...)` is equivalent to `((LavaRunnable) ...)`


***`consumer(LavaConsumer<? super X, ? extends E> consumer)`***
⇒ *`LavaConsumer<X, E>`*  

Convenience method to ensure given lambda implements [`LavaConsumer`].
This may be necessary e.g. when a method is overloaded to take either
a [`LavaConsumer`] or a [`LavaFunction`] or a similar functional interface.
Writing `consumer(...)` is equivalent to `((LavaConsumer<X>) ...)`,
where `X` is inferred from the context and the given lambda expression.


***`biConsumer(LavaBiConsumer<? super X, ? super Y, ? extends E> biConsumer)`***
⇒ *`LavaBiConsumer<X, Y, E>`*  

Convenience method to ensure given lambda implements [`LavaBiConsumer`].
This may be necessary e.g. when a method is overloaded to take either
a [`LavaBiConsumer`] or a [`LavaBiFunction`] or a similar functional interface.
Writing `biConsumer(...)` is equivalent to `((LavaBiConsumer<X, Y, E>) ...)`,
where `X` and `Y` are inferred from the context and the given lambda expression.


***`supplier(LavaSupplier<? extends R, ? extends E> supplier)`***
⇒ *`LavaSupplier<R, E>`*  

Convenience method to ensure given lambda implements [`LavaSupplier`].
This may be necessary e.g. when a method is overloaded to take either
a [`LavaRunnable`] or a [`LavaSupplier`] or a similar functional interface.
Writing `supplier(...)` is equivalent to `((LavaSupplier<R, E>) ...)`,
where `R` is inferred from the context and the given lambda expression.


***`function(LavaFunction<? super X, ? extends R, ? extends E> function)`***
⇒ *`LavaFunction<X, R, E>`*  

Convenience method to ensure given lambda implements [`LavaFunction`].
This may be necessary e.g. when a method is overloaded to take either
a [`LavaFunction`] or a [`LavaConsumer`] or a similar functional interface.
Writing `function(...)` is equivalent to `((LavaFunction<X, R, E>) ...)`,
where `X` and `R` are inferred from the context and the given lambda expression.


***`biFunction(LavaBiFunction<? super X, ? super Y, ? extends R, ? extends E> biFunction)`***
⇒ *`LavaBiFunction<X, Y, R, E>`*  

Convenience method to ensure given lambda implements [`LavaBiFunction`].
This may be necessary e.g. when a method is overloaded to take either
a [`LavaBiFunction`] or a [`LavaBiConsumer`] or a similar functional interface.
Writing `biFunction(...)` is equivalent to `((LavaBiFunction<X, Y, R, E>) ...)`,
where `X`, `Y` and `R` are inferred from the context and the given lambda expression.


***`condition(LavaCondition<? extends E> condition)`***
⇒ *`LavaCondition<E>`*  

Convenience method to ensure given lambda implements [`LavaCondition`].
This may be necessary e.g. when a method is overloaded to take either
a [`LavaCondition`] or a [`LavaSupplier`] or a similar functional interface.
Writing `condition(...)` is equivalent to `((LavaCondition<E>) ...)`.


***`predicate(LavaPredicate<? super X, ? extends E> predicate)`***
⇒ *`LavaPredicate<X, E>`*  

Convenience method to ensure given lambda implements [`LavaPredicate`].
This may be necessary e.g. when a method is overloaded to take either
a [`LavaPredicate`] or a [`LavaFunction`] or a similar functional interface.
Writing `predicate(...)` is equivalent to `((LavaPredicate<X>) ...)`,
where `X` is inferred from the context and the given lambda expression.


***`biPredicate(LavaBiPredicate<? super X, ? super Y, ? extends E> biPredicate)`***
⇒ *`LavaBiPredicate<X, Y, E>`*  

Convenience method to ensure given lambda implements [`LavaBiPredicate`].
This may be necessary e.g. when a method is overloaded to take either
a [`LavaBiPredicate`] or a [`LavaBiFunction`] or a similar functional interface.
Writing `biPredicate(...)` is equivalent to `((LavaBiPredicate<X, Y>) ...)`,
where `X`, `Y` are inferred from the context and the given lambda expression.


***`unaryOp(LavaFunction<? super X, ? extends X, ? extends E> operator)`***
⇒ *`LavaFunction<X, X, E>`*  

Convenience method to ensure given [`LavaFunction`] lambda fulfills the
criteria of an unary operation, i.e. argument and result type match.


***`binaryOp(LavaBiFunction<? super X, ? super X, ? extends X, ? extends E> operator)`***
⇒ *`LavaBiFunction<X, X, X, E>`*  

Convenience method to ensure given [`LavaBiFunction`] lambda fulfills the
criteria of an binary operation, i.e. argument and result types match.


***`relation(LavaBiPredicate<? super X, ? super X, ? extends E> relation)`***
⇒ *`LavaBiPredicate<X, X, E>`*  

Convenience method to ensure given [`LavaBiPredicate`] lambda fulfills the
criteria of an binary relation, i.e. argument types match.


***`bind(LavaConsumer<? super X, ? extends E> bound)`***
⇒ *`LavaConsumerBinder<X, E>`*  

Start currying `bound`.

See also: 
- [`LavaConsumerBinder`]


***`bind(LavaBiConsumer<? super X, ? super Y, ? extends E> bound)`***
⇒ *`LavaBiConsumerBinder<X, Y, E>`*  

Start currying `bound`.

See also: 
- [`LavaBiConsumerBinder`]


***`bind(LavaFunction<? super X, ? extends R, ? extends E> bound)`***
⇒ *`LavaFunctionBinder<X, R, E>`*  

Start currying `bound`.

See also: 
- [`LavaFunctionBinder`]


***`bind(LavaBiFunction<? super X, ? super Y, ? extends R, ? extends E> bound)`***
⇒ *`LavaBiFunctionBinder<X, Y, R, E>`*  

Start currying `bound`.

See also: 
- [`LavaBiFunctionBinder`]


***`bind(LavaPredicate<? super X, ? extends E> bound)`***
⇒ *`LavaPredicate<X, E>`*  

Start currying `bound`.

See also: 
- [`LavaPredicateBinder`]


***`bind(LavaBiPredicate<? super X, ? super Y, ? extends E> bound)`***
⇒ *`LavaBiPredicate<X, Y, E>`*  

Start currying `bound`.

See also: 
- [`LavaBiPredicateBinder`]


**`toConsumer(LavaRunnable<? extends E> runnable)`**
⇒ *`LavaConsumer<Object, E>`*  

Extend a given [`LavaRunnable`] to a [`LavaConsumer`] ignoring its argument.


**`toBiConsumer(LavaRunnable<? extends E> runnable)`**
⇒ *`LavaBiConsumer<Object, Object, E>`*  

Extend a [`LavaRunnable`] to a [`LavaBiConsumer`] ignoring its arguments.


**`toBiConsumer(LavaConsumer<? super X, ? extends E> consumer)`**
⇒ *`LavaBiConsumer<X, Object, E>`*  

Extend a [`LavaConsumer`] to a [`LavaBiConsumer`] ignoring its second argument.


**`toFunction(LavaSupplier<? extends R, ? extends E> supplier)`**
⇒ *`LavaFunction<Object, R, E>`*  

Extend a [`LavaSupplier`] to a [`LavaFunction`] ignoring its argument.


**`toBiFunction(LavaSupplier<? extends R, ? extends E> supplier)`**
⇒ *`LavaBiFunction<Object, Object, R, E>`*  

Extend a [`LavaSupplier`] to a [`LavaBiFunction`] ignoring its arguments.


**`toBiFunction(LavaFunction<? super X, ? extends R, ? extends E> function)`**
⇒ *`LavaBiFunction<X, Object, R, E>`*  

Extend a [`LavaFunction`] to a [`LavaBiFunction`] ignoring its second argument.


**`toPredicate(LavaCondition<? extends E> supplier)`**
⇒ *`LavaPredicate<Object, E>`*  

Extend a [`LavaCondition`] to a [`LavaPredicate`] ignoring its argument.


**`toBiPredicate(LavaCondition<? extends E> supplier)`**
⇒ *`LavaBiPredicate<Object, Object, E>`*  

Extend a [`LavaCondition`] to a [`LavaBiPredicate`] ignoring its arguments.


**`toBiPredicate(LavaPredicate<? super X, ? extends E> predicate)`**
⇒ *`LavaBiPredicate<X, Object, E>`*  

Extend a [`LavaPredicate`] to a [`LavaBiPredicate`] ignoring its second argument.


***`flipArguments(LavaBiConsumer<X, Y, E> biConsumer)`***
⇒ *`LavaBiConsumer<Y, X, E>`*  

Flip arguments of a [`LavaBiConsumer`]


***`flipArguments(LavaBiFunction<X, Y, R, E> biFunction)`***
⇒ *`LavaBiFunction<Y, X, R, E>`*  

Flip arguments of a [`LavaBiFunction`]


***`flipArguments(LavaBiPredicate<X, Y, E> biPredicate)`***
⇒ *`LavaBiPredicate<Y, X, E>`*  

Flip arguments of a [`LavaBiPredicate`]


***`toJavaFun(LavaRunnable<? extends java.lang.RuntimeException> runnable)`***
⇒ *`Runnable`*  

Turn a [`LavaRunnable`] declaring [`RuntimeException`] as exception limit into a [`Runnable`].


***`toJavaFun(LavaConsumer<? super X, ? extends java.lang.RuntimeException> consumer)`***
⇒ *`Consumer<X>`*  

Turn a [`LavaConsumer`] declaring [`RuntimeException`] as exception limit into a [`Consumer`].


***`toJavaFun(LavaBiConsumer<? super X, ? super Y, ? extends java.lang.RuntimeException> biConsumer)`***
⇒ *`BiConsumer<X, Y>`*  

Turn a [`LavaBiConsumer`] declaring [`RuntimeException`] as exception limit into a [`BiConsumer`].


***`toJavaFun(LavaSupplier<? extends R, ? extends java.lang.RuntimeException> supplier)`***
⇒ *`Supplier<R>`*  

Turn a [`LavaSupplier`] declaring [`RuntimeException`] as exception limit into a [`Supplier`].


***`toJavaFun(LavaFunction<? super X, ? extends R, ? extends java.lang.RuntimeException> function)`***
⇒ *`Function<X, R>`*  

Turn a [`LavaFunction`] declaring [`RuntimeException`] as exception limit into a [`Function`].


***`toJavaFun(LavaBiFunction<? super X, ? super Y, ? extends R, ? extends java.lang.RuntimeException> biFunction)`***
⇒ *`BiFunction<X, Y, R>`*  

Turn a [`LavaBiFunction`] declaring [`RuntimeException`] as exception limit into a [`BiFunction`].


***`toJavaFun(LavaPredicate<? super X, ? extends java.lang.RuntimeException> predicate)`***
⇒ *`Predicate<X>`*  

Turn a [`LavaPredicate`] declaring [`RuntimeException`] as exception limit into a [`Predicate`].


***`toJavaFun(LavaBiPredicate<? super X, ? super Y, ? extends java.lang.RuntimeException> biPredicate)`***
⇒ *`BiPredicate<X, Y>`*  

Turn a [`LavaBiPredicate`] declaring [`RuntimeException`] as exception limit into a [`BiPredicate`].



### NoOp<X, Y, E>
_[(src)](src/main/java/org/tinyj/lava/utils/NoOp.java)_  

The no-operation, does absolutely nothing. This is equivalent to
`(...) -> {}`. Can be used where ever a [`Runnable`],
[`Consumer`], or [`BiConsumer`] is required.


***`NO_OP`***
⇒ *`NoOp`*  

**`run()`**  

**`accept(X x, Y y)`**  

**`accept(X x)`**  

**`checkedAccept(X x, Y y)`**  

**`checkedAccept(X x)`**  

**`checkedRun()`**  


### True<X, Y, E>
_[(src)](src/main/java/org/tinyj/lava/utils/True.java)_  

The _true_-predicate, returns `true` for all tested values.
This is equivalent to `(...) -> true`. Can be used where ever a
[`BooleanSupplier`], [`Predicate`], or [`BiPredicate`] is required.


***`TRUE`***
⇒ *`True`*  

**`test(X x, Y y)`**
⇒ *`boolean`*  

**`getAsBoolean()`**
⇒ *`boolean`*  

**`test(X x)`**
⇒ *`boolean`*  

**`checkedTest(X x, Y y)`**
⇒ *`boolean`*  

**`checkedTest()`**
⇒ *`boolean`*  

**`checkedTest(X x)`**
⇒ *`boolean`*  

**`negate()`**
⇒ *`False<X, Y, E>`*  

**`and(Predicate<? super X> other)`**
⇒ *`Predicate<X>`*  

**`and(BiPredicate<? super X, ? super Y> other)`**
⇒ *`BiPredicate<X, Y>`*  

**`or(Predicate<? super X> other)`**
⇒ *`Predicate<X>`*  

**`or(BiPredicate<? super X, ? super Y> other)`**
⇒ *`BiPredicate<X, Y>`*  


## org.tinyj.lava.binder

### JavaBiConsumerBinder<X, Y>
_[(src)](src/main/java/org/tinyj/lava/binder/JavaBiConsumerBinder.java)_  
`X`: the type of the first argument to the operation  
`Y`: the type of the second argument to the operation  

Enable various forms of currying on Java's [`BiConsumer`].

To enable a fluent syntax binders wrapping the curried function are returned
where applicable. This introduces some overhead that might be an issue if
either the result is invoked many times or many results are produced. Use
`bound()` to unwrap results.


**`bound`**
⇒ *`BiConsumer<X, Y>`*  

**`JavaBiConsumerBinder(BiConsumer<? super X, ? super Y> bound)`** _(contructor)_  

**`flip()`**
⇒ *`JavaBiConsumerBinder<Y, X>`*  

Flip the arguments.


**`bind(X x, Y y)`**
⇒ *`Runnable`*  

Curries both arguments.


**`bindFirst(X x)`**
⇒ *`JavaConsumerBinder<Y>`*  

Curry the first argument.


**`bindSecond(Y y)`**
⇒ *`JavaConsumerBinder<X>`*  

Curry the second argument.


**`link(Supplier<? extends X> x, Supplier<? extends Y> y)`**
⇒ *`Runnable`*  

Link both arguments to supplied values. `x` and `y` are invoked each
time the resulting [`Runnable`] is invoked and the results are supplied as
arguments to the bound [`BiConsumer`].


**`link(Function<? super U, ? extends X> x, Function<? super V, ? extends Y> y)`**
⇒ *`JavaBiConsumerBinder<U, V>`*  

Map both arguments. `x` and `y` are invoked each time the resulting
[`BiConsumer`] is invoked and the results are supplied as arguments to the
bound [`BiConsumer`].


**`linkFirst(Supplier<? extends X> x)`**
⇒ *`JavaConsumerBinder<Y>`*  

Link the first argument to supplied value. `x` is invoked each time the
resulting [`Consumer`] is invoked and the results is supplied as first
argument to the bound [`BiConsumer`].


**`linkFirst(Function<? super U, ? extends X> x)`**
⇒ *`JavaBiConsumerBinder<U, Y>`*  

Map the first argument. `x` is invoked each time the resulting
[`BiConsumer`] is invoked and the result is supplied as first argument
to the bound [`BiConsumer`].


**`linkSecond(Supplier<? extends Y> y)`**
⇒ *`JavaConsumerBinder<X>`*  

Link the second argument to supplied value. `y` is invoked each time the
resulting [`Consumer`] is invoked and the results is supplied as second
argument to the bound [`BiConsumer`].


**`linkSecond(Function<? super V, ? extends Y> y)`**
⇒ *`JavaBiConsumerBinder<X, V>`*  

Map the second argument. `y` is invoked each time the resulting
[`BiConsumer`] is invoked and the result is supplied as second argument
to the bound [`BiConsumer`].


**`bound()`**
⇒ *`BiConsumer<X, Y>`* *(the wrapped [`BiConsumer`])*  

**`andThen(BiConsumer<? super X, ? super Y> after)`**
⇒ *`JavaBiConsumerBinder<X, Y>`*  

**`accept(X x, Y y)`**  


### JavaBiFunctionBinder<X, Y, R>
_[(src)](src/main/java/org/tinyj/lava/binder/JavaBiFunctionBinder.java)_  
`X`: the type of the first argument to the function  
`Y`: the type of the second argument to the function  
`R`: the type of the result of the function  

Enable various forms of currying on Java's [`BiFunction`].

To enable a fluent syntax binders wrapping the curried function are returned
where applicable. This introduces some overhead that might be an issue if
either the result is invoked many times or many results are produced. Use
`bound()` to unwrap results.


**`bound`**
⇒ *`BiFunction<X, Y, R>`*  

**`JavaBiFunctionBinder(BiFunction<? super X, ? super Y, ? extends R> bound)`** _(contructor)_  

**`flip()`**
⇒ *`BiFunction<Y, X, R>`*  

Flip the arguments.


**`bind(X x, Y y)`**
⇒ *`Supplier<R>`*  

Curries both arguments.


**`bindFirst(X x)`**
⇒ *`JavaFunctionBinder<Y, R>`*  

Curry the first argument.


**`bindSecond(Y y)`**
⇒ *`JavaFunctionBinder<X, R>`*  

Curry the second argument.


**`link(Supplier<? extends X> x, Supplier<? extends Y> y)`**
⇒ *`Supplier<R>`*  

Link both arguments to supplied values. `x` and `y` are invoked each
time the resulting [`Supplier`] is invoked and the results are supplied as
arguments to the bound [`BiFunction`].


**`link(Function<? super U, ? extends X> x, Function<? super V, ? extends Y> y)`**
⇒ *`JavaBiFunctionBinder<U, V, R>`*  

Map both arguments. `x` and `y` are invoked each time the resulting
[`BiFunction`] is invoked and the results are supplied as arguments to the
bound [`BiFunction`].


**`linkFirst(Supplier<? extends X> x)`**
⇒ *`JavaFunctionBinder<Y, R>`*  

Link the first argument to supplied value. `x` is invoked each time the
resulting [`Function`] is invoked and the results is supplied as first
argument to the bound [`BiFunction`].


**`linkFirst(Function<? super U, ? extends X> x)`**
⇒ *`JavaBiFunctionBinder<U, Y, R>`*  

Map the first argument. `x` is invoked each time the resulting
[`BiFunction`] is invoked and the result is supplied as first argument
to the bound [`BiFunction`].


**`linkSecond(Supplier<? extends Y> y)`**
⇒ *`JavaFunctionBinder<X, R>`*  

Link the second argument to supplied value. `y` is invoked each time the
resulting [`Function`] is invoked and the results is supplied as second
argument to the bound [`BiFunction`].


**`linkSecond(Function<? super V, ? extends Y> y)`**
⇒ *`JavaBiFunctionBinder<X, V, R>`*  

Map the second argument. `y` is invoked each time the resulting
[`BiFunction`] is invoked and the result is supplied as second argument
to the bound [`BiFunction`].


**`bound()`**
⇒ *`BiFunction<X, Y, R>`* *(the wrapped [`BiFunction`])*  

**`andThen(Function<? super R, ? extends V> after)`**
⇒ *`JavaBiFunctionBinder<X, Y, V>`*  

**`apply(X x, Y y)`**
⇒ *`R`*  


### JavaBiPredicateBinder<X, Y>
_[(src)](src/main/java/org/tinyj/lava/binder/JavaBiPredicateBinder.java)_  
`X`: the type of the first argument to the predicate  
`Y`: the type of the second argument the predicate  

Enable various forms of currying on Java's [`BiPredicate`].

To enable a fluent syntax binders wrapping the curried function are returned
where applicable. This introduces some overhead that might be an issue if
either the result is invoked many times or many results are produced. Use
`bound()` to unwrap results.


**`bound`**
⇒ *`BiPredicate<X, Y>`*  

**`JavaBiPredicateBinder(BiPredicate<? super X, ? super Y> bound)`** _(contructor)_  

**`flip()`**
⇒ *`BiPredicate<Y, X>`*  

Flip the arguments.


**`bind(X x, Y y)`**
⇒ *`BooleanSupplier`*  

Curries both arguments.


**`bindFirst(X x)`**
⇒ *`JavaPredicateBinder<Y>`*  

Curry the first argument.


**`bindSecond(Y y)`**
⇒ *`JavaPredicateBinder<X>`*  

Curry the second argument.


**`link(Supplier<? extends X> x, Supplier<? extends Y> y)`**
⇒ *`BooleanSupplier`*  

Link both arguments to supplied values. `x` and `y` are invoked each
time the resulting [`BooleanSupplier`] is invoked and the results are
supplied as arguments to the bound [`BiPredicate`].


**`link(Function<? super U, ? extends X> x, Function<? super V, ? extends Y> y)`**
⇒ *`JavaBiPredicateBinder<U, V>`*  

Map both arguments. `x` and `y` are invoked each time the resulting
[`BiPredicate`] is invoked and the results are supplied as arguments to the
bound [`BiPredicate`].


**`linkFirst(Supplier<? extends X> x)`**
⇒ *`JavaPredicateBinder<Y>`*  

Link the first argument to supplied value. `x` is invoked each time the
resulting [`Predicate`] is invoked and the results is supplied as first
argument to the bound [`BiPredicate`].


**`linkFirst(Function<? super U, ? extends X> x)`**
⇒ *`JavaBiPredicateBinder<U, Y>`*  

Map the first argument. `x` is invoked each time the resulting
[`BiPredicate`] is invoked and the result is supplied as first argument
to the bound [`BiPredicate`].


**`linkSecond(Supplier<? extends Y> y)`**
⇒ *`JavaPredicateBinder<X>`*  

Link the second argument to supplied value. `y` is invoked each time the
resulting [`Predicate`] is invoked and the results is supplied as second
argument to the bound [`BiPredicate`].


**`linkSecond(Function<? super V, ? extends Y> y)`**
⇒ *`JavaBiPredicateBinder<X, V>`*  

Map the second argument. `y` is invoked each time the resulting
[`BiPredicate`] is invoked and the result is supplied as second argument
to the bound [`BiPredicate`].


**`bound()`**
⇒ *`BiPredicate<X, Y>`* *(the wrapped [`BiPredicate`])*  

**`test(X x, Y y)`**
⇒ *`boolean`*  


### JavaConsumerBinder\<X>
_[(src)](src/main/java/org/tinyj/lava/binder/JavaConsumerBinder.java)_  
`X`: the type of the argument to the operation  

Enable various forms of currying on Java's [`Consumer`].

To enable a fluent syntax binders wrapping the curried function are returned
where applicable. This introduces some overhead that might be an issue if
either the result is invoked many times or many results are produced. Use
`bound()` to unwrap results.


**`bound`**
⇒ *`Consumer<X>`*  

**`JavaConsumerBinder(Consumer<? super X> bound)`** _(contructor)_  

**`bind(X x)`**
⇒ *`Runnable`*  

Curry argument with `x`.


**`linkTo(Supplier<? extends X> x)`**
⇒ *`Runnable`*  

Link the argument to supplied value. `x` is invoked each time the
resulting [`Runnable`] is invoked and the results is supplied as argument
to the curried [`Consumer`].


**`linkTo(Function<? super U, ? extends X> x)`**
⇒ *`JavaConsumerBinder<U>`*  

Map the argument. `x` is invoked each time the resulting [`Consumer`] is
invoked and the result is supplied as argument to the curried [`Consumer`].


**`linkTo(BiFunction<? super U, ? super V, ? extends X> x)`**
⇒ *`JavaBiConsumerBinder<U, V>`*  

Map the argument. `x` is invoked each time the resulting [`BiConsumer`] is
invoked and the result is supplied as argument to the curried [`Consumer`].


**`bound()`**
⇒ *`Consumer<X>`* *(the wrapped [`Consumer`])*  

**`andThen(Consumer<? super X> after)`**
⇒ *`Consumer<X>`*  

**`accept(X x)`**  


### JavaFunctionBinder<X, R>
_[(src)](src/main/java/org/tinyj/lava/binder/JavaFunctionBinder.java)_  
`X`: the type of the input to the function  
`R`: the type of the result of the function  

Enable various forms of currying on Java's [`Function`].

To enable a fluent syntax binders wrapping the curried function are returned
where applicable. This introduces some overhead that might be an issue if
either the result is invoked many times or many results are produced. Use
`bound()` to unwrap results.


**`bound`**
⇒ *`Function<X, R>`*  

**`JavaFunctionBinder(Function<? super X, ? extends R> bound)`** _(contructor)_  

**`bind(X x)`**
⇒ *`Supplier<R>`*  

Curry argument with `x`.


**`linkTo(Supplier<? extends X> x)`**
⇒ *`Supplier<R>`*  

Link the argument to supplied value. `x` is invoked each time the
resulting [`Supplier`] is invoked and the results is supplied as argument
to the curried [`Function`].


**`linkTo(Function<? super U, ? extends X> x)`**
⇒ *`JavaFunctionBinder<U, R>`*  

Map the argument. `x` is invoked each time the resulting [`Function`] is
invoked and the result is supplied as argument to the curried [`Function`].


**`linkTo(BiFunction<? super U, ? super V, ? extends X> x)`**
⇒ *`JavaBiFunctionBinder<U, V, R>`*  

Map the argument. `x` is invoked each time the resulting [`BiFunction`] is
invoked and the result is supplied as argument to the curried [`Function`].


**`bound()`**
⇒ *`Function<X, R>`* *(the wrapped [`Function`])*  

**`compose(Function<? super U, ? extends X> before)`**
⇒ *`JavaFunctionBinder<U, R>`*  

**`andThen(Function<? super R, ? extends V> after)`**
⇒ *`JavaFunctionBinder<X, V>`*  

**`apply(X x)`**
⇒ *`R`*  


### JavaPredicateBinder\<X>
_[(src)](src/main/java/org/tinyj/lava/binder/JavaPredicateBinder.java)_  
`X`: the type of the input to the predicate  

Enable various forms of currying on Java's [`Predicate`].

To enable a fluent syntax binders wrapping the curried function are returned
where applicable. This introduces some overhead that might be an issue if
either the result is invoked many times or many results are produced. Use
`bound()` to unwrap results.


**`bound`**
⇒ *`Predicate<X>`*  

**`JavaPredicateBinder(Predicate<? super X> bound)`** _(contructor)_  

**`bind(X x)`**
⇒ *`BooleanSupplier`*  

Curry argument with `x`.


**`linkTo(Supplier<? extends X> x)`**
⇒ *`BooleanSupplier`*  

Link the argument to supplied value. `x` is invoked each time the
resulting [`BooleanSupplier`] is invoked and the results is supplied as
argument to the curried [`Predicate`].


**`linkTo(Function<? super U, ? extends X> x)`**
⇒ *`JavaPredicateBinder<U>`*  

Map the argument. `x` is invoked each time the resulting [`Predicate`] is
invoked and the result is supplied as argument to the curried [`Predicate`].


**`linkTo(BiFunction<? super U, ? super V, ? extends X> x)`**
⇒ *`JavaBiPredicateBinder<U, V>`*  

Map the argument. `x` is invoked each time the resulting [`BiPredicate`] is
invoked and the result is supplied as argument to the curried [`Predicate`].


**`bound()`**
⇒ *`Predicate<X>`* *(the wrapped [`Predicate`])*  

**`test(X x)`**
⇒ *`boolean`*  


### LavaBiConsumerBinder<X, Y, E>
_[(src)](src/main/java/org/tinyj/lava/binder/LavaBiConsumerBinder.java)_  
`X`: the type of the first argument to the operation  
`Y`: the type of the second argument to the operation  

Enable various forms of currying on [`LavaBiConsumer`].

To enable a fluent syntax binders wrapping the curried function are returned
where applicable. This introduces some overhead that might be an issue if
either the result is invoked many times or many results are produced. Use
`bound()` unwrap these results.


**`bound`**
⇒ *`LavaBiConsumer<X, Y, E>`*  

**`LavaBiConsumerBinder(LavaBiConsumer<? super X, ? super Y, ? extends E> bound)`** _(contructor)_  

**`flip()`**
⇒ *`LavaBiConsumerBinder<Y, X, E>`*  

Flip the arguments.


**`bind(X x, Y y)`**
⇒ *`LavaRunnable<E>`*  

Curries both arguments.


**`bindFirst(X x)`**
⇒ *`LavaConsumerBinder<Y, E>`*  

Curry the first argument.


**`bindSecond(Y y)`**
⇒ *`LavaConsumerBinder<X, E>`*  

Curry the second argument.


**`link(LavaSupplier<? extends X, ?> x, LavaSupplier<? extends Y, ?> y)`**
⇒ *`LavaRunnable<?>`*  

Link both arguments to supplied values. `x` and `y` are invoked each
time the resulting [`LavaRunnable`] is invoked and the results are supplied as
arguments to the bound [`LavaBiConsumer`].


**`link(LavaFunction<? super U, ? extends X, ?> x, LavaFunction<? super V, ? extends Y, ?> y)`**
⇒ *`LavaBiConsumerBinder<U, V, ?>`*  

Map both arguments. `x` and `y` are invoked each time the resulting
[`LavaBiConsumer`] is invoked and the results are supplied as arguments to the
bound [`LavaBiConsumer`].


**`linkFirst(LavaSupplier<? extends X, ?> x)`**
⇒ *`LavaConsumerBinder<Y, ?>`*  

Link the first argument to supplied value. `x` is invoked each time the
resulting [`LavaConsumer`] is invoked and the results is supplied as first
argument to the bound [`LavaBiConsumer`].


**`linkFirst(LavaFunction<? super U, ? extends X, ?> x)`**
⇒ *`LavaBiConsumerBinder<U, Y, ?>`*  

Map the first argument. `x` is invoked each time the resulting
[`LavaBiConsumer`] is invoked and the result is supplied as first argument
to the bound [`LavaBiConsumer`].


**`linkSecond(LavaSupplier<? extends Y, ?> y)`**
⇒ *`LavaConsumerBinder<X, ?>`*  

Link the second argument to supplied value. `y` is invoked each time the
resulting [`LavaConsumer`] is invoked and the results is supplied as second
argument to the bound [`LavaBiConsumer`].


**`linkSecond(LavaFunction<? super V, ? extends Y, ?> y)`**
⇒ *`LavaBiConsumerBinder<X, V, ?>`*  

Map the second argument. `y` is invoked each time the resulting
[`LavaBiConsumer`] is invoked and the result is supplied as second argument
to the bound [`LavaBiConsumer`].


**`bound()`**
⇒ *`LavaBiConsumer<X, Y, E>`* *(the wrapped [`LavaBiConsumer`])*  

**`checkedAccept(X x, Y y)`**  


### LavaBiFunctionBinder<X, Y, R, E>
_[(src)](src/main/java/org/tinyj/lava/binder/LavaBiFunctionBinder.java)_  
`X`: the type of the first argument to the function  
`Y`: the type of the second argument to the function  
`R`: the type of the result of the function  

Enable various forms of currying on [`LavaBiFunction`].

To enable a fluent syntax binders wrapping the curried function are returned
where applicable. This introduces some overhead that might be an issue if
either the result is invoked many times or many results are produced. Use
`bound()` unwrap these results.


**`bound`**
⇒ *`LavaBiFunction<X, Y, R, E>`*  

**`LavaBiFunctionBinder(LavaBiFunction<? super X, ? super Y, ? extends R, ? extends E> bound)`** _(contructor)_  

**`flip()`**
⇒ *`LavaBiFunction<Y, X, R, E>`*  

Flip the arguments.


**`bind(X x, Y y)`**
⇒ *`LavaSupplier<R, E>`*  

Curries both arguments.


**`bindFirst(X x)`**
⇒ *`LavaFunctionBinder<Y, R, E>`*  

Curry the first argument.


**`bindSecond(Y y)`**
⇒ *`LavaFunctionBinder<X, R, E>`*  

Curry the second argument.


**`link(LavaSupplier<? extends X, ?> x, LavaSupplier<? extends Y, ?> y)`**
⇒ *`LavaSupplier<R, ?>`*  

Link both arguments to supplied values. `x` and `y` are invoked each
time the resulting [`LavaSupplier`] is invoked and the results are supplied as
arguments to the bound [`LavaBiFunction`].


**`link(LavaFunction<? super U, ? extends X, ?> x, LavaFunction<? super V, ? extends Y, ?> y)`**
⇒ *`LavaBiFunctionBinder<U, V, R, ?>`*  

Map both arguments. `x` and `y` are invoked each time the resulting
[`LavaBiFunction`] is invoked and the results are supplied as arguments to the
bound [`LavaBiFunction`].


**`linkFirst(LavaSupplier<? extends X, ?> x)`**
⇒ *`LavaFunctionBinder<Y, R, ?>`*  

Link the first argument to supplied value. `x` is invoked each time the
resulting [`LavaFunction`] is invoked and the results is supplied as first
argument to the bound [`LavaBiFunction`].


**`linkFirst(LavaFunction<? super U, ? extends X, ?> x)`**
⇒ *`LavaBiFunctionBinder<U, Y, R, ?>`*  

Map the first argument. `x` is invoked each time the resulting
[`LavaBiFunction`] is invoked and the result is supplied as first argument
to the bound [`LavaBiFunction`].


**`linkSecond(LavaSupplier<? extends Y, ?> y)`**
⇒ *`LavaFunctionBinder<X, R, ?>`*  

Link the second argument to supplied value. `y` is invoked each time the
resulting [`LavaFunction`] is invoked and the results is supplied as second
argument to the bound [`LavaBiFunction`].


**`linkSecond(LavaFunction<? super V, ? extends Y, ?> y)`**
⇒ *`LavaBiFunctionBinder<X, V, R, ?>`*  

Map the second argument. `y` is invoked each time the resulting
[`LavaBiFunction`] is invoked and the result is supplied as second argument
to the bound [`LavaBiFunction`].


**`bound()`**
⇒ *`LavaBiFunction<X, Y, R, E>`* *(the wrapped [`LavaBiFunction`])*  

**`checkedApply(X x, Y y)`**
⇒ *`R`*  


### LavaBiPredicateBinder<X, Y, E>
_[(src)](src/main/java/org/tinyj/lava/binder/LavaBiPredicateBinder.java)_  
`X`: the type of the first argument to the predicate  
`Y`: the type of the second argument the predicate  

Enable various forms of currying on [`LavaBiPredicate`].

To enable a fluent syntax binders wrapping the curried function are returned
where applicable. This introduces some overhead that might be an issue if
either the result is invoked many times or many results are produced. Use
`bound()` unwrap these results.


**`bound`**
⇒ *`LavaBiPredicate<X, Y, E>`*  

**`LavaBiPredicateBinder(LavaBiPredicate<? super X, ? super Y, ? extends E> bound)`** _(contructor)_  

**`flip()`**
⇒ *`LavaBiPredicate<Y, X, E>`*  

Flip the arguments.


**`bind(X x, Y y)`**
⇒ *`LavaCondition<E>`*  

Curries both arguments.


**`bindFirst(X x)`**
⇒ *`LavaPredicateBinder<Y, E>`*  

Curry the first argument.


**`bindSecond(Y y)`**
⇒ *`LavaPredicateBinder<X, E>`*  

Curry the second argument.


**`link(LavaSupplier<? extends X, ?> x, LavaSupplier<? extends Y, ?> y)`**
⇒ *`LavaCondition<?>`*  

Link both arguments to supplied values. `x` and `y` are invoked each
time the resulting [`LavaCondition`] is invoked and the results are
supplied as arguments to the bound [`LavaBiPredicate`].


**`link(LavaFunction<? super U, ? extends X, ?> x, LavaFunction<? super V, ? extends Y, ?> y)`**
⇒ *`LavaBiPredicateBinder<U, V, ?>`*  

Map both arguments. `x` and `y` are invoked each time the resulting
[`LavaBiPredicate`] is invoked and the results are supplied as arguments to the
bound [`LavaBiPredicate`].


**`linkFirst(LavaSupplier<? extends X, ?> x)`**
⇒ *`LavaPredicateBinder<Y, ?>`*  

Link the first argument to supplied value. `x` is invoked each time the
resulting [`LavaPredicate`] is invoked and the results is supplied as first
argument to the bound [`LavaBiPredicate`].


**`linkFirst(LavaFunction<? super U, ? extends X, ?> x)`**
⇒ *`LavaBiPredicateBinder<U, Y, ?>`*  

Map the first argument. `x` is invoked each time the resulting
[`LavaBiPredicate`] is invoked and the result is supplied as first argument
to the bound [`LavaBiPredicate`].


**`linkSecond(LavaSupplier<? extends Y, ?> y)`**
⇒ *`LavaPredicateBinder<X, ?>`*  

Link the second argument to supplied value. `y` is invoked each time the
resulting [`LavaPredicate`] is invoked and the results is supplied as second
argument to the bound [`LavaBiPredicate`].


**`linkSecond(LavaFunction<? super V, ? extends Y, ?> y)`**
⇒ *`LavaBiPredicateBinder<X, V, ?>`*  

Map the second argument. `y` is invoked each time the resulting
[`LavaBiPredicate`] is invoked and the result is supplied as second argument
to the bound [`LavaBiPredicate`].


**`bound()`**
⇒ *`LavaBiPredicate<X, Y, E>`* *(the wrapped [`LavaBiPredicate`])*  

**`checkedTest(X x, Y y)`**
⇒ *`boolean`*  


### LavaConsumerBinder<X, E>
_[(src)](src/main/java/org/tinyj/lava/binder/LavaConsumerBinder.java)_  
`X`: the type of the argument to the operation  

Enable various forms of currying on [`LavaConsumer`].

To enable a fluent syntax binders wrapping the curried function are returned
where applicable. This introduces some overhead that might be an issue if
either the result is invoked many times or many results are produced. Use
`bound()` unwrap these results.


**`bound`**
⇒ *`LavaConsumer<X, E>`*  

**`LavaConsumerBinder(LavaConsumer<? super X, ? extends E> bound)`** _(contructor)_  

**`bind(X x)`**
⇒ *`LavaRunnable<E>`*  

Curry argument with `x`.


**`linkTo(LavaSupplier<? extends X, ?> x)`**
⇒ *`LavaRunnable<?>`*  

Link the argument to supplied value. `x` is invoked each time the
resulting [`LavaRunnable`] is invoked and the results is supplied as argument
to the curried [`LavaConsumer`].


**`linkTo(LavaFunction<? super U, ? extends X, ?> x)`**
⇒ *`LavaConsumerBinder<U, ?>`*  

Map the argument. `x` is invoked each time the resulting [`LavaConsumer`] is
invoked and the result is supplied as argument to the curried [`LavaConsumer`].


**`linkTo(LavaBiFunction<? super U, ? super V, ? extends X, ?> x)`**
⇒ *`LavaBiConsumerBinder<U, V, ?>`*  

Map the argument. `x` is invoked each time the resulting [`LavaBiConsumer`] is
invoked and the result is supplied as argument to the curried [`LavaConsumer`].


**`bound()`**
⇒ *`LavaConsumer<X, E>`* *(the wrapped [`LavaConsumer`])*  

**`acceptFirst()`**
⇒ *`LavaBiConsumer<U, V, E>`*  

**`acceptSecond()`**
⇒ *`LavaBiConsumer<U, V, E>`*  

**`checkedAccept(X x)`**  


### LavaFunctionBinder<X, R, E>
_[(src)](src/main/java/org/tinyj/lava/binder/LavaFunctionBinder.java)_  
`X`: the type of the input to the function  
`R`: the type of the result of the function  

Enable various forms of currying on [`LavaFunction`].

To enable a fluent syntax binders wrapping the curried function are returned
where applicable. This introduces some overhead that might be an issue if
either the result is invoked many times or many results are produced. Use
`bound()` unwrap these results.


**`bound`**
⇒ *`LavaFunction<X, R, E>`*  

**`LavaFunctionBinder(LavaFunction<? super X, ? extends R, ? extends E> bound)`** _(contructor)_  

**`bind(X x)`**
⇒ *`LavaSupplier<R, E>`*  

Curry argument with `x`.


**`linkTo(LavaSupplier<? extends X, ?> x)`**
⇒ *`LavaSupplier<R, ?>`*  

Link the argument to supplied value. `x` is invoked each time the
resulting [`LavaSupplier`] is invoked and the results is supplied as argument
to the curried [`LavaFunction`].


**`linkTo(LavaFunction<? super U, ? extends X, ?> x)`**
⇒ *`LavaFunctionBinder<U, R, ?>`*  

Map the argument. `x` is invoked each time the resulting [`LavaFunction`] is
invoked and the result is supplied as argument to the curried [`LavaFunction`].


**`linkTo(LavaBiFunction<? super U, ? super V, ? extends X, ?> x)`**
⇒ *`LavaBiFunctionBinder<U, V, R, ?>`*  

Map the argument. `x` is invoked each time the resulting [`LavaBiFunction`] is
invoked and the result is supplied as argument to the curried [`LavaFunction`].


**`bound()`**
⇒ *`LavaFunction<X, R, E>`* *(the wrapped [`LavaFunction`])*  

**`applyFirst()`**
⇒ *`LavaBiFunction<U, V, R, E>`*  

**`applySecond()`**
⇒ *`LavaBiFunction<U, V, R, E>`*  

**`checkedApply(X x)`**
⇒ *`R`*  


### LavaPredicateBinder<X, E>
_[(src)](src/main/java/org/tinyj/lava/binder/LavaPredicateBinder.java)_  
`X`: the type of the input to the predicate  

Enable various forms of currying on [`LavaPredicate`].

To enable a fluent syntax binders wrapping the curried function are returned
where applicable. This introduces some overhead that might be an issue if
either the result is invoked many times or many results are produced. Use
`bound()` unwrap these results.


**`bound`**
⇒ *`LavaPredicate<X, E>`*  

**`LavaPredicateBinder(LavaPredicate<? super X, ? extends E> bound)`** _(contructor)_  

**`bind(X x)`**
⇒ *`LavaCondition<E>`*  

Curry argument with `x`.


**`linkTo(Supplier<? extends X> x)`**
⇒ *`LavaCondition<E>`*  

Link the argument to supplied value. `x` is invoked each time the
resulting [`LavaCondition`] is invoked and the results is supplied as
argument to the curried [`LavaPredicate`].


**`linkTo(LavaFunction<? super U, ? extends X, ?> x)`**
⇒ *`LavaPredicateBinder<U, ?>`*  

Map the argument. `x` is invoked each time the resulting [`LavaPredicate`] is
invoked and the result is supplied as argument to the curried [`LavaPredicate`].


**`linkTo(LavaBiFunction<? super U, ? super V, ? extends X, ?> x)`**
⇒ *`LavaBiPredicateBinder<U, V, ?>`*  

Map the argument. `x` is invoked each time the resulting [`LavaBiPredicate`] is
invoked and the result is supplied as argument to the curried [`LavaPredicate`].


**`bound()`**
⇒ *`LavaPredicate<X, E>`* *(the wrapped [`LavaPredicate`])*  

**`testFirst()`**
⇒ *`LavaBiPredicate<U, V, E>`*  

**`testSecond()`**
⇒ *`LavaBiPredicate<U, V, E>`*  

**`checkedTest(X x)`**
⇒ *`boolean`*  


[`BiConsumer`]: https://docs.oracle.com/javase/9/docs/api/java/util/function/BiConsumer.html
[`BiFunction`]: https://docs.oracle.com/javase/9/docs/api/java/util/function/BiFunction.html
[`BiPredicate`]: https://docs.oracle.com/javase/9/docs/api/java/util/function/BiPredicate.html
[`BooleanSupplier`]: https://docs.oracle.com/javase/9/docs/api/java/util/function/BooleanSupplier.html
[`Consumer`]: https://docs.oracle.com/javase/9/docs/api/java/util/function/Consumer.html
[`Function`]: https://docs.oracle.com/javase/9/docs/api/java/util/function/Function.html
[`JavaBiConsumerBinder`]: #javabiconsumerbinderx-y
[`JavaBiFunctionBinder`]: #javabifunctionbinderx-y-r
[`JavaBiPredicateBinder`]: #javabipredicatebinderx-y
[`JavaConsumerBinder`]: #javaconsumerbinderx
[`JavaFunctionBinder`]: #javafunctionbinderx-r
[`JavaPredicateBinder`]: #javapredicatebinderx
[`LavaBiConsumerBinder`]: #lavabiconsumerbinderx-y-e
[`LavaBiConsumer`]: https://github.com/tinyj/tinyj-lava-api/blob/master/APIdoc.md#lavabiconsumerx-y-e
[`LavaBiFunctionBinder`]: #lavabifunctionbinderx-y-r-e
[`LavaBiFunction`]: https://github.com/tinyj/tinyj-lava-api/blob/master/APIdoc.md#lavabifunctionx-y-r-e
[`LavaBiPredicateBinder`]: #lavabipredicatebinderx-y-e
[`LavaBiPredicate`]: https://github.com/tinyj/tinyj-lava-api/blob/master/APIdoc.md#lavabipredicatex-y-e
[`LavaCondition`]: https://github.com/tinyj/tinyj-lava-api/blob/master/APIdoc.md#lavaconditione
[`LavaConsumerBinder`]: #lavaconsumerbinderx-e
[`LavaConsumer`]: https://github.com/tinyj/tinyj-lava-api/blob/master/APIdoc.md#lavaconsumerx-e
[`LavaFunctionBinder`]: #lavafunctionbinderx-r-e
[`LavaFunction`]: https://github.com/tinyj/tinyj-lava-api/blob/master/APIdoc.md#lavafunctionx-r-e
[`LavaPredicateBinder`]: #lavapredicatebinderx-e
[`LavaPredicate`]: https://github.com/tinyj/tinyj-lava-api/blob/master/APIdoc.md#lavapredicatex-e
[`LavaRunnable`]: https://github.com/tinyj/tinyj-lava-api/blob/master/APIdoc.md#lavarunnablee
[`LavaSupplier`]: https://github.com/tinyj/tinyj-lava-api/blob/master/APIdoc.md#lavasupplierr-e
[`Predicate`]: https://docs.oracle.com/javase/9/docs/api/java/util/function/Predicate.html
[`Runnable`]: https://docs.oracle.com/javase/9/docs/api/java/lang/Runnable.html
[`RuntimeException`]: https://docs.oracle.com/javase/9/docs/api/java/lang/RuntimeException.html
[`Supplier`]: https://docs.oracle.com/javase/9/docs/api/java/util/function/Supplier.html
