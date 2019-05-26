package file_update_on_place;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileUpdateOnPlaceExample
{
    /**
     * 一个就地更新文件内容的简单示例
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        String path = "./file_update_on_place_test";
        try (RandomAccessFile access = new RandomAccessFile(path, "rw"))
        {
            String line = null;
            long lastPosition = 0;
            while ((line = access.readLine()) != null)
            {
                // System.out.println(line);
                long curPosition = access.getFilePointer();
                if (line.contains("bbb"))
                {
                    String content = line.replace("bbb", "sa");
                    // FileUpdateOnPlace.insert(access, lastPosition, content);
                    curPosition = FileUpdateOnPlace.replace(access, lastPosition, curPosition, content + '\n');
                }
                lastPosition = curPosition;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

class FileUpdateOnPlace
{
    private static byte[] buffer = new byte[1024 * 10];
    private static int num = 0; // 表示buffer有效内容的长度

    /**
     * 替换文件指定范围内的内容
     * 
     * @param access
     * @param start 包含
     * @param end 不包含
     * @param content 替换后的内容
     * @return 替换后的位置
     * @throws IOException
     */
    public static long replace(RandomAccessFile access, long start, long end, String content) throws IOException
    {
        File tmp = File.createTempFile("tmp", null);
        // 暂存替换范围后的内容
        FileOutputStream fos = new FileOutputStream(tmp);
        access.seek(end);
        while (-1 != (num = access.read(buffer)))
        {
            fos.write(buffer, 0, num);
        }
        // 开始替换
        access.setLength(access.length() - (end - start) + content.length()); // 将文件大小设置成替换后的大小，避免替换的内容比原文短的情况
        access.seek(start);
        access.write(content.getBytes());
        long resPosition = access.getFilePointer();
        FileInputStream fis = new FileInputStream(tmp);
        while (-1 != (num = fis.read(buffer)))
        {
            access.write(buffer, 0, num);
        }
        
        fos.close();
        fis.close();
        tmp.delete();
        return resPosition;
    }

    /**
     * 在文件的指定位置插入内容
     * 
     * @param access
     * @param pos
     * @param content
     * @return
     * @throws IOException
     */
    public static long insert(RandomAccessFile access, long pos, String content) throws IOException
    {
        // 创建临时空文件
        File tmp = File.createTempFile("tmp", null);
        // 将插入位置后的内容暂存到临时文件中
        FileOutputStream fos = new FileOutputStream(tmp);
        access.seek(pos);
        while (-1 != (num = access.read(buffer)))
        {
            fos.write(buffer, 0, num);
        }
        // 将内容插入指定位置
        access.seek(pos);
        access.write(content.getBytes());
        long resPosition = access.getFilePointer();
        FileInputStream fis = new FileInputStream(tmp);
        while (-1 != (num = fis.read(buffer)))
        {
            access.write(buffer, 0, num);
        }

        fos.close();
        fis.close();
        tmp.delete();
        return resPosition;
    }
}
