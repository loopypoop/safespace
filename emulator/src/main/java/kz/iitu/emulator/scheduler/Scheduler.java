package kz.iitu.emulator.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iitu.emulator.model.Indicator;
import kz.iitu.emulator.service.IntegrationService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Random;


@Component
@AllArgsConstructor
public class Scheduler {

    private final IntegrationService integrationService;

    @Scheduled(initialDelay = 1000L, fixedDelay = 30000L)
    public void sendIndicatorsScheduled() {
        List<String> users = Arrays.asList("adilili", "detest", "user1", "user2", "user3");

        for (String username : users) {
            this.integrationService.getTokenWebClient(username, "123").subscribe(token -> {
                String[] split_string = token.split("\\.");
                String base64EncodedBody = split_string[1];
                String body = new String(Base64.getUrlDecoder().decode(base64EncodedBody));

                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode;
                Indicator indicator;
                try {
                    rootNode = mapper.readTree(body);

                    indicator = Indicator.builder()
                            //95-100
                            .bloodOxygen(getRandomDouble(93.0, 99.5))
                            //90-125
                            .upperBloodPressure(getRandomInt(89, 125))
                            //65-90
                            .lowerBloodPressure(getRandomInt(64, 90))
                            //36.5-37.0
                            .temperature(getRandomDouble(36.5, 37.0))
                            //55-90
                            .heartRate(getRandomInt(53, 90))
                            .checkTime(new Timestamp(System.currentTimeMillis()))
                            .userId(Long.parseLong(String.valueOf(rootNode.get("id"))))
                            .isLast(true)
                            .build();

                    this.integrationService.sendIndicators(indicator, token).subscribe(System.out::println);

                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
        }
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
