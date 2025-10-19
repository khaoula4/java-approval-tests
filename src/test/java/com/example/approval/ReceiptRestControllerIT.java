package com.example.approval;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class ReceiptRestControllerIT {


    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnApprovedReceiptTextFromApi() throws Exception {
        String actualResponseBody = mockMvc.perform(get("/api/receipts/1001"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Approvals.verify(actualResponseBody);
    }
}
