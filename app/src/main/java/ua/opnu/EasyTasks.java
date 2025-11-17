package ua.opnu;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.Arrays;

public class EasyTasks {

    public static void main(String[] args) {
    }
    private static final int DIGIT_TO_AVOID = 9;
    private static final int BASE = 10;

    public List<Integer> doubling(List<Integer> input) {
        List<Integer> result = new ArrayList<>();
        for (Integer number : input) {
            result.add(number * 2);
        }
        return result;
    }

    public List<Integer> square(List<Integer> input) {
        List<Integer> result = new ArrayList<>();
        for (Integer number : input) {
            result.add(number * number);
        }
        return result;
    }

    public List<String> moreY(List<String> strings) {
        List<String> result = new ArrayList<>();
        for (String s : strings) {
            result.add("y" + s + "y");
        }
        return result;
    }

    public List<Integer> noNeg(List<Integer> input) {
        List<Integer> result = new ArrayList<>();
        for (Integer number :input) {
            if (number >= 0) {
                result.add(number);
            }
        }
        return result;
    }

    public List<Integer> no9(List<Integer> input) {
        List<Integer> result = new ArrayList<>();
        for (Integer number : input) {
            if (Math.abs(number) % BASE != DIGIT_TO_AVOID) {
                result.add(number);
            }
        }
        return result;
    }

    public List<String> noZ(List<String> strings) {
        List<String> result = new ArrayList<>();
        for (String s : strings) {
            if (!s.contains("z")) {
                result.add(s);
            }
        }
        return result;
    }

    public List<String> refinedStrings(List<String> input) {
        return input.stream()
                .distinct()
                .sorted(Comparator.comparing(String::length).reversed())
                .collect(Collectors.toList());
    }

    public List<String> flatten(List<String> strings) {
        return strings.stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .collect(Collectors.toList());
    }
}
