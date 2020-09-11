package com.example.demo4;

import org.junit.Test;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/8/26 16:13
 * @Description:
 */
public class Test14 {

	public static void main(String[] args) {
		int whileMax = 2000000000;

		long start = System.currentTimeMillis();
		int sum = 0;
		for (int i = 0; i < whileMax; i++) {
			sum++;
		}
		System.out.println(System.currentTimeMillis() - start);

		start = System.currentTimeMillis();
		long sum2 = 0;
		for (int i = 0; i < whileMax; i++) {
			sum2++;
		}
		System.out.println(System.currentTimeMillis() - start);
	}

	Last modified 2020-9-11; size 838 bytes
	MD5 checksum 767e744c6babb62b36c3a33f2a3c6505
	Compiled from "Test14.java"
	public class com.example.demo4.Test14
	minor version: 0
	major version: 52
	flags: ACC_PUBLIC, ACC_SUPER
	Constant pool:
			#1 = Methodref          #7.#30         // java/lang/Object."<init>":()V
			#2 = Integer            2000000000
			#3 = Methodref          #31.#32        // java/lang/System.currentTimeMillis:()J
			#4 = Fieldref           #31.#33        // java/lang/System.out:Ljava/io/PrintStream;
			#5 = Methodref          #34.#35        // java/io/PrintStream.println:(J)V
			#6 = Class              #36            // com/example/demo4/Test14
			#7 = Class              #37            // java/lang/Object
			#8 = Utf8               <init>
   #9 = Utf8               ()V
  #10 = Utf8               Code
  #11 = Utf8               LineNumberTable
  #12 = Utf8               LocalVariableTable
  #13 = Utf8               this
			#14 = Utf8               Lcom/example/demo4/Test14;
  #15 = Utf8               main
  #16 = Utf8               ([Ljava/lang/String;)V
  #17 = Utf8               i
  #18 = Utf8               I
  #19 = Utf8               args
  #20 = Utf8               [Ljava/lang/String;
  #21 = Utf8               whileMax
  #22 = Utf8               start
  #23 = Utf8               J
  #24 = Utf8               sum
  #25 = Utf8               sum2
  #26 = Utf8               StackMapTable
  #27 = Class              #20            // "[Ljava/lang/String;"
			#28 = Utf8               SourceFile
  #29 = Utf8               Test14.java
  #30 = NameAndType        #8:#9          // "<init>":()V
			#31 = Class              #38            // java/lang/System
			#32 = NameAndType        #39:#40        // currentTimeMillis:()J
			#33 = NameAndType        #41:#42        // out:Ljava/io/PrintStream;
			#34 = Class              #43            // java/io/PrintStream
			#35 = NameAndType        #44:#45        // println:(J)V
			#36 = Utf8               com/example/demo4/Test14
  #37 = Utf8               java/lang/Object
  #38 = Utf8               java/lang/System
  #39 = Utf8               currentTimeMillis
  #40 = Utf8               ()J
  #41 = Utf8               out
  #42 = Utf8               Ljava/io/PrintStream;
  #43 = Utf8               java/io/PrintStream
  #44 = Utf8               println
  #45 = Utf8               (J)V
	{
  public com.example.demo4.Test14();
		descriptor: ()V
		flags: ACC_PUBLIC
		Code:
		stack=1, locals=1, args_size=1
		0: aload_0
		1: invokespecial #1                  // Method java/lang/Object."<init>":()V
		4: return
			LineNumberTable:
		line 10: 0
		LocalVariableTable:
		Start  Length  Slot  Name   Signature
		0       5     0  this   Lcom/example/demo4/Test14;

		public static void main(java.lang.String[]);
		descriptor: ([Ljava/lang/String;)V
		flags: ACC_PUBLIC, ACC_STATIC
		Code:
		stack=5, locals=8, args_size=1
		0: ldc           #2                  // int 2000000000
		2: istore_1
		3: invokestatic  #3                  // Method java/lang/System.currentTimeMillis:()J
		6: lstore_2
		7: iconst_0
		8: istore        4
		10: iconst_0
		11: istore        5
		13: iload         5
		15: iload_1
		16: if_icmpge     28
		19: iinc          4, 1
		22: iinc          5, 1
		25: goto          13
		28: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
		31: invokestatic  #3                  // Method java/lang/System.currentTimeMillis:()J
		34: lload_2
		35: lsub
		36: invokevirtual #5                  // Method java/io/PrintStream.println:(J)V
		39: invokestatic  #3                  // Method java/lang/System.currentTimeMillis:()J
		42: lstore_2
		43: lconst_0
		44: lstore        5
		46: iconst_0
		47: istore        7
		49: iload         7
		51: iload_1
		52: if_icmpge     67
		55: lload         5
		57: lconst_1
		58: ladd
		59: lstore        5
		61: iinc          7, 1
		64: goto          49
		67: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
		70: invokestatic  #3                  // Method java/lang/System.currentTimeMillis:()J
		73: lload_2
		74: lsub
		75: invokevirtual #5                  // Method java/io/PrintStream.println:(J)V
		78: return
			LineNumberTable:
		line 13: 0
		line 15: 3
		line 16: 7
		line 17: 10
		line 18: 19
		line 17: 22
		line 20: 28
		line 22: 39
		line 23: 43
		line 24: 46
		line 25: 55
		line 24: 61
		line 27: 67
		line 28: 78
		LocalVariableTable:
		Start  Length  Slot  Name   Signature
		13      15     5     i   I
		49      18     7     i   I
		0      79     0  args   [Ljava/lang/String;
		3      76     1 whileMax   I
		7      72     2 start   J
		10      69     4   sum   I
		46      33     5  sum2   J
		StackMapTable: number_of_entries = 4
		frame_type = 255 /* full_frame */
		offset_delta = 13
		locals = [ class "[Ljava/lang/String;", int, long, int, int ]
		stack = []
		frame_type = 250 /* chop */
		offset_delta = 14
		frame_type = 253 /* append */
		offset_delta = 20
		locals = [ long, int ]
		frame_type = 250 /* chop */
		offset_delta = 17
	}
	SourceFile: "Test14.java"
}


