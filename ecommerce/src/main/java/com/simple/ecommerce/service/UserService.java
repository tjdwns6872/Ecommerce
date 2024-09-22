package com.simple.ecommerce.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
   String login(String type);

   String socialCallback(Map<String, Object> params);
}
