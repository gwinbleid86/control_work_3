package state;

import exceptions.CustomException;
import models.Item;
import strategy.Bronze;
import strategy.Gold;
import strategy.Silver;
import strategy.Strategy;

public class ForSale implements State {
    @Override
    public void startSale(Item item) throws CustomException {
        throw new CustomException("Этот товар уже учавствует в торгах.\n");
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
            item.setState("In stock");
            System.out.printf("Товар %s успешно вернулся на склад.%n", item.getName());
        } else {
            throw new CustomException("Нельзя вернуть на склад товар, так как он уже в резерве.");
        }
    }

    @Override
    public void giveToTheWinner(Item item) throws CustomException {
        if (item.getPrice() > 0) {
            makeHonoraryCode(item);
            item.setStateObj(new Sold());
            item.setState("Sold");
            System.out.printf("Товар %s успешно передан победителю.%n", item.getName());
        } else {
            throw new CustomException("Нельзя отдать товар безплатно!\n");
        }
    }

    private void makeHonoraryCode(Item item) {
        Strategy strategy = null;
        if (item.getPrice() >= 1000) {
            strategy = new Gold();
        }
        if (item.getPrice() >= 500 && item.getPrice() < 1000) {
            strategy = new Silver();
        }
        if (item.getPrice() < 500) {
            strategy = new Bronze();
        }
        strategy.generateHonoraryCode(item);
    }
}
