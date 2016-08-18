package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    Point p1 = new Point(-3, -5);
    Point p2 = new Point(3, -5);

    //Вызывает функцию
    System.out.println("Расстояние между точками с координатами " + p1.x + ", " + p1.y + " и " + p2.x + ", " + p2.y +
            " = " + distance(p1, p2));

    //Вызывает метод из класса Point
    System.out.println("Расстояние между точками с координатами " + p1.x + ", " + p1.y + " и " + p2.x + ", " + p2.y +
            " = " + p1.distance(p2));

  }

  public static double distance(Point p1, Point p2) {
    double d = Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2));
    return d;
  }

}
