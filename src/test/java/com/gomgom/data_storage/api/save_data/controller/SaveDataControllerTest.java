package com.gomgom.data_storage.api.save_data.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SaveDataControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("CSV File Upload test")
    void createSaveDataCsv() {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        ClassPathResource classPathResource = new ClassPathResource("카카오페이 풀스택 개발 과제 - 데이터셋.csv");
        builder.part("file", classPathResource).header("Content-type", "text/csv");

        webTestClient.post()
                .uri("/saveData/create-csv")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.fileName").isEqualTo("카카오페이 풀스택 개발 과제 - 데이터셋.csv")
                .jsonPath("$.contentType").isEqualTo("text/csv")
                .jsonPath("$.size").isEqualTo(4642)
        ;
    }


}