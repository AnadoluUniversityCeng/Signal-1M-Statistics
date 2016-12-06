package edu.ceng;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Month;
import java.util.IntSummaryStatistics;

/**
 * Helper class
 */
class Helper {

    private Helper() {
    }

    static void printDistinctMonths(String file) throws IOException {
        Files.lines(Paths.get(file), StandardCharsets.US_ASCII)
                .map(new JSonMapper())
                .map(Article::getMonth)
                .distinct()
                .forEach(System.out::println);
    }

    static void printStats(String file, FIELD field, Month month, TYPE type) throws IOException {

        IntSummaryStatistics summaryStatistics = Files.lines(Paths.get(file), StandardCharsets.US_ASCII)
                .map(new JSonMapper())
                .filter(new TypeFilter(type))
                .filter(new MonthFilter(month))
                .map(new FieldSelector(field))
                .mapToInt(new CountFunction())
                .summaryStatistics();

        System.out.println("numArticles:" + summaryStatistics.getCount());
        System.out.println("wordCount:" + summaryStatistics.getSum());
        System.out.println(String.format("averageLength:%.5f", summaryStatistics.getAverage()));

    }
}
