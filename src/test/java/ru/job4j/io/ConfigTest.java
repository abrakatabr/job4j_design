package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Alex Pozharov");
    }

    @Test
    void whenPairWithCommentEndEmptyStrings() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Alex Pozharov");
    }

    @Test
    void whenException() {
        String path = "./data/pair_with_invalid_separator.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> {
            config.load();
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Wrong delimiter");
    }

    @Test
    void whenPairWithTwoSeparators() {
        String path = "./data/pair_with_two_separators.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Alex Pozharov=name");
    }
}