package kz.iitu.business.controller;

import kz.iitu.business.model.UserDetail;
import kz.iitu.business.service.IUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user-detail")
public class UserDetailController {

    @Autowired
    private IUserDetailService userDetailService;

    @GetMapping("/{userId}")
    public Mono<ResponseEntity<UserDetail>> getByUserId(@PathVariable Long userId) {
        return userDetailService.getByUserId(userId).map(ResponseEntity::ok);
    }

    @GetMapping("/name/{id}")
    public Mono<ResponseEntity<String>> getFirstName(@PathVariable Long id) {
        return userDetailService.getName(id).map(ResponseEntity::ok);
    }

    @GetMapping("/check")
    public Mono<UserDetail> getFirstOrLast() {
        return userDetailService.getFirstOrLast();
    }

    @GetMapping("/filter")
    public Flux<UserDetail> filterList() {
        return userDetailService.filterList();
    }
}
