/**
 * ID: 1417006
 * NAME: VIDUSH H. NAMAH
 * COURSE: BSC (HONS) COMPUTER SCIENCE
 * LEVEL: 3 GROUP B
 * DATE: 05.03.2017
 */

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    /**
     * SERIAL SORTING COMPLETES IN ABOUT 2s (1000000 VALUES).
     * THREAD SORTING RUNTIME VARIES (ALWAYS BELOW SERIAL TIME).
     *
     * THE THREADED SORT CALLS A NEW THREAD TO SORT EACH PARTITION
     * WHILE THE TOTAL NUMBER OF THREADS (VARIABLE: LIMIT) DOES NOT
     * EXCEED THE NUMBER OF THREADS LOGICALLY AVAILABLE (THREAD_MAX).
     *
     * IN THE EVENT THE LIMIT HAS BEEN REACHED, THE THREAD PROCEEDS
     * TO SORT ITS PARTITION SERIALLY.
     *
     * THIS WAS AN ATTEMPT TO OPTIMIZE THE USE OF THE PROCESSOR AND
     * AVOID UNNECESSARY OVERHEADS BY CREATING VIRTUAL THREADS THAT
     * EXCEED THE LIMIT - HENCE EXECUTING SERIALLY REGARDLESS.
     */

    private static final int SIZE = 1000000;
    private static int THREAD_MAX = Runtime.getRuntime().availableProcessors();
    private static AtomicInteger LIMIT = new AtomicInteger(0);
    private static ExecutorService Pool = Executors.newFixedThreadPool(THREAD_MAX);
    //private static volatile ExecutorService Pool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        //VARIABLES
        int[] SerialArray = new int[SIZE];
        int[] ThreadArray = new int[SIZE];

        System.out.println(SIZE + " INTEGERS.");
        Random R = new Random();
        for (int I = 0; I < SIZE; I++) {
            SerialArray[I] = R.nextInt(100);
            ThreadArray[I] = SerialArray[I];
        }

        System.out.println("SORTING ARRAY");

        long Start, End;
        /**--SERIAL SORT STARTS=--**/
        Start = System.nanoTime();
        SerialSort(SerialArray, 0, SIZE -1);
        End = System.nanoTime();

        if (Common.isSorted(SerialArray))
            System.out.printf("SERIAL [%fs]\n", ((End-Start)*1E-9));
        else System.out.println("ERROR: UNSORTED");
        /**--SERIAL SORT ENDS=--**/

        /**--THREADED SORT STARTS=--**/
        Start = System.nanoTime();
        ThreadedSort(ThreadArray);
        End = System.nanoTime();

        if (Common.isSorted(ThreadArray))
            System.out.printf("THREAD [%fs]\n", ((End-Start)*1E-9));
        else System.out.println("ERROR: THREAD_MAX TIMED OUT");
        /**--THREADED SORT ENDS=--**/

        System.out.printf("THREAD LIMIT: %d\nTHREADS USED: %d\n", THREAD_MAX, LIMIT.get());

        return;
    }

    public static void SerialSort(int[] Array, int L, int R) {
        if (R > L) {
            int i = Common.Partition(Array, L, R);

            SerialSort(Array, L, i - 1);
            SerialSort(Array, i + 1, R);
        }
    }

    public static void ThreadedSort(int[] Array) throws Exception {
        int L=0; int R=Array.length-1;

        QSCallable Q = new QSCallable(Array, L, R);
        Pool.submit(Q).get();

        //Pool.awaitTermination(2, TimeUnit.SECONDS);
        Pool.shutdown();
    }

    /**
     * CALLABLE THREADED QUICK-SORT
     **/
    static class QSCallable implements Callable<Boolean> {

        private int[] Array;
        private int L, R;
        private Boolean isSerial = false;

        public QSCallable(int[] A, int Left, int Right) {
            if (LIMIT.get()>THREAD_MAX-2) isSerial=true;
            else LIMIT.incrementAndGet();

            Array = A;
            L = Left;
            R = Right;
        }

        @Override
        public Boolean call() throws Exception {
            if (R > L) {
                int i = Common.Partition(Array, L, R);

                if (isSerial) {
                    SerialSort(Array, L, i - 1);
                    SerialSort(Array, i + 1, R);
                }

                else {

                    QSCallable Q1 = new QSCallable(Array, L, i - 1);
                    QSCallable Q2 = new QSCallable(Array, i + 1, R);

                    Future<Boolean> B1 = Pool.submit(Q1);
                    Future<Boolean> B2 = Pool.submit(Q2);

                    B1.get(); B2.get();
                }
            }

            return true;
        }
    }

    /**
     * COMMON FUNCTIONS
     **/
    static class Common {
        public static int Partition(int[] Array, int L, int R) {
            int Pivot = Array[R];

            int I = L;
            int J = R;

            while (I < J) {
                while (Array[I] < Pivot)
                    I++;

                while (I < J && Array[J] >= Pivot)
                    J--;

                if (I < J) Swap(Array, I, J);
                else Swap(Array, I, R);
            }

            return I;
        }

        public static void Swap(int[] Array, int i, int j) {
            int Buffer = Array[j];
            Array[j] = Array[i];
            Array[i] = Buffer;
        }

        public static Boolean isSorted(int[] Array) {
            for (int i = 0; i < Array.length - 1; i++)
                if (Array[i] > Array[i + 1]) return false;

            return true;
        }

        public static synchronized void PrintArray(int[] Array) {
            for (int I: Array)
                System.out.printf("%d ", I);

            System.out.println();
        }
    }
}