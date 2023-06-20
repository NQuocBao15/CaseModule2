package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    public static <T> void writeFile(String path, List<T> items) {
        try {
            PrintWriter printWriter = new PrintWriter(path);
            for (T products : items) {
                printWriter.println(products.toString());
            }
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(path + "Không có dữ liệu hoặc file rỗng");
        }
    }

    public static List<String> readFile(String path) {
        List<String> products = new ArrayList<>();
        try {
            File file = new File(path);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!(line.trim()).isEmpty()) {
                    products.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(path + "Không có dữ liệu");
        }
        return products;
    }
}
