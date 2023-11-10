package com.example.btl_ttcsn.controller;

import com.example.btl_ttcsn.base.VsResponseUtil;
import com.example.btl_ttcsn.dto.common.UserDetailDTO;
import com.example.btl_ttcsn.dto.request.UserCreateRequestDTO;
import com.example.btl_ttcsn.dto.response.UserCreateResponseDTO;
import com.example.btl_ttcsn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody UserCreateRequestDTO userCreateRequestDTO){
        return ResponseEntity.ok(VsResponseUtil.success(userService.create(userCreateRequestDTO)));
    }
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody UserDetailDTO userDetailDTO){
        return ResponseEntity.ok(VsResponseUtil.success(userService.update(userDetailDTO)));
    }
    @GetMapping("")
    public ResponseEntity<?> getCurrentUser(){
        return ResponseEntity.ok(VsResponseUtil.success(userService.getCurruntUser()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(VsResponseUtil.success(userService.getUserById(id)));
    }


}
