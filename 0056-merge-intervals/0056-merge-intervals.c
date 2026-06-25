#include <stdlib.h>

int compareIntervals(const void* a, const void* b) {
    int* intervalA = *(int**)a;
    int* intervalB = *(int**)b;
    return intervalA[0] - intervalB[0];
}

int** merge(int** intervals, int intervalsSize, int* intervalsColSize, int* returnSize, int** returnColumnSizes) {
    if (intervalsSize == 0) {
        *returnSize = 0;
        *returnColumnSizes = NULL;
        return NULL;
    }

    qsort(intervals, intervalsSize, sizeof(int*), compareIntervals);

    int** merged = (int**)malloc(intervalsSize * sizeof(int*));
    merged[0] = (int*)malloc(2 * sizeof(int));
    merged[0][0] = intervals[0][0];
    merged[0][1] = intervals[0][1];
    
    int count = 1;

    for (int i = 1; i < intervalsSize; i++) {
        if (intervals[i][0] <= merged[count - 1][1]) {
            if (intervals[i][1] > merged[count - 1][1]) {
                merged[count - 1][1] = intervals[i][1];
            }
        } else {
            merged[count] = (int*)malloc(2 * sizeof(int));
            merged[count][0] = intervals[i][0];
            merged[count][1] = intervals[i][1];
            count++;
        }
    }

    *returnSize = count;
    *returnColumnSizes = (int*)malloc(count * sizeof(int));
    for (int i = 0; i < count; i++) {
        (*returnColumnSizes)[i] = 2;
    }

    return merged;
}