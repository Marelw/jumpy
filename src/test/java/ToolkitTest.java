import java.awt.*;  

/*
    Koden visar skärmens storlek. 
    Kan vara användbart om man vill ha en default size beroende på 
    vilken skärm man har. 
*/

public class ToolkitTest {  
  public static void main(String[] args) {  
      Toolkit t = Toolkit.getDefaultToolkit();  
      System.out.println("Screen resolution = " + t.getScreenResolution());  
      Dimension d = t.getScreenSize();  
      System.out.println("Screen width = " + d.width);  
      System.out.println("Screen height = " + d.height);  
  }  
}  
