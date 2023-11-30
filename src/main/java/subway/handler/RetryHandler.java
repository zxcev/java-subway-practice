package subway.handler;

import java.util.function.Supplier;

public final class RetryHandler {

    public static <T> T retry(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (final IllegalArgumentException e) {
                System.out.printf(e.getMessage());
            }
        }
    }

    public static void retry(final Runnable runnable) {
        while (true) {
            try {
                runnable.run();
                return;
            } catch (final IllegalArgumentException e) {
                System.out.printf(e.getMessage());
            }
        }
    }
}
