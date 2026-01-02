# MapReduce Word Count (Hadoop)

## Overview
Word counting application using Hadoop MapReduce framework. Demonstrates distributed computing concepts with Mapper and Reducer classes.

## Files
- **WordCount.java** - Main MapReduce program with Mapper and Reducer
- **input.txt** - Sample input file

## Code Explanation

### WordMapper Class
```java
extends Mapper<LongWritable, Text, Text, IntWritable>
// Input: (line number, line text)
// Output: (word, 1)

map(key, value, context) {
    words = value.split(" ")
    for each word:
        context.write(word, 1)
}
```

### WordReducer Class
```java
extends Reducer<Text, IntWritable, Text, IntWritable>
// Input: (word, [1,1,1,...])
// Output: (word, total_count)

reduce(key, values, context) {
    sum = 0
    for each value:
        sum += value
    context.write(key, sum)
}
```

### Main Method
```java
Job job = new Job(conf, "wordcount")
job.setMapperClass(WordMapper.class)
job.setReducerClass(WordReducer.class)
job.setOutputKeyClass(Text.class)
job.setOutputValueClass(IntWritable.class)
FileInputFormat.addInputPath(job, input)
FileOutputFormat.setOutputPath(job, output)
job.waitForCompletion(true)
```

## MapReduce Flow
```
Input Files → Split → Map → Shuffle & Sort → Reduce → Output
     |          |       |         |            |          |
  "hello"   chunk1   (hello,1)  (hello,[1,1]) (hello,2)  result
  "hello"   chunk2   (hello,1)
```

## How to Run
```bash
# Compile
javac -classpath $(hadoop classpath) WordCount.java
jar cf wordcount.jar WordCount*.class

# Run on Hadoop
hadoop jar wordcount.jar WordCount /input /output

# View results
hadoop fs -cat /output/part-r-00000
```

---

## Viva Questions

### Basic Questions
1. **What is MapReduce?**
   - Programming model for processing large datasets in parallel across a cluster using map and reduce functions.

2. **What is the Mapper?**
   - Processes input key-value pairs and produces intermediate key-value pairs.

3. **What is the Reducer?**
   - Aggregates intermediate values for each key and produces final output.

4. **What is Hadoop?**
   - Open-source framework for distributed storage (HDFS) and processing (MapReduce) of large datasets.

5. **What are the input/output types for Mapper?**
   - Input: LongWritable (offset), Text (line)
   - Output: Text (word), IntWritable (count)

### Intermediate Questions
6. **Explain the Shuffle and Sort phase.**
   - Framework groups all values for same key together and sorts by key before sending to Reducer.

7. **Why use Text instead of String?**
   - Text is Hadoop's serializable wrapper for String, optimized for network transfer.

8. **What is IntWritable?**
   - Hadoop's serializable wrapper for int type.

9. **What is a Combiner?**
   - Mini-reducer that runs on mapper output to reduce data transfer (local aggregation).

10. **How many Reducers are used by default?**
    - 1 reducer, but can be configured with job.setNumReduceTasks(n).

### Advanced Questions
11. **What is HDFS?**
    - Hadoop Distributed File System - stores large files across multiple machines with replication.

12. **What is data locality in MapReduce?**
    - Moving computation to where data resides to minimize network transfer.

13. **What is speculative execution?**
    - Running duplicate tasks on different nodes to handle slow nodes (stragglers).

14. **What is InputSplit?**
    - Logical division of input data, typically 128MB (HDFS block size).

15. **Difference between InputFormat and OutputFormat?**
    - InputFormat: How to read and split input (TextInputFormat, etc.)
    - OutputFormat: How to write output (TextOutputFormat, etc.)

16. **What happens if Reducer fails?**
    - Job tracker reschedules the task on another node.

17. **What is partitioner in MapReduce?**
    - Determines which reducer receives which key (default: hash-based).
