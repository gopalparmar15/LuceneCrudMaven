package com.prominentpixel;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import sun.java2d.marlin.Version;


class CsvIndexing {
    public static void main(String[] args) throws IOException {

        //directory is loaction of path
        Directory dir = FSDirectory.open(Paths.get("/home/pp-5/index"));
        //indexWriterconfig StandardAnalyzer is analyzed text into token
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new StandardAnalyzer());
        //create indexing
        IndexWriter indexWriter = new IndexWriter(dir, indexWriterConfig);
        //add data in field using add method

        List<String> result=disPlay();
        for (String s : result) {
            s = s.replace("\"", "");
            String[] data = s.split(",");
            addDoc(indexWriter,data[0],data[1],data[2],data[3],data[4]);
        }
        indexWriter.commit();
        indexWriter.close();

    }

    private static void addDoc(IndexWriter w, String id, String question,String status,String level,String type) throws IOException{
        //create document
        Document doc = new Document();
        //add id
        doc.add(new StringField("id",id,Field.Store.YES));
        //add name
        doc.add(new StringField("question", question, Field.Store.YES));
        //add statue
        doc.add(new StringField("status", status, Field.Store.YES));
        //add level
        doc.add(new StringField("level", level, Field.Store.YES));
        //add type
        doc.add(new StringField("type", type, Field.Store.YES));
        w.addDocument(doc);
    }
    public static List<String> disPlay()
    {
        String[] str = new String[0];
        List<String> lines = null;
        try {
          lines= Files.readAllLines(Paths.get("/home/pp-5/files/quetion.csv"));
            for(String line:lines) {
                line = line.replace("\"", "");

                str=line.split(",");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }

}
