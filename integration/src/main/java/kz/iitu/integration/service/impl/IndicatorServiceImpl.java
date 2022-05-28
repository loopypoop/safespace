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
import java.util.Random;

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
                this.indicatorRepository.save(indicator).subscribe(s -> {
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
    public Mono<Indicator> recheck(Indicator indicator, Long userId) {
        if (indicator == null) {
            indicator = Indicator.builder()
                    //95-100
                    .bloodOxygen(getRandomDouble(94.0, 99.5))
                    //90-125
                    .upperBloodPressure(getRandomInt(89, 125))
                    //65-90
                    .lowerBloodPressure(getRandomInt(64, 90))
                    //36.5-37.0
                    .temperature(getRandomDouble(36.5, 37.0))
                    //55-90
                    .heartRate(getRandomInt(53, 90))
                    .checkTime(new Timestamp(System.currentTimeMillis()))
                    .userId(userId)
                    .isLast(true)
                    .build();
        }
        indicator.setIsLast(true);
        Indicator finalIndicator = indicator;
        return this.indicatorRepository.findByUserIdAndIsLast(indicator.getUserId(), true)
                .map(v -> {
                    finalIndicator.setId(v.getId());
                    this.indicatorRepository.save(finalIndicator).subscribe(s -> {

                        int count = 0;
                        String message = "";
                        if (finalIndicator.getTemperature() > 37.5) {
                            count++;
                            message = "Temperature is too high! Please check your indicators again.";
                            System.out.println("Temperature is too high!");
                        }
                        if (finalIndicator.getBloodOxygen() < 95.0) {
                            count++;
                            message = "Blood oxygen is too low! Please check your indicators again.";
                            System.out.println("Blood oxygen is too low!");
                        }
                        if (finalIndicator.getUpperBloodPressure() > 140) {
                            count++;
                            message = "Upper blood pressure is too high! Please check your indicators again.";
                            System.out.println("Upper blood pressure is too high!");
                        }
                        if (finalIndicator.getHeartRate() > 90 || finalIndicator.getHeartRate() < 55) {
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
                                    .userId(finalIndicator.getUserId())
                                    .build();

                            this.notificationIntegrationService.createNotification(notificationEmployee);

                            //Sending notification to doctors
                            this.userDetailRepository.getByUserId(finalIndicator.getUserId()).subscribe(n -> {
                                Notification notificationDoctor = Notification.builder()
                                        .createdAt(new Timestamp(System.currentTimeMillis()))
                                        .topic("Employee with abnormal indicators")
                                        .content("Please check for employee " + n.getFirstName() + " " + n.getLastName() + ", ID: " + n.getUserId()
                                                + ". The employee has abnormal indicators.")
                                        .isSeen(false)
                                        .build();
                                this.userRepository.getAllByRoleAndIsActive("Doctor", true).subscribe(u -> {
                                    notificationDoctor.setUserId(u.getId());
                                    this.notificationIntegrationService.createNotification(notificationDoctor);
                                });
                            });
                        }
                    });
                    return finalIndicator;
                });
    }

    public double getRandomDouble(double min, double max) {
        Random random = new Random();
        return round(min + (max - min) * random.nextDouble(), 2);
    }

    public int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
