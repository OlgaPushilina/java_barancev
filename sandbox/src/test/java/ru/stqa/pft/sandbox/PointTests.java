package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistanceZero() {
    Point p1 = new Point(5 , 10);
    Point p2 = new Point(5 , 10);
    Assert.assertEquals(p1.distance(p2), 0.0);
  }

  @Test
  public void testDistanceDouble() {
    Point p1 = new Point(3 , 7);
    Point p2 = new Point(5 , 10);
    Assert.assertEquals(p1.distance(p2), 3.605551275463989);
  }

  @Test
  public void testDistanceInteger() {
    Point p1 = new Point(-3, -5);
    Point p2 = new Point(3, -5);
    Assert.assertEquals(p1.distance(p2), 6.0);
  }

  // Этот тест должен "упасть"
  @Test
  public void testDistanceToFail() {
    Point p1 = new Point(3 , 7);
    Point p2 = new Point(5 , 10);
    Assert.assertEquals(p1.distance(p2), 3.605);
  }
}
