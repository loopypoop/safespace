package kz.iitu.integration.service.impl;

import kz.iitu.integration.model.Indicator;
import kz.iitu.integration.model.Notification;
import kz.iitu.integration.repository.IndicatorRepository;
import kz.iitu.integration.repository.UserDetailRepository;
import kz.iitu.integration.repository.UserRepository;
import kz.iitu.integration.service.IndicatorService;
import kz.iitu.integration.service.notification.NotificationIntegrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class IndicatorServiceImpl implements IndicatorService {

    private final IndicatorRepository indicatorRepository;

    private final UserRepository userRepository;

    private final UserDetailRepository userDetailRepository;

    private final NotificationIntegrationService notificationIntegrationService;

    @Override
    public void addIndicator(Indicator indicator) {
        indicator.setIsLast(true);
        this.indicatorRepository.existsByUserId(indicator.getUserId()).subscribe(exists -> {
            if (!exists) {
                System.out.println("1 step: " + exists);
                this.indicatorRepository.save(indicator).subscribe(s -> {
                    System.out.println("2 step: " + exists);

                    int count = 0;
                    String message = "";
                    if (indicator.getTemperature() > 37.0 || indicator.getTemperature() < 36.5) {
                        count++;
                        message = "Temperature is too high! Please check your indicators again.";
                        System.out.println("Temperature is too high!");
                    }
                    if (indicator.getBloodOxygen() < 95.0) {
                        count++;
                        message = "Blood oxygen is too low! Please check your indicators again.";
                        System.out.println("Blood oxygen is too low!");
                    }
                    if (indicator.getUpperBloodPressure() > 125 || indicator.getUpperBloodPressure() < 90) {
                        count++;
                        message = "Upper blood pressure is too high/low! Please check your indicators again.";
                        System.out.println("Upper blood pressure is too high/low!");
                    }
                    if (indicator.getLowerBloodPressure() > 90 || indicator.getLowerBloodPressure() < 65) {
                        count++;
                        message = "Lower blood pressure is too high/low! Please check your indicators again.";
                        System.out.println("Lower blood pressure is too high/low!");
                    }
                    if (indicator.getHeartRate() > 90 || indicator.getHeartRate() < 55) {
                        count++;
                        message = "Heart rate is too high/low! Please check your indicators again.";
                        System.out.println("Heart rate is too high/low!");
                    }

                    if (count > 1) {
                        message = "Some of your indicators are abnormal. Please check it again!";
                    }

                    if (count != 0) {
                        //Sending notification to employee
                        Notification notificationEmployee = Notification.builder()
                                .createdAt(new Timestamp(System.currentTimeMillis()))
                                .topic("Abnormal indicators")
                                .content(message)
                                .isSeen(false)
                                .userId(indicator.getUserId())
                                .build();

                        this.notificationIntegrationService.createNotification(notificationEmployee);
                    }
                });
            } else {
                this.indicatorRepository.findByUserIdAndIsLast(indicator.getUserId(), true)
                        .subscribe(v -> {
                            v.setIsLast(false);
                            this.indicatorRepository.save(v).subscribe(s -> {

                                int count = 0;
                                String message = "";
                                if (indicator.getTemperature() > 37.0 || indicator.getTemperature() < 36.5) {
                                    count++;
                                    message = "Temperature is too high! Please check your indicators again.";
                                    System.out.println("Temperature is too high!");
                                }
                                if (indicator.getBloodOxygen() < 95.0) {
                                    count++;
                                    message = "Blood oxygen is too low! Please check your indicators again.";
                                    System.out.println("Blood oxygen is too low!");
                                }
                                if (indicator.getUpperBloodPressure() > 125 || indicator.getUpperBloodPressure() < 90) {
                                    count++;
                                    message = "Upper blood pressure is too high/low! Please check your indicators again.";
                                    System.out.println("Upper blood pressure is too high/low!");
                                }
                                if (indicator.getLowerBloodPressure() > 90 || indicator.getLowerBloodPressure() < 65) {
                                    count++;
                                    message = "Lower blood pressure is too high/low! Please check your indicators again.";
                                    System.out.println("Lower blood pressure is too high/low!");
                                }
                                if (indicator.getHeartRate() > 90 || indicator.getHeartRate() < 55) {
                                    count++;
                                    message = "Heart rate is too high/low! Please check your indicators again.";
                                    System.out.println("Heart rate is too high/low!");
                                }

                                if (count > 1) {
                                    message = "Some of your indicators are abnormal. Please check it again!";
                                }

                                if (count != 0) {
                                    Notification notification = Notification.builder()
                                            .createdAt(new Timestamp(System.currentTimeMillis()))
                                            .topic("Abnormal indicators")
                                            .content(message)
                                            .isSeen(false)
                                            .userId(indicator.getUserId())
                                            .build();
                                    this.notificationIntegrationService.createNotification(notification);
                                }
                                this.indicatorRepository.save(indicator).subscribe();
                            });
                        });
            }
        });
    }

    @Override
    public Mono<Indicator> recheck(Indicator indicator) {
        indicator.setIsLast(true);
        return this.indicatorRepository.findByUserIdAndIsLast(indicator.getUserId(), true)
                .map(v -> {
                    indicator.setId(v.getId());
                    this.indicatorRepository.save(indicator).subscribe(s -> {

                        int count = 0;
                        String message = "";
                        if (indicator.getTemperature() > 37.5) {
                            count++;
                            message = "Temperature is too high! Please check your indicators again.";
                            System.out.println("Temperature is too high!");
                        }
                        if (indicator.getBloodOxygen() < 95.0) {
                            count++;
                            message = "Blood oxygen is too low! Please check your indicators again.";
                            System.out.println("Blood oxygen is too low!");
                        }
                        if (indicator.getUpperBloodPressure() > 140) {
                            count++;
                            message = "Upper blood pressure is too high! Please check your indicators again.";
                            System.out.println("Upper blood pressure is too high!");
                        }
                        if (indicator.getHeartRate() > 90 || indicator.getHeartRate() < 55) {
                            count++;
                            message = "Heart rate is too high/low! Please check your indicators again.";
                            System.out.println("Heart rate is too high/low!");
                        }

                        if (count > 1) {
                            message = "Some of your indicators are abnormal. Please check it again!";
                        }

                        if (count != 0) {
                            //Sending notification to employee
                            Notification notificationEmployee = Notification.builder()
                                    .createdAt(new Timestamp(System.currentTimeMillis()))
                                    .topic("Abnormal indicators")
                                    .content(message)
                                    .isSeen(false)
                                    .userId(indicator.getUserId())
                                    .build();

                            this.notificationIntegrationService.createNotification(notificationEmployee);

                            //Sending notification to doctors
                            this.userDetailRepository.getByUserId(indicator.getUserId()).subscribe(n -> {
                                Notification notificationDoctor = Notification.builder()
                                        .createdAt(new Timestamp(System.currentTimeMillis()))
                                        .topic("Employee with abnormal indicators")
                                        .content("Please check for employee " + n.getFirstName() + " " + n.getLastName() + ", ID: " + n.getUserId()
                                                + ". The employee has abnormal indicators.")
                                        .isSeen(false)
                                        .build();
                                this.userRepository.getAllByRole("Doctor").subscribe(u -> {
                                    notificationDoctor.setUserId(u.getId());
                                    this.notificationIntegrationService.createNotification(notificationDoctor);
                                });
                            });
                        }
                    });
                    return indicator;
                });
    }
}
