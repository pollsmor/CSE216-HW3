import java.util.*;
import java.util.stream.Collectors;

public class StreamUtils {
    /**
     * @param strings:  the input collection of <code>String</code>s.
     * @return          a collection of those <code>String</code>s in the input collection
                        that start with a capital letter.
     */
    public static Collection<String> capitalized(Collection<String> strings) {
        return strings.stream()
                .filter(n -> n != null && n.length() > 0 && Character.isUpperCase(n.charAt(0)))
                .collect(Collectors.toList());
    }

    /**
     * Find and return the longest <code>String</code> in a given collection of <code>String</code>s.
     *
     * @param strings:      the given collection of <code>String</code>s.
     * @param from_start:   a <code>boolean</code> flag that decides how ties are broken.
                            If <code>true</code>, then the element encountered earlier in
     *                      the iteration is returned, otherwise the later element is returned.
     * @return              the longest <code>String</code> in the given collection,
     *                      where ties are broken based on <code>from_start</code>.
     */
    public static String longest(Collection<String> strings, boolean from_start) {
        return strings.stream()
                .reduce((str1, str2) -> {
                    if (str2 == null) return str1;

                    if (from_start) {
                        return str1.length() >= str2.length() ? str1 : str2;
                    } else {
                        return str2.length() >= str1.length() ? str2 : str1;
                    }
                })
                .orElse("N/A");
    }

    /**
     * Find and return the least element from a collection of given elements that are comparable.
     *
     * @param items:        the given collection of elements
     * @param from_start:   a <code>boolean</code> flag that decides how ties are broken.
     *                      If <code>true</code>, the element encountered earlier in the
     *                      iteration is returned, otherwise the later element is returned.
     * @param <T>:          the type parameter of the collection (i.e., the items are all of type T).
     * @return              the least element in <code>items</code>, where ties are
     *                      broken based on <code>from_start</code>.
     */
    public static <T extends Comparable<T>> T least(Collection<T> items, boolean from_start) {
        return items.stream()
                .reduce((T e1, T e2) -> {
                    if (e2 == null) return e1;

                    if (from_start) {
                        return e1.compareTo(e2) <= 0 ? e1 : e2;
                    } else {
                        return e2.compareTo(e1) <= 0 ? e2 : e1;
                    }
                })
                .orElse(null);
    }

    /**
     * Flattens a map to a stream of <code>String</code>s, where each element in the list
     * is formatted as "key -> value" (i.e., each key-value pair is converted to a string
     * with this format).
     *
     * @param aMap: the specified input map.
     * @param <K>:  the type parameter of keys in <code>aMap</code>.
     * @param <V>:  the type parameter of values in <code>aMap</code>.
     * @return      the flattened list representation of <code>aMap</code>.
     */
    public static <K, V> List<String> flatten(Map<K, V> aMap) {
        return aMap.entrySet()
                .stream()
                .map(s -> s.getKey() + " -> " + s.getValue())
                .collect(Collectors.toList());
    }
}
