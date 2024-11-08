package com.productservice.productservice09april.Commons;

import com.productservice.productservice09april.dtos.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationCommons {
    private RestTemplate restTemplate;

    public AuthenticationCommons(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public UserDTO validateToken(String token){
        //make a call to validateToken API from userservice to validate the token
        UserDTO userDTO = restTemplate.getForObject(
            "http://localhost:8181/user/validate/"+token,
                UserDTO.class
        );
        return userDTO;
    }
}
