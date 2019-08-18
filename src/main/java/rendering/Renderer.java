package rendering;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Renderer {
    private int width;
    private int height;

    public Renderer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void renderToFile(List<Renderable> renderables, File outputFile) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        renderToImage(renderables, image);
        try {
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    void renderToImage(List<Renderable> renderables, Image image) {
        Graphics graphics = image.getGraphics();
        for (Renderable r: renderables) {
            Set<Color>[][] colorRaster = r.getColorRaster();
            graphics.translate(r.getTopLeftX(), r.getTopLeftY());
            for (int x = 0; x < colorRaster.length; x++) {
                for (int y = 0; y < colorRaster[x].length; y++) {
                    if (colorRaster[x][y] != null) {
                        Color average = getAverageColor(colorRaster[x][y]);
                        graphics.setColor(average);
                    } else {
                        graphics.setColor(Color.BLACK);
                    }
                    graphics.drawRect(x, y, 1, 1);
                }
            }
        }
    }

    private static Color getAverageColor(Set<Color> colors) {
        if(colors.isEmpty()) {
            return Color.BLACK;
        }

        int totalR = 0,
            totalG = 0,
            totalB = 0,
            totalA = 0,
            n = colors.size();

        for (Color colorItem: colors) {
            totalA += colorItem.getAlpha();
            totalR += colorItem.getRed();
            totalG += colorItem.getGreen();
            totalB += colorItem.getBlue();
        }

        return new Color(totalR/n, totalG/n, totalB/n, totalA/n);
    }
}
