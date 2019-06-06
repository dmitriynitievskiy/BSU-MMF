import java.util.ArrayList;

public class Runner {

    public static void main(String[] args) {
        function();
    }

    public static void function() {
        Wheel[] wheels = {
                new Wheel(15),
                new Wheel(15),
                new Wheel(15),
                new Wheel(15)
        };
        Motor motor = new Motor("1234567890", 1);
        Car car = new Car("A6", 12, "Audi", motor, wheels);
        car.ride();
        car.coutBrand();
        System.out.println(car);
    }
}
