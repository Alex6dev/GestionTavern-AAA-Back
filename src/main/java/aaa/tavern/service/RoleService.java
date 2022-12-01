package aaa.tavern.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.entity.Role;
import aaa.tavern.dao.RoleRepository;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepository ;

    public void createRoleIfNotExists(String roleName){
        Optional<Role> optRole = roleRepository.findByName(roleName);

        if(optRole.isEmpty())
            roleRepository.save(new Role(roleName)) ;
    }


}
