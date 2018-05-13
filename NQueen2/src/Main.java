import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main 
{
	static int N;
	static int ans;
	static int map[]; // visit[x] = y
	
	public static void main(String[] args)  throws IOException 
	{
		// TODO Auto-generated method stub
		
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(scan.readLine());
		map = new int[N];
		
		dfs(0);
		System.out.println(ans);

	}
	
	public static void dfs(int row)
	{
		if(row == N)
		{
			ans++;
		}
		
		else
		{	
			for(int i = 0; i < N; i++)
			{
				map[row] = i;
				
				if(check(row))
				{
					dfs(row + 1);
				}
			}
		}
	}
	
	public static boolean check(int x)
	{
		for(int i = 0; i < x; i++) // x행 전까지만 보기. (x행 전까지만 queen이 놓여 있기 때문에)
		{
			if(map[i] == map[x] || Math.abs(i - x) == Math.abs(map[i] - map[x])) // 열, 대각선 체크 (행은 자동으로 다름)
			{
				return false;
			}
		}
		
		return true;
	}
}
