import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("На сколько человек делить счет?");
        int quantity = askQuantity(br);

        System.out.println("Введите пожалуйта позиции счета и их стоймость.");
        ArrayList<Goods> goods = new ArrayList<>();
        goods = receiveGoods(br, goods);

        br.close();

        Goods.calculate(goods, quantity);
    }

    private static int askQuantity(BufferedReader br) throws IOException {

        int quantity;
        while (true) {
            try
            {
                quantity = Integer.parseInt(br.readLine());
                if (quantity < 1) {
                    System.out.println("Некорректное значение. Введите пожалуйста значение больше 1");
                } else if (quantity == 1) {
                    System.out.println("На одного человека нечего делить. Введите пожалуйста значение больше 1"); // Считаю это значение должно быть значением выхода
                } else
                    return quantity;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Вы ввели некорректные данные, введите пожалуйсто целое число больше 1");
            }
        }
    }

    private static ArrayList<Goods> receiveGoods(BufferedReader br, ArrayList<Goods> goods) throws IOException {

        while(true){
            goods.add(requestProducts(br));
            int goodsSize = goods.size();
            Double productPrice = goods.get(goodsSize - 1).productPrice;
            System.out.println(String.format("Товар '%s' с ценой '%.2f' успешно добавлен.", goods.get(goodsSize - 1).productName, productPrice));
            System.out.println("Вы хотите добавить еще один товар? Введите 'Завершить' для завершения воода товаров.");
            String endAsk = br.readLine();
            if (endAsk.equalsIgnoreCase("завершить"))
                break;
        }
        return goods;
    }

    private static Goods requestProducts(BufferedReader br) throws IOException {

            String productName;
            Double productPrice;
            System.out.println("Наименование товара:");

            while (true) {
                    productName = br.readLine();
                    break;
            }
            System.out.println("Стоймость товара:");

            while (true) {
                try
                {
                    productPrice = Double.parseDouble(br.readLine());
                    if (productPrice >= 0) {
                        return new Goods(productName, productPrice);
                    } else {
                        System.out.println("Введите пожалуйста цену как положительное число");
                    }
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Вы ввели некорректные данные. Введите пожалуйста цену как положительное число.");
                }
            }
    }
}
