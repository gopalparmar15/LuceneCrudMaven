package com.prominentpixel.anlyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
//import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.StringReader;

public class WhiteSpaceAnalyzer {
    void whiteSpaceAnalyzer() throws IOException {
        //create string
        String s="MY GMAIL - ID PARMARGOPAL@055.COM ";
        //create WhitespaceAnalyzer
        Analyzer analyzer=new org.apache.lucene.analysis.core.WhitespaceAnalyzer();
        //create token in token stream
        TokenStream tokenStream=analyzer.tokenStream("content",new StringReader(s));
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
        //WhitespaceAnalyzer class object
        WhiteSpaceAnalyzer whiteSpaceAnalyzer=new WhiteSpaceAnalyzer()paceAnalyzer();
        //simpleAnalyzer method call
        whiteSpaceAnalyzer.whiteSpaceAnalyzer();
    }
}
