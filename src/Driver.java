import java.util.*;

public class Driver {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("kazuma", "", "Megumin", "Aqua", "darkness", "wiz", "02", "WoodTier", "ABCDEFGH");
        System.out.println(StreamUtils.capitalized(names));

        System.out.println(StreamUtils.longest(names, false));
    }
}
