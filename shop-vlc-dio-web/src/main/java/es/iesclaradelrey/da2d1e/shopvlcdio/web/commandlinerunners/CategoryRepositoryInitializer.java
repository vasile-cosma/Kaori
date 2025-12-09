package es.iesclaradelrey.da2d1e.shopvlcdio.web.commandlinerunners;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import org.springframework.boot.CommandLineRunner;

public class CategoryRepositoryInitializer implements CommandLineRunner {
    private static final int CATEGORY_COUNT = 5;
    private static final String CATEGORY_GREEN = "Té verde";
    private static final String CATEGORY_IMG_GREEN = "/images/organic-3-green.png";
    private static final String CATEGORY_YELLOW = "Té amarillo";
    private static final String CATEGORY_WHITE = "Té blanco";
    private static final String CATEGORY_RED = "Té rojo";
    private static final String CATEGORY_BLACK = "Té negro";
    private final CategoryService categoryService;

    public CategoryRepositoryInitializer(CategoryService categoryService) { this.categoryService = categoryService;}

    @Override
    public void run(String... args) throws Exception {
        Category category = Category.builder()
                .ID((long) i + 1)
                .name(CATEGORY_GREEN)
                .description()
                .image()

    }
}
