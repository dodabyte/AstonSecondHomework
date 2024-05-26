package com.example.astonsecondhomework.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GroupControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAllGroups() throws Exception {
        mockMvc.perform(get("/groups"))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                              [
                                  {
                                      "id": 4,
                                      "name": "АВТ-045",
                                      "course": 4,
                                      "semester": 8,
                                      "students": [
                                          {
                                              "id": 5,
                                              "lastName": "Евгеньев",
                                              "firstName": "Евгений",
                                              "patronymic": "Евгеньевич"
                                          },
                                          {
                                              "id": 7,
                                              "lastName": "Тестов2",
                                              "firstName": "Тест2",
                                              "patronymic": "Тестович2"
                                          }
                                      ]
                                  },
                                  {
                                      "id": 7,
                                      "name": "АВТ-049",
                                      "course": 4,
                                      "semester": 8,
                                      "students": []
                                  }
                              ]
                        """)
                );
    }

    @Test
    void findGroupById() throws Exception {
        mockMvc.perform(get("/groups/4"))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                            {
                                "id": 4,
                                "name": "АВТ-045",
                                "course": 4,
                                "semester": 8,
                                "students": [
                                    {
                                        "id": 5,
                                        "lastName": "Евгеньев",
                                        "firstName": "Евгений",
                                        "patronymic": "Евгеньевич"
                                    },
                                    {
                                        "id": 7,
                                        "lastName": "Тестов2",
                                        "firstName": "Тест2",
                                        "patronymic": "Тестович2"
                                    }
                                ]
                            }
                        """)
                );
    }

    @Test
    void insertGroup() throws Exception {
        String jsonBody = """
                {
                    "name": "АВТ-048",
                    "course": 4,
                    "semester": 8
                }
                """;
        mockMvc.perform(post("/groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                            {
                                "id": 1,
                                "name": "АВТ-048",
                                "course": 4,
                                "semester": 8,
                                "students": null
                            }
                        """)
                );
    }

    @Test
    void updateGroup() throws Exception {
        String jsonBody = """
                {
                    "name": "АВТ-111",
                    "course": 3,
                    "semester": 6
                }
                """;
        mockMvc.perform(put("/groups/7")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                            {
                                "id": 7,
                                "name": "АВТ-111",
                                "course": 3,
                                "semester": 6,
                                "students": null
                            }
                        """)
                );
    }

    @Test
    void insertStudentsToGroup() throws Exception {
        mockMvc.perform(put("/groups/7/students/5"))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                            {
                                "id": 7,
                                "name": "АВТ-111",
                                "course": 3,
                                "semester": 6,
                                "students": [
                                    {
                                        "id": 5,
                                        "lastName": "Евгеньев",
                                        "firstName": "Евгений",
                                        "patronymic": "Евгеньевич"
                                    }
                                ]
                            }
                        """)
                );
    }

    @Test
    void deleteGroup() throws Exception {
        mockMvc.perform(delete("/groups/12"))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().string("true")
                );
    }

    @Test
    void deleteStudentsFromGroup() throws Exception {
        mockMvc.perform(delete("/groups/students/5"))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().string("true")
                );
    }
}