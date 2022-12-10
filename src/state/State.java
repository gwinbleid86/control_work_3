package state;

import exceptions.CustomException;
import models.Item;
import strategy.Bronze;
import strategy.Gold;
import strategy.Silver;
import strategy.Strategy;

public enum State {
    IN_STOCK("In stock") {
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

    },
    FOR_SALE("For sale") {
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
    },
    SOLD("Sold") {
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
    };

    private String value;

    State(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public abstract void startSale(Item item) throws CustomException;

    public abstract void ricePrice(Item item) throws CustomException;

    public abstract void withdraw(Item item) throws CustomException;

    public abstract void giveToTheWinner(Item item) throws CustomException;
}
