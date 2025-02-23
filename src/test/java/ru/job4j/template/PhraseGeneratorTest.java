package ru.job4j.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled
class PhraseGeneratorTest {
    private Map<String, String> map = new HashMap<>();
    private String template = "I am a ${name}, Who are ${subject}?";
    private Generator generator = new PhraseGenerator();

    @BeforeEach
    public void init() {
        map.clear();
    }

    @Test
    public void whenCorrectArgs() {
        map.put("name", "Alex");
        map.put("subject", "you");
        String expected = "I am a Alex, Who are you?";
        String result = generator.produce(template, map);
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void whenInvalidFirstKey() {
        map.put("namee", "Alex");
        map.put("subject", "you");
        assertThatThrownBy(() -> generator.produce(template, map))
        .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenInvalidSecondKey() {
        map.put("name", "Alex");
        map.put("subjectt", "you");
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenMissingFirstKey() {
        map.put("subject", "you");
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenMissingSecondKey() {
        map.put("name", "Alex");
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenFirstValueIsNull() {
        map.put("name", null);
        map.put("subject", "you");
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenSecondValueIsNull() {
        map.put("name", "Alex");
        map.put("subject", null);
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenInvalidFirstKeyInTemplate() {
        map.put("name", "Alex");
        map.put("subject", "you");
        String invalidTemplate = "I am a ${namee}, Who are ${subject}?";
        assertThatThrownBy(() -> generator.produce(invalidTemplate, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenInvalidSecondKeyInTemplate() {
        map.put("name", "Alex");
        map.put("subject", "you");
        String invalidTemplate = "I am a ${name}, Who are ${subjectt}?";
        assertThatThrownBy(() -> generator.produce(invalidTemplate, map))
                .isInstanceOf(IllegalArgumentException.class);
    }
}