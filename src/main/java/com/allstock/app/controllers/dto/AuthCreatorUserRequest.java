package com.allstock.app.controllers.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreatorUserRequest(@NotBlank String username,
                                     @NotBlank String password,
                                     @Valid AuthCreateRoleRequest roleRequest){
}
