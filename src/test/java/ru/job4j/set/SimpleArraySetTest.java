package ru.job4j.set;

import org.junit.jupiter.api.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.*;

class SimpleArraySetTest {

    @Test
    void whenAddNonNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddSomeElementsNonNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.contains(1)).isFalse();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
        assertThat(set.contains(2)).isFalse();
        assertThat(set.add(2)).isTrue();
        assertThat(set.contains(2)).isTrue();
        assertThat(set.add(2)).isFalse();
        assertThat(set.contains(3)).isFalse();
        assertThat(set.add(3)).isTrue();
        assertThat(set.contains(3)).isTrue();
        assertThat(set.add(3)).isFalse();
        assertThat(set).hasSize(3).containsExactlyInAnyOrder(1, 2, 3);
    }

    @Test
    void whenAddNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
        assertThat(set).hasSize(1);
    }

    @Test
    void whenContainsNotNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        set.add(1);
        set.add(3);
        set.add(5);
        assertThat(set.contains(3)).isTrue();
    }

    @Test
    void whenNotContains() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        set.add(1);
        set.add(3);
        set.add(5);
        assertThat(set.contains(2)).isFalse();
    }

    @Test
    void whenContainsNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        set.add(1);
        set.add(null);
        set.add(5);
        assertThat(set.contains(null)).isTrue();
    }
}