package ru.job4j.collection.binarytree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BinarySearchTreeTest {
    @Test
    void whenAddToEmptyTreeThenListContainsOneElement() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertThat(tree.put("first")).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(1)
                .containsExactly("first");
    }

    @Test
    void whenAddTwoToEmptyTreeThenListContainsTwoElement() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertThat(tree.put("first")).isTrue();
        assertThat(tree.put("second")).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(2)
                .containsExactly("first", "second");
    }

    @Test
    void whenAddElementThenContainElementOk() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.put("first");
        tree.put("second");
        tree.put("three");
        assertThat(tree.contains("second")).isTrue();
        assertThat(tree.contains("four")).isFalse();
    }

    @Test
    void whenAddMaximumNotEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 8, 7}) {
            tree.put(element);
        }
        assertThat(tree.maximum()).isEqualTo(8);
    }

    @Test
    void whenAddMaximumIsEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.put(element);
        }
        assertThat(tree.maximum()).isEqualTo(7);
    }

    @Test
    void whenAddMinimumIsEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.minimum()).isEqualTo(1);
    }

    @Test
    void whenAddMinimumIsNotEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7 }) {
            tree.put(element);
        }
        assertThat(tree.minimum()).isEqualTo(2);
    }

    @Test
    void whenSymmetricalOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inSymmetricalOrder()).hasSize(7)
                .containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenPreOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inPreOrder()).hasSize(7)
                .containsExactly(4, 2, 1, 3, 6, 5, 7);
    }

    @Test
    void whenPostOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inPostOrder()).hasSize(7)
                .containsExactly(1, 3, 2, 5, 7, 6, 4);
    }

    @Test
    void whenRemoveWithoutChildren() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        int[] array = new int[]{2, 1, 10, 6, 14, 4, 8, 12, 16, 11, 9, 13, 15, 17, 3, 5, 7};
        for (int i : array) {
            tree.put(i);
        }
        assertThat(tree.remove(5)).isTrue();
        assertThat(tree.contains(5)).isFalse();
        assertThat(tree.inSymmetricalOrder()).hasSize(16)
                .containsExactly(1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12, 13, 14,
                        15, 16, 17);
    }

    @Test
    void whenRemoveWith1Child() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        int[] array = new int[]{2, 1, 10, 6, 14, 4, 8, 12, 16, 11, 9, 13, 15, 17, 3, 5, 7, 0};
        for (int i : array) {
            tree.put(i);
        }
        assertThat(tree.remove(1)).isTrue();
        assertThat(tree.contains(1)).isFalse();
        assertThat(tree.inSymmetricalOrder()).hasSize(17)
                .containsExactly(0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
                        15, 16, 17);
    }

    @Test
    void whenRemoveWith2Child() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        int[] array = new int[]{2, 1, 10, 6, 14, 4, 8, 12, 16, 11, 9, 13, 15, 17, 3, 5, 7};
        for (int i : array) {
            tree.put(i);
        }
        assertThat(tree.remove(6)).isTrue();
        assertThat(tree.contains(6)).isFalse();
        assertThat(tree.inSymmetricalOrder()).hasSize(16)
                .containsExactly(1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14,
                        15, 16, 17);
    }

    @Test
    void whenTreeIsEmpty() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assertThat(tree.remove(6)).isFalse();
        assertThat(tree.inSymmetricalOrder()).hasSize(0);
    }

    @Test
    void whenClearThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        int[] array = new int[]{2, 1, 10, 6, 14, 4, 8, 12, 16, 11, 9, 13, 15, 17, 3, 5, 7};
        for (int i : array) {
            tree.put(i);
        }
        tree.clear();
        assertThat(tree.contains(2)).isFalse();
        assertThat(tree.inPostOrder().size()).isEqualTo(0);
    }

    @Test
    void whenRootIsNullThenClearIsOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.clear();
        assertThat(tree.inSymmetricalOrder().size()).isEqualTo(0);
    }
}