package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by t_aleksandr on 9/16/16.
 */
public class ContactDetailsTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Olga").withLastname("Test1")
              .withAddress("100 Main Street San Francisco, CA").withHomephone("516-29-08")
              .withMobilephone("+7888").withWorkphone("(650)11790)").withEmail("olga@test.com")
              .withEmail2("olga1@test.com").withEmail3("olga2@test.com"));
    }
  }

  @Test
  public void testContactDetails() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);

    assertThat((mergeDetails(contact)), equalTo((cleaned(contactInfoFromDetailsForm.getDetails()))));
  }

  private String mergeDetails(ContactData contact) {
    return Stream.of(contact.getFirstname(), contact.getLastname(), contact.getAddress(),
            contact.getAllPhones(), contact.getAllEmails())
            .filter(s -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String details) {
    return details.replaceAll("[-()]", "").replaceAll("www.test.com", "")
            .replaceAll("H:", "").replaceAll("M:", "").replaceAll("W:", "")
            .replaceAll("\n\n", "\n").replaceFirst(" ", "\n").replaceAll("\n ", "\n")
            .replaceAll("com ", "com").replaceAll("7 ", "7");
  }

}
