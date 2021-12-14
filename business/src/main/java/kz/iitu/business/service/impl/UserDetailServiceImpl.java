package kz.iitu.business.service.impl;

import kz.iitu.business.model.UserDetail;
import kz.iitu.business.repository.UserDetailRepository;
import kz.iitu.business.service.IUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements IUserDetailService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public UserDetail getByUserId(Long id) {
        return this.userDetailRepository.getByUserId(id);
    }
}
