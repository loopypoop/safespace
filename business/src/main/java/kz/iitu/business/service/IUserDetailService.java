package kz.iitu.business.service;

import kz.iitu.business.model.User;
import kz.iitu.business.model.UserDetail;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface IUserDetailService {
    Mono<UserDetail> getByUserId(Long id);

    Mono<String> getName(Long id);

    Mono<UserDetail> getFirstOrLast();

    Flux<UserDetail> filterList();

    Flux<UserDetail> getAllUsersByPagination(Map<String, String> params);

    Mono<UserDetail> updateUser(UserDetail userDetail);

}
