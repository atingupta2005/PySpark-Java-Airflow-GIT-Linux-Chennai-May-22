import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class Demo
{
    public static void main(String[] args)
    {
        try
        {
        	String str_file_name;
            Studentinfo si = new Studentinfo("Abhi", 104, "110044");
            System.out.println(si);
            
            str_file_name = write_student(si);
            
            Studentinfo so = read_student(str_file_name);
            System.out.println(so);
            
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    
    private static String write_student(Studentinfo si) throws IOException
    {
    	String str_file_name = "student.txt";
        FileOutputStream fos = new FileOutputStream("student.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(si);
        oos.flush();
        oos.close();
        return str_file_name;
    }
    
    private static Studentinfo read_student(String str_file_name) throws IOException, ClassNotFoundException
    {
    	FileInputStream fis = new FileInputStream(str_file_name);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Studentinfo so = (Studentinfo)ois.readObject();
        
        return so;
    }

}