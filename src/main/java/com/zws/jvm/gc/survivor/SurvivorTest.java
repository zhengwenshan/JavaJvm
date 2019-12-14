package com.zws.jvm.gc.survivor;

/**
 *
 * -verbose:gc -Xmx200M -Xmn50M -XX:TargetSurvivorRatio=60 -XX:+PrintTenuringDistribution -XX:+PrintGCDetails
 * -XX:+PrintGCDateStamps -XX:+UseConcMarkSweepGC -XX:+UseParNewGC
 * @author zhengws
 * @date 2019-12-14 10:44
 */
public class SurvivorTest {

    private static void myGc() {
        for (int i = 0; i < 40; i++) {
            byte[] bytes = new byte[1024 * 1024];
        }
    }

    public static void main(String[] args) {
        byte[] bytes1 = new byte[512 * 1024];
        byte[] bytes2 = new byte[512 * 1024];

        myGc();
        System.out.println("1111111111111111");

        myGc();
        System.out.println("2222222222222222");

        myGc();
        System.out.println("3333333333333333");


        byte[] bytes3 = new byte[1024 * 1024];
        byte[] bytes4 = new byte[1024 * 1024];
        byte[] bytes5 = new byte[1024 * 1024];

        myGc();
        System.out.println("4444444444444444");

        myGc();
        System.out.println("5555555555555555");

        myGc();
        System.out.println("6666666666666666");

        myGc();
        System.out.println("7777777777777777");

        /**
         * 2019-12-14T10:57:33.489-0800: [GC (Allocation Failure) 2019-12-14T10:57:33.489-0800: [ParNew
         *
         * 说明一：Desired survivor size 3145728 bytes 的由来
         *  由于设置新生代的大小为50M，且采用默认的Eden survivor 比例，8:1,因此每个survivor的大小为5M，而我们手动设置了-XX:TargetSurvivorRatio=60，
         *  也就是当 Survivor 占用的空间达到60%后，则认为Survivor空间不足，则进行自动调整对象存活阈值。
         *  5 M * 0.6 = 3M 也就是(3145728 bytes)
         *
         * Desired survivor size 3145728 bytes, new threshold 3 (max 3)
         * - age   1:    1476224 bytes,    1476224 total
         * : 40141K->1490K(46080K), 0.0009066 secs] 40141K->1490K(199680K), 0.0009507 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
         * 1111111111111111
         * 2019-12-14T10:57:33.494-0800: [GC (Allocation Failure) 2019-12-14T10:57:33.494-0800: [ParNew
         * Desired survivor size 3145728 bytes, new threshold 3 (max 3)
         * - age   1:        248 bytes,        248 total
         * - age   2:    1462712 bytes,    1462960 total
         * : 42221K->1599K(46080K), 0.0008522 secs] 42221K->1599K(199680K), 0.0008894 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
         * 2222222222222222
         * 2019-12-14T10:57:33.498-0800: [GC (Allocation Failure) 2019-12-14T10:57:33.498-0800: [ParNew
         * Desired survivor size 3145728 bytes, new threshold 3 (max 3)
         * - age   1:         72 bytes,         72 total
         * - age   2:        248 bytes,        320 total
         * - age   3:    1461432 bytes,    1461752 total
         * : 42123K->1595K(46080K), 0.0006687 secs] 42123K->1595K(199680K), 0.0006924 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * 3333333333333333
         *
         * 说明二：当Survivor 空间对象占用率达到60%后，则认为Survivor空间不足，则进行自动调整对象存活阈值
         * 将对象存活时间自动调整为1，也就是经过一次垃圾收集后，依然没有被回收掉，则直接进入到老年代。
         *
         * 2019-12-14T10:57:33.501-0800: [GC (Allocation Failure) 2019-12-14T10:57:33.501-0800: [ParNew
         * Desired survivor size 3145728 bytes, new threshold 1 (max 3)
         * - age   1:    3145848 bytes,    3145848 total
         * - age   2:         72 bytes,    3145920 total
         * - age   3:        248 bytes,    3146168 total
         * : 42322K->3215K(46080K), 0.0026960 secs] 42322K->4663K(199680K), 0.0027181 secs] [Times: user=0.00 sys=0.01, real=0.00 secs]
         * 4444444444444444
         *
         * 说明三：当Survivor 空间对象占用率低于60%后，则认为Survivor有足够的空间，则进行自动调整恢复对象存活阈值
         * 将对象存活时间恢复为3 (手动设置的最大值)，也就是来回经过3次垃圾收集后，依然没有被回收掉，则才将该对象放入到老年代中。
         *
         * 2019-12-14T10:57:33.507-0800: [GC (Allocation Failure) 2019-12-14T10:57:33.507-0800: [ParNew
         * Desired survivor size 3145728 bytes, new threshold 3 (max 3)
         * - age   1:         72 bytes,         72 total
         * : 43947K->23K(46080K), 0.0020546 secs] 45395K->4544K(199680K), 0.0020783 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
         * 5555555555555555
         * 2019-12-14T10:57:33.512-0800: [GC (Allocation Failure) 2019-12-14T10:57:33.512-0800: [ParNew
         * Desired survivor size 3145728 bytes, new threshold 3 (max 3)
         * - age   1:         72 bytes,         72 total
         * - age   2:         72 bytes,        144 total
         * : 40758K->5K(46080K), 0.0004664 secs] 45278K->4526K(199680K), 0.0004898 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * 6666666666666666
         * 2019-12-14T10:57:33.515-0800: [GC (Allocation Failure) 2019-12-14T10:57:33.515-0800: [ParNew
         * Desired survivor size 3145728 bytes, new threshold 3 (max 3)
         * - age   1:         72 bytes,         72 total
         * - age   2:         72 bytes,        144 total
         * - age   3:         72 bytes,        216 total
         * : 40742K->8K(46080K), 0.0004527 secs] 45262K->4528K(199680K), 0.0004777 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
         * 7777777777777777
         * Heap
         *  par new generation   total 46080K, used 16988K [0x00000007b3800000, 0x00000007b6a00000, 0x00000007b6a00000)
         *   eden space 40960K,  41% used [0x00000007b3800000, 0x00000007b48952f8, 0x00000007b6000000)
         *   from space 5120K,   0% used [0x00000007b6500000, 0x00000007b6502040, 0x00000007b6a00000)
         *   to   space 5120K,   0% used [0x00000007b6000000, 0x00000007b6000000, 0x00000007b6500000)
         *  concurrent mark-sweep generation total 153600K, used 4520K [0x00000007b6a00000, 0x00000007c0000000, 0x00000007c0000000)
         *  Metaspace       used 3085K, capacity 4496K, committed 4864K, reserved 1056768K
         *   class space    used 339K, capacity 388K, committed 512K, reserved 1048576K
         */
    }

}
