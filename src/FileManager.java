import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager
{
    public FileManager(){}

    public String readFile (String filePath) throws IOException
    {
        StringBuilder fileContent = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append(System.lineSeparator());
            }
        }
        return fileContent.toString();
    }

    public void writeToFile(String filePath, String content) throws IOException
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
        {
           writer.write(content);
        }
    }

    public boolean fileExists(String filePath)
    {
        return Files.exists(Paths.get(filePath));
    }

    public void createFileIfMissing(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }
}