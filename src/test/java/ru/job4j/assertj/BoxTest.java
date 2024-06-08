package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import org.assertj.core.data.Percentage;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .hasSize(6);
    }

    @Test
    void isThisUnknownObject() {
        Box box = new Box(4, -8);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .doesNotEndWithIgnoringCase("cube");
    }

    @Test
    void whenTetrahedronThen4() {
        Box box = new Box(4, 8);
        int count = box.getNumberOfVertices();
        assertThat(count).isEqualTo(4)
                .isLessThan(6);
    }

    @Test
    void whenCubeThen8() {
        Box box = new Box(8, 5);
        int count = box.getNumberOfVertices();
        assertThat(count).isEqualTo(8)
                .isGreaterThan(5)
                .isPositive();
    }

    @Test
    void whenExist() {
        Box box = new Box(4, 5);
        boolean result = box.isExist();
        assertThat(result).isTrue()
                .isNotEqualTo(false);
    }

    @Test
    void whenNotExist() {
        Box box = new Box(7, 5);
        boolean result = box.isExist();
        assertThat(result).isFalse()
                .isNotNull();
    }

    @Test
    void whenTetrahedronEdge8Then110And85() {
        Box box = new Box(4, 8);
        double result = box.getArea();
        assertThat(result).isCloseTo(110.85d, withPrecision(0.01d))
                .isCloseTo(110.85d, Percentage.withPercentage(1.0d));
    }

    @Test
    void whenTriangleEdge5Then0() {
        Box box = new Box(3, 5);
        double result = box.getArea();
        assertThat(result).isCloseTo(0d, withPrecision(0.0001d))
                .isLessThan(1d);
    }
}