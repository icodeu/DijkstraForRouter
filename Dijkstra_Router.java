import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 *王欢 
 *实验四 路由算法
 */
public class Dijkstra_Router {

	public static void main(String[] args) throws IOException {
		File inFile = new File("data.in");
		File outFile = new File("data.out");
		Scanner scanner = new Scanner(inFile);
		FileOutputStream fos = new FileOutputStream(outFile);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"));

		int i, j;
		int n = scanner.nextInt();
		int target = scanner.nextInt();
		int[][] d = new int [n+2][n+2];
		int []book = new int [n+2];
		int []f = new int[n+2];
		int []path = new int[n+2];
		int min = 65535, min_pos = 0, min_path;
		for (i=0;i<n+2;i++)
			book[i] = 0;
		book[1] = 1;
		
		for (i=1;i<=n;i++)
			for (j=1;j<=n;j++)
				d[i][j] = scanner.nextInt();
		
		for (i=2;i<=n;i++){
			f[i] = d[1][i];
			if (d[1][i] != 65535)
				path[i] = 1;
		}
		
		writer.write("运算过程：");
		writer.newLine();
		writer.flush();
		
		System.out.println("运算过程：");
		
		while (containsAllNodes(book, n)){
			min = 65535;
			for (i=2;i<=n;i++)
				if (book[i] == 0 && f[i] < min){
					min = f[i];
					min_pos = i;
				}
			book[min_pos] = 1;
			
			for (i=1;i<=n;i++)
				if (d[min_pos][i] != 65535){
					if ( (f[min_pos] + d[min_pos][i]) < f[i] ){
						f[i] = (f[min_pos] + d[min_pos][i]);
						path[i] = min_pos;
					}
				}
					
			for (i=1;i<=n;i++){
				System.out.print(f[i] + " ");
				writer.write(Integer.toString(f[i]) + " ");
			}
			System.out.println();

			writer.newLine();
			writer.flush();
		}
		min_path = f[target];
		System.out.println("起点: 1   " + "终点:"+ target);
		
		//寻找最短路径
		List<String> pathList = new ArrayList<>();
		pathList.add(target+"");
		while (target != 1){
			target = path[target];
			pathList.add(target+"");
		}
		System.out.print("最短路径：" );
		for (i=pathList.size()-1;i>=0;i--)
			System.out.print(pathList.get(i) + "  ");
		System.out.println();

		writer.write("最短路径长： " + Integer.toString(min_path));
		writer.newLine();
		writer.flush();
		System.out.println("最短路径长： " + min_path);
	}

	private static boolean containsAllNodes(int[] book, int n) {
		for (int i=1;i<=n;i++){
			if (book[i] == 0)
				return true;
		}
		return false;
	}

}
