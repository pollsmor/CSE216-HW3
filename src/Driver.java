import java.util.*;

public class Driver {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("kazuma", "a", "Megumin", "Aqua", "AA", "darkness", "wiz", "02", "WoodTier", "ABCDEFGH");
        List<Person> people = Arrays.asList(new Person("Adam", 13), new Person("George", 14), new Person("Kevin", 13));
        System.out.println(StreamUtils.capitalized(names));

        System.out.println(StreamUtils.longest(names, false));

        System.out.println(StreamUtils.least(people, true));
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
