package kz.iitu.emulator.scheduler;

import kz.iitu.emulator.service.IntegrationService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
@AllArgsConstructor
public class Scheduler {

    private final IntegrationService integrationService;

    @Scheduled(initialDelay = 1000L, fixedDelay = 3000L)
    public void sendIndicators() {

        this.integrationService.getTokenWebClient("adil", "123").subscribe(System.out::println);

        System.out.println("Random between 1 and 100: " + getRandomDouble(1.0, 100.0));
        System.out.println("Random between 101 and 200: " + getRandomInt(101, 200));
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
