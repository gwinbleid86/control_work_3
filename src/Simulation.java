import exceptions.CustomException;
import models.Item;
import utilites.FileService;

import java.util.Scanner;

public class Simulation {
    private static final String NEW_LINE = "\n";
    private static final String HEADER = "\n # | ID  | Name ";
    private static final String HEADER_DELIMITER = "---+-----+------";
    private static final String LINE = " %s | %s | %s ";
    private static final String DETAIL_ITEM_HEADER = "\n ID | Name | Price | Honorary Code | State";
    private static final String DETAIL_ITEM_LINE = " %s | %s | %s | %s | %s";

    private static final Item[] items = FileService.getGoods();

    public void run() {
//        setDefaultState();
        action();
    }

//    private void setDefaultState() {
//        for (var item : items) {
//            if (item.getState().contains("stock")) {
//                item.setStateObj(new InStock());
//            }
//            if (item.getState().contains("sale")) {
//                item.setStateObj(new ForSale());
//            }
//            if (item.getState().contains("sold")) {
//                item.setStateObj(new Sold());
//            }
//        }
//    }

    private void action() {
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
                "6 - Вернуться в список товаров;" + NEW_LINE);

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
                    item.giveToTheWinner();
                    actionWithItem(item);
                    break;
                case 4:
                    item.withdraw();
                    actionWithItem(item);
                    break;
                case 5:
                    actionWithItem(item);
                    break;
                case 6:
                    action();
                    break;
                default:
                    print("Неверно введен номер действия. Попробуйте еще раз.");
                    actionWithItem(item);
            }
        } catch (CustomException e) {
            print(e.getMessage());
            actionWithItem(item);
        }
    }

    private int choiceItem(String message) {
        print(message);
        Scanner scanner = new Scanner(System.in);
        int answer = scanner.nextInt();
        return answer;
    }

    private void printItemList(Item... items) {
        StringBuilder sb = new StringBuilder(HEADER).append(NEW_LINE).append(HEADER_DELIMITER).append(NEW_LINE);
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
