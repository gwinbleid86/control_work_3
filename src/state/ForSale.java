package state;

import exceptions.CustomException;
import models.Item;

public class ForSale implements State {
    @Override
    public void startSale(Item item) throws CustomException {
        throw new CustomException("Этот товар уже учавствует в торгах.");
    }

    @Override
    public void ricePrice(Item item) throws CustomException {
        item.setPrice(item.getPrice() + 100);
        System.out.printf("Цена на товар %s возросла.%n", item.getName());
    }

    @Override
    public void withdraw(Item item) throws CustomException {
        if (item.getPrice() == 0) {
            item.setStateObj(new InStock());
            System.out.printf("Товар %s успешно вернулся на склад.%n", item.getName());
        } else {
            throw new CustomException("Нельзя вернуть на склад товар, так как он уже в резерве.");
        }
    }

    @Override
    public void giveToTheWinner(Item item) throws CustomException {
        if (item.getPrice() > 0) {
            item.setStateObj(new Sold());
            System.out.printf("Товар %s успешно передан победителю.%n", item.getName());
        } else {
            throw new CustomException("Нельзя отдать товар безплатно!");
        }
    }
}
