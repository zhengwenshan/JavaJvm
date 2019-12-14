package com.zws.jvm.gc.collector;

/**
 *
 * -verbose:gc
 * -Xms20M
 * -Xmx20M
 * -Xmn10M
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8
 * -XX:+UseConcMarkSweepGC
 * -XX:+PrintCommandLineFlags
 * -XX:+CMSScavengeBeforeRemark
 *
 *
 * 备注：
 * -XX:+CMSScavengeBeforeRemark
 * 在CMS GC前启动一次ygc，目的在于减少old gen对ygc gen的引用，降低remark时的开销-----一般CMS的GC耗时 80%都在remark阶段
 *
 * -XX:CMSInitiatingOccupancyFraction=90 -XX:+UseCMSInitiatingOccupancyOnly (这两个设置没有任何效果.)
 *
 * @author zhengws
 * @date 2019-12-14 16:42
 */
public class CmsCollector {
    public static void main(String[] args) {
        int size = 1024 * 1024;
        byte[] bytes1 = new byte[4 * size];
        System.out.println("11111111111111");
        byte[] bytes2 = new byte[4 * size];
        System.out.println("22222222222222");
        byte[] bytes3 = new byte[4 * size];
        System.out.println("33333333333333");
        byte[] bytes4 = new byte[2 * size];
        System.out.println("44444444444444");

        /**
         * 输出结果：
         * 11111111111111
         * [GC (Allocation Failure) [ParNew: 5793K->410K(9216K), 0.0028541 secs] 5793K->4508K(19456K), 0.0028897 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
         * 22222222222222
         * [GC (Allocation Failure) [ParNew: 4745K->525K(9216K), 0.0029726 secs] 8843K->8722K(19456K), 0.0029932 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
         * [GC (CMS Initial Mark) [1 CMS-initial-mark: 8196K(10240K)] 12873K(19456K), 0.0002116 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * [CMS-concurrent-mark-start]
         * 33333333333333
         * [CMS-concurrent-mark: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
         * [CMS-concurrent-preclean-start]
         * [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * [CMS-concurrent-abortable-preclean-start]
         * [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * [GC (CMS Final Remark) [YG occupancy: 7045 K (9216 K)][Rescan (parallel) , 0.0003543 secs][weak refs processing, 0.0000074 secs][class unloading, 0.0002005 secs][scrub symbol table, 0.0004683 secs][scrub string table, 0.0001520 secs][1 CMS-remark: 8196K(10240K)] 15241K(19456K), 0.0012373 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * 44444444444444
         * [CMS-concurrent-sweep-start]
         * [CMS-concurrent-sweep: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * [CMS-concurrent-reset-start]
         * [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * Heap
         *  par new generation   total 9216K, used 7127K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
         *   eden space 8192K,  80% used [0x00000007bec00000, 0x00000007bf272610, 0x00000007bf400000)
         *   from space 1024K,  51% used [0x00000007bf400000, 0x00000007bf4837e0, 0x00000007bf500000)
         *   to   space 1024K,   0% used [0x00000007bf500000, 0x00000007bf500000, 0x00000007bf600000)
         *  concurrent mark-sweep generation total 10240K, used 8196K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
         *  Metaspace       used 2968K, capacity 4496K, committed 4864K, reserved 1056768K
         *   class space    used 328K, capacity 388K, committed 512K, reserved 1048576K
         *
         *
         *   说明：
         *   1、如果老年代垃圾收集器采用CMS，则新生代垃圾收集器默认是ParNew垃圾收集器
         *   2、CMS垃圾收集器的几个阶段：
         *   CMS-initial-mark  初始标记，仅仅只标记与GC Roots直接相连的对象与及年轻代所引用的对象，会进行STW。
         *   CMS-concurrent-mark 并发标记，与用户线程并发执行，根据初始标记的对象进行Trace.
         *   CMS-concurrent-preclean
         *   CMS-concurrent-abortable-preclean
         *   CMS-final-remark
         *   CMS-concurrent-sweep
         *   CMS-concurrent-reset
         */
    }
}
