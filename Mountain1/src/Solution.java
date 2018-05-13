import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution 
{
	static int map[][];
	static boolean visit[][];
	static int N, K;
	static int max = 0;
	static final int dx[] = {0, 0, 1, -1};
	static final int dy[] = {1, -1, 0, 0};
	static int ans;
	static boolean used = false;

	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		// TODO Auto-generated method stub
		
		int T;
		String tmp[];
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(scan.readLine());
		
		for(int i = 0; i < T; i++)
		{
			ans = 0;
			max = 0;
			tmp = scan.readLine().split(" ");
			N = Integer.parseInt(tmp[0]);
			K = Integer.parseInt(tmp[1]);
			map = new int[N][N];
			visit = new boolean[N][N];
			
			for(int j = 0; j < N; j++)
			{
				tmp = scan.readLine().split(" ");
				
				for(int k = 0; k < tmp.length; k++)
				{
					map[j][k] = Integer.parseInt(tmp[k]);
				}
			}
			
			getMax();
			
			for(int x = 0; x < N; x++)
			{
				for(int y = 0; y < N; y++)
				{
					if(map[x][y] == max)
					{
						visit[x][y] = true;
						dfs(x, y, 1);
						visit[x][y] = false;
					}
				}
			}
			
			System.out.println("#" + (i + 1) + " " + ans);
		}
	}
	
	public static void dfs(int bx, int by, int cnt)
	{
		int ax, ay;
		int tmp = 0;
		ans = Math.max(ans, cnt);
		
		for(int i = 0; i < 4; i++)
		{
			ax = bx + dx[i];
			ay = by + dy[i];
			
			if(0 <= ax && ax <= N - 1 && 0 <= ay && ay <= N - 1)
			{
				if(map[bx][by] > map[ax][ay] && !visit[ax][ay])
				{
					visit[ax][ay] = true;
					dfs(ax, ay, cnt + 1);
					visit[ax][ay] = false;
				}
				
				else if(!used && map[bx][by] > map[ax][ay] - K && !visit[ax][ay])
				{
					used = true;
					visit[ax][ay] = true;
					tmp = map[ax][ay];
					map[ax][ay] = map[bx][by] - 1; 
					dfs(ax, ay, cnt + 1);
					map[ax][ay] = tmp;
					visit[ax][ay] = false;
					used = false;
				}
			}
		}
	}
	
	public static void getMax()
	{
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				max = Math.max(max, map[i][j]);
			}
		}
	}
}
