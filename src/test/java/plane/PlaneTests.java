package plane;

import org.junit.*;
import plane.Plane;

import java.util.HashSet;


public class PlaneTests {
    private Plane plane;

    @Before
    public void setup(){
        plane = new Plane(100, 100, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOutOfRangeSet() {
        plane.setCellsAt(100, 101, new HashSet<>());
    }
}
