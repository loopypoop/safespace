package kz.iitu.business.service.impl;

import kz.iitu.business.model.PageSupport;
import kz.iitu.business.model.UserDetail;
import kz.iitu.business.repository.UserDetailRepository;
import kz.iitu.business.repository.UserRepository;
import kz.iitu.business.service.IUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements IUserDetailService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private UserRepository userRepository;

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

    public Mono<PageSupport<UserDetail>> getAllUsersByPagination(Map<String, String> params) {
        PageRequest pageRequest = createPageRequest(params);
        AtomicReference<Long> size = new AtomicReference<>(0L);
        if (!params.containsKey("search")) {
            this.userDetailRepository.countUsers().subscribe(size::set);

            return userDetailRepository.findAllUsers(pageRequest)
                    .collectList()
                    .map(list -> new PageSupport<>(
                            list
                                    .stream()
                                    .collect(Collectors.toList()),
                            pageRequest.getPageNumber(), pageRequest.getPageSize(), size.get()
                    ));
        } else {
            this.userDetailRepository.countUsersSearch("%" + params.get("search") + "%").subscribe(size::set);

            return userDetailRepository.findUsersSearch(pageRequest, "%" + params.get("search") + "%")
                    .collectList()
                    .map(list -> new PageSupport<>(
                            list
                                    .stream()
                                    .collect(Collectors.toList()),
                            pageRequest.getPageNumber(), pageRequest.getPageSize(), size.get()
                    ));
        }

    }

    public Flux<UserDetail> getAllUsers() {
        return userDetailRepository.findAll();
    }

    public Mono<UserDetail> updateUser(UserDetail userDetail) {
        return userDetailRepository.save(userDetail);
    }

    @Override
    public Mono<UserDetail> getFirstOrLast() {
        return this.userDetailRepository.findAll().collect(Collectors.reducing((i1, i2) -> i1)).map(Optional::get);
    }

    @Override
    public Flux<UserDetail> filterList() {
        return this.userDetailRepository.findAll().filter(v -> v.getPosition().equals("Dev"));
    }

    private PageRequest createPageRequest(Map<String, String> params) {
        int page = 0;
        int size = 5;
        Sort sort = Sort.by("id");
        if (params.containsKey("page") && params.containsKey("size")) {
            page = Integer.parseInt(params.get("page"));
            size = Integer.parseInt(params.get("size"));
        }
        if (params.containsKey("sortBy"))
            sort = Sort.by(params.get("sortBy"));
        if (params.containsKey("sortDirection")) {
            if (params.get("sortDirection").equals("asc")) {
                sort.ascending();
            } else {
                sort.descending();
            }
        }

        return PageRequest.of(page, size, sort);
    }
}
