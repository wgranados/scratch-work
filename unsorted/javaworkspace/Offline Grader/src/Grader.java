/** Name: William Granados
 *  Date: 6/8/15
 *  Purpose: handles the grading for the applet
 * */
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;


public class Grader {
	
	// containers for input and output for each testcase
	private Vector<String>input = new Vector<String>();
	private Vector<String>output = new Vector<String>();
	private Vector<String>programOutput = new Vector<String>();
	
	private Scanner s;
	
	/** Opens the corresponding level text file for the specified level.
	 * @param file file to be opened
	 * */
	public Vector<String> openFile(File file){
		Vector<String>destination;
		//File file = new File(filePath);
		destination = new Vector<String>();
		try {
			s = new Scanner(file);
		    while(s.hasNext()){
				  String ss = s.nextLine().trim();
				  destination.add(ss);
			}
		}catch(Exception noFile) { 
		    System.out.println("No file found: " + file.getPath());
		}
		return destination;
	}	
	/**Grades the program on all of the test cases provided
	 * return false if the program fails any test case
	 * @param programPath file path for .exe program 
	 * @param inputPath file path for input.txt
	 * @param outputPath file path for output.txt
	 * */
	public boolean grade(String programPath,String folderPath,String inputPath,String outputPath){
		// error handling
		if(programPath.isEmpty() || folderPath.isEmpty() || inputPath.isEmpty())
		   return false;
		// opening all of the files in the folder
		File folder = new File(folderPath);
		File[] files = folder.listFiles();
		for(int i = 0;i < (int)files.length;i++){
			if(files[i].isFile() && files[i].getName().contains(".in"))// input file
			    input = openFile(files[i]);
			else{// output file
				output = openFile(files[i]);
				// if it fails the current test case we return false
				if(!gradeIndividualCase(programPath,inputPath,outputPath)){
					input.clear();
					output.clear();
					return false;
				}
				else{
					input.clear();
					output.clear();
				}
			}
		}
		return true;
	}
	/**Grades the program against an individual test case
	 * @param programPath file path for .exe program 
	 * @param inputPath file path for input.txt
	 * @param outputPath file path for output.txt
	 */
	public boolean gradeIndividualCase(String programPath,String inputPath,String outputPath){
		// print the output to some designated file then have the C++
		// program read from there.
		try{
			// print the input to the input folder used for the C++ program
			PrintWriter writer = new PrintWriter(inputPath, "UTF-8");
			for(int i = 0;i < (int)input.size();i++){
				writer.println(input.get(i));
			}
			writer.close();
		}catch (Exception e){
			e.printStackTrace();
		} 
		try{
			// Opens the .exe file for the C++ program then gets the output from the designated output.txt file 
			// make sure the program does print to this file otherwise it will fail each test case
			 ProcessBuilder builder = new ProcessBuilder("cmd /c  start " + programPath);
             Process process = builder.start();
             InputStream inputStream = process.getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1);
             String line;
             while ((line = bufferedReader.readLine()) != null) {
                 System.out.println(line);
             }
             inputStream.close();
             bufferedReader.close();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
