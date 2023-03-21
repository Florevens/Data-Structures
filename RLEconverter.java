package app;

import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.stream.FileCacheImageInputStream;

import java.io.*;

public class RLEconverter {
   private final static int DEFAULT_LEN = 100; // used to create arrays.

   /*
    *  This method reads in an uncompressed ascii image file that contains
    *  2 characters. It stores each line of the file in an array.
    *  It then calls compressAllLines to get an array that stores the compressed
    *  version of each uncompressed line from the file. The compressed array
    *  is then passed to the getCompressedFileStr method which returns a String
    *  of all compressed lines (the two charcaters are written in the first line)
    *  in CSV format. This String is written to a text file with the prefix "RLE_"
    *  added to the original, uncompressed file name.
    *  Note that dataSize keeps track of the number of lines in the file. The array
    *  that holds the lines of the file is initialized to the DEFAULT_LEN, which
    *  is assumed to be << the number of lines in the file.
    */
  public void compressFile(String fileName) throws IOException{
    Scanner scan = new Scanner(new FileReader(fileName));
    String line = null;
    String[] decompressed = new String [DEFAULT_LEN];
    int dataSize = 0;
    while(scan.hasNext()){
      line = scan.next();
      if(line != null && line.length()>0)
        decompressed[dataSize]=line;
        dataSize++;
    }
    scan.close();
    char[] fileChars = discoverAsciiChars(decompressed, dataSize);
    String[] compressed = compressAllLines(decompressed, dataSize, fileChars);
    writeFile(getCompressedFileStr(compressed, fileChars), "RLE_"+fileName);
  }


/*
 * This method implements the RLE compression algorithm. It takes a line of uncompressed data
 * from an ascii file and returns the RLE encoding of that line in CSV format.
 * The two characters that make up the image file are passed in as a char array, where
 * the first cell contains the first character that occurred in the file.
*/
public String compressLine(String line, char[] fileChars){
   //TODO: Implement this method
   String compLine = "";
   int count = 1;
   for (int i = 0; i < line.length(); i++) {
     count = 1;
     if( i == 0 && line.charAt(i) != fileChars[0]){
       compLine+=0;
       compLine+=",";
       continue;
      }
      else if(i == 0 && line.charAt(i) == fileChars[0]){

      }
      else {
        while (i < line.length()  && line.charAt(i) == line.charAt(i - 1)) {
          count++;
         i++;
        }
        compLine += count;
        if(i != compLine.length()){
          compLine+= ",";
        }
      }
    }
    compLine = compLine.substring(0, compLine.lastIndexOf(","));
    if(line.charAt(line.length()-1) != line.charAt(line.length()-2)){
      compLine+=","+1;
    }
    return compLine;
  }
  /*
   *  This method discovers the two ascii characters that make up the image.
   *  It iterates through all of the lines and writes each compressed line
   *  to a String array which is returned. The method compressLine is called on
   *  each line.
   *  The dataSize is the number of lines in the file, which is likely to be << the length of lines.
   */
  public String[] compressAllLines(String[] lines, int dataSize, char[] fileChars){
    //TODO: Implement this method

    String [] compsLine = new String[dataSize];
    fileChars[0] = lines[0].charAt((0));
    char firstChar = fileChars[0];
    for(int i = 0; i < dataSize; i++ ){
      for(int j = 0; j < lines[i].length();j++){
        if(lines[i].charAt(j) != fileChars[0]){
          fileChars[1] = lines[i].charAt(j);
        }
      }
    }
    char secondChar = fileChars[1];
    for(int k = 0; k < dataSize; k++){
      compsLine[k] = compressLine(lines[k], fileChars);
    }
   return compsLine;
  }


/*
 *  This method assembles the lines of compressed data for
 *  writing to a file. The first line must be the 2 ascii characters
 *  in comma-separated format.
 */
public String getCompressedFileStr(String[] compressed, char[] fileChars) {
    //TODO: Implement this method
      String cF = "";
      cF+= fileChars[0]+ ","+ fileChars[1];
      for(int i = 0; i < compressed.length;i++){
        cF += "\n"+ compressed[i];
      }

    return cF;
}
   /*
    *  This method reads in an RLE compressed ascii image file that contains
    *  2 characters. It stores each line of the file in an array.
    *  It then calls decompressAllLines to get an array that stores the decompressed
    *  version of each compressed line from the file. The first row contains the two
    *  ascii charcaters used in the original image file. The decompressed array
    *  is then passed to the getDecompressedFileStr method which returns a String
    *  of all decompressed lines, thus restoring the original, uncompressed image.
    *  This String is written to a text file with the prefix "DECOMP_"
    *  added to the original, compressed file name.
    *  Note that dataSize keeps track of the number of lines in the file. The array
    *  that holds the lines of the file is initialized to the DEFAULT_LEN, which
    *  is assumed to be << the number of lines in the file.
    */
  public void decompressFile(String fileName) throws IOException{
    Scanner scan = new Scanner(new FileReader(fileName));
    String line = null;
    String[] compressed = new String [DEFAULT_LEN];
    int dataSize =0;
    while(scan.hasNext()){
      line = scan.next();
      if(line != null && line.length()>0)
        compressed[dataSize]=line;
        dataSize++;
    }
    scan.close();
    String[] decompressed = decompressAllLines(compressed, dataSize);
    writeFile(getDecompressedFileStr(decompressed), "DECOMP_"+fileName);
  }

