import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x1, y1, x2, y2;

        System.out.print("Введите координаты первой точки (x1,y1): ");
        x1 = sc.nextInt();
        y1 = sc.nextInt();
        System.out.print("Введите координаты второй точки (x2,y2): ");
        x2 = sc.nextInt();
        y2 = sc.nextInt();

        Rectangle rectangle = new Rectangle(x1, y1, x2, y2);
        System.out.println("Площадь прямоугольника равна: " + rectangle.getSquare());
    }
}
