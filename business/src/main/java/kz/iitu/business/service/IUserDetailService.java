package kz.iitu.business.service;

import kz.iitu.business.model.UserDetail;

public interface IUserDetailService {
    UserDetail getByUserId(Long id);
}
