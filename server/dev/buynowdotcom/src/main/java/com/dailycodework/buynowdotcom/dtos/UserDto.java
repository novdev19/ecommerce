package com.dailycodework.buynowdotcom.dtos;

import com.dailycodework.buynowdotcom.model.Cart;
import com.dailycodework.buynowdotcom.model.Order;
import com.dailycodework.buynowdotcom.model.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
