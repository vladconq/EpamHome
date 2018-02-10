public class Rectangle {
    int x1, y1, x2, y2, sqA, sqB;

    public Rectangle() {

    }

    public Rectangle(int x1, int y1, int x2, int y2) {
        if (x1 <= x2) {
            this.x1 = x1;
            this.x2 = x2;
        } else {
            this.x1 = x2;
            this.x2 = x1;
        }

        if (y1 <= y2) {
            this.y1 = y1;
            this.y2 = y2;
        } else {
            this.y1 = y2;
            this.y2 = y1;
        }
    }

    public double getSquare(Rectangle rec1, Rectangle rec2) {

        boolean flagX = false;
        boolean flagY = false;

        // Проверка пересечения
        if (rec2.x1 >= rec1.x2 || rec1.x1 >= rec2.x2) {
            System.out.println("Прямоугольники не пересекаются!");
            System.exit(0);
        } else {
            // Координата X
            // внешний и внутренний случаи
            if (((rec1.x1 <= rec2.x1) && (rec1.x2 >= rec2.x2) && (flagX = true)) || ((rec2.x1 <= rec1.x1) && (rec2.x2 >= rec1.x2))) {
                if (flagX)
                    sqA = Math.abs(rec2.x2 - rec2.x1);
                else sqA = Math.abs(rec1.x2 - rec1.x1);
                flagX = false;
            }
            // промежуточный случай слева
            else if (((rec1.x1 >= rec2.x1) && (rec1.x1 <= rec2.x2) && (rec1.x2 >= rec2.x2) && (flagX = true) || ((rec2.x1 >= rec1.x1) && (rec2.x1 <= rec1.x2) && (rec2.x2 >= rec1.x2)))) {
                if (flagX)
                    sqA = Math.abs(rec2.x2 - rec1.x1);
                else sqA = Math.abs(rec1.x2 - rec2.x1);
                flagX = false;
            }
            // промежуточный случай справа
            else if (((rec2.x1 >= rec1.x1) && (rec2.x1 <= rec1.x2) && (rec2.x2 >= rec1.x2) && (flagX = true)) || ((rec1.x1 >= rec2.x1) && (rec1.x1 <= rec2.x2) && (rec1.x2 >= rec2.x2))) {
                if (flagX)
                    sqA = Math.abs(rec1.x2 - rec2.x1);
                else sqA = Math.abs(rec2.x2 - rec1.x1);
            }

            // Координата Y
            // внешний и внутренний случаи
            if (((rec1.y1 <= rec2.y1) && (rec1.y2 >= rec2.y2) && (flagY = true)) || ((rec2.y1 <= rec1.y1) && (rec2.y2 >= rec1.y2))) {
                if (flagY)
                    sqB = Math.abs(rec2.y2 - rec2.y1);
                else sqB = Math.abs(rec1.y2 - rec1.y1);
                flagY = false;
            }
            // промежуточный случай сверху
            else if (((rec1.y1 >= rec2.y1) && (rec1.y1 <= rec2.y2) && (rec1.y2 >= rec2.y2) && (flagY = true) || ((rec2.y1 >= rec1.y1) && (rec2.y1 <= rec1.y2) && (rec2.y2 >= rec1.y2)))) {
                if (flagY)
                    sqB = Math.abs(rec2.y2 - rec1.y1);
                else sqB = Math.abs(rec1.y2 - rec2.y1);
                flagY = false;
            }
            // промежуточный случай снизу
            else if (((rec2.y1 >= rec1.y1) && (rec2.y1 <= rec1.y2) && (rec2.y2 >= rec1.y2) && (flagY = true)) || ((rec1.y1 >= rec2.y1) && (rec1.y1 <= rec2.y2) && (rec1.y2 >= rec2.y2))) {
                if (flagY)
                    sqB = Math.abs(rec1.y2 - rec2.y1);
                else sqB = Math.abs(rec2.x2 - rec1.y1);
            }


        }
//        System.out.println(sqA);
//        System.out.println(sqB);
        return sqA * sqB;
    }
}