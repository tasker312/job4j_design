package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 3);
        assertThat(box.whatsThis()).isEqualTo("Cube");
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void isUnknownObject() {
        Box box = new Box(6, 2);
        assertThat(box.whatsThis()).isEqualTo("Unknown object");
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void getNumberOfVerticesForSphere() {
        Box box = new Box(0, 10);
        assertThat(box.whatsThis()).isEqualTo("Sphere");
        assertThat(box.getNumberOfVertices()).isEqualTo(0);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void getNumberOfVerticesForTetrahedron() {
        Box box = new Box(4, 5);
        assertThat(box.getNumberOfVertices()).isEqualTo(4);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void isNothingExist() {
        Box box = new Box(0, 0);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void isCubeExist() {
        Box box = new Box(8, 1);
        assertThat(box.getNumberOfVertices()).isEqualTo(8);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void getSphereArea() {
        Box box = new Box(0, 10);
        assertThat(box.getNumberOfVertices()).isEqualTo(0);
        assertThat(box.getArea()).isEqualTo(1256.63, withPrecision(0.01d));
    }

    @Test
    void getCubeArea() {
        Box box = new Box(8, 4);
        assertThat(box.getNumberOfVertices()).isEqualTo(8);
        assertThat(box.getArea()).isEqualTo(96);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void getAreaForUnknownObject() {
        Box box = new Box(6, 2);
        assertThat(box.getNumberOfVertices()).isEqualTo(-1);
        assertThat(box.getArea()).isEqualTo(0);
        assertThat(box.isExist()).isFalse();
    }
}
