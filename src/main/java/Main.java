import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        int firstStream = countPersonsUnder18(persons);
        System.out.println(firstStream);

        List<String> secondStream = getFamiliesBetween18And27(persons);
        System.out.println(secondStream);

        List<Person> thirdStream = filterAndSortPersons(persons);
        System.out.println(thirdStream);
    }

    public static int countPersonsUnder18(Collection<Person> persons) {
        return (int) persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
    }

    public static List<String> getFamiliesBetween18And27(Collection<Person> persons) {
        return persons.stream()
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
    }

    public static List<Person> filterAndSortPersons(Collection<Person> persons) {
        return persons.stream()
                .filter(person -> {
                    int age = person.getAge();
                    boolean isFemale = person.getSex() == Sex.WOMAN;
                    return (isFemale && age >= 18 && age <= 60) || (!isFemale && age <= 65);
                })
                .filter(person -> person.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
}