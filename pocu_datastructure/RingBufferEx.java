package pocu_datastructure;

public class RingBufferEx {
    public static void main(String[] args) {
        RingBuffer ringBuffer = new RingBuffer();


        Thread putThread = new PutThread(ringBuffer);
        putThread.start();

        Thread delThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1222);
                        ringBuffer.delete();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        delThread.start();

    }
}

/**
 * ringBuffer 를 테스트하기 위해 만든 insert 용 스레드
 */
class PutThread extends Thread{
    RingBuffer ringBuffer;
    int n;

    public PutThread(RingBuffer ringBuffer){
        this.n = 1;
        this.ringBuffer = ringBuffer;
    }

    @Override
    public void run(){
        while (true){
            try{
                ringBuffer.insert(n++);
                Thread.sleep(500);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

/**
 * 순환되어 돌아가는 Queue 를 만들자.
 * 반드시 선입후출이어야 하고, 빈 공간(0)이 있을 경우에만 숫자를 넣을 수 있도록 한다.
 */
class RingBuffer{
    int[] buf = new int[8];
    int front = 0;
    int back = 0;

    public void printBuffer(){
        for(int i=0; i < buf.length; ++i){
            System.out.print(buf[i]+ "  ");
        }
        System.out.println();
    }

    public synchronized void insert(int n){
        if(back >= buf.length){
            if(front > 0)
                back = 0;
            else{
                return;
            }
        }

        if(buf[back] == 0){
            buf[back++] = n;

            System.out.print("PUT :");
            printBuffer();
        }
    }

    public void delete(){
        if(buf[front] == 0){
            return;
        }

        buf[front++] = 0;
        if(front >= buf.length){
            front = 0;
        }

        System.out.print("DEL :");
        printBuffer();
    }

    public void search(int n){

    }
}
