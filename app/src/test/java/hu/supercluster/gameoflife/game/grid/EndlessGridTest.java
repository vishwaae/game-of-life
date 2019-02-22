package hu.supercluster.gameoflife.game.grid;

import org.junit.Before;
import org.junit.Test;

import hu.supercluster.gameoflife.game.cell.Cell;
import hu.supercluster.gameoflife.game.cell.CellFactory;
import hu.supercluster.gameoflife.game.cell.SimpleCell;
import hu.supercluster.gameoflife.game.cell.SimpleCellFactory;
import hu.supercluster.gameoflife.test.RobolectricTest;

import static org.fest.assertions.api.Assertions.*;

public class EndlessGridTest extends RobolectricTest {
    EndlessGrid<SimpleCell> grid;
    CellFactory<SimpleCell> cellFactory;

    @Before
    public void setup() {
        cellFactory = new SimpleCellFactory();
        grid = new EndlessGrid<>(3, 6, cellFactory);
    }

    @Test
    public void testAllCellsAreDeadByDefault() {
        for (int j = 0; j < grid.getSizeY(); j++) {
            for (int i = 0; i < grid.getSizeX(); i++) {
                assertThat(grid.getCell(i, j).isAlive()).isEqualTo(false);
            }
        }
    }

    @Test
    public void testGetSizeX() {
        assertThat(grid.getSizeX()).isEqualTo(3);
    }

    @Test
    public void testGetSizeY() {
        assertThat(grid.getSizeY()).isEqualTo(6);
    }

    @Test
    public void testNormalizeX() {
        assertThat(grid.normalizeX(-5)).isEqualTo(1);
    }

    @Test
    public void testNormalizeY() {
        assertThat(grid.normalizeY(7)).isEqualTo(1);
    }

    @Test
    public void testPutCell() {
        SimpleCell cell = cellFactory.create(1, 1);
        cell.setState(Cell.STATE_ALIVE);
        grid.putCell(cell);
        assertThat(grid.getCell(1, 1).isAlive()).isTrue();
    }

    @Test
    public void testEquals() {
        EndlessGrid other = null;

        assertThat(grid).isEqualTo(grid);
        assertThat(grid.equals(other)).isFalse();
        assertThat(grid.equals(this)).isFalse();

        other = new EndlessGrid<>(1, 6, cellFactory);
        assertThat(grid).isNotEqualTo(other);

        other = new EndlessGrid<>(3, 1, cellFactory);
        assertThat(grid).isNotEqualTo(other);

        other = new EndlessGrid<>(3, 6, cellFactory);
        other.getCell(1, 1).setState(Cell.STATE_ALIVE);
        assertThat(grid).isNotEqualTo(other);

        grid.getCell(1, 1).setState(Cell.STATE_ALIVE);
        assertThat(grid).isEqualTo(other);
    }
}
