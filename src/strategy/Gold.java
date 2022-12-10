package strategy;

import models.Item;
import utilites.CodeGenerator;

public class Gold implements Strategy {
    private static final CodeGenerator GENERATOR = new CodeGenerator();

    @Override
    public void generateHonoraryCode(Item item) {
        item.setHonoraryCode(GENERATOR.makeCode("Gold-" + item.getId()));
    }
}
