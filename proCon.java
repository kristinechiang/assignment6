
public class proCon {
    private static boolean flags =false;
    public static void main(String[] args){
        class Goods{
            private String name;
            private int num;
            public synchronized void produce(String name){
                if(flags)
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                this.name =name+"编号："+num++;
                System.out.println("生产了...."+this.name);
                flags =true;
                notifyAll();
            }
            public synchronized void consume(){
                if(!flags)
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                System.out.println("消费了******"+name);
                flags =false;
                notifyAll();
            }

        }
        final Goods g =new Goods();
        new Thread(new Runnable(){
            public void run(){
                while(true){
                    g.produce("商品");
                }
            }
        }).start();
        new Thread(new Runnable(){
            public void run(){
                while(true){
                    g.consume();
                }
            }
        }).start();
    }
}