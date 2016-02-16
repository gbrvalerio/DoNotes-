package br.com.gabrielvalerio.DoNotes.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class NoteFinder {
	
	public static ListView<String> getNotesList(){
		return new ListView<String>(getTitles());
	}
	
	private static ArrayList<String> lookForFiles(){
		File directory = new File("D:\\Java\\");
		String[] fileList = directory.list();		
		ArrayList<String> noteList = new ArrayList<>();
		Pattern p = Pattern.compile("note[0-9]+.txt");
		
		for(String file : fileList){
			if(p.matcher(file).matches()){
				noteList.add(directory.getAbsolutePath() + "\\" + file);
			}
		}
		return noteList;
	}
	
	private static ObservableList<String> getTitles(){
		ArrayList<String> auxList = new ArrayList<>();
		
		for(String path : lookForFiles()){
			try(Scanner s = new Scanner(new File(path))){
				String title = s.nextLine().replaceAll("#Title: ", "");
				auxList.add(title);
			} catch (FileNotFoundException e) {
				
			}
		}
		return FXCollections.observableArrayList(auxList);
	}
	
}
