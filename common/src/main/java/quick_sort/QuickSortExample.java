package quick_sort;

public class QuickSortExample
{
	public void quickSort(int[] arr, int low, int high)
	{
		if (low >= high)
		{
			return;
		}
		int tmp = arr[low];
		int i = low;
		int j = high;

		while (i < j)
		{
			// 先看右边，依次往左递减，直到找到小于基准数的数字
			while (arr[j] >= tmp && i < j)
				j--;
			// 比基准小的记录移到低端
			arr[i] = arr[j];
			// 后看左边，依次往右递增，直到找到大于基准数的数字
			while (arr[i] <= tmp && i < j)
				i++;
			// 比基准大的记录移到高端
			arr[j] = arr[i];
		}

		// 记录基准
		arr[i] = tmp;
		// 递归调用左半数组
		quickSort(arr, low, j - 1);
		// 递归调用右半数组
		quickSort(arr, j + 1, high);
	}

	public static void main(String[] args)
	{
		int[] arr = { 2,6,1,3,9,34,27,18,28,87,73,90 };
		QuickSortExample example = new QuickSortExample();
		example.quickSort(arr, 0, arr.length - 1);
		
		System.out.println("排序后的数组：");
		for (int ele : arr)
		{
			System.out.print(ele + " ");
		}
	}

}
