package state;

import exceptions.CustomException;
import models.Item;

public class Sold implements State {
    @Override
    public void startSale(Item item) throws CustomException {
        throw new CustomException("Нельзя выставить на аукцион, так как товар уже продан.\n");
    }

    @Override
    public void ricePrice(Item item) throws CustomException {
        throw new CustomException("Нельзя поднять цену, так как товар уже продан.\n");
    }

    @Override
    public void withdraw(Item item) throws CustomException {
        throw new CustomException("Нельзя вернуть на склад, так как товар уже продан.\n");
    }

    @Override
    public void giveToTheWinner(Item item) throws CustomException {
        throw new CustomException("Нельзя отдать товар кому-то другому, так как товар уже продан.\n");
    }
}
