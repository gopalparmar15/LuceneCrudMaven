package com.prominentpixel.fileindexing;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class Indexer {
    public static void main(String[] args) throws Exception {
        //store indexdir path
         String indexDir = "E:\\Lucene\\Index";
         //store datadir path
         String dataDir = "E:\\Lucene\\Data";
        //start time
        long start = System.currentTimeMillis();
        //indexer class object create
        Indexer indexer = new Indexer(indexDir);
        // numindex define
        int numIndexed;
        try {

           numIndexed = indexer.index(dataDir, new TextFilesFilter());
        } finally {
            indexer.close();
        }
        //end time
       long end = System.currentTimeMillis();
        //print number of index and start time and end time
        System.out.println("Indexing " + numIndexed + " files took "
                + (end - start) + " milliseconds");
    }
    //
    private IndexWriter writer;
    //create indexer class constructor
    public Indexer(String indexDir) throws IOException {
    // create Fsdirectory
        Directory dir = FSDirectory.open(Paths.get("E:\\Lucene\\Index"));
        //create StandardAnalyzer
        Analyzer an=new StandardAnalyzer();
        IndexWriterConfig config=new IndexWriterConfig(an);
    //create indexd using index writer
        writer = new IndexWriter(dir,config);
    }
    public void close() throws IOException {
        //close indexwriter
        writer.close();
    }
    //--------*------//
    public int index(String dataDir, FileFilter filter)
            throws Exception {
        //create file arrray
        File[] files = new File(dataDir).listFiles();
        //for loop
        for (File f: files) {
            //if condition chack file
            if (!f.isDirectory() && !f.isHidden() && f.exists() && f.canRead() && (filter == null || filter.accept(f)))
            {
                //create indexFile method
                indexFile(f);
            }
        }
        //return number of file
        return writer.numDocs();
    }
    //create TextFilesFilterr  class
    static class TextFilesFilter implements FileFilter {
        //create accept metod
        public boolean accept(File path) {
            //return  file name ,lower case ,.txt extention
            return path.getName().toLowerCase()
                    .endsWith(".txt");
        }
    }
    //create getdocument method
    protected Document getDocument(File f) throws Exception {
        //create document class object
        Document doc = new Document();
        //add content file
        doc.add(new Field("contents", new FileReader(f)));
        //add field name
        doc.add(new Field("filename", f.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED));
        //add fullpath
        doc.add(new Field("fullpath", f.getCanonicalPath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
        //return doc
        return doc;
    }
    private void indexFile(File f) throws Exception {
        //print path
        System.out.println("Indexing " + f.getCanonicalPath());
        //stor file in doc
        Document doc = getDocument(f);
        //add document with document method
        writer.addDocument(doc);
    }
}