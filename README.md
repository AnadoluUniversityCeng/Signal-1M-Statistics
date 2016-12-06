# Document Statistics of the The Signal Media One-Million News Articles Dataset 

The [Signal Media One-Million News Articles Dataset](http://research.signalmedia.co/newsir16/signal-dataset.html) was released to facilitate conducting research on news articles.
The dataset is shipped as a single text file having 1000000 lines each of which is a JSON object representing an article. 
Each article has the following fields:

* **id**: a unique identifier for the article
* **title**: the title of the article
* **content**: the textual content of the article (may occasionally contain HTML and JavaScript content)
* **source**: the name of the article source (e.g. Reuters)
* **published**: the publication date of the article
* **media-type**: either "News" or "Blog"
Below is an excerpt from the dataset: (content has been truncated)

``` json
{
  "id": "e9c0f76d-0199-4dbf-a523-51b7f45a134b",
  "content": "This special edition of Fund This features what very well may be the most epic geek campaign ever.",
  "title": "Fund This! One Campaign to Rule Them All!",
  "media-type": "Blog",
  "source": "GeekMom",
  "published": "2015-08-20T14:00:41Z"
}
```

To obtain the dataset (`sample-1M.jsonl`), please follow the instructions given [here](http://research.signalmedia.co/newsir16/signal-dataset.html).

In this homework, you will calculate average document length of articles that meet certain criteria supplied by the user.
The user would like to filter/select articles according their type (News or Blog) and publication month (SEPTEMBER AUGUST JULY).
Moreover, the user would like to specify a field name (title or content) whose average length will be calculated.

For example, if the user would like find out the average *title* length of the *Blog* articles published on *July*, she will issue:

* java -jar target/HW3-1.0.jar -file /Users/iorixxx/Downloads/sample-1M.jsonl -field title -month JULY -type Blog

and the program will print out something like:

```
numArticles:1040
wordCount:7626
averageLength:7.33269
```

Another example, if the user would like find out the average *content* length of the *News* articles published on *September*, she will issue:

* java -jar target/HW3-1.0.jar -file /Users/iorixxx/Downloads/sample-1M.jsonl -field content -month SEPTEMBER -type News

and the program will print out something like:

```
numArticles:732504
wordCount:290660073
averageLength:396.80339
```

The starter code contains all the useful stuff: Enums (TYPE, FIELD) for the command line arguments, Article class that encapsulate articles, CountFunction that returns the number of words in a string and the following helper method:
``` java
static void printStats(String file, FIELD field, java.time.Month month, TYPE type) throws IOException {

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
```

You need to:

* Implement `JSonMapper`, `TypeFilter`, `MonthFilter` and `FieldSelector` classes and add them to the source tree. 
* Modify the `Main.java` file to make things run as stated.

To parse command line arguments please use [args4j](https://github.com/kohsuke/args4j/blob/master/args4j-maven-plugin-example/src/main/java/org/kohsuke/args4j/maven/Example.java), which is already included in the pom.xml file.
In `JSonMapper`, to convert/map a JSon string to an Article object please use [google-gson](https://github.com/google/gson), which is already included in the pom.xml file.

* Do not modify the `Helper.java` file
* Do not modify the `CountFunction.java` file
* Do not modify the `Article.java` file
* Do not modify the `pom.xml` file

Note that the assessment will be solely based on `Main.java` and new files (`JSonMapper`, `TypeFilter`, `MonthFilter`, `FieldSelector`) to be added by you.