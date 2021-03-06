import java.util.*;
import java.util.function.*;

public class Driver {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("kazuma", null, "Megumin", "Aqua", "AA", "darkness", "wiz", "02", "WoodTier", "ABCDEFGH");
        List<Person> people = Arrays.asList(new Person("Adam", 13), new Person("George", 14), new Person("Kevin", 13), null);
        Map<String, String> dict = new HashMap<>();
        dict.put("lol", "what");
        dict.put("google", "com");
        dict.put("mushoku", "tensei");
        dict.put("What", "nani");

        Map<String, Person> ppl = new HashMap<>();
        ppl.put("1", people.get(0));
        ppl.put("2", people.get(1));
        ppl.put("3", people.get(2));
        ppl.put("4", people.get(3));

        System.out.println(StreamUtils.capitalized(names));

        System.out.println(StreamUtils.longest(names, false));
        System.out.println(StreamUtils.longest(Collections.singletonList("What"), false));

        System.out.println(StreamUtils.least(people, true));
        System.out.println(StreamUtils.least(Collections.singletonList("What"), true));

        System.out.println(StreamUtils.flatten(dict));
        System.out.println(StreamUtils.flatten(ppl));

        // Part 2 testing ==============================================================================================
        System.out.println("==============================================================================================");
        List<Double> zipArgs = Arrays.asList(1.0, 1.0, 3.0, 0.0, 4.0);
        List<HigherOrderUtils.NamedBiFunction<Double, Double, Double>> zipFunctions =
                Arrays.asList(HigherOrderUtils.add, HigherOrderUtils.multiply,
                HigherOrderUtils.add, HigherOrderUtils.divide);
        System.out.println(HigherOrderUtils.zip(zipArgs, zipFunctions));
        System.out.println(HigherOrderUtils.zip(new ArrayList<Double>(), new ArrayList<HigherOrderUtils.NamedBiFunction<Double, Double, Double>>()));
        System.out.println("Updated zipArgs: " + zipArgs);

        // Lowercase - do not try with anything other than a lowercase letter
        Function<Character, String> concatenateChar = e -> {
            String output = "";
            for (int i = 0; i <= (int) e - (int) 'a'; i++) output += e;
            return output;
        };

        Function<String, Integer> lengthOfStr = String::length;

        HigherOrderUtils.FunctionComposition<Character, String, Integer> comp = new HigherOrderUtils.FunctionComposition<>();
        System.out.println(comp.composition.apply(concatenateChar, lengthOfStr).apply('z'));
    }

    // Used for least method testing
    public static class Person implements Comparable<Person>{
        private String name;
        private int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person other) {
            return age - other.age;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
