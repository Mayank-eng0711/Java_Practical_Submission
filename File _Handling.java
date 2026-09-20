import java.io.*;

public class FileHandling {
    public static void main(String[] args) {
        try {
            // 1. Write data into the file
            FileWriter fw = new FileWriter("sample.txt");

            fw.write("Welcome to Java File Handling.\n");
            fw.write("This is a text file.\n");

            fw.close();

            // 2. Read data from the file
            BufferedReader br = new BufferedReader(
                    new FileReader("sample.txt"));

            System.out.println("File Contents:");

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();

            // 3. Append data to the file
            FileWriter append = new FileWriter("sample.txt", true);

            append.write("This line is appended to the file.\n");

            append.close();

            // 4. Read the updated file
            br = new BufferedReader(
                    new FileReader("sample.txt"));

            System.out.println("\nUpdated File Contents:");

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
