package com.example.letscode;

import com.example.letscode.Domain.ItemEntity;
import com.example.letscode.Domain.User;
import com.example.letscode.Repository.ItemEntityRepository;
import com.example.letscode.Service.MainService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class MainServiceTest {

    @InjectMocks
    MainService mainService;

    @Mock
    ItemEntityRepository itemEntityRepository;

    Model model;
    User user;
    @BeforeEach
    void setAll() {
    model = Mockito.mock(Model.class);
        user =  Mockito.mock(User.class);
    }

    @Test
    void whenPostItemThenTrue() {
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        ItemEntity itemEntity = ItemEntity.builder().build();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(itemEntityRepository.save(itemEntity)).thenReturn(itemEntity);

        assertTrue(mainService.postItem(itemEntity, bindingResult, user, model)) ;
    }
    @Test
    void whenPostItemThenFalse() {
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        ItemEntity itemEntity = ItemEntity.builder().build();

        when(bindingResult.hasErrors()).thenReturn(true);

        assertFalse(mainService.postItem(itemEntity, bindingResult, user, model));
    }

}
