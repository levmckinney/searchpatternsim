package rendering;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.List;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class RendererTests {
    private Renderer renderer;
    private Image mockImg;
    private Graphics mockGraphics;
    List<Renderable> renderables;

    @Before
    public void setup(){
        renderer = new Renderer(100, 100);
        mockImg = mock(Image.class);
        mockGraphics = mock(Graphics.class);
        when(mockImg.getGraphics()).thenReturn(mockGraphics);
        Color[][] colorRaster = new Color[100][100];
        Renderable mockRenderable = mock(Renderable.class);
        renderables = Collections.singletonList(mockRenderable);
    }

    @Test
    public void testRenderToImage() {
        renderer.renderToImage(renderables, mockImg);
    }
}
