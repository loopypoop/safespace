package kz.iitu.business.service;

import kz.iitu.business.model.UserDetail;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserDetailService {
    Mono<UserDetail> getByUserId(Long id);
    Mono<String> getName(Long id);
    Mono<UserDetail> getFirstOrLast();
    Flux<UserDetail> filterList();
}
