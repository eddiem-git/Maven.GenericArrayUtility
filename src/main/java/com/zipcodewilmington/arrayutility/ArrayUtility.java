package com.zipcodewilmington.arrayutility;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<I extends Object> {
    private final I[] inputArray;

    public ArrayUtility(I[] inputArray) {
        this.inputArray = inputArray;
    }

    public I getMostCommonFromMerge(I[] arrayToMerge) {
        List<I> mergedArray = Stream.concat(Stream.of(inputArray), Stream.of(arrayToMerge))
                .collect(Collectors.toList());

        Map<I, Long> map = mergedArray
                .stream()
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        long occurence = 0;
        I value = null;
        for (Map.Entry<I, Long> each : map.entrySet()) {
            if (each.getValue() > occurence) {
                occurence = each.getValue();
                value = each.getKey();
            }
        }

        return value;
    }

    public Integer countDuplicatesInMerge(I[] arrayToMerge, I valueToEvaluate) {
        return Stream.concat(Stream.of(inputArray), Stream.of(arrayToMerge))
                .collect(Collectors.toList())
                .stream()
                .filter(i -> i == valueToEvaluate).collect(Collectors.toList()).size();
    }

    public Integer getNumberOfOccurrences(I valueToEvaluate) {
        Integer count = 0;
        for (int currentIndex = 0; currentIndex < inputArray.length; currentIndex++) {
            I currentElement = inputArray[currentIndex];
            if (currentElement.equals(valueToEvaluate)) {
                count++;
            }
        }
        return count;
    }
    public I[] removeValue(I valueToRemove) {
        List<I> array = Arrays.stream(inputArray)
                .filter(i -> i != valueToRemove)
                .collect(Collectors.toList());

        I[] result = Arrays.copyOf(this.inputArray, array.size());

        for (int i = 0; i < array.size(); i++) {
            result[i] = array.get(i);
        }

        return result;
    }
}