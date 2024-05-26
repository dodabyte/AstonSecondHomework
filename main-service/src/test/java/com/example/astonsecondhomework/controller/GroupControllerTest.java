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
                                      "name": "���-045",
                                      "course": 4,
                                      "semester": 8,
                                      "students": [
                                          {
                                              "id": 5,
                                              "lastName": "��������",
                                              "firstName": "�������",
                                              "patronymic": "����������"
                                          },
                                          {
                                              "id": 7,
                                              "lastName": "������2",
                                              "firstName": "����2",
                                              "patronymic": "��������2"
                                          }
                                      ]
                                  },
                                  {
                                      "id": 7,
                                      "name": "���-049",
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
                                "name": "���-045",
                                "course": 4,
                                "semester": 8,
                                "students": [
                                    {
                                        "id": 5,
                                        "lastName": "��������",
                                        "firstName": "�������",
                                        "patronymic": "����������"
                                    },
                                    {
                                        "id": 7,
                                        "lastName": "������2",
                                        "firstName": "����2",
                                        "patronymic": "��������2"
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
                    "name": "���-048",
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
                                "name": "���-048",
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
                    "name": "���-111",
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
                                "name": "���-111",
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
                                "name": "���-111",
                                "course": 3,
                                "semester": 6,
                                "students": [
                                    {
                                        "id": 5,
                                        "lastName": "��������",
                                        "firstName": "�������",
                                        "patronymic": "����������"
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