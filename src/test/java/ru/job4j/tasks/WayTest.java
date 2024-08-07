package ru.job4j.tasks;

import org.junit.jupiter.api.Test;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

class WayTest {

    @Test
    void whenWayFrom1To4() {
        int start = 1;
        int finish = 4;
        int[][] connections = {{1, 2}, {3, 4}, {2, 3}};
        int[] expected = {1, 2, 3, 4};
        Way way = new Way(start, finish, connections);
        way.setNode();
        assertThat(way.findWay()).containsExactly(expected);
    }

    @Test
    void whenWayFrom1To9() {
        int start = 1;
        int finish = 9;
        int[][] connections = {{1, 2}, {3, 4}, {2, 3}, {1, 5}, {5, 6}, {6, 7}, {6, 8}, {6, 9}};
        int[] expected = {1, 5, 6, 9};
        Way way = new Way(start, finish, connections);
        way.setNode();
        assertThat(way.findWay()).containsExactly(expected);
    }

    @Test
    void whenWayFrom1To12() {
        int start = 1;
        int finish = 12;
        int[][] connections = {{1, 2}, {3, 4}, {2, 3}, {1, 5}, {5, 6}, {6, 7}, {6, 8}, {6, 9}, {3, 10}, {8, 12}};
        int[] expected = {1, 5, 6, 8, 12};
        Way way = new Way(start, finish, connections);
        way.setNode();
        assertThat(way.findWay()).containsExactly(expected);
    }

    @Test
    void whenWayFrom1To11() {
        int start = 1;
        int finish = 11;
        int[][] connections = {{1, 2}, {3, 4}, {2, 3}, {1, 5}, {5, 6}, {7, 8}, {4, 11}, {7, 9}, {4, 10}, {3, 12}};
        int[] expected = {1, 2, 3, 4, 11};
        Way way = new Way(start, finish, connections);
        way.setNode();
        assertThat(way.findWay()).containsExactly(expected);
    }

    @Test
    void whenWayNotFound() {
        int start = 1;
        int finish = 12;
        int[][] connections = {{1, 2}, {3, 4}, {2, 3}, {1, 5}, {5, 6}, {6, 7}, {6, 8}, {6, 9}, {3, 10}, {11, 12}};
        Way way = new Way(start, finish, connections);
        way.setNode();
        assertThat(way.findWay()).isNull();
    }
}