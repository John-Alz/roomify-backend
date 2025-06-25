package com.roomify.roomifybackend.presentation.dto.request;

public record ResetPasswordRequest(
        String token,
        String newPassword
) {
}
