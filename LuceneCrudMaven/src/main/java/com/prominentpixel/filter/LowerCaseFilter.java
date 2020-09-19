package com.prominentpixel.filter;
import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;


import java.io.IOException;
import java.io.StringReader;

public class LowerCaseFilter {
    void lowerCaseFilter() throws IOException {
        //create string
        String s="MY GMAIL - ID PARMARGOPAL@055.COM ";
        //read string using whitespaceTokenizer
        Tokenizer tokenizer=new WhitespaceTokenizer(new StringReader(s));
        //create LowercaseFilter to token
        TokenStream tokenStream=new org.apache.lucene.analysis.LowerCaseFilter(tokenizer);
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
        //lowercase filter class object
        LowerCaseFilter lowerCaseFilter=new LowerCaseFilter();
        //call lowercaseFilter method
        lowerCaseFilter.lowerCaseFilter();
    }
}
