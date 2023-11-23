package com.project.schedulerinfo.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LokoInfo {
    private UUID kodeLoko;
    private String namaLoko;
    private String dimensiLoko;
    private String status;
    private LocalDate tanggal;
    private LocalTime jam;
}
