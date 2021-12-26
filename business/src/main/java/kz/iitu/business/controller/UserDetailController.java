package kz.iitu.business.controller;

import kz.iitu.business.model.User;
import kz.iitu.business.model.UserDetail;
import kz.iitu.business.repository.UserDetailRepository;
import kz.iitu.business.service.IUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/user-detail")
public class UserDetailController {

    @Autowired
    private IUserDetailService userDetailService;

    @GetMapping("/{userId}")
    public Mono<ResponseEntity<UserDetail>> getByUserId(@PathVariable Long userId) {
        return userDetailService.getByUserId(userId).map(ResponseEntity::ok);
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<?>> updateUserDetail(@RequestBody UserDetail userDetail) {
        try {
            return Mono.just(ResponseEntity.ok(userDetailService.updateUser(userDetail)));
        } catch (Exception e) {
            System.out.println(e);
            return Mono.just(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

//    @PutMapping("/update/{userId}")
//    public Mono<ResponseEntity<?>> updateUserDetail(@PathVariable Long userId,  @RequestBody UserDetail userDetail) {
//        try {
//            if (userDetailService.updateUser(userDetail) != null) {
//                Mono<UserDetail> curUserDetail = userDetailService.getByUserId(userId);
//
//                return Mono.just(ResponseEntity.ok(userDetailService.updateUser(userDetail)));
//            } else {
//                return Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//            return Mono.just(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
//        }
//    }

    @GetMapping("/users")
    public Flux<UserDetail> getAllUsersByPagination(@RequestParam Map<String,String> params) {
        try {
            return userDetailService.getAllUsersByPagination(params);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
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
