package operation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * IO包工具类
 *
 * @author 曾
 * Time：2023-09-26 21:22
 */
public class IOUtils {

    /**
     * 写入文件
     */
    public static void writeFile(List<String> expressions,String filePath){
        try {
            // 创建文件对象
            File file = new File(filePath);
            // 如果文件不存在，则创建文件
            if (!file.exists()) {
                file.createNewFile();
            }
            // 创建文件写入流
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            // 将集合数据写入文件
            for (String item : expressions) {
                bufferedWriter.write(item);
                bufferedWriter.newLine(); // 换行
            }
            // 关闭文件写入流
            bufferedWriter.close();
            System.out.println("集合数据已写入文件 " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读出文件
     */
    public static List<String> readFile(String filePath){
        // 创建集合用于存储读取的数据
        List<String> collectionData = new ArrayList<>();
        try {
            // 创建文件读取流
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            // 逐行读取文件内容
            while ((line = bufferedReader.readLine()) != null) {
                collectionData.add(line);
            }
            // 关闭文件读取流
            bufferedReader.close();
            // 打印读取的数据
            for (String item : collectionData) {
                System.out.println(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  collectionData;
    }
}
