package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import org.assertj.core.data.Index;
import java.util.List;
import java.util.Set;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).contains("first")
                .hasSize(5)
                .containsExactly("first", "second", "three", "four", "five")
                .doesNotContain("six")
                .endsWith("four", "five");
    }

    @Test
    void checkToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "first", "second", "three", "four", "five");
        assertThat(set).hasSize(5)
                .allMatch(s -> s.length() > 2)
                .anyMatch(s -> s.equals("first"))
                .containsExactlyInAnyOrder("first", "second", "three", "four", "five");
    }

    @Test
    void checkToMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).isNotNull()
                .hasSize(5)
                .containsKey("second")
                .containsEntry("three", 2)
                .doesNotContainKey("six")
                .allSatisfy((s, i) -> {
                    assertThat(s.length()).isLessThan(10);
                    assertThat(i).isLessThan(5);
                });
    }
}