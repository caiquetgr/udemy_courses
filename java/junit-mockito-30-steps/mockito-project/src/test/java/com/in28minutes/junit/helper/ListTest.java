package com.in28minutes.junit.helper;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Test
    void letsMockListSizeMethod() {

        List<?> list = mock(List.class);

        when(list.size()).thenReturn(2).thenReturn(3);
        assertEquals(list.size(), 2);
        assertEquals(list.size(), 3);

    }

    @Test
    void letsMockListSizeMethod_BDD() {

        // Given
        List<String> list = mock(List.class);
        given(list.get(0)).willReturn("in28Minutes");

        // When
        String result = list.get(0);

        // Then
        assertThat(result, is("in28Minutes"));

    }

}
