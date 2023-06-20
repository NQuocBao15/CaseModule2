package utils;

import service.IModel;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileUtils {
    public FileUtils() {
    }

    public static <T> void writeDataToFile(String path, List<T> items) {
        try {
            PrintWriter printWriter = new PrintWriter(path);
            Iterator var = items.iterator();

            while (var.hasNext()) {
                Object item = var.next();
                printWriter.println(item.toString());
            }
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(path + " invalid");
        }
    }

    public static <T> List<T> readData(String path, Class<T> tClass) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            List<T> list = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null) {
                IModel<T> iModel = (IModel)tClass.getDeclaredConstructor().newInstance();
                list.add(iModel.parseData(line));
            }
            fileReader.close();
            bufferedReader.close();
            return list;
        } catch (FileNotFoundException var7) {
            var7.printStackTrace();
        } catch (IOException var8) {
            var8.printStackTrace();
        } catch (InvocationTargetException var9) {
            throw new RuntimeException(var9);
        } catch (InstantiationException var10) {
            throw new RuntimeException(var10);
        } catch (IllegalAccessException var11) {
            throw new RuntimeException(var11);
        } catch (NoSuchMethodException var12) {
            throw new RuntimeException(var12);
        }

        return null;
    }
}