   /*
   * This method decodes lines that were encoded by the RLE compression algorithm.
   * It takes a line of compressed data and returns the decompressed, or original version
   * of that line. The two characters that make up the image file are passed in as a char array,
   * where the first cell contains the first character that occurred in the file.
   */
  public String decompressLine(String line, char[] fileChars){
    //TODO: Implement this method
   String decom= "";
   int count = 0;
   String[] decompArray = line.split(",");
   for (int i = 0; i < decompArray.length; i++) {
     for( int j = 0; j < Integer.parseInt(decompArray[i]); j++){
       decom+=fileChars[count];
      }
      count = 1-count;
    }
    return decom;
  }


    /*
   *  This method iterates through all of the compressed lines and writes
   *  each decompressed line to a String array which is returned.
   *  The method decompressLine is called on each line. The first line in
   *  the compressed array passed in are the 2 ascii characters used to make
   *  up the image.
   *  The dataSize is the number of lines in the file, which is likely to be << the length of lines.
   *  The array returned contains only the decompressed lines to be written to the decompressed file.
   */
  public String[] decompressAllLines(String[] lines, int dataSize){


    String [] allLines = new String[dataSize];
    char [] fileChars = new char[2];
    fileChars[0] = lines[0].charAt(0);
    fileChars[1] = lines[0].charAt(1);
    for(int i = 2; i <= lines.length-1; i++){
      allLines[i-1] = "\n"+ decompressLine(lines[i-1], fileChars);
      }
      if(allLines[allLines.length-1] == null){
      allLines[allLines.length-1] = "\n"+ decompressLine(lines[allLines.length-1], fileChars);
      }
      if(allLines[0] == null){
      allLines[0] = "";

      }
      return allLines;
      }






  /*
   *  This method assembles the lines of decompressed data for
   *  writing to a file.
   */
  public String getDecompressedFileStr(String[] decompressed){

    String data = "";
     for(int i = 0; i < decompressed.length;i++){
       data += "\n"+ decompressed[i];
     }
  return data;
}

  // assume the file contains only 2 different ascii characters.
  public char[] discoverAsciiChars(String[] decompressed, int dataSize){
//TODO: Implement this method
  char [] ascii = new char[2];
  ascii[0] = decompressed[0].charAt(0);
  for (int i = 0; i <=decompressed.length-1; i++) {
    for (int k = i+1; k < decompressed[i].length(); k++) {
      if (ascii[0] != decompressed[i].charAt(k)){
        ascii[1] =  decompressed[i].charAt(k);
      }
    }
  }
  return ascii;
}
  public void writeFile(String data, String fileName) throws IOException{
		PrintWriter pw = new PrintWriter(fileName);
      pw.print(data);
      pw.close();
   }
}