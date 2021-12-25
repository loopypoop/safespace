package kz.iitu.business.service.impl;

import kz.iitu.business.model.UserDetail;
import kz.iitu.business.repository.UserDetailRepository;
import kz.iitu.business.service.IUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements IUserDetailService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public Mono<UserDetail> getByUserId(Long id) {
        return this.userDetailRepository.getByUserId(id);
    }

    @Override
    public Mono<String> getName(Long id) {
        Mono<String> firstname = this.userDetailRepository.findById(id).map(UserDetail::getFirstName);
        firstname = firstname.map(v -> v += "44");
        return firstname;
    }

    @Override
    public Mono<UserDetail> getFirstOrLast() {
        return this.userDetailRepository.findAll().collect(Collectors.reducing((i1, i2) -> i1)).map(Optional::get);
    }

    @Override
    public Flux<UserDetail> filterList() {
        return this.userDetailRepository.findAll().filter(v -> v.getPosition().equals("Dev"));
    }
}
