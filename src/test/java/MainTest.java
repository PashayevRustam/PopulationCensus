import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    @Test
    public void testCountPersonsUnder18() {
        List<Person> persons = createRandomPersons(1000);
        int expected = (int) persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        int actual = Main.countPersonsUnder18(persons);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetFamiliesBetween18And27() {
        List<Person> persons = createRandomPersons(1000);
        List<String> expected = persons.stream()
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        List<String> actual = Main.getFamiliesBetween18And27(persons);
        assertEquals(expected, actual);
    }

    @Test
    public void testFilterAndSortPersons() {
        List<Person> persons = createRandomPersons(1000);
        List<Person> expected = persons.stream()
                .filter(person -> {
                    int age = person.getAge();
                    boolean isFemale = person.getSex() == Sex.WOMAN;
                    return (isFemale && age >= 18 && age <= 60) || (!isFemale && age <= 65);
                })
                .filter(person -> person.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        List<Person> actual = Main.filterAndSortPersons(persons);
        assertEquals(expected, actual);
    }

    private List<Person> createRandomPersons(int count) {
        List<String> names = List.of("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = List.of("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            persons.add(new Person(
                    names.get(random.nextInt(names.size())),
                    families.get(random.nextInt(families.size())),
                    random.nextInt(100),
                    Sex.values()[random.nextInt(Sex.values().length)],
                    Education.values()[random.nextInt(Education.values().length)])
            );
        }
        return new ArrayList<>(persons);
    }
}
