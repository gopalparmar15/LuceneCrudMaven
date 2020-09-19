package com.prominentpixel.anlyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.StringReader;



public class SimpleAnalyzer {
    void simpleAnalyzer() throws IOException
    {
        //create string
        String text="MY NAME IS GOPAL PARMAR123";
        //create simpleAnalyzer
        Analyzer analyzer=new org.apache.lucene.analysis.core.SimpleAnalyzer();
        //create token in token stream
        TokenStream tokenStream=analyzer.tokenStream("content",new StringReader(text));
        //add token using
          TermAttribute termAttribute=tokenStream.addAttribute(TermAttribute.class);
        //while loop
        while (tokenStream.incrementToken())
        {
            //print termAttribute
            System.out.println(tokenStream);
        }

    }
    public static void main(String[] args) throws IOException {
        //SimpleAnalyzer class object
    SimpleAnalyzer simpleAnalyzer=new SimpleAnalyzer();
    //simpleAnalyzer method call
     simpleAnalyzer.simpleAnalyzer();
    }
}
