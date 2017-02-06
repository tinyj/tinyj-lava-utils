package org.tinyj.lava.fun;

import org.tinyj.lava.LavaRunnable;

public final class NoOp<E extends Exception>
    implements LavaRunnable<E>, Runnable {

  public static final NoOp<?> NO_OP = new NoOp<>();

  @SuppressWarnings({"unchecked", "MethodNameSameAsClassName"})
  public static <E extends Exception> NoOp<E> NoOp() { return (NoOp<E>) NO_OP; }

  private NoOp() {}

  @Override
  public final void checkedRun() {}

  @Override
  public final void run() {}
}
