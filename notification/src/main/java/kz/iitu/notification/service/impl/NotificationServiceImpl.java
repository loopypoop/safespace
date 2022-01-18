package kz.iitu.notification.service.impl;

import kz.iitu.notification.model.Notification;
import kz.iitu.notification.repository.NotificationRepository;
import kz.iitu.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public Mono<Integer> getNewNotificationCount(Long userId) {
        return this.notificationRepository.countAllByUserIdAndIsSeen(userId, false);
    }

    @Override
    public Flux<Notification> getAllPageable(Long userId, Map<String, String> map) {
        PageRequest pageRequest = createPageRequest(map);
        if (map.containsKey("topic")) {
            String topic = map.get("topic");
            return this.notificationRepository.findAllByUserIdAndTopic(userId, topic, pageRequest);
        } else {
            return this.notificationRepository.findAllByUserId(userId, pageRequest);
        }
    }

    @Override
    public Mono<Notification> getNotification(Long id) {
        return this.notificationRepository.findById(id).map(v -> {
            if (!v.isSeen()) {
                v.setSeen(true);
                this.notificationRepository.save(v).subscribe();
            }
            return v;
        });
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
