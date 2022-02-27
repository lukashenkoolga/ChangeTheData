package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {

    }

    public static class Registration {
        public static String generateName(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return faker.name().lastName() + " " + faker.name().firstName();
        }


        public static String getDate(int shift) {
            return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public static String generatePhone(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return faker.phoneNumber().phoneNumber();
        }

        public static String generateCity(String ru) {
            Faker faker = new Faker(new Locale("ru"));
            return faker.address().city();
        }

        private Registration() {
        }
    }
}
