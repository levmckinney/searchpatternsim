import plane.Plane;
import rendering.Renderable;
import rendering.Renderer;

import java.io.File;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Plane plane = new Plane(100, 200, 1);
        Renderer renderer = new Renderer(100, 200);
        File outputFile = new File("img.png");
        renderer.renderToFile(
                Collections.singletonList((Renderable) plane),
                outputFile);
    }
}
