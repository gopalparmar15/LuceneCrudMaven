package com.prominentpixel.LuceneCrud;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.apache.lucene.index.DirectoryReader.open;


public class LuceneCrudUser {
    //variables
     String id,question,status,level,type,categoryid;

     //
  private static IndexWriter getIndexWriter() throws IOException
    {
        //directory is loaction of path
        Directory dir = FSDirectory.open(Paths.get("C:/Users/GOPAL PARMAR/Desktop/index"));
        //indexWriterconfig StandardAnalyzer is analyzed text into token
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        //return indexwriter
        return new IndexWriter(dir,config);
    }
    //create scanner class
    Scanner sc=new Scanner(System.in);
    public void setUp() throws IOException
    {
	//creating index 
        IndexWriter writer=getIndexWriter();
        for(int i=0;i<5;i++)
        {
            //print "enter a id:"
            System.out.print("enter a id:");
            //user input
            id=sc.nextLine();
            //print "enter a Question:"
            System.out.print("enter a Question:");
            //user input
            question= sc.nextLine();
            //print "enter a status [ACTIVE/INACTIVE]:"
            System.out.print("enter a status [ACTIVE/INACTIVE]:");
            //user input
            status=sc.nextLine();
            //print "enter a categoryid:"
            System.out.print("enter a categoryid:");
            //user input
            categoryid=sc.nextLine();
            //print "enter a level [BEGGINER,INTRMIDEATE,EXPERT]:"
            System.out.print("enter a level [BEGGINER,INTRMIDEATE,EXPERT]:");
            //user input
            level=sc.nextLine();
            //print "enter a type [SINGLE,MULTI]:"
            System.out.print("enter a type [SINGLE,MULTI]:");
            //user input
            type=sc.nextLine();
            //create document class
            Document doc = new Document();
            //add  element in id   Field.Store.Yes store value
            doc.add(new StringField("id",id,Field.Store.YES));
            //add  element in  question  Field.Store.Yes store value
            doc.add(new StringField("question",question,Field.Store.YES));
            //add  element in status  Field.Store.Yes store value
            doc.add(new StringField("status",status,Field.Store.YES));
            //add  element in categoryid  Field.Store.Yes store value
            doc.add(new StringField("categoryid",categoryid,Field.Store.YES));
            //add  element in level  Field.Store.Yes store value
            doc.add(new StringField("level",level,Field.Store.YES));
            //add  element in type  Field.Store.Yes store value
            doc.add(new StringField("type",type,Field.Store.YES));
            //adddocument using indexwriter object
            writer.addDocument(doc);
        }
        //close Indexwriter
        writer.close();
    }
  public void delete() throws IOException{
      //create a new indexing inexwriter
        IndexWriter writer = getIndexWriter();
        // print "enter delete id:"
        System.out.print("enter delete id:");
        //user input
        id=sc.nextLine();
        // //delete user value in indexing
        writer.deleteDocuments(new Term("id",id));
      //commit method
        writer.commit();
      //close method Indexwriter
        writer.close();
    }

   public void update() throws IOException {
        IndexWriter writer =getIndexWriter();
       //print "update a id:"
            System.out.print("update a id:");
       //user input
            id = sc.nextLine();
       //print "update a Question:"
            System.out.print("update a Question:");
       //user input
            question = sc.nextLine();
       //print "update a status [ACTIVE/INACTIVE]:"
            System.out.print("update a status [ACTIVE/INACTIVE]:");
       //user input
            status = sc.nextLine();
       //print "update a categoryid:"
            System.out.print("update a categoryid:");
       //user input
            categoryid = sc.nextLine();
       //print "update a level [BEGGINER,INTRMIDEATE,EXPERT]:"
            System.out.print("update a level [BEGGINER,INTRMIDEATE,EXPERT]:");
       //user input
            level = sc.nextLine();
       //print "update a type [SINGLE,MULTI]:"
            System.out.print("update a type [SINGLE,MULTI]:");
       //user input
            type = sc.nextLine();
                  //create document class
            Document doc = new Document();
                //add  element in id   Field.Store.Yes store value
            doc.add(new StringField("id", id,Field.Store.YES));
              //add  element in  question  Field.Store.Yes store value
            doc.add(new StringField("question", question, Field.Store.YES));
              //add  element in status  Field.Store.Yes store value
            doc.add(new StringField("status", status, Field.Store.YES));
             //add  element in categoryid  Field.Store.Yes store value
            doc.add(new StringField("categoryid", categoryid, Field.Store.YES));
             //add  element in level  Field.Store.Yes store value
            doc.add(new StringField("level", level, Field.Store.YES));
             //add  element in type  Field.Store.Yes store value
            doc.add(new StringField("type", type, Field.Store.YES));
             //update value using updatedocument method
            writer.updateDocument(new Term("id",id), doc);
            //close writer
            writer.close();
   }

     public void search() throws IOException {
          //print field name
         System.out.print("enter field name:");
         //user field
         String field=sc.nextLine();
         //print search name
         System.out.print("enter search name:");
         //user name
         String name=sc.nextLine();
         //term object create
        Term term=new Term(field,name);
        Query q=new TermQuery(term);
         // loction of index
        Directory indexDirectory =FSDirectory.open(Paths.get("C:/Users/GOPAL PARMAR/Desktop/index"));
         //indexreader read indexing
        IndexReader reader = open(indexDirectory);
         //indexsearcher is search element in reader object
        IndexSearcher searcher = new IndexSearcher(reader);
         //store value of search
        TopDocs docs = searcher.search(q, 100);
         //store value of search
        ScoreDoc[] hits = docs.scoreDocs;
         // for loop  hits.legth
         for (ScoreDoc hit : hits) {
             //store  id in document
             int docId = hit.doc;
             //searche id in doc
             Document d = searcher.doc(docId);
             //print value of quetion field
             System.out.println(d.get(field));
         }
         //close index reader
        reader.close();
      }



    public static void main(String[] args) throws IOException {
        //create Lucenecrud class object
        LuceneCrudUser lc=new LuceneCrudUser();
        //call setup();
        lc.setUp();
        //call delete();
        lc.delete();
        //call update();
        lc.update();
        //call search();
        lc.search();
    }
}
