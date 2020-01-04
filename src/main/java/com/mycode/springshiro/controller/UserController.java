package com.mycode.springshiro.controller;

import com.mycode.springshiro.pojo.dto.UserInfoDto;
import com.mycode.springshiro.pojo.vo.ResponseJsonData;
import com.mycode.springshiro.pojo.vo.UserInfoVo;
import com.mycode.springshiro.service.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wenyutun
 * @description: 测试web服务
 * @date: 2019/8/19
 * @version: 1.0
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 获取全部用户信息
     * <p>
     * RequiresPermissions("user:list:view") 注解说明具有用户：列表：查看权限的才可以访问）
     * RequiresRoles("admin") 注解说明具有admin角色的才可以访问）
     * 官网明确给出权限定义格式，包括通配符等，我希望你自行去查看
     * </p>
     *
     * @return userInfoVos
     */
    @GetMapping(value = "/get/get_all")
    @RequiresRoles("admin")
    public ResponseJsonData getAllUser() {
        final List<UserInfoDto> allUsers = userInfoService.getAllUsers();
        final List<UserInfoVo> userInfoVos = allUsers.stream().map(this::convertVoInfoFromDto)
                .collect(Collectors.toList());
        return ResponseJsonData.ok("查询成功", userInfoVos);
    }

    /**
     * 通过主键查询用户信息
     * @param id 用户ID
     * @return userInfoVo
     */
    @GetMapping(value = "/get/id")
    public ResponseJsonData getUserById(@RequestParam(value = "id") Long id) {
        final UserInfoDto userInfoDto = userInfoService.selectUserById(id);
        final UserInfoVo userInfoVo = convertVoInfoFromDto(userInfoDto);
        return ResponseJsonData.ok("查询成功", userInfoVo);
    }


    /**
     * 将用户实体模型数据复制到用户领域模型
     *
     * @param userInfoDto 用户领域模型
     * @return userInfoVo
     */
    private UserInfoVo convertVoInfoFromDto(UserInfoDto userInfoDto) {
        if (userInfoDto == null) {
            return null;
        }
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfoDto, userInfoVo);
        return userInfoVo;
    }
}
