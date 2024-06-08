package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkEmptyNames() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[0];
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }

    @Test
    void checkNotContainEquals() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"key;value"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(names[0])
                .hasMessageContaining("=");
    }

    @Test
    void checkStartsWithEquals() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"=key=value"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(names[0])
                .hasMessageContaining("key");
    }

    @Test
    void checkEndsWithEquals() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"keyvalue="};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(names[0])
                .hasMessageContaining("value");
    }
}