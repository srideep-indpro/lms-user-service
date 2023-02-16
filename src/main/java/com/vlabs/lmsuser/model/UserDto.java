package com.vlabs.lmsuser.model;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDto {
    private int userID;
    private String userName;
    private String userEmail;
    private List<String> userRole;
}
