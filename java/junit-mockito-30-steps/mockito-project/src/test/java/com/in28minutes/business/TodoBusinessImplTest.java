package com.in28minutes.business;

import com.in28minutes.data.api.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoBusinessImplTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoBusinessImpl todoBusiness;

    @Captor
    private ArgumentCaptor<String> argumentCaptor;

    @Test
    void whenRetrieveTodosRelatedToSpring_shouldReturn2() {

        when(todoService.retrieveTodos("Caique"))
                .thenReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn JSF"));

        List<String> todos = todoBusiness.retrieveTodosRelatedToSpring("Caique");

        assertThat(todos.size(), is(2));
        assertThat(todos, hasItem("Learn Spring MVC"));
        assertThat(todos, hasItem("Learn Spring"));

    }

    @Test
    void whenDeleteTodosNotRelatedToSpring_shouldDelete1() {

        when(todoService.retrieveTodos("Caique"))
                .thenReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn JSF"));

        todoBusiness.deleteTodosNotRelatedToSpring("Caique");

        /*verify(todoService, times(1)).deleteTodo("Learn JSF");
        verify(todoService, never()).deleteTodo("Learn Spring MVC");
        verify(todoService, never()).deleteTodo("Learn Spring");*/

        then(todoService).should().deleteTodo("Learn JSF");
        then(todoService).should(never()).deleteTodo("Learn Spring MVC");
        then(todoService).should(never()).deleteTodo("Learn Spring");

    }

    @Test
    void whenDeleteTodosNotRelatedToSpring_shouldDelete1_argumentCaptor() {

        when(todoService.retrieveTodos("Caique"))
                .thenReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn JSF"));

        todoBusiness.deleteTodosNotRelatedToSpring("Caique");

        /*verify(todoService, times(1)).deleteTodo("Learn JSF");
        verify(todoService, never()).deleteTodo("Learn Spring MVC");
        verify(todoService, never()).deleteTodo("Learn Spring");*/

        then(todoService).should().deleteTodo(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue(), is("Learn JSF"));

    }

    @Test
    void whenDeleteTodosNotRelatedToSpring_shouldDelete2_argumentCaptor() {

        when(todoService.retrieveTodos("Caique"))
                .thenReturn(Arrays.asList("Learn Spring MVC", "Learn VRaptor", "Learn JSF"));

        todoBusiness.deleteTodosNotRelatedToSpring("Caique");

        /*verify(todoService, times(1)).deleteTodo("Learn JSF");
        verify(todoService, never()).deleteTodo("Learn Spring MVC");
        verify(todoService, never()).deleteTodo("Learn Spring");*/

        then(todoService).should(times(2)).deleteTodo(argumentCaptor.capture());
        assertThat(argumentCaptor.getAllValues(), hasItems("Learn JSF", "Learn VRaptor"));

    }

}
