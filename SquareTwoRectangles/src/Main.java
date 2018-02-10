import java.util.Scanner;

public class Main {
    static int x1, y1, x2, y2;
    // vr
    public static void initial() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите координаты первой точки (x1,y1): ");
        x1 = sc.nextInt();
        y1 = sc.nextInt();
        System.out.print("Введите координаты второй точки (x2,y2): ");
        x2 = sc.nextInt();
        y2 = sc.nextInt();
    }

    public static void main(String[] args) {
        System.out.println("Проинициализируем первый прямоугольник.");
        initial();
        Rectangle rectangle1 = new Rectangle(x1, y1, x2, y2);
        System.out.println("Проинициализируем второй прямоугольник.");
        initial();
        Rectangle rectangle2 = new Rectangle(x1, y1, x2, y2);

        Rectangle compReq = new Rectangle();
        System.out.println(compReq.getSquare(rectangle1, rectangle2));
    }
}
