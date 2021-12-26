package kz.iitu.business.service.impl;

import kz.iitu.business.model.User;
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

    public Flux<UserDetail> getAllUsersByPagination(Map<String, String> params) {
        PageRequest request = createPageRequest(params);
        return userDetailRepository.findAllUsers(request);
    }

    private PageRequest createPageRequest(Map<String, String> params) {
        int page;
        int size;
        Sort sort = Sort.by("id");
        page = Integer.parseInt(params.get("page"));
        size = Integer.parseInt(params.get("size"));
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


    @Override
    public Mono<UserDetail> getFirstOrLast() {
        return this.userDetailRepository.findAll().collect(Collectors.reducing((i1, i2) -> i1)).map(Optional::get);
    }

    @Override
    public Flux<UserDetail> filterList() {
        return this.userDetailRepository.findAll().filter(v -> v.getPosition().equals("Dev"));
    }
}
