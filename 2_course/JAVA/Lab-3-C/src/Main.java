public class Main {
    public static void main(String[] args) {
        Line[] line = new Line[4];
        line[0] = new Line(-2.2,2.2,2.2,-2.2);
        line[1] = new Line(8.6,-4.1,6.2,5.6);
        line[2] = new Line(-9.4,3.2,2.1,-5.5);
        line[3] = new Line(7.5,-6.5,-2.3,4.3);

        for (int i = 0; i < 4; i++) {
            /*с осью Ox*/
            System.out.print("Точка пересечения линии " + (i + 1) + " с осью Ox: ");
            double xIntersect = -(line[i].getX1() * line[i].getY2() - line[i].getX2() * line[i].getY1()) / (line[i].getY1() - line[i].getY2());
            System.out.print("{" + xIntersect + ";0}");
            System.out.println();
            /*с осью Oy*/
            System.out.print("Точка пересечения линии " + (i + 1) + " с осью Oy: ");
            double yIntersect = -(line[i].getX1() * line[i].getY2() - line[i].getX2() * line[i].getY1()) / (line[i].getX2() - line[i].getX1());
            System.out.print("{0;" + yIntersect + "}");
            System.out.println();
        }

        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = i+1; j < 4; j++) {
                double a1 = line[i].getY1() - line[i].getY2();
                double a2 = line[j].getY1() - line[j].getY2();
                double b1 = line[i].getX2() - line[i].getX1();
                double b2 = line[j].getX2() - line[j].getX1();
                double d = a1*b2 - a2*b1;
                if (d != 0) {
                    double c1 = line[i].getY2() * line[i].getX1() - line[i].getX2() * line[i].getY1();
                    double c2 = line[j].getY2() * line[j].getX1() - line[j].getX2() * line[j].getY1();
                    double xInter = (b1*c2 - b2*c1) / d;
                    double yInter = (a2*c1 - a1*c2) / d;
                    System.out.print("Точка пересечения прямой " + (i+1) + " и " + (j+1) + " : ");
                    System.out.println("{" + xInter + ";" + yInter + "}");
                }
                else {
                    System.out.println("Прямые " + (i+1) + " и " + (j+1) + "параллельны");
                    counter++;
                }

            }
        }
        if (counter == 0) {
            System.out.println("Нет пересекающихся прямых");
        }



    }
}
