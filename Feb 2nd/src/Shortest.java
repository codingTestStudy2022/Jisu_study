/*
 * 2022.02.11
 * 백준 1753번 "최단경로"
 * https://www.acmicpc.net/problem/1753
 * 
 * 다익스트라 유형
 */

import java.util.*;
import java.io.*;

public class Shortest {
	
	static int start;
	static ArrayList[] vertex;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		
		start = Integer.parseInt(br.readLine())-1;
		vertex = new ArrayList[n];
		dp = new int[n];
		
		for(int i = 0; i<n; i++){
			vertex[i] = new ArrayList<Vertex2>();
			dp[i] = Integer.MAX_VALUE;
		}
		
		for(int i = 0; i<v; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int value = Integer.parseInt(st.nextToken());
			
			vertex[from].add(new Vertex2(from, to, value));
		}
		
		br.close();
		
		dijkstra();
		
		for(int min: dp) {
			if(min == Integer.MAX_VALUE) {
				bw.append("INF\n");
			} else {
				bw.append(min + "\n");
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	static void dijkstra() {
		PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return Integer.compare(a[1], b[1]);
			}
		});
		
		int[] start_node = {start, 0};
		queue.add(start_node);
		dp[start] = 0;
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int node = now[0];
			int now_v = now[1];
			
			if(dp[node] != now_v) continue;
			
			List<Vertex2> list = vertex[node];
			
			for(Vertex2 v: list) {
				int next = v.to;
				int v_value = v.value;
				if(dp[next] > (dp[node] + v_value)) {
					dp[next] = dp[node] + v_value;
					int[] item = {next, dp[next]};
					queue.add(item);
				}
			}
		}
	}
}

class Vertex2{
	int from;
	int to;
	int value;
	
	public Vertex2(int from, int to, int value) {
		this.from = from;
		this.to = to;
		this.value = value;
	}
}
