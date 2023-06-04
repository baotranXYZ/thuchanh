package com.example.tuan5_ketnoicsdl.services;

import com.example.tuan5_ketnoicsdl.entity.User;
import com.example.tuan5_ketnoicsdl.repository.IRoleRepository;
import com.example.tuan5_ketnoicsdl.repository.IuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private IuserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;
    public void save(User user){
        userRepository.save(user);
        Long userId = userRepository.getUserIdByUsername(user.getUsername());
        Long roleId = roleRepository.getRoleIdByName("USER");
        if(roleId != 0 && userId != 0){
            userRepository.addRoleToUser(userId, roleId);
        }
    }
}
