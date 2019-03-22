package com.example.demo.contraller;

import com.example.demo.entry.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hunter
 * @date 2019/03/21
 **/
@RestController
@RequestMapping(value = "/service/project")
public class ProjectContraller {

    @GetMapping(value = "/{id}")
    public Object queryById(@PathVariable(value = "id") String id) {
        UserInfo user = new UserInfo();
        user.setId(id);
        user.setName("project-fff");
        return user;
    }
}
