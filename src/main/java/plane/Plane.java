package plane;

import rendering.Renderable;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Plane implements Renderable {
    private int width;
    private int height;
    private int resolution;
    private Set<Cell>[][] grid;

    public Plane(int width, int height, int resolution) {
        this.width = width;
        this.height = height;
        this.resolution = resolution;
        grid = new Set[width*resolution][height*resolution];
        for(int x = 0; x < getXSize(); x++) {
            for(int y = 0; y < getYSize(); y++) {
                grid[x][y] = new HashSet<>();
            }
        }
    }

    public Set[][] getGrid() {
        return grid;
    }

    public int getXSize() {
        return width * resolution;
    }

    public int getYSize() {
        return height * resolution;
    }

    public Set<Cell> getCellsAt(int x, int y) {
        if(x >= getXSize() || y >= getYSize()) {
            return null;
        }
        return this.grid[x][y];
    }

    public void setCellsAt(int x, int y, Set<Cell> cell) {
        if(x >= getXSize() || y >= getYSize()) {
            throw new IndexOutOfBoundsException(String.format("y value %d, x value %d is not withing plane with" +
                    " dimensions X %d and Y %d", x, y, getXSize() - 1, getYSize() - 1));
        }
        this.grid[x][y] = cell;
    }

    public void addToCellsAt(int x, int y, Cell cell) {
        if(x >= getXSize() || y >= getYSize()) {
            throw new IndexOutOfBoundsException(String.format("y value %d, x value %d is not withing plane with" +
                    " dimensions X %d and Y %d", x, y, getXSize() - 1, getYSize() - 1));
        }
        this.grid[x][y].add(cell);
    }

    public int getTopLeftX() {
        return 0;
    }

    public int getTopLeftY() {
        return 0;
    }

    public Set<Color>[][] getColorRaster() {
        Set[][] colorRaster = new Set[getXSize()][getYSize()];
        for (int i = 0; i < getXSize(); i++) {
            for (int j = 0; j < getYSize(); j++) {
                colorRaster[i][j] = getCellsAt(i,j)
                                    .stream()
                                    .map(Cell::getColor)
                                    .collect(Collectors.toSet());
            }
        }
        return colorRaster;
    }
}
