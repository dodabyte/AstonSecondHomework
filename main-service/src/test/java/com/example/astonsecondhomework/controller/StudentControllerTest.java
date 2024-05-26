package com.example.astonsecondhomework.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAllStudents() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                              [
                                   {
                                       "id": 4,
                                       "lastName": "Куликов",
                                       "firstName": "Вадим",
                                       "patronymic": "Васильевич",
                                       "group": null
                                   },
                                   {
                                       "id": 6,
                                       "lastName": "Тестов",
                                       "firstName": "Тест",
                                       "patronymic": "Тестович",
                                       "group": null
                                   },
                                   {
                                       "id": 7,
                                       "lastName": "Тестов2",
                                       "firstName": "Тест2",
                                       "patronymic": "Тестович2",
                                       "group": {
                                           "id": 4,
                                           "name": "АВТ-045",
                                           "course": 4,
                                           "semester": 8
                                       }
                                   },
                                   {
                                       "id": 5,
                                       "lastName": "Евгеньев",
                                       "firstName": "Евгений",
                                       "patronymic": "Евгеньевич",
                                       "group": null
                                   }
                               ]
                        """)
                );
    }

    @Test
    void findStudentById() throws Exception {
        mockMvc.perform(get("/students/4"))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                            {
                               "id": 4,
                               "lastName": "Куликов",
                               "firstName": "Вадим",
                               "patronymic": "Васильевич",
                               "group": null
                            }
                        """)
                );
    }

    @Test
    void insertStudent() throws Exception {
        String jsonBody = """
                {
                    "lastName": "Григорьев",
                    "firstName": "Григорий",
                    "patronymic": "Григорьев",
                    "shortGroup": {
                        "id": 4
                    }
                }
                """;
        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                            {
                                "id": 7,
                                "lastName": "Григорьев",
                                "firstName": "Григорий",
                                "patronymic": "Григорьев",
                                "group": {
                                    "id": 4,
                                    "name": "АВТ-045",
                                    "course": 4,
                                    "semester": 8
                                }
                            }
                        """)
                );
    }

    @Test
    void updateStudent() throws Exception {
        String jsonBody = """
                {
                    "lastName": "Васильев",
                    "firstName": "Василий",
                    "patronymic": "Васильевич",
                    "group": {
                        "id": 4
                    }
                }
                """;
        mockMvc.perform(put("/students/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                            {
                                "id": 4,
                                "lastName": "Васильев",
                                "firstName": "Василий",
                                "patronymic": "Васильевич",
                                "group": {
                                    "id": 4,
                                    "name": "АВТ-045",
                                    "course": 4,
                                    "semester": 8
                                }
                            }
                        """)
                );
    }

    @Test
    void insertGroupInStudent() throws Exception {
        mockMvc.perform(put("/students/5/groups/4"))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                            {
                                "id": 5,
                                "lastName": "Евгеньев",
                                "firstName": "Евгений",
                                "patronymic": "Евгеньевич",
                                "group": {
                                    "id": 4,
                                    "name": "АВТ-045",
                                    "course": 4,
                                    "semester": 8
                                }
                            }
                        """)
                );
    }

    @Test
    void deleteStudent() throws Exception {
        mockMvc.perform(delete("/students/6"))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().string("true")
                );
    }

    @Test
    void deleteGroupFromStudent() throws Exception {
        mockMvc.perform(delete("/students/6/groups"))
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().string("true")
                );
    }
}