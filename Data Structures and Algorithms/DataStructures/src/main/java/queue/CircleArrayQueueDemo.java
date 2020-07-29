package queue;

import java.util.Scanner;

/**
 * @author lanqilu
 * @date 2020/2/4  16:14
 **/

public class CircleArrayQueueDemo {

    public static void main(String[] args) {

        //测试
        System.out.println("测试数组模拟环形队列");

        // 创建一个环形队列
        // 说明:设置4, 其队列的有效数据最大是3
        CircleArray queue = new CircleArray(4);
        // 接收用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        // 输出一个菜单
        System.out.println("s(show): 显示队列");
        System.out.println("e(exit): 退出程序");
        System.out.println("a(add): 添加数据到队列");
        System.out.println("g(get): 从队列取出数据");
        System.out.println("h(head): 查看队列头的数据");
        while (loop) {
            // 接收一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.print("输出一个数:");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                // 取出数据
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    }
                    catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                // 查看队列头的数据
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    }
                    catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                // 退出
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }

}

class CircleArray {
    /**
     * 表示数组的最大容量
     **/
    private int maxSize;
    /**
     * front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
     * <p>front 的初始值 = 0</p>
     **/
    private int front;
    /**
     * rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
     * <p>rear 的初始值 = 0</p>
     **/
    private int rear;
    /**
     * 该数据用于存放数据, 模拟队列
     **/
    private int[] arr;

    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    /**
     * 判断队列是否满
     **/
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断队列是否为空
     **/
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加数据到队列
     **/
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据~");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将 rear 后移, 这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    /**
     * 获取队列的数据, 出队列
     **/
    public int getQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            // 通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        // 这里需要分析出front是指向队列的第一个元素
        // 1.先把front对应的值保留到一个临时变量
        // 2.将front后移,考虑取模
        // 3.将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;

    }

    /**
     * 求出当前队列有效数据的个数
     **/
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 显示队列的所有数据
     **/
    public void showQueue() {
        // 遍历
        if (isEmpty()) {
            System.out.println("队列空的，没有数据~~");
            return;
        }
        // 思路：从front开始遍历，遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * 显示队列的头数据,注意不是取出数据
     **/
    public int headQueue() {
        // 判断
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return arr[front];
    }
}
