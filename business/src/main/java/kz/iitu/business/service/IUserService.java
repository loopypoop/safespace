package kz.iitu.business.service;

import reactor.core.publisher.Mono;

public interface IUserService {
    public Mono<String> deactivateUser(Long userId);
}
