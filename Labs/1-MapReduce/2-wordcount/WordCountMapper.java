import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    //hadoop supported data types                                                                                                                                                          
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //taking one line at a time and tokenizing the same                                                                                                                                
        String line = value.toString();
        StringTokenizer tokenizer = new StringTokenizer(line);

        //iterating through all the words available in that line and forming the key value pair                                                                                            
        while (tokenizer.hasMoreTokens()) {
            word.set(tokenizer.nextToken());
            //sending to output collector which inturn passes the same to reducer                                                                                                          
            context.write(word, one);
        }
    }

}