package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.commons.Date;
import seedu.address.model.commons.Name;
import seedu.address.model.person.*;
import seedu.address.model.project.Project;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Date("15-12-2019"), new Username("AYeoh"),
                new Password("AYeohPass1!"), new Role("HR"), new Salary("5000"), getProjectSet()),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Date("16-11-2020"), new Username("BYu"),
                new Password("BYu12345@"), new Role("Manager"), new Salary("6000"), getProjectSet("AndroidApp", "customWebsite")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Date("20-10-2020"), new Username("COlveiro"),
                new Password("Charlotte987$"), new Role("Developer"), new Salary("4500"), getProjectSet("AndroidApp")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Date("23-09-2021"), new Username("DLi"),
                new Password("David678!$"), new Role("Developer"), new Salary("5500"), getProjectSet("customWebsite")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Date("24-12-2020"), new Username("IIbrahim"),
                new Password("Ibrahim2345&"), new Role("HR"), new Salary("5000"), getProjectSet()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Date("29-02-2022"), new Username("RBalakrishnan"),
                new Password("RoyBala543@"), new Role("Developer"), new Salary("5500"), getProjectSet("AndroidApp", "customWebsite"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Project> getProjectSet(String... strings) {
        return Arrays.stream(strings)
                .map(Project::new)
                .collect(Collectors.toSet());
    }

}
