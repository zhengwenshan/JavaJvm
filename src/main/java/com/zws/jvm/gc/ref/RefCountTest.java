package com.zws.jvm.gc.ref;

import com.sun.management.GarbageCollectorMXBean;
import com.sun.management.GcInfo;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @author zhengws
 * @date 2019-08-22 14:23
 */


public class RefCountTest {

    /***
     * [GC (System.gc()) [PSYoungGen: 6717K->592K(76288K)] 6717K->600K(251392K), 0.0014336 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
     * [Full GC (System.gc()) [PSYoungGen: 592K->0K(76288K)] [ParOldGen: 8K->392K(175104K)] 600K->392K(251392K), [Metaspace: 2958K->2958K(1056768K)], 0.0036940 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
     * Heap
     *  PSYoungGen      total 76288K, used 1966K [0x000000076ab00000, 0x0000000770000000, 0x00000007c0000000)
     *   eden space 65536K, 3% used [0x000000076ab00000,0x000000076aceba28,0x000000076eb00000)
     *   from space 10752K, 0% used [0x000000076eb00000,0x000000076eb00000,0x000000076f580000)
     *   to   space 10752K, 0% used [0x000000076f580000,0x000000076f580000,0x0000000770000000)
     *  ParOldGen       total 175104K, used 392K [0x00000006c0000000, 0x00000006cab00000, 0x000000076ab00000)
     *   object space 175104K, 0% used [0x00000006c0000000,0x00000006c00623c0,0x00000006cab00000)
     *  Metaspace       used 2965K, capacity 4496K, committed 4864K, reserved 1056768K
     *   class space    used 328K, capacity 388K, committed 512K, reserved 1048576K
     * @param args
     */
    public static void main(String[] args) {
        RefCount objA = new RefCount();
//        objA.instance = objA  = null;
//        objA.instance = objA;
        RefCount objB = new RefCount();

        objA.instance = objB;
        objB.instance = objA;

//
        objA = null;
        objB = null;

        System.out.println(objA);
        System.gc();

        printGc();
    }


    private static void printGc(){
        List<GarbageCollectorMXBean> garbageCollectorMXBeanList = reloadGarbageCollectorMXBeanList();
        for (GarbageCollectorMXBean gcMXBean : garbageCollectorMXBeanList) {

            String lowerName = gcMXBean.getName().substring(0, 1).toLowerCase() + gcMXBean.getName().substring(1, gcMXBean.getName().length()).replace(" ", "");

            GcInfo gcInfo = gcMXBean.getLastGcInfo();
            if (gcInfo != null) {
                System.out.println(lowerName);
                System.out.println("before: " + gcInfo.getMemoryUsageBeforeGc());
                System.out.println("after: " + gcInfo.getMemoryUsageAfterGc());
            }

        }
    }

    public static synchronized List<GarbageCollectorMXBean> reloadGarbageCollectorMXBeanList(){
        List<GarbageCollectorMXBean> garbageCollectorMXBeanList = ManagementFactory.getPlatformMXBeans(GarbageCollectorMXBean.class);
        return garbageCollectorMXBeanList;
    }
}
