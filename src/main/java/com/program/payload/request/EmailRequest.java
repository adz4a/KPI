package com.program.payload.request;

import javax.validation.constraints.NotBlank;

public class EmailRequest {

    @NotBlank
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
