
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Utilizando las herramientas ReentrantLocky Condition proporcionadas en java.util.concurrent, desarrolle Terrain1.
 * 
 * @author CSD Juansa Sendra
 * @version 2021
 */
public class Terrain1 implements Terrain {
    Viewer v;
    ReentrantLock lock = new ReentrantLock();
    Condition c = lock.newCondition();

    public  Terrain1 (int t, int ants, int movs) {
        v=new Viewer(t,ants,movs,"1.- ReentrantLock and Condition");
        for (int i=0; i<ants; i++) new Ant(i,this,movs).start();
    }

    public void     hi      (int a) {
        lock.lock();
        try{
            v.hi(a); 
        }finally{lock.unlock();}
    }

    public void     bye     (int a) {
        lock.lock();
        try{
            v.bye(a);
        }finally{lock.unlock();}
    }

    public void     move    (int a) throws InterruptedException {
        lock.lock();
        try {
            v.turn(a); Pos dest=v.dest(a); 
            while (v.occupied(dest)) {c.await(); v.retry(a);}
            v.go(a); 
            c.signalAll();
        }finally{lock.unlock();}
    }
}
