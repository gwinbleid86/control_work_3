package state;

import exceptions.CustomException;
import models.Item;

public class InStock implements State {
    @Override
    public void startSale(Item item) throws CustomException {
        item.setStateObj(new ForSale());
        item.setState("For sale");
        System.out.println("Торги начались!\n");
    }

    @Override
    public void ricePrice(Item item) throws CustomException {
        throw new CustomException("Нельзя поднять цену так как товар еще не учавствует в торгах.\n");
    }

    @Override
    public void withdraw(Item item) throws CustomException {
        throw new CustomException("Нельзя снять с торгов товар, который в них не учавствует.\n");
    }

    @Override
    public void giveToTheWinner(Item item) throws CustomException {
        throw new CustomException("Нельзя отдать товар напрямую со склада.\n");
    }
}
