import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("На сколько человек делить счет?");
        int Quantity = AskQuantity(scanner);

        System.out.println("Введите пожалуйта позиции счета и их стоймость.");
        ArrayList<Product> Goods = new ArrayList<>();
        Goods = ReceiveGoods(scanner, Goods);

        scanner.close();

        Calculate(Goods, Quantity);
    }

    private static class Product{
        private String ProductName;
        private Double ProductPrice;

        Product(String ProductName, Double ProductPrice){
            this.ProductName = ProductName;
            this.ProductPrice = ProductPrice;
        }
    }

    private static int AskQuantity(Scanner scanner){

        int Quantity;
        while (true) {
            if (scanner.hasNextInt()) {
                Quantity = scanner.nextInt();
                if (Quantity < 1) {
                    System.out.println("Некорректное значение. Введите пожалуйста значение больше 1");
                } else if (Quantity == 1) {
                    System.out.println("На одного человека нечего делить. Введите пожалуйста значение больше 1"); // Считаю это значение должно быть значением выхода
                    scanner.nextLine();
                } else
                    return Quantity;
            } else {
                System.out.println("Вы ввели некорректные данные, введите пожалуйсто целое число больше 1");
                scanner.next();
            }
        }
    }

    private static ArrayList<Product> ReceiveGoods(Scanner scanner, ArrayList<Product> Goods){

        while(true){
            Goods.add(RequestGoods(scanner));
            int GoodsSize = Goods.size();
            Double ProductPrice = Goods.get(GoodsSize - 1).ProductPrice;

            System.out.println(String.format("Товар '%s' с ценой '%.2f' успешно добавлен.", Goods.get(GoodsSize - 1).ProductName, ProductPrice));
            System.out.println("Вы хотите добавить еще один товар? Введите 'Завершить' для завершения воода товаров.");
            String EndAsk = scanner.next();
            if (EndAsk.equalsIgnoreCase("завершить"))
                break;
        }
        return Goods;
    }

    private static Product RequestGoods(Scanner scanner){

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String ProductName;
            Double ProductPrice;
            System.out.println("Наименование товара:");

            while (true) {
                try {
                    ProductName = br.readLine();
                    break;
                } catch (IOException e) {
                    System.out.println("Ошибка считывания данных! Повториите ввод наименования товара еще раз");
                    // заглушка на баг репорт. В идеале надо сделать способ выхода из бесконечно цикла
                }
            }

            System.out.println("Стоймость товара:");

            while (true) {
                if (scanner.hasNextDouble()) {
                    ProductPrice = scanner.nextDouble();
                    if (ProductPrice >= 0) {
                        return new Product(ProductName, ProductPrice);

                    } else {
                        System.out.println("Введите пожалуйста цену как положительное число");
                    }
                } else {
                    System.out.println("Вы ввели некорректные данные. Введите пожалуйста цену как положительное число.");
                    scanner.next();
                }
            }
    }

    private static void Calculate(ArrayList<Product> Goods, int Quantity){
        int i = 1;
        double Sum = 0;
        for (Product TheProduct : Goods) {
            System.out.println(String.format("Товар %d: %s цена: %.2f", i, TheProduct.ProductName, TheProduct.ProductPrice));
            Sum += TheProduct.ProductPrice;
            i++;
        }

        Double SumPerPeople = Sum / Quantity;
        String TextRub = TakeEnding(SumPerPeople.intValue());
        System.out.println(String.format("Итого с каждого человека по %.2f %s.", SumPerPeople,  TextRub));
    }

    private static String TakeEnding (int SumPerPeopleInt){
        int Remainder = SumPerPeopleInt % 10;

        if (Remainder == 1)
            return "рубль";
        else if (Remainder > 1 && Remainder < 5)
            return "рубля";
        else
            return "рублей";
    }
}
