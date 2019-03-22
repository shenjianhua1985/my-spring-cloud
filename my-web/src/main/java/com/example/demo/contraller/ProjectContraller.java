package com.example.demo.contraller;

import com.example.demo.entry.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "用户信息查询(主要操作表——t_user_info)")
public class ProjectContraller {

    @ApiOperation(value = "查询用户信息", notes = "根据条件查询查询水表信息（分页）", response = UserInfo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id号", paramType = "path", dataType = "string")
    })
    @GetMapping(value = "/{id}")
    public Object queryById(@PathVariable(value = "id") String id) {
        UserInfo user = new UserInfo();
        user.setId(id);
        user.setName("project-fff");
        return user;
    }
}
