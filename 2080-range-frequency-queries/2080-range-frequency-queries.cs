public class RangeFreqQuery {
    private Dictionary<int, List<int>> valueIndices;

    public RangeFreqQuery(int[] arr) {
        valueIndices = new Dictionary<int, List<int>>();
        for (int i = 0; i < arr.Length; i++) {
            if (!valueIndices.ContainsKey(arr[i])) {
                valueIndices[arr[i]] = new List<int>();
            }
            valueIndices[arr[i]].Add(i);
        }
    }
    
    public int Query(int left, int right, int value) {
        if (!valueIndices.ContainsKey(value)) {
            return 0;
        }

        List<int> indices = valueIndices[value];
        
        int rightCount = UpperBound(indices, right);
        int leftCount = LowerBound(indices, left);
        
        return rightCount - leftCount;
    }

    private int LowerBound(List<int> list, int target) {
        int low = 0, high = list.Count;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private int UpperBound(List<int> list, int target) {
        int low = 0, high = list.Count;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}