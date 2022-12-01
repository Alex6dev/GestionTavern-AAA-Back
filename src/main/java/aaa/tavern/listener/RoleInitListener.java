package aaa.tavern.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

import aaa.tavern.service.RoleService;

@Component
public class RoleInitListener implements ApplicationListener<ApplicationContextEvent> {
    @Autowired
    RoleService roleService ;

    @Override
    public void onApplicationEvent(ApplicationContextEvent event){
        roleService.createRoleIfNotExists("USER") ;
        roleService.createRoleIfNotExists("ADMIN") ;
    }
}
