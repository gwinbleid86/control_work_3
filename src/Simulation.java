import models.Item;
import utilites.FileService;

public class Simulation {
    private static final String NEW_LINE = "%n";
    private static final String HEADER = " ID | Name | Price | Honorary Code | State";

    public void run() {
        Item[] items = FileService.getGoods();


    }

    private void print(Item... items) {
        StringBuilder sb = new StringBuilder(HEADER).append(NEW_LINE);
    }
}
