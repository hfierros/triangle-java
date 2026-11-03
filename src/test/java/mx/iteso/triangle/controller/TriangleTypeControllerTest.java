package mx.iteso.triangle.controller;

import mx.iteso.triangle.dto.TriangleDto;
import mx.iteso.triangle.dto.TriangleTypeResponse;
import mx.iteso.triangle.enums.TriangleTypes;
import mx.iteso.triangle.service.TriangleType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/* This test class tests the controller, doing a direct call to it */
@SpringBootTest
class TriangleTypeControllerTest {

    @Autowired
    private TriangleTypeController triangleTypeController;

    @MockitoBean
    private TriangleType triangleType;


    @Test
    public void testTriangleTypeServiceGetTriangleType() {
        TriangleDto triangle = new TriangleDto(1L, 1L, 1L);
        when(triangleType.getTriangleType(any())).thenReturn(TriangleTypes.SCALENE);

        ResponseEntity<TriangleTypeResponse> response = triangleTypeController.getTriangleType(triangle);
        assertEquals(HttpStatusCode.valueOf(200),response.getStatusCode());
        assertEquals(TriangleTypes.SCALENE,response.getBody().getType());

    }

    @ParameterizedTest
    @CsvSource({
            "3, 3, 3, EQUILATERAL",
            "3, 3, 4, ISOSCELES",
            "3, 4, 5, SCALENE",
            "3, 4, 3, ISOSCELES",
            "4, 3, 3, ISOSCELES",
    })
    void testGetTriangleType(Long a, Long b, Long c, TriangleTypes expected) {

        TriangleType service = new TriangleType();
        TriangleDto triangle = new TriangleDto(a, b, c);

        assertEquals(expected, service.getTriangleType(triangle));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, 2",
            "1, 2, 1",
            "2, 1, 1"
    })
    public void testGetTriangleTypeWithInvalidTriangle(Long a, Long b, Long c) {
        TriangleDto triangle = new TriangleDto(a, b, c);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> triangleTypeController.getTriangleType(triangle));

        assertEquals("The side lengths do not correspond to a real triangle", exception.getMessage());
    }


}