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

        }
        public static String generateValidCity() {
            String[] cities = new String[]{"Москва", "Санкт-Петербург", "Новосибирск", "Краснодар", "Екатеринбург"};
            int itemIndex = (int) (Math.random() * cities.length);
            return cities[itemIndex];
        }

        public static String generateInvalidCity() {
            String[] cities = new String[]{"Тайшет", "Киренск", "Благовещенск", "Канск", "Зеленогорск"};
            int itemIndex = (int) (Math.random() * cities.length);
            return cities[itemIndex];
        }

    }
