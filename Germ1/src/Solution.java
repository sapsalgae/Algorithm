import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/* SW EXPERT ACADEMY - 미생물 격리 */

public class Solution
{
	static int N, M, K;
	static int map[][];
	static int dir[][];
	
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		
		int x, y, num, d;
		int T;
		String tmp[];
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(scan.readLine());
		
		for(int i = 1; i <= T; i++)
		{
			tmp = scan.readLine().split(" ");
			N = Integer.parseInt(tmp[0]); 
			M = Integer.parseInt(tmp[1]); 
			K = Integer.parseInt(tmp[2]); 
			
			map = new int[N][N];
			dir = new int[N][N];
			
			for(int j = 0; j < K; j++)
			{
				tmp = scan.readLine().split(" ");
				
				x = Integer.parseInt(tmp[0]); 
				y = Integer.parseInt(tmp[1]); 
				num = Integer.parseInt(tmp[2]); 
				d = Integer.parseInt(tmp[3]);
				
				map[x][y] = num; dir[x][y] = d;
			}
			
			System.out.println("#" + i + " "  + simulation());
		}
	}
	
	public static int simulation()
	{
		int time = 0;
		int nextMap[][];
		int nextDir[][];
		int max[][]; // 동시에 3개가 겹쳐지는 순간이 있는데 8, 9, 16이 동시에 합쳐지면 16의 방향대로 가야되는데 8 + 9 = 17이 되어서 9의 방향대로 흘러 갈 수 있음. 이를 해결하기 위해 max 값을 저장함. (max = 8 -> 9 -> 16)
		
		while(time < M)
		{
			time++;
			
			nextMap = new int[N][N];
			nextDir = new int[N][N];
			max = new int[N][N];
			
			for(int i = 0; i < N; i++)
			{
				for(int j = 0; j < N; j++)
				{
					if(map[i][j] != 0)
					{
						switch(dir[i][j])
						{
							case 1:
								if(i - 1 == 0) // 약품 처리
								{		
									nextMap[i - 1][j] += map[i][j] / 2;
									nextDir[i - 1][j] = 2;
								}
								
								else
								{
									if(max[i - 1][j] < map[i][j]) // 다른 미생물과 합쳐지는 경우, 다른 미생물 군집 수가 많으면 방향은 유지
									{
										nextDir[i - 1][j] = 1;
										max[i - 1][j] = map[i][j];
									}
									
									nextMap[i - 1][j] += map[i][j];
								}
								
								break;
								
							case 2:
								if(i + 1 == N - 1)
								{		
									nextMap[i + 1][j] += map[i][j] / 2;
									nextDir[i + 1][j] = 1;
								}
								
								else
								{	
									if(max[i + 1][j] < map[i][j])
									{
										nextDir[i + 1][j] = 2;
										max[i + 1][j] = map[i][j];
									}
									
									nextMap[i + 1][j] += map[i][j];
								}
								
								break;
								
							case 3:
								if(j - 1 == 0)
								{		
									nextMap[i][j - 1] += map[i][j] / 2;
									nextDir[i][j - 1] = 4;
								}
								
								else
								{
									if(max[i][j - 1] < map[i][j])
									{
										nextDir[i][j - 1] = 3;
										max[i][j - 1] = map[i][j];
									}
									
									nextMap[i][j - 1] += map[i][j];
								}
								
								break;
								
							case 4:
								if(j + 1 == N - 1) 
								{		
									nextMap[i][j + 1] += map[i][j] / 2;
									nextDir[i][j + 1] = 3;
								}
								
								else
								{
									if(max[i][j + 1] < map[i][j])
									{
										nextDir[i][j + 1] = 4;
										max[i][j + 1] = map[i][j];
									}
									
									nextMap[i][j + 1] += map[i][j];
								}
								
								break;
						}
					}
				}
			}
			
			for(int i = 0; i < N; i++)
			{
				map[i] = Arrays.copyOf(nextMap[i], N);
				dir[i] = Arrays.copyOf(nextDir[i], N);
			}
		}
		
		return getNum();
	}
	
	public static int getNum()
	{
		int total = 0;
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				total += map[i][j];
			}
		}
		
		return total;
	}
}