import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class HigherOrderUtils {
    public static interface NamedBiFunction<T, U, R> extends BiFunction<T, U, R> {
        String name();
    }

    public static NamedBiFunction<Double, Double, Double> add = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {
            return "add";
        }

        @Override
        public Double apply(Double num1, Double num2) {
            return num1 + num2;
        }
    };

    public static NamedBiFunction<Double, Double, Double> subtract = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {
            return "diff";
        }

        @Override
        public Double apply(Double num1, Double num2) {
            return num1 - num2;
        }
    };

    public static NamedBiFunction<Double, Double, Double> multiply = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {
            return "mult";
        }

        @Override
        public Double apply(Double num1, Double num2) {
            return num1 * num2;
        }
    };

    public static NamedBiFunction<Double, Double, Double> divide = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {
            return "div";
        }

        @Override
        public Double apply(Double num1, Double num2) {
            if (num2 == 0)
                throw new ArithmeticException();

            return (double) (int) (num1 / num2);    // Truncates then converts back to a double
        }
    };

    /**
     * Applies a given list of bifunctions -- functions that take two arguments of a certain type
     * and produce a single instance of that type -- to a list of arguments of that type. The
     * functions are applied in an iterative manner, and the result of each function is stored in
     * the list in an iterative manner as well, to be used by the next bifunction in the next
     * iteration. For example, given
     *      List<Double> args = Arrays.asList(1d, 1d, 3d, 0d, 4d), and
     *      List<NamedBiFunction<Double, Double, Double>> bfs = [add, multiply, add, divide],
     * <code>zip(args, bfs)</code> will proceed iteratively as follows:
     *      - index 0: the result of add(1,1) is stored in args[1] to yield args = [1,2,3,0,4]
     *      - index 1: the result of multiply(2,3) is stored in args[2] to yield args = [1,2,6,0,4]
     *      - index 2: the result of add(6,0) is stored in args[3] to yield args = [1,2,6,6,4]
     *      - index 3: the result of divide(6,4) is stored in args[4] to yield args = [1,2,6,6,1]
     *
     * @param args:         the arguments over which <code>bifunctions</code> will be applied.
     * @param bifunctions:  the list of bifunctions that will be applied on <code>args</code>.
     * @param <T>:          the type parameter of the arguments (e.g., Integer, Double)
     * @return              the item in the last index of <code>args</code>, which has the final
     *                      result of all the bifunctions being applied in sequence.
     */
    public static <T> T zip(List<T> args, List<NamedBiFunction<T, T, T>> bifunctions) {
        if (args.size() <= bifunctions.size())
            throw new IllegalArgumentException();

        return IntStream.range(0, args.size())
                .mapToObj(i -> {
                    if (i >= bifunctions.size()) return args.get(i); // Return element in list as is

                    args.set(i + 1, bifunctions.get(i).apply(args.get(i), args.get(i + 1)));
                    return args.get(i);
                })
                .reduce((e1, e2) -> e2) // Get last element
                .orElse(null);
    }

    public static class FunctionComposition<T, U, R> {
        public BiFunction<Function<T, U>, Function<U, R>, Function<T, R>> composition = Function::andThen;
    }
}
