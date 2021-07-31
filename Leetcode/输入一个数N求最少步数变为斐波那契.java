int main()
{
	int f0 = 0, f1 = 1,f2;
	int N = 0;
	scanf_s("%d", &N);
	while (1)
	{
		f2 = f1 + f0;
		if (N <= f2)
		{
			if (f2 - N < N - f1)
			{
				printf("%d", f2 - N);
			}
			else
			{
				printf("%d", N - f1);
			}
			break;
		}
		f0 = f1;
		f1 = f2;
	}
	return 0;
}
————————————————
版权声明：本文为CSDN博主「西科陈冠希」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/weixin_43762735/article/details/109891579