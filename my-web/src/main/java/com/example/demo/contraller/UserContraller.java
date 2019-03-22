package com.example.demo.contraller;

import com.example.demo.entry.UserInfo;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hunter
 * @date 2019/03/21
 **/
@RestController
@RequestMapping(value = "/service/user")
public class UserContraller {

    @GetMapping(value = "/{id}")
    public Object queryById(@PathVariable(value = "id") String id) {
        UserInfo user = new UserInfo();
        user.setId(id);
        user.setName("user-abc");
        return user;
    }
}
