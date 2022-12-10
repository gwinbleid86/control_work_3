package models;

import exceptions.CustomException;
import state.State;

public class Item {
    private int id;
    private String name;
    private int price;
    private String honoraryCode;
    private State state;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getHonoraryCode() {
        return honoraryCode;
    }

    public void setHonoraryCode(String honoraryCode) {
        this.honoraryCode = honoraryCode;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void startSale() throws CustomException {
        state.startSale(this);
    }

    public void ricePrice() throws CustomException {
        state.ricePrice(this);
    }

    public void withdraw() throws CustomException {
        state.withdraw(this);
    }

    public void giveToTheWinner() throws CustomException {
        state.giveToTheWinner(this);
    }
}
