package com.prominentpixel.filter;

import org.apache.lucene.analysis.LengthFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.WhitespaceTokenizer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;

import java.io.IOException;
import java.io.StringReader;

public class LenghtFilter {
    void lenghtFilter() throws IOException {
        //create string
        String s="my name is parmar gopal manojbhai nitin nasit";
        //read string using whitespaceTokenizer
        Tokenizer tokenizer=new WhitespaceTokenizer(new StringReader(s));
        //store 2to5 latter token value using lenghtfilter
        TokenStream tokenStream= new LengthFilter(tokenizer, 2, 5);
        //add token using
        TermAttribute termAttribute=tokenStream.addAttribute(TermAttribute.class);
        //while loop
        while (tokenStream.incrementToken())
        {
            //print termAttribute
            System.out.println(termAttribute);
        }
    }
    public static void main(String[] args) throws IOException {
        //lenghtFilter class  create object
        LenghtFilter lenghtFilter=new LenghtFilter();
        //call lenghtFilter() method
        lenghtFilter.lenghtFilter();
    }
}
