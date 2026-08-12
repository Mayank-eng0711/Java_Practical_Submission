abstract class Polymorphism {

    // Abstract method
    abstract void calculateArea();

    // Concrete method
    void display() {
        System.out.println("This is a shape.");
    }
}

// Circle subclass
class Circle extends Polymorphism {
    double radius = 5;

    // Overriding abstract method
    void calculateArea() {
        double area = Math.PI * radius * radius;
        System.out.println("Area of Circle = " + area);
    }
}

// Rectangle subclass
class Rectangle extends Polymorphism {
    double length = 10;
    double width = 5;

    // Overriding abstract method
    void calculateArea() {
        double area = length * width;
        System.out.println("Area of Rectangle = " + area);
    }
}

// Main class
public class Main {
    public static void main(String[] args) {

        // Parent class reference
        Polymorphism shape;

        // Runtime polymorphism with Circle
        shape = new Circle();
        shape.display();
        shape.calculateArea();

        System.out.println();

        // Runtime polymorphism with Rectangle
        shape = new Rectangle();
        shape.display();
        shape.calculateArea();
    }
}
