package com.foundry.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
        // Garante que o Spring consegue montar o application context.
        // Se essa falhar, algo no wiring está quebrado.
    }
}