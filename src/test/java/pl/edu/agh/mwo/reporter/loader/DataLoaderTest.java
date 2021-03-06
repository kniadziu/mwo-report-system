package pl.edu.agh.mwo.reporter.loader;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.mwo.reporter.model.Company;
import pl.edu.agh.mwo.reporter.model.Person;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class DataLoaderTest {

    @Test
    public void loadData_filterByDate() throws Exception {
        DataLoader dataLoader = new DataLoader();

        List<Path> paths = Arrays.asList(Paths.get("resources/2012/01/Kowalski_Jan.xls"), Paths.get("resources/2012/01/Nowak_Piotr.xls"));

        Company company = dataLoader.loadData(paths, LocalDate.of(2012, 1, 15), LocalDate.of(2012, 1, 20), null);

        Assert.assertNotNull(company);
        List<Person> persons = company.getPersons();

        Assert.assertEquals(2, persons.size());

        Person person0 = persons.get(0);
        Assert.assertEquals("Kowalski Jan", person0.getName());
        Assert.assertEquals(5, person0.getTasks().size());

        Person person1 = persons.get(1);
        Assert.assertEquals("Nowak Piotr", person1.getName());
        Assert.assertEquals(1, person1.getTasks().size());

        Assert.assertEquals(LocalDate.of(2012, 1, 16), company.getStartDate());
        Assert.assertEquals(LocalDate.of(2012, 1, 20), company.getEndDate());
    }

    @Test
    public void loadData_noFiltering() throws Exception {
        DataLoader dataLoader = new DataLoader();

        List<Path> paths = Arrays.asList(Paths.get("resources/2012/01/Kowalski_Jan.xls"), Paths.get("resources/2012/01/Nowak_Piotr.xls"));

        Company company = dataLoader.loadData(paths, null, null, null);

        Assert.assertNotNull(company);
        List<Person> persons = company.getPersons();

        Assert.assertEquals(2, persons.size());

        Person person0 = persons.get(0);
        Assert.assertEquals("Kowalski Jan", person0.getName());
        Assert.assertEquals(11, person0.getTasks().size());

        Person person1 = persons.get(1);
        Assert.assertEquals("Nowak Piotr", person1.getName());
        Assert.assertEquals(10, person1.getTasks().size());

        Assert.assertEquals(LocalDate.of(2012, 1, 5), company.getStartDate());
        Assert.assertEquals(LocalDate.of(2012, 1, 30), company.getEndDate());
    }

    @Test
    public void loadData_filterByFirstName() throws Exception {
        DataLoader dataLoader = new DataLoader();

        List<Path> paths = Arrays.asList(Paths.get("resources/2012/01/Kowalski_Jan.xls"), Paths.get("resources/2012/01/Nowak_Piotr.xls"), Paths.get("resources/2013/02/Rymanowski_Jan.xls"));

        Company company = dataLoader.loadData(paths, null, null, "_Jan");

        Assert.assertNotNull(company);
        List<Person> persons = company.getPersons();

        Assert.assertEquals(2, persons.size());

        Person person0 = persons.get(0);
        Assert.assertEquals("Kowalski Jan", person0.getName());
        Assert.assertEquals(11, person0.getTasks().size());

        Person person1 = persons.get(1);
        Assert.assertEquals("Rymanowski Jan", person1.getName());
        Assert.assertEquals(16, person1.getTasks().size());

        Assert.assertEquals(LocalDate.of(2012, 1, 5), company.getStartDate());
        Assert.assertEquals(LocalDate.of(2013, 2, 16), company.getEndDate());
    }

    @Test
    public void loadData_filterBySurname() throws Exception {
        DataLoader dataLoader = new DataLoader();

        List<Path> paths = Arrays.asList(Paths.get("resources/2012/01/Kowalski_Jan.xls"), Paths.get("resources/2012/01/Nowak_Piotr.xls"), Paths.get("resources/2013/02/Rymanowski_Jan.xls"));

        Company company = dataLoader.loadData(paths, null, null, "Nowak_");

        Assert.assertNotNull(company);
        List<Person> persons = company.getPersons();

        Assert.assertEquals(1, persons.size());

        Person person1 = persons.get(0);
        Assert.assertEquals("Nowak Piotr", person1.getName());
        Assert.assertEquals(10, person1.getTasks().size());

        Assert.assertEquals(LocalDate.of(2012, 1, 13), company.getStartDate());
        Assert.assertEquals(LocalDate.of(2012, 1, 30), company.getEndDate());
    }

}