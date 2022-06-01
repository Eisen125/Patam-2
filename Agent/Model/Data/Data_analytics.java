package Model.Data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import com.opencsv.CSVReader;

public class Data_analytics {




 public static void ReadData(String file){
  String[] nextToken;
  try {
   FileReader fileReader = new FileReader(file);
CSVReader csvReader = new CSVReader(fileReader);
while((nextRecord=csvReader.readNext()!=null)){
 for(String cell : nextRecord){
  System.out.println(cell+);
 }
}
  } catch (FileNotFoundException e) {
   e.printStackTrace();
  }

 }

}

