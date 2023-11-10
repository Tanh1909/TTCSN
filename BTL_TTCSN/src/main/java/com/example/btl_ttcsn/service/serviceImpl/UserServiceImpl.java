package com.example.btl_ttcsn.service.serviceImpl;

import com.example.btl_ttcsn.dto.common.UserDetailDTO;
import com.example.btl_ttcsn.dto.request.UserCreateRequestDTO;
import com.example.btl_ttcsn.dto.response.UserCreateResponseDTO;
import com.example.btl_ttcsn.entity.Role;
import com.example.btl_ttcsn.entity.User;
import com.example.btl_ttcsn.exception.NotFoundException;
import com.example.btl_ttcsn.exception.UnauthorizedException;
import com.example.btl_ttcsn.repository.RoleRepository;
import com.example.btl_ttcsn.repository.UserRepository;
import com.example.btl_ttcsn.security.UserDetailImpl;
import com.example.btl_ttcsn.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public UserCreateResponseDTO create(UserCreateRequestDTO userCreateRequestDTO) {
        User user=modelMapper.map(userCreateRequestDTO,User.class);
        if(userRepository.findByUsername(user.getUsername())!=null){
            throw new UnauthorizedException("username already exists");
        }
        Role role=roleRepository.findById(1L).orElse(new Role(1L,"user"));
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return modelMapper.map(user,UserCreateResponseDTO.class);
    }

    @Override
    public UserCreateResponseDTO update(UserDetailDTO userDetailDTO) {
        User user=modelMapper.map(userDetailDTO,User.class);
        userRepository.save(user);
        return modelMapper.map(user,UserCreateResponseDTO.class);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserCreateResponseDTO forgotPassword(String email) {
        return null;
    }

    @Override
    public UserCreateResponseDTO getCurruntUser() {
        Authentication authentication = null;
        try {
            authentication = SecurityContextHolder.getContext().getAuthentication();
            String username= (String) authentication.getPrincipal();
            User user=userRepository.findByUsername(username);
            return modelMapper.map(user,UserCreateResponseDTO.class);

        }  catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException("Login First!");
        }

    }

    @Override
    public UserDetailDTO getUserById(Long id) {
        User user=userRepository.findById(id).get();
        if(user==null){
            throw new NotFoundException("Not Found User!");
        }
        return modelMapper.map(user,UserDetailDTO.class);
    }
}
