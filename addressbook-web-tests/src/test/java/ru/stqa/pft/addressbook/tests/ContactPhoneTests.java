package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by t_aleksandr on 9/13/16.
 */
public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Olga").withLastname("Test1").withTitle("Tester").withCompany("QA")
              .withAddress("100 Main Street San Francisco, CA").withHomephone("516-29-08").withGroup("test1")
              .withMobilephone("+7 888").withWorkphone("(650)11790)").withEmail("olga@test.com")
              .withEmail2("olga1@test.com").withEmail3("olga2@test.com"));
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Stream.of(contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone())
            .filter(s -> !s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

   /*private String mergePhones(ContactData contact) {
    String result = "";
    if (!contact.getHomephone().equals("")) {
      result = result + cleaned(contact.getHomephone());
    }
    if (!contact.getMobilephone().equals("")) {
      result = result + "/n" + (cleaned(contact.getMobilephone()).concat("\n"));
    }
    if (!contact.getWorkphone().equals("")) {
      result = result + "/n" + cleaned(contact.getWorkphone());
    }
    return result;
  }*/

  /*private String mergePhones(ContactData contact) {
     StringBuilder sb = new StringBuilder("");
     if (!contact.getHomephone().equals("")) {
       sb.append(cleaned(contact.getHomephone()));
     }
     if (!contact.getMobilephone().equals("")) {
       sb.append("/n");
       sb.append(cleaned(contact.getMobilephone()));
     }
     if (!contact.getWorkphone().equals("")) {
       sb.append("/n");
       sb.append(cleaned(contact.getWorkphone()));
     }
     return String.valueOf(sb);
   }*/
}
