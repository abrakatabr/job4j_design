package ru.job4j.iterator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.*;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 2, 4))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIf() {
        input.add(6);
        input.add(8);
        input.add(9);
        Predicate<Integer> filter = (t) -> t % 2 == 0;
        ListUtils.removeIf(input, filter);
        assertThat(input).hasSize(3).containsExactly(1, 3, 9);
    }

    @Test
    void whenRemoveAll() {
        input.add(6);
        input.add(8);
        input.add(9);
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 3, 8, 11, 12, 15));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(2).containsExactly(6, 9);
    }

    @Test
    void whenReplaceIF() {
        input.add(6);
        input.add(8);
        input.add(9);
        Predicate<Integer> filter = (t) -> t % 3 == 0;
        ListUtils.replaceIf(input, filter, 1);
        assertThat(input).hasSize(5).containsExactly(1, 1, 1, 8, 1);
    }
}