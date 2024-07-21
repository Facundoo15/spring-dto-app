package com.crymuzz.springdtoapplication.service;


import com.crymuzz.springdtoapplication.controller.dto.UserDTO;

import java.util.List;

public interface IUserService {

    List<UserDTO> findAll();
    UserDTO findById(Long id);
    UserDTO save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO, Long id);
    String delete(Long id);

}
