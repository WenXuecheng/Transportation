package com.rucn.transportation.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class UserPasswordDTO {
    private Integer id;
    private String username;
    private String password;
    private String newPassword;
    @JsonIgnore
    private String oPassword;

}
