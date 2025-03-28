package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class AnalysisTest {

    @Test
    public void whenAlwaysAvailable(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.csv").toFile();
        File target = tempDir.resolve("target.csv").toFile();
        try (PrintWriter pw = new PrintWriter(source)) {
            pw.println("200 10:56:01");
            pw.println("200 10:57:01");
            pw.println("300 10:58:01");
            pw.println("200 10:59:01");
            pw.println("300 11:01:02");
            pw.println("200 11:02:02");
        }
        new Analysis().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(target))) {
            br.lines().forEach(sb::append);
        }
        assertThat(sb.toString()).isEqualTo("");
    }

    @Test
    public void when1Diapason(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.csv").toFile();
        File target = tempDir.resolve("target.csv").toFile();
        try (PrintWriter pw = new PrintWriter(source)) {
            pw.println("200 10:56:01");
            pw.println("500 10:57:01");
            pw.println("400 10:58:01");
            pw.println("200 10:59:01");
            pw.println("500 11:01:02");
            pw.println("200 11:02:02");
        }
        new Analysis().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(target))) {
            br.lines().forEach(sb::append);
        }
        assertThat(sb.toString()).isEqualTo("10:57:01;10:59:01;" + "11:01:02;11:02:02;");
    }

    @Test
    public void when2Diapasons(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.csv").toFile();
        File target = tempDir.resolve("target.csv").toFile();
        try (PrintWriter pw = new PrintWriter(source)) {
            pw.println("200 10:56:01");
            pw.println("500 10:57:01");
            pw.println("400 10:58:01");
            pw.println("500 10:59:01");
            pw.println("400 11:01:02");
            pw.println("200 11:02:02");
        }
        new Analysis().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(target))) {
            br.lines().forEach(sb::append);
        }
        assertThat(sb.toString()).isEqualTo("10:57:01;11:02:02;");
    }

    @Test
    public void whenNotAvailableNow(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.csv").toFile();
        File target = tempDir.resolve("target.csv").toFile();
        try (PrintWriter pw = new PrintWriter(source)) {
            pw.println("200 10:56:01");
            pw.println("500 10:57:01");
        }
        new Analysis().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(target))) {
            br.lines().forEach(sb::append);
        }
        assertThat(sb.toString()).isEqualTo("10:57:01;");
    }
}
