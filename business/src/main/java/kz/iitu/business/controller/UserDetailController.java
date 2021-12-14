package kz.iitu.business.controller;

import kz.iitu.business.model.UserDetail;
import kz.iitu.business.service.IUserDetailService;
import kz.iitu.business.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-detail")
public class UserDetailController {

    @Autowired
    private IUserDetailService userDetailService;

    @GetMapping("/{userId}")
    public UserDetail getByUserId(@PathVariable Long userId) {
        return userDetailService.getByUserId(userId);
    }
}
