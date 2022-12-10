package models;

import exceptions.CustomException;
import state.State;

public class Item {
    private int id;
    private String name;
    private int price;
    private String honoraryCode;
    private String state;
    private transient State stateObj;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public State getStateObj() {
        return stateObj;
    }

    public void setStateObj(State stateObj) {
        this.stateObj = stateObj;
    }

    public void startSale() throws CustomException {
        stateObj.startSale(this);
    }

    public void ricePrice() throws CustomException {
        stateObj.ricePrice(this);
    }

    public void withdraw() throws CustomException {
        stateObj.withdraw(this);
    }

    public void giveToTheWinner() throws CustomException {
        stateObj.giveToTheWinner(this);
    }
}
