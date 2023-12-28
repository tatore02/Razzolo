package test.java.it.razzolodevs.razzolo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

public abstract class UninformedConfiguration extends Configuration {
    protected HashSet<String> dictionary;

    @BeforeEach
    protected void init() throws IOException {
        foundWords = new HashSet<>();
        _buildDictionary();
        start = System.nanoTime();
    }

    @AfterEach
    protected void teardown() {
        final var end = System.nanoTime();
        System.out.format("Found words: %s\n", foundWords);
        System.out.format("Elapsed time: %g s\n", ((double)(end-start))/1000000000);
        for(String s : trueWords)
            if(!foundWords.contains(s))
                System.out.format("%s not found\n",s);
    }

    private void _buildDictionary() throws IOException{
        dictionary = new HashSet<>();
        file.getChannel().position(0);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
        while(bufferedReader.ready())
            dictionary.add(bufferedReader.readLine());
    }
}