package com.raines.raineslearn.io.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

public class ReadFromFile {

    private static List<String> configList = new CopyOnWriteArrayList<>();

//    public static void main(String[] args) throws Exception {
//        readLineByAio("asynchronous.txt");
//    }

    /**
     * aio按行读取，缺陷是首次申请的bufferSize必须超过文件总大小；适用于小文件按行处理
     * 或许aio就不太适用这种业务场景，而是适合哪种不需要按行读的；只读字节类型的数据
     *
     * @param filePath
     * @throws Exception
     */
    private static void readLineByAio(String filePath) throws Exception {
        completionHandlerDemo(filePath);
        //futureDemo(asynchronousFileChannel, byteBuffer);
    }

    private static void completionHandlerDemo(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
        long position = 0;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ByteBuffer stringBuffer = ByteBuffer.allocate(1024);
        asynchronousFileChannel.read(byteBuffer, position, byteBuffer, new CompletionHandlerImpl(stringBuffer, configList, countDownLatch));
        try {
            countDownLatch.await();
            asynchronousFileChannel.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        Path file = Paths.get("asynchronous.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);

        ByteBuffer buffer = ByteBuffer.allocate(100_000);
        Future<Integer> result = channel.read(buffer, 0);

        while (!result.isDone()) {
            ProfitCalculator.calculateTax();
        }

        Integer bytesRead = result.get();

//        System.out.write(buffer.array(), 0, bytesRead);

        System.out.println(buffer.limit());
        System.out.println(buffer.position());
        //flip()将一个能够继续添加数据元素的填充状态的缓冲区翻转成一个准备读出元素的释放状态
        buffer.flip();
        String line = StandardCharsets.UTF_8.decode(buffer).toString();
        buffer.clear();//重置开始位置、最大容量
        System.out.println(line);

        System.out.println("Bytes read [" + bytesRead + "]");
    }
}
class ProfitCalculator {
    public ProfitCalculator() {
    }
    public static void calculateTax() {
//        System.out.println("ok");
    }
}