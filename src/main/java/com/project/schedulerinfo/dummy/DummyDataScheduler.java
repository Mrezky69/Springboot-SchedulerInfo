package com.project.schedulerinfo.dummy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.project.schedulerinfo.model.LokoInfo;

@Component
public class DummyDataScheduler {

    @Autowired
    private KafkaTemplate<String, LokoInfo> kafkaTemplate;

    private Random random = new Random();

    @Scheduled(fixedRate = 10000)
    public void createDummyData() {

        UUID kodeLoko = UUID.randomUUID();
        String namaLoko = generateRandomName();
        String dimensiLoko = generateRandomDimensions();
        String status = generateRandomStatus();
        LocalDate tanggal = LocalDate.now();
        LocalTime jam = LocalTime.now();

        LokoInfo lokoInfo = new LokoInfo(kodeLoko, namaLoko, dimensiLoko, status, tanggal, jam);

        kafkaTemplate.send("data-dummy", lokoInfo);

        System.out.println("Test Dummy Data : " + lokoInfo);
    }

    private String generateRandomName() {
        String[] namaAcak = { "Loko Express", "Loko Cepat", "Loko Baru", "Loko Perdana" };
        int randomIndex = random.nextInt(namaAcak.length);
        return namaAcak[randomIndex];
    }

    private String generateRandomDimensions() {
        int panjang = random.nextInt(10) + 1;
        int lebar = random.nextInt(5) + 1;
        int tinggi = random.nextInt(4) + 1;
        return panjang + "m x " + lebar + "m x " + tinggi + "m";
    }

    private String generateRandomStatus() {
        String[] statusOptions = { "Aktif", "Tidak Aktif", "Dalam Pemeliharaan" };
        int randomIndex = random.nextInt(statusOptions.length);
        return statusOptions[randomIndex];
    }
}
