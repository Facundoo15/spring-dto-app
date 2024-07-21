package com.crymuzz.springdtoapplication.service.impl;

import com.crymuzz.springdtoapplication.controller.dto.UserDTO;
import com.crymuzz.springdtoapplication.persistence.entity.UserEntity;
import com.crymuzz.springdtoapplication.repository.UserRepository;
import com.crymuzz.springdtoapplication.service.IUserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        return this.userRepository.findAll().stream().map(e -> modelMapper.map(e, UserDTO.class)).toList();
    }

    @Override
    public UserDTO findById(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent())
            return modelMapper.map(optionalUser.get(), UserDTO.class);
        return new UserDTO();
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
            this.userRepository.save(userEntity);
            userRepository.flush();
            return userDTO;
        }catch (Exception e){
            throw new UnsupportedOperationException("Error al registrar al usuario");
        }
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO userDTO, Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<UserEntity> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()){
            UserEntity userEntity = optionalUser.get();
            userEntity.setName(userDTO.getName());
            userEntity.setEmail(userDTO.getEmail());
            userEntity.setLastName(userDTO.getLastName());
            userEntity.setAge(userDTO.getAge());
            this.userRepository.save(userEntity);
            return modelMapper.map(userEntity, UserDTO.class);
        }
        throw new IllegalArgumentException("Usuario no existe");
    }


    @Override
    @Transactional
    public String delete(Long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            this.userRepository.deleteById(id);
            return "Usuario ID: " + id + "eliminado ";
        }
        return "El usuario no existe";
    }
}
