public class Runner {
    public static void main(String[ ] args) {
        buy();
    }

    public static void buy(){

        //Тест вложенного класса
        Payment.Hello test = new Payment.Hello();
        test.getHello();

        //Анонимный класс и его тест
        Runnable test2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Example of Anonym Class");
            }
        };
        test2.run();

        //Задаем список покупок
        Product p  = new Product(10, "Хлеб");
        Product p1 = new Product(12,"Молоко");
        Product p2 = new Product(15,"Мороженое");
        Payment payment = new Payment(p);
        payment.getCreator()
                .addProduct(p)
                .addProduct(p1)
                .addProduct(p2);
        payment.getCreator()
                .addProduct(p)
                .addProduct(p2).cancelPurchase()
                .addProduct(p2)
                .addProduct(p2)
                .addProduct(p1)
                .addProduct(p);

        System.out.println(payment);
    }
}
