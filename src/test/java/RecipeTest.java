import edu.bsu.cs222.menubuilder.Recipe;
import edu.bsu.cs222.menubuilder.WebRecipe;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class RecipeTest {
    Recipe recipe = new WebRecipe("https://www.yummly.com/recipe/Eggplant-Caponata-Recipe-Leite_s-Culinaria-40936?prm-v1");

    @Test
    private void testWebRecipe() {
    }
}
