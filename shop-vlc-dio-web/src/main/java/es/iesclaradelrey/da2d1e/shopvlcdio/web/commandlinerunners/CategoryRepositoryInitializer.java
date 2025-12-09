package es.iesclaradelrey.da2d1e.shopvlcdio.web.commandlinerunners;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;
import java.util.List;

public class CategoryRepositoryInitializer implements CommandLineRunner {
    private static final int CATEGORY_COUNT = 6;
    private static final String CATEGORY_GREEN = "Té verde";
    private static final String CATEGORY_YELLOW = "Té amarillo";
    private static final String CATEGORY_WHITE = "Té blanco";
    private static final String CATEGORY_OOLONG = "Té oolong";
    private static final String CATEGORY_RED = "Té rojo";
    private static final String CATEGORY_BLACK = "Té negro";
    private static final String CATEGORY_IMG_GREEN = "/images/categories/green-tea.png";
    private static final String CATEGORY_IMG_YELLOW = null;
    private static final String CATEGORY_IMG_WHITE = "/images/categories/white-tea.png";
    private static final String CATEGORY_IMG_OOLONG = "/images/categories/oolong-tea.png";
    private static final String CATEGORY_IMG_RED = "/images/categories/red-tea.png";
    private static final String CATEGORY_IMG_BLACK = "/images/categories/black-tea.png";
    private static final String CATEGORY_DESC_GREEN = "Fresco y vegetal, se elabora con hojas mínimamente oxidadas para preservar su color verde y sus matices herbales. Es conocido por su suavidad y su perfil ligero.";
    private static final String CATEGORY_DESC_YELLOW = "Raro y delicado, se somete a una ligera fermentación controlada que suaviza su sabor. Ofrece notas dulces y un aroma cálido y aterciopelado.";
    private static final String CATEGORY_DESC_WHITE = "El menos procesado de todos. Sus brotes tiernos dan una infusión sutil, dulce y floral, con una textura muy suave y refinada.";
    private static final String CATEGORY_DESC_OOLONG = "Semioxidado, combina características del té verde y negro. Presenta sabores complejos que van de florales a tostados, con un aroma rico y elegante.";
    private static final String CATEGORY_DESC_RED = "Té fermentado tradicional de China con cuerpo terroso y profundo. Su maduración aporta un sabor cálido, robusto y ligeramente dulce.";
    private static final String CATEGORY_DESC_BLACK = "Totalmente oxidado, ofrece una taza intensa con notas malteadas, especiadas o frutales. Es el té más robusto y de sabor más definido.";
    private static final List<String> CATEGORY_NAMES = Arrays.asList(CATEGORY_GREEN, CATEGORY_YELLOW, CATEGORY_WHITE, CATEGORY_OOLONG, CATEGORY_RED, CATEGORY_BLACK);
    private static final List<String> CATEGORY_IMAGES = Arrays.asList(CATEGORY_IMG_GREEN, CATEGORY_IMG_YELLOW, CATEGORY_IMG_WHITE, CATEGORY_OOLONG, CATEGORY_IMG_RED, CATEGORY_IMG_BLACK);
    private static final List<String> CATEGORY_DESCRIPTIONS = Arrays.asList(CATEGORY_DESC_GREEN, CATEGORY_DESC_YELLOW, CATEGORY_DESC_WHITE, CATEGORY_DESC_OOLONG, CATEGORY_DESC_RED, CATEGORY_DESC_BLACK);

    private final CategoryService categoryService;


    public CategoryRepositoryInitializer(CategoryService categoryService) { this.categoryService = categoryService;}

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < CATEGORY_COUNT; i++) {
            Category category = Category.builder()
                    .ID((long)i+1)
                    .name(CATEGORY_NAMES.get(i))
                    .description(CATEGORY_DESCRIPTIONS.get(i))
                    .image(CATEGORY_IMAGES.get(i))
                    .build();

            categoryService.save(category);
        }
    }
    }


