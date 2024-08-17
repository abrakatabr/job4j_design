package ru.job4j.sort;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MergeTest {

    @Test
    void whenSortedThenOk() {
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        assertThat(Merge.mergesort(array)).containsExactly(-13, 2, 3, 4, 4, 6, 8, 10);
    }

    @Test
    void whenArrayLength1() {
        int[] array = {5};
        int[] expected = {5};
        assertThat(Merge.mergesort(array)).containsExactly(expected);
    }

    @Test
    void whenArrayLengthOdd() {
        int[] array = {1, 6, -8, 3, 10};
        int[] expected = {-8, 1, 3, 6, 10};
        assertThat(Merge.mergesort(array)).containsExactly(expected);
    }

    @Test
    void whenArrayLengthIsZero() {
        int[] array = {};
        int[] expected = {};
        assertThat(Merge.mergesort(array)).containsExactly(expected);
    }
}