package strategy;

import models.Item;
import utilites.CodeGenerator;

public class Bronze implements Strategy {
    private static final CodeGenerator GENERATOR = new CodeGenerator();

    @Override
    public void generateHonoraryCode(Item item) {
        item.setHonoraryCode(GENERATOR.makeCode("Bronze-" + item.getId()));
    }

}
