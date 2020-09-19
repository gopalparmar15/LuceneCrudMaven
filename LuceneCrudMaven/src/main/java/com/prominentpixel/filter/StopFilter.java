package com.prominentpixel.filter;
import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import java.io.IOException;
import java.io.StringReader;

public class StopFilter {
    void stopFilter()throws IOException
    {
        //create string
        String string="my gmail id - parmargopal055@gmail.com";
        //read string usig WhiteSpaceTokenizer
        Tokenizer tokenizer=new WhitespaceTokenizer(new StringReader(string));
        //create StopFilter to token
        TokenStream tokenStream=new org.apache.lucene.analysis.StopFilter(tokenizer, StopAnalyzer.ENGLISH_STOP_WORDS_SET);
        //add token
        TermAttribute termAttribute=tokenStream.addAttribute(TermAttribute.class);
        //while loop define
        while (tokenStream.incrementToken())
        {
            //print termAttribute
            System.out.println(termAttribute);
        }
    }
    public static void main(String[] args) throws IOException {
        //Stopfilter filter class object
        StopFilter stopFilter=new StopFilter();
        //call Stopfilter method
        stopFilter.stopFilter();
    }
}
