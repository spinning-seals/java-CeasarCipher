import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class FileManager
{
    public FileManager(){}

    public String readFile (String filePath) throws IOException
    {
        StringBuilder fileContent = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8)))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append(System.lineSeparator());
            }
        }
        return fileContent.toString().trim();
    }

    public void writeToFile(String filePath, String content) throws IOException
    {
        if (filePath == null || filePath.isBlank())
        {
            throw new IllegalArgumentException("ERROR: INVALID FILE PATH!");
        }
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