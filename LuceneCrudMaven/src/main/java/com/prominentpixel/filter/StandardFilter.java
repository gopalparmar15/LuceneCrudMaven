package com.prominentpixel.filter;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.StringReader;

public class StandardFilter {
    void standardFilter()throws IOException
    {
        //create string
        String string="my gmail id - parmargopal055@gmail.com";
        //read string using Standardtokenizer
        Tokenizer tokenizer = new StandardTokenizer(new StringReader(string));
        //create standardFilter to token
        TokenStream tokenStream=new org.apache.lucene.analysis.standard.StandardFilter(tokenizer);
        //add token
        TermAttribute termAttribute=tokenStream.addAttribute(TermAttribute.class);
        //while loop
        while (tokenStream.incrementToken())
        {
            //print termAttribute
            System.out.println(termAttribute);
        }
    }
    public static void main(String[] args) throws IOException {
        //standard filter class object
        StandardFilter standardFilter=new StandardFilter();
        //call standardFilter method
        standardFilter.standardFilter();
    }

}
