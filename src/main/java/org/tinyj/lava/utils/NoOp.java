package org.tinyj.lava.utils;

import org.tinyj.lava.LavaBiConsumer;
import org.tinyj.lava.LavaConsumer;
import org.tinyj.lava.LavaRunnable;
import org.tinyj.lava.NoException;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * The no-operation, does absolutely nothing. This is equivalent to
 * {@code (...) -> {}}. Can be used where ever a {@link Runnable},
 * {@link Consumer}, or {@link BiConsumer} is required.
 */
public final class NoOp<X, Y, E extends Exception>
    implements Runnable, Consumer<X>, BiConsumer<X, Y>,
    LavaRunnable<E>, LavaConsumer<X, E>, LavaBiConsumer<X, Y, E> {

  static final NoOp NO_OP = new NoOp();

  private NoOp() {}

  @Override
  public void run() {}

  @Override
  public void accept(X x, Y y) {}

  @Override
  public void accept(X x) {}

  @Override
  public void checkedAccept(X x, Y y) throws NoException {}

  @Override
  public void checkedAccept(X x) throws NoException {}

  @Override
  public void checkedRun() throws NoException {}
}
