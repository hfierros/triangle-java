package mx.iteso.triangle.service;

import mx.iteso.triangle.dto.TriangleDto;
import mx.iteso.triangle.enums.TriangleTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TriangleTypeTest {

    @Autowired
    private TriangleType triangleType;

    @Test
    public void testScaleneTriangle() {
        TriangleDto scalene = new TriangleDto(2L,3L,4L);
        TriangleTypes type = triangleType.getTriangleType(scalene);
        assertEquals(TriangleTypes.SCALENE, type);
    }

    @Test
    public void testInvalidTriangle() {
        TriangleDto scalene = new TriangleDto(1L,3L,4L);
        TriangleTypes type = triangleType.getTriangleType(scalene);
        assertEquals(TriangleTypes.SCALENE, type);
    }
}