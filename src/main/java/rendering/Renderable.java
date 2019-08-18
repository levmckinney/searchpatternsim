package rendering;

import java.awt.*;
import java.util.Set;

public interface Renderable {
    int getTopLeftX();
    int getTopLeftY();
    Set<Color>[][] getColorRaster();
}
