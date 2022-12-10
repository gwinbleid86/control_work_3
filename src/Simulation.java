import exceptions.CustomException;
import models.Item;
import utilites.FileService;

import java.util.Scanner;

public class Simulation {
    private static final String NEW_LINE = "%n";
    private static final String HEADER = " # | ID | Name ";
    private static final String LINE = " %s | %s | %s ";
    private static final String DETAIL_ITEM_HEADER = " ID | Name | Price | Honorary Code | State";
    private static final String DETAIL_ITEM_LINE = " %s | %s | %s | %s | %s";

    private static final Item[] items = FileService.getGoods();

    public void run() {
        printItemList(items);

        int choice = choiceItem("Выберите товар (введите его номер): ");

        Item item = items[choice - 1];

        actionWithItem(item);
    }

    private void actionWithItem(Item item) {
        printItem(item);

        print("1 - Выставить на аукцион;\n" +
                "2 - Поднять цену;\n" +
                "3 - Выдать победителю;\n" +
                "4 - Снять с торгов;\n" +
                "5 - Отобразить информацию о товаре;\n" +
                "6 - Вернуться в список товаров;");

        int answer = choiceItem("что вы хотите сделать (введите номер): ");

        try {
            switch (answer) {
                case 1:
                    item.startSale();
                    actionWithItem(item);
                    break;
                case 2:
                    item.ricePrice();
                    actionWithItem(item);
                    break;
                case 3:
                    item.withdraw();
                    actionWithItem(item);
                    break;
                case 4:
                    item.giveToTheWinner();
                    actionWithItem(item);
                    break;
                case 5:
                    printItem(item);
                    actionWithItem(item);
                    break;
                case 6:
                    run();
                    break;
                default:
                    print("Неверно введен номер действия. Попробуйте еще раз.");
                    actionWithItem(item);
            }
        } catch (CustomException e) {
            print(e.getMessage());
        }
    }

    private int choiceItem(String message) {
        print(message);
        int answer = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            answer = Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return answer;
    }

    private void printItemList(Item... items) {
        StringBuilder sb = new StringBuilder(HEADER).append(NEW_LINE);
        int count = 1;
        for (var item : items) {
            sb.append(String.format(LINE, count, item.getId(), item.getName()));
            sb.append(NEW_LINE);
            count++;
        }
        print(sb + NEW_LINE);
    }

    private void printItem(Item item) {
        StringBuilder sb = new StringBuilder(DETAIL_ITEM_HEADER).append(NEW_LINE);
        sb.append(String.format(DETAIL_ITEM_LINE, item.getId(), item.getName(), item.getPrice(), item.getHonoraryCode(), item.getState()));
        sb.append(NEW_LINE);

        print(sb + NEW_LINE);
    }

    private void print(String message) {
        System.out.print(message);
    }
}
