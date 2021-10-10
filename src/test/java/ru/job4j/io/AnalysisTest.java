package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalysisTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenAlwaysAvailable() throws IOException {
        File source = folder.newFile("source.csv");
        File target = folder.newFile("target.csv");
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
        assertThat(sb.toString(), is(""));
    }

    @Test
    public void when1Diapason() throws IOException {
        File source = folder.newFile("source.csv");
        File target = folder.newFile("target.csv");
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
        assertThat(sb.toString(), is("10:57:01;10:59:01;" + "11:01:02;11:02:02;"));
    }

    @Test
    public void when2Diapasons() throws IOException {
        File source = folder.newFile("source.csv");
        File target = folder.newFile("target.csv");
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
        assertThat(sb.toString(), is("10:57:01;11:02:02;"));
    }

    @Test
    public void whenNotAvailableNow() throws IOException {
        File source = folder.newFile("source.csv");
        File target = folder.newFile("target.csv");
        try (PrintWriter pw = new PrintWriter(source)) {
            pw.println("200 10:56:01");
            pw.println("500 10:57:01");
        }
        new Analysis().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(target))) {
            br.lines().forEach(sb::append);
        }
        assertThat(sb.toString(), is("10:57:01;"));
    }
}
