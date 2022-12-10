package state;

import exceptions.CustomException;
import models.Item;

public interface State {
    void startSale(Item item) throws CustomException;

    void ricePrice(Item item) throws CustomException;

    void withdraw(Item item) throws CustomException;

    void giveToTheWinner(Item item) throws CustomException;
}
