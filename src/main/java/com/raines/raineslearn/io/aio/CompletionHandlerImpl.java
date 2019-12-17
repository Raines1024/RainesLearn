package com.raines.raineslearn.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CompletionHandlerImpl implements CompletionHandler<Integer, ByteBuffer> {

    private ByteBuffer stringBuffer;

    private List<String> configList;

    private CountDownLatch countDownLatch;

    public CompletionHandlerImpl(ByteBuffer stringBuffer, List<String> configList, CountDownLatch countDownLatch) {
        this.stringBuffer = stringBuffer;
        this.configList = configList;
        this.countDownLatch = countDownLatch;
    }


    @Override
    public void completed(Integer readBytes, ByteBuffer buffer) {
        System.out.println("Bytes Read = " + readBytes);
        System.out.println("thread-id=" + Thread.currentThread().getId());
        buffer.flip();
        boolean merge = false;
        while (buffer.hasRemaining()) {
            byte b = buffer.get();
            if (b == 10 || b == 13) { // 换行或回车,windows系统2个字符都会出现
                merge = true;
            } else {
                if (merge) {
                    stringBuffer.flip();
                    String line = Charset.forName("utf-8").decode(stringBuffer).toString();
                    stringBuffer.clear();//重置开始位置、最大容量
                    configList.add(line);
                    System.out.println(line);
                    merge = false;
                }
                if (!stringBuffer.hasRemaining()) {
                    stringBuffer = reAllocate(stringBuffer);
                }
                stringBuffer.put(b);
            }
        }
        if (stringBuffer.position() != 0) {//将剩下的认为是一行
            stringBuffer.flip();
            String line = Charset.forName("utf-8").decode(stringBuffer).toString();
            stringBuffer.clear();
            System.out.println(line);
            configList.add(line);
        }
        countDownLatch.countDown();
    }

    public void failed(Throwable exc, ByteBuffer attachment) {
        System.out.println(exc.getCause());
        countDownLatch.countDown();
    }

    private static ByteBuffer reAllocate(ByteBuffer stringBuffer) {
        final int capacity = stringBuffer.capacity();
        byte[] newBuffer = new byte[capacity * 2];
        System.arraycopy(stringBuffer.array(), 0, newBuffer, 0, capacity);
        return (ByteBuffer) ByteBuffer.wrap(newBuffer).position(capacity);
    }

}
