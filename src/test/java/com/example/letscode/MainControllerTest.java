package com.example.letscode;

import com.example.letscode.Controller.MainController;
import com.example.letscode.Service.MainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {
    @InjectMocks
    MainController mainController;

    @Mock
    MainService mainService;

    private MockMvc mockMvc;

    private static final String GET_INFO_FOR_INDEX = "/main";
    private static final String DELETE_ITEM = "/delete?id=1";
    private static final String BUY_ITEM = "/buy?id=1";
    private static final String POST_ITEM = "/add/new";
    private static final String POST_ADD_PRICE = "/addPrice?id=1&addValue=1";


    @BeforeEach
    void setAll() {
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }
    
    @Test
    void whenPostItemThenSuccess() throws Exception {
        when(mainService.postItem(any(),any(),any(),any())).thenReturn(true);
        mockMvc.perform(get(POST_ITEM))
                .andExpect(status().isOk());

        mockMvc.perform(post(POST_ITEM))
                .andExpect(status().is3xxRedirection());
    }

}
