/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasakhir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Dell
 */
public class TugasAkhir {

    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        String path = "D:\\Kuliah\\PTKI\\Data_Tugas_Akhir\\";
        
        File[] files = findFilesInDirectory(path);
        Map<String, ArrayList> invertedIndex = new TreeMap<String, ArrayList> ();
        
        for (int i = 0; i < files.length; i++) {
            
            System.out.println("===========================");
            System.out.println("nama file : " + files[i]);
            String namaDoc = files[i].getName();
            int titik = namaDoc.indexOf(".");
            namaDoc = namaDoc.substring(0, titik);
            System.out.println("nama doc : " + namaDoc);

            Scanner input = new Scanner(files[i]); 
 
            int count = 0;
            while (input.hasNext()) 
            {
                String word = input.next();
                word = word.toLowerCase();
                
                while (word.matches(".*[.,?!()|:;\\-].*")) //[]
                {
                    if (word.contains("."))
                    {
                        int tandaBaca = word.indexOf(".");
                        word = word.substring(0, tandaBaca);
                    }
                    else if (word.contains(","))
                    {
    //                    int tandaBaca = word.indexOf(",");
                        word = word.replace(",", "");
                    }
                    else if (word.contains(":"))
                    {
                        int tandaBaca = word.indexOf(":");
                        word = word.substring(0, tandaBaca);
                    }
                    else if (word.contains(";"))
                    {
                        int tandaBaca = word.indexOf(";");
                        word = word.substring(0, tandaBaca);
                    }
                    else if (word.contains("!"))
                    {
                        int tandaBaca = word.indexOf("!");
                        word = word.substring(0, tandaBaca);
                    }
                    else if (word.contains("?"))
                    {
                        int tandaBaca = word.indexOf("?");
                        word = word.substring(0, tandaBaca);
                    }
                    else if (word.contains("\""))
                    {
                        int tandaBaca = word.indexOf("\"");
                        word = word.substring(0, tandaBaca);
                    }
                    else if (word.contains("("))
                    {
    //                    int tandaBaca = word.indexOf("(");
                        word = word.replace("(", "");
                    }
                    else if (word.contains(")"))
                    {
                        int tandaBaca = word.indexOf(")");
                        word = word.substring(0, tandaBaca);
                    }
                    else if (word.contains("|"))
                    {
                        word = word.replace("|", "");
                    }
                }
                count = count + 1;
                
//                System.out.println(word);
                
//                get dari map apakah word sudah ada atau belum
//                jika sudah ada, maka tambahkan, jika tidak, maka buat map baru
                
                if (word.length() > 0)
                {
                    ArrayList<String> valueWord = invertedIndex.get(word);
                    if (valueWord != null)
                    {
                        if (!valueWord.contains(namaDoc))
                        {
                            valueWord.add(namaDoc);
                            invertedIndex.put(word, valueWord);
                        }
                    }
                    else
                    {
                        ArrayList<String> postingList = new ArrayList<String>();
                        postingList.add(namaDoc);
                        invertedIndex.put(word, postingList);
                    }
                }
            }
            System.out.println("Word count: " + count);
        }
        
        invertedIndex.forEach((key, value) -> System.out.println(key + ":" + value));
    }
    
//    method untuk membaca file document yang ada
    public static File[] findFilesInDirectory(String directoryPath) {

        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles();

//        for (int i = 0; i < listOfFiles.length; i++) {
//            if (listOfFiles[i].isFile()) {
//                System.out.println("File " + listOfFiles[i].getName());
//            } else if (listOfFiles[i].isDirectory()) {
//                System.out.println("Directory " + listOfFiles[i].getName());
//            }
//        }

//        System.out.println("banyak isi file : " + listOfFiles.length);
        return listOfFiles;
    }    
}
