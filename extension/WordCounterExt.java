/*
file name:      WordCounter.java
Authors:        Ike Lage
last modified:  10/21/2023

How to run:     java WordCounter
*/

import java.util.ArrayList;
import java.io.* ;


public class WordCounterExt {

	private MapSet<String, Integer> wordCounts ;
	private int wordCount ;

	//constructor, where data_structure is either "bst" or "hashmap"
	public WordCounterExt( String data_structure ) {
		if ( data_structure.equals( "BST" ) ) {
			wordCounts = new BSTMap<String, Integer>() ;
		} 
        else if(data_structure.equals( "HashMap" ) ) {
			wordCounts = new HashMap<String, Integer>() ;
		}
        else if(data_structure.equals( "ArrayList" ) ) {
            wordCounts = new ArrayListMap<String, Integer>() ;
        }
        else{
            assert false : "Invalid data structure" ;
        }

	}

	//given the filename of a text file, read the text file and return an ArrayList list of all the words in the file.
	public ArrayList<String> readWords( String filename ) {

		ArrayList <String> words = new ArrayList<String>() ;

		try {
		  // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
		  FileReader fr = new FileReader(filename);
		  // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
		  BufferedReader br = new BufferedReader(fr);
		  
		  // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
		  String line = br.readLine();
		  // start a while loop that loops while line isn't null
		  while(line != null){
		      // assign to line the result of calling the readLine method of your BufferedReader object.
		  		String [] lineWords = line.split("[ ]+") ;
		  		for ( String word : lineWords ) {
		  			words.add( word );
		  		}
		  		line = br.readLine();
		  }
		  // call the close method of the BufferedReader
		  br.close();

		  this.wordCount = words.size() ;

		  return words ;
		}
		catch(FileNotFoundException ex) {
		  System.out.println("WordCounter.readWords():: unable to open file " + filename );
		}
		catch(IOException ex) {
		  System.out.println("WordCounter.readWords():: error reading file " + filename);
		}

		return null ;
	}

	//given an ArrayList of words, put the words into the map data structure. Return the time taken in ms.
	public double buildMap( ArrayList<String> words ) {

		long startTime = System.currentTimeMillis() ;

		for ( String word : words ) {

			if ( this.wordCounts.containsKey( word ) ) {
				this.wordCounts.put( word , this.wordCounts.get( word ) + 1 );
			} else {
				this.wordCounts.put( word , 1 );
			}
		}

		long totalTime = System.currentTimeMillis() - startTime ;
		return totalTime ;
	}

	//return the total word count from the last time readWords was called.
	public int totalWordCount() {
		return this.wordCount ;
	}

	//return the unique word count
	public int uniqueWordCount() {
		return this.wordCounts.size() ;
	}

	//return the number of times the word occurred in the list of words.
	public int getCount( String word ) {
		Integer count = this.wordCounts.get( word );
		if ( count != null ) {
			return count ;
		}
		return 0 ;
	} 

	// return the frequency of the word in the list of words.
	public int getFrequency( String word ) {
		return this.getCount( word ) / this.totalWordCount() ;
	}

	//clear the map data structure.
	public void clearMap() {
		this.wordCounts.clear() ;
		this.wordCount = 0 ;
	}

	//write a word count file given the current set of words in the data structure.
	public boolean writeWordCount( String filename ) {

		try {
		 	// assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
		 	FileWriter fw = new FileWriter(filename);
			fw.write( Integer.toString( this.totalWordCount() ) + "\n" ) ;
			ArrayList <String> keys = this.wordCounts.keySet() ;
			ArrayList <Integer> values = this.wordCounts.values() ;
			for ( int i = 0 ; i < keys.size() ; i ++  ) {
				fw.write( keys.get( i ) + " - " + Integer.toString( values.get( i ) ) + "\n" );
			}
			fw.close() ;
			return true ;
		}
		catch(FileNotFoundException ex) {
		  System.out.println("WordCounter.readWords():: unable to open file " + filename );
		}
		catch(IOException ex) {
		  System.out.println("WordCounter.readWords():: error reading file " + filename);
		}
		return false ;

	}

    /**
     * Returns the top 10 words in the wordCounts map.
     * @return a string representation of the top 10 words and their counts.
     */
    public String getTop10Words(){
        StringBuilder sb = new StringBuilder();

        // Sort the entry set by value in descending order
        ArrayList<MapSet.KeyValuePair<String, Integer>> entrySet = this.wordCounts.entrySet();
        entrySet.sort((a,b) -> b.getValue().compareTo(a.getValue()));
        
        for (int i = 0; i < 10; i++) {
            MapSet.KeyValuePair<String, Integer> keyValuePair = entrySet.get(i);
            sb.append(keyValuePair.getKey()).append(": ").append(keyValuePair.getValue()).append("\n");
        }

        return sb.toString();
    }

	public static void main( String[] args ) {
		//Choose file for a specific year -- this is a sample for 2008
		String filenameR = "CLEANED_reddit_comments_2015.txt" ;
        String filenameS = "CLEANED_shakespeare.txt" ;

		//Use the BST
		WordCounterExt wcR = new WordCounterExt( "BST" ) ;
        WordCounterExt wcS = new WordCounterExt( "BST" ) ;

        //Use the HashMap
        WordCounterExt wcRHash = new WordCounterExt( "HashMap" ) ;
        WordCounterExt wcSHash = new WordCounterExt( "HashMap" ) ;

        //Use the ArrayList
        WordCounterExt wcRArray = new WordCounterExt( "ArrayList" ) ;
        WordCounterExt wcSArray = new WordCounterExt( "ArrayList" ) ;

		//Get the words out of the file
		ArrayList <String> wordsR = wcR.readWords( filenameR ) ;
        ArrayList <String> wordsS = wcS.readWords( filenameS ) ;

        System.out.println("-".repeat(50)+"\nAverage time to build map\n");

        double timeR = 0.0;
        double timeRHash = 0.0;
        double timeS = 0.0;
        double timeSHash = 0.0;
        double timeRArray = 0.0;
        double timeSArray = 0.0;

        //Build the map 10 times and average the time
        for(int i=0; i<10; i++){
            timeR += wcR.buildMap(wordsR);
            timeS += wcS.buildMap(wordsS);
            timeRHash += wcRHash.buildMap(wordsR);
            timeSHash += wcSHash.buildMap(wordsS);
            timeRArray += wcRArray.buildMap(wordsR);
            timeSArray += wcSArray.buildMap(wordsS);

            wcR.clearMap();
            wcS.clearMap();
            wcRHash.clearMap();
            wcSHash.clearMap();
            wcRArray.clearMap();
            wcSArray.clearMap();
        }

        // Print the average time taken for each data structure
        System.out.println("Average time for Reddit comments (BST): " + (timeR/10) + " ms");
        System.out.println("Average time for Reddit comments (HashMap): " + (timeRHash/10) + " ms");
        System.out.println("Average time for Shakespeare (BST): " + (timeS/10) + " ms");
        System.out.println("Average time for Shakespeare (HashMap): " + (timeSHash/10) + " ms");
        System.out.println("Average time for Reddit comments (ArrayList): " + (timeRArray/10) + " ms");
        System.out.println("Average time for Shakespeare (ArrayList): " + (timeSArray/10) + " ms");

	}
}