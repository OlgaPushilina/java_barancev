package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;

public class AddToGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test"));
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      File photo = new File("src/test/resources/Cat1.jpg");
      app.contact().create(new ContactData()
              .withFirstname("Olga").withLastname("Test1")
              .withAddress("100 Main Street San Francisco, CA").withHomephone("516-29-08")
              .withMobilephone("+7 888").withWorkphone("(650)11790)").withEmail("olga@test.com")
              .withEmail2("olga1@test.com").withEmail3("olga2@test.com").withPhoto(photo));
    }
  }

  @Test
  public void testAddToGroup() {
    app.goTo().homePage();
    Contacts allContacts = app.db().contacts();
    Groups allGroups = app.db().groups();
    ContactData contact = allContacts.iterator().next();
    GroupData group = allGroups.iterator().next();
    app.contact().selectContactById(contact.getId());
    app.contact().addToGroup(group);

    Contacts newContacts = app.db().contacts();
    Set<ContactData> updatedContacts = newContacts.stream()
            .filter(c -> c.getId() == contact.getId())
            .collect(Collectors.toSet());
    Groups groups = updatedContacts.iterator().next().getGroups();
    Assert.assertTrue(groups.contains(group));

    /*for (ContactData newContact : newContacts) {
      if (newContact.getId() == contact.getId()) {
        Groups groups = newContact.getGroups();
        Assert.assertTrue(groups.contains(group));
      }
    }*/
  }
}
