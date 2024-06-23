package com.gestone.gestone_api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestone.gestone_api.marbleshop.Marbleshop;
import com.gestone.gestone_api.marbleshop.MarbleshopService;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MarbleshopService marbleshopService;

    public User saveAdminUser(AdminUserRequestDTO adminUserDTO) {
        var adminUser = new User();
        adminUser.setName(adminUserDTO.name());
        adminUser.setEmail(adminUserDTO.email());
        adminUser.setPassword(adminUserDTO.password());
        adminUser.setPhone(adminUserDTO.phone());
        adminUser.setType(UserType.ADMIN);
        var marbleshop = new Marbleshop(adminUserDTO.marbleshop().name(), adminUserDTO.marbleshop().email(),
                adminUserDTO.marbleshop().phone());
        marbleshop.getUsers().add(adminUser);
        var savedMarbleshop = marbleshopService.saveMarbleshop(marbleshop);
        adminUser.setMarbleshop(savedMarbleshop);
        var savedUser = userRepository.save(adminUser);
        return savedUser;
    }
}
