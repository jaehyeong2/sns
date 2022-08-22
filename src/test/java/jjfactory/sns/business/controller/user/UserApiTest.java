package jjfactory.sns.business.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import jjfactory.sns.business.domain.user.User;
import jjfactory.sns.business.request.user.UserCreate;
import jjfactory.sns.business.request.user.UserUpdate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Transactional
@SpringBootTest
class UserApiTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("유저 정보 업데이트 폼 get")
    void getUpdateForm() throws Exception {
        //given
        User user = User.builder()
                .birth("960822")
                .email("wogud222@gmail.com")
                .phone("01012341234")
                .build();
        em.persist(user);

        //expected
        mvc.perform(MockMvcRequestBuilders.get("/users/{id}",user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("wogud222@gmail.com"))
                .andDo(print());
    }

    @Test
    @DisplayName("회워탈퇴 성공")
    void withdraw() throws Exception {
        //given
        User user = User.builder()
                .birth("960822")
                .email("wogud222@gmail.com")
                .phone("01012341234")
                .build();
        em.persist(user);

        //expected
        mvc.perform(MockMvcRequestBuilders.delete("/users/{id}",user.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("Y"))
                .andDo(print());
    }

    @Test
    @DisplayName("유저정보 업데이트 성공")
    void updateUserInfo() throws Exception {
        //given
        User user = User.builder()
                .birth("960822")
                .email("wogud222@gmail.com")
                .phone("01012341234")
                .build();
        em.persist(user);

        UserUpdate dto = UserUpdate.builder()
                .birth("960822")
                .email("wogud222@gmail.com")
                .phone("01012341234")
                .build();
        String stringUser = objectMapper.writeValueAsString(dto);

        //expected
        mvc.perform(MockMvcRequestBuilders.put("/users/{id}",user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stringUser))
                .andExpect(status().isOk())
                .andExpect(content().string("Y"))
                .andDo(print());
    }
}