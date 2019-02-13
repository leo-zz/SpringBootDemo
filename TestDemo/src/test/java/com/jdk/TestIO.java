package com.jdk;

import org.omg.SendingContext.RunTime;
import org.springframework.expression.spel.ast.Selection;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestIO {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        final TestIO testIO = new TestIO();

        //测试迭代打印文件路径
//        String path="C:\\Users\\j2eeLe\\Desktop\\共享家项目";
//        File file = new File(path);
//        testIO.listAllFiles(file);

        //文件复制
//        String path1="C:\\Users\\j2eeLe\\Desktop\\共享家项目\\水果记录.xlsx";
//        File file1 = new File(path1);
//        String path2="C:\\Users\\j2eeLe\\Desktop\\共享家项目\\aa.xlsx";
//        File file2 = new File(path2);
//        testIO.FileCopy(file1,file2);

        //使用reader进行文件写入并读取打印
//        String filePath = "C:\\Users\\j2eeLe\\Desktop\\a.txt";
//        FileReader reader=null;
//        FileWriter writer=null;
//        BufferedReader bufferedReader=null;
//
//        try {
//            writer=new FileWriter(filePath);
//            writer.write("hello leo，快快长大");
//            writer.flush();
//
//            reader = new FileReader(filePath);
//            bufferedReader = new BufferedReader(reader);
//            StringBuffer sb = new StringBuffer();
//            String readLine;
//            while ((readLine = bufferedReader.readLine()) != null) {
//                sb.append(readLine);
//            }
//            System.out.println(sb);
//            ;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                bufferedReader.close();
//                writer.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        //对象序列化
//        String filePath = "C:\\Users\\j2eeLe\\Desktop\\a.txt";
//        A a = new A(5, "age");
//        ObjectOutputStream oos=null;
//        ObjectInputStream ois=null;
//        try {
//            oos = new ObjectOutputStream(new FileOutputStream(filePath));
//            oos.writeObject(a);
//            oos.flush();
//
//
//            ois = new ObjectInputStream(new FileInputStream(filePath));
//            Object o = ois.readObject();
//            System.out.println((A)o);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                oos.close();
//                ois.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        //从URL中读取数据
//        URL url = null;
//        BufferedReader br = null;
//        try {
//            url = new URL("http://www.baidu.com");
//
//
//            /* 字节流 */
//            InputStream is = url.openStream();
//
//            /* 字符流 */
//            InputStreamReader isr = new InputStreamReader(is, "utf-8");
//
//            /* 提供缓存功能 */
//            br = new BufferedReader(isr);
//
//            String line;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } finally {
//            br.close();
//
//        }

        //文件NIO进行文件复制
//        String path1="C:\\Users\\j2eeLe\\Desktop\\a.txt";
//        File file1 = new File(path1);
//        String path2="C:\\Users\\j2eeLe\\Desktop\\b.txt";
//        File file2 = new File(path2);
//        testIO.nioFileCopy(file1,file2);


        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            try {
                System.out.println("开启Socket Server");
                testIO.startSocketServer(8088);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        service.execute(() -> {
            Socket socket = new Socket();
            try {
                System.out.println("开启Socket Client，等待SocketServer启动");
                Thread.sleep(5000);
                socket.connect(new InetSocketAddress("127.0.0.1", 8088));
                System.out.println("Socket连接成功");
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write("hello leo，快快长大".getBytes());
                outputStream.flush();
                System.out.println("Socket写入成功，关闭输出流。");
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.shutdown();

    }

    public void listAllFiles(File dir) {
        //文件为空或不存在
        if (dir == null || !dir.exists()) {
            System.out.println("传入的文件必须存在且不为空");
            return;
        }
        //File为文件
        if (dir.isFile()) {
            System.out.println(dir.getName());
        }
        //File为路径，则迭代执行dir下的所有文件或路径
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                listAllFiles(file);
            }
        }
    }

    public void FileCopy(File src, File desc) {
        FileInputStream srcFIS = null;
        FileOutputStream descFOS = null;
        try {
            srcFIS = new FileInputStream(src);
            descFOS = new FileOutputStream(desc);

            byte[] datas = new byte[100 * 1024];
            int length = 0;
            while ((length = srcFIS.read(datas)) != -1) {
                descFOS.write(datas, 0, length);
            }
            ;
            descFOS.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                srcFIS.close();
                descFOS.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class A implements Serializable {

        private int x;
        private String y;

        A(int x, String y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "x = " + x + "  " + "y = " + y;
        }

    }

    public void nioFileCopy(File src, File desc) {
        if (src == null || !src.exists()) {
            throw new RuntimeException("源文件不能为空且必须存在");
        }
        if (!desc.exists()) {
            try {
                desc.createNewFile();
                System.out.println("输出文件不存在则创建文件");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileOutputStream fos = null;
        FileInputStream fis = null;
        FileChannel fisChannel = null;
        FileChannel fosChannel = null;
        try {
            fis = new FileInputStream(src);
            fisChannel = fis.getChannel();
            fos = new FileOutputStream(desc);
            fosChannel = fos.getChannel();

//            ByteBuffer[] buffers = new ByteBuffer[100*1024];
            ByteBuffer buffers = ByteBuffer.allocate(100 * 1024);
            long read;

            long position = fisChannel.position();
            long size = fisChannel.size();
            System.out.println("输入流channel前：position:" + position + "，size:" + size);
            while ((read = fisChannel.read(buffers)) != -1) {
                position = fisChannel.position();
                size = fisChannel.size();
                System.out.println("输入流channel后：read:" + read + "，position:" + position + "，size:" + size);

                buffers.flip();
                long position1 = fosChannel.position();
                long size1 = fosChannel.size();
                System.out.println("输出流channel前：position:" + position1 + "，size:" + size1);
                long write = fosChannel.write(buffers);
                buffers.clear();
                position1 = fosChannel.position();
                size1 = fosChannel.size();
                System.out.println("输出流channel中：write" + write + ",position:" + position1 + "，size:" + size1);
            }
            ;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
                fosChannel.close();
                fisChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readDataFromChannel(SocketChannel sChannel) {
        StringBuffer buffer = new StringBuffer();
        ByteBuffer byteBuffer = ByteBuffer.allocate(10 * 1024);
        int read = 0;
        String content="";

        try {
            while ((read = sChannel.read(byteBuffer)) != -1) {
                byteBuffer.flip();
                int limit = byteBuffer.limit();
                System.out.println("********read:"+read+"****limit:"+limit);
                byte[] bytes = new byte[limit];  //byte表示字节
//                char[] chars = new char[limit]; //char表示字符，也占用1个字节
                for (int i = 0; i < limit; i++) {
                    bytes[i] = byteBuffer.get(i);
                }
                //StringBuffer StringBuilder不能将存入byte信息，只能存入字符数据
//                buffer.append(bytes);
                buffer.append(new String(bytes));

                content = buffer.toString();
                System.out.println("content:"+content+",string:"+new String(bytes));
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public void startSocketServer(int port) throws IOException {
//        套接字NIO
        //创建Selector
        Selector selector = Selector.open();
        //创建ServerSocketChannel
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        //设置Channel为非阻塞
        ssChannel.configureBlocking(false);
        //向Selector中注册此Channel
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        //拿取Channel关联的serversocker对象
        ServerSocket serverSocket = ssChannel.socket();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", port);
        //绑定serverSocket监听的地址
        serverSocket.bind(address);
        System.out.println("serverSocket创建完毕");

        while (true) {
            //阻塞的方式，用于获取selector中可以进行I/O操作的key的数量
            int num = selector.select();
            System.out.println("获取到可读写的channel数量：" + num);

            //SelectionKey是代表Selector中Channel的token
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //判断当前channel是否可以接收新的socket连接
                if (key.isAcceptable()) {
                    System.out.println("channel可以接收新的socket连接");
                    //返回key对应的channel
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    //返回channel中的socket连接的sChannel，
                    // 当前channel是非阻塞模式，因此如果没有socket连接的话，会直接返回null，不会阻塞
                    //但是该方法返回的socketchannel仍然默认是阻塞模式
                    SocketChannel sChannel = channel.accept();
                    System.out.println("channel接收到新的socket连接：" + sChannel);
                    if (sChannel != null) {
                        sChannel.configureBlocking(false);
                        sChannel.register(selector, SelectionKey.OP_READ);
                    }
                    //判断当前channel是否可以读取
                } else if (key.isReadable()) {
                    System.out.println("channel可以读取");
                    SocketChannel channel = (SocketChannel) key.channel();
                    String s = readDataFromChannel(channel);
                    System.out.println("读取到的内容:"+s+"，关闭channel");
                    channel.close();
                }
                iterator.remove();
            }
        }
    }
}
