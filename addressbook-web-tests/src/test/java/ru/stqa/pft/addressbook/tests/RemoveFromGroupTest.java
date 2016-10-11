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

/**
 * Created by Olga on 10/10/16.
 */
public class RemoveFromGroupTest extends TestBase {

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
  public void testRemoveFromGroup() {
    Groups allGroups = app.db().groups();
    GroupData group = allGroups.iterator().next();
    ContactData contactToRemove = ensureGroupContainsContacts(group);
    app.goTo().homePage();
    app.contact().selectGroupContactsPage(group);
    app.contact().selectContactById(contactToRemove.getId());
    app.contact().removeFromGroup();
    Contacts contacts = updatedGroups(group);
    Assert.assertFalse(contacts.contains(contactToRemove));
  }

  private ContactData ensureGroupContainsContacts (GroupData group) {
    if (group.getContacts().size() <= 0) {
      Contacts allContacts = app.db().contacts();
      ContactData contact = allContacts.iterator().next();
      app.contact().selectContactById(contact.getId());
      app.contact().addToGroup(group);
      Contacts contacts = updatedGroups(group);
      return contacts.iterator().next();
    } else {
      return group.getContacts().iterator().next();
    }
  }

  private Contacts updatedGroups(GroupData group) {
    Groups newGroups = app.db().groups();
    Set<GroupData> updatedGroups = newGroups.stream()
            .filter(g -> g.getId() == group.getId())
            .collect(Collectors.toSet());
    return updatedGroups.iterator().next().getContacts();
  }
}
