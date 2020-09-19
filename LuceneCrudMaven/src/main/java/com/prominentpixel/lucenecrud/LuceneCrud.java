package com.prominentpixel.LuceneCrud;

import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.apache.lucene.index.DirectoryReader.open;

public class LuceneCrud {
    public LuceneCrud() throws IOException {
    }
    //create id array 5 
    public String[] id={"1","2","3","4","5"};
    //create quetion array
    public String[] quetion={"what is your name","what is your age","what is your birth date","what is your age","what is your birth palce"};

  private static IndexWriter getIndexWriter() throws IOException
    {
        //directory is loaction of path
        Directory dir = FSDirectory.open(Paths.get("C:/Users/GOPAL PARMAR/Desktop/index1"));
        //indexWriterconfig StandardAnalyzer is analyzed text into token
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        //return indexwriter object
        return new IndexWriter(dir,config);
    }

    public void setUp() throws IOException
    {

        //Indexwriter is create indexing
        IndexWriter writer=getIndexWriter();
        //
        for(int i=0;i<id.length;i++)
        {
            //create  document object
            Document doc = new Document();
            //add  element in id field Field.Store.Yes store value
            doc.add(new StringField("id",id[i],Field.Store.YES));
            //add elemet in question field Field.Store.Yes store value
            doc.add(new StringField("quetion",quetion[i],Field.Store.YES));
            //both value store in adddocument Method using doc object
            writer.addDocument(doc);

        }
        //close the indexwriter
        writer.close();
    }

 public void delete() throws IOException{
        //create a new indexing inexwriter
        IndexWriter writer = getIndexWriter();
         //create scanner class object
        Scanner sc=new Scanner(System.in);
        //print enter delete id number text using print method
        System.out.println("enter deelete id number:");
        //enter user id value
        String id=sc.nextLine();
        //delete id in user value
        writer.deleteDocuments(new Term("id",id));
        //commit method
        writer.commit();
        //close method Indexwriter
        writer.close();
    }

    public void upDate() throws IOException {
        //create new indexing
        IndexWriter writer =getIndexWriter();
        //create document class object
        Document doc=new Document();
        // scanner class object
        Scanner scanner=new Scanner(System.in);
        //print id
        System.out.println("enter a update id:");
        //user id
        String id=scanner.next();
        //print
        System.out.println("update a question:");
        //user questions
        String question=scanner.nextLine();
        //store id value Field.Store.YES- store value  
        doc.add(new TextField("id",id,Field.Store.YES));
        //store question value Field.Store.YES- store value  
        doc.add(new TextField("question", question, Field.Store.YES));
        //store updatedocument value in id base
        writer.updateDocument(new Term("id",id),doc);
        //indexwriter close in using close method
        writer.close();
     }


    public void search() throws IOException {
      //term object create
        Term term=new Term("quetion","date");
        Query q=new TermQuery(term);
        // loction of index
       Directory indexDirectory = FSDirectory.open(Paths.get("C:/Users/GOPAL PARMAR/Desktop/index1"));
       //indexreader read indexing
        IndexReader reader = open(indexDirectory);
        //indexsearcher is search element in reader object
        IndexSearcher searcher = new IndexSearcher(reader);
        //store value of search
        TopDocs docs = searcher.search(q, 100);
        //store value of search
        ScoreDoc[] hits = docs.scoreDocs;
        // for loop  hits.legth
        for (int i=0;i<hits.length;i++)
        {
            //store  id in document
            int docId = hits[i].doc;
            //searche id in doc
            Document d = searcher.doc(docId);
            //print value of quetion field
            System.out.println(d.get("quetion"));
        }
        //close index reader
        reader.close();
      }
    public static void main(String[] args) throws IOException {
      //create Lucenecrud class object
        LuceneCrud lc=new LuceneCrud();
        //call setup method using lucenecrud class object
        lc.setUp();
        //call delete method using lucenecrud class object
        lc.delete();
        //call update method using lucenecrud class object
        lc.upDate();
        //call search method using lucenecrud class object
        lc.search();
    }
}
