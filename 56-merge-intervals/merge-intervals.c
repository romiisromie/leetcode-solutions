#include <stdlib.h>

int compareIntervals(const void* a, const void* b) {
    int* intervalA = *(int**)a;
    int* intervalB = *(int**)b;
    if (intervalA[0] != intervalB[0]) {
        return intervalA[0] - intervalB[0];
    }
    return intervalA[1] - intervalB[1];
}

int** merge(int** intervals, int intervalsSize, int* intervalsColSize, int* returnSize, int** returnColumnSizes) {
    if (intervalsSize == 0) {
        *returnSize = 0;
        *returnColumnSizes = NULL;
        return NULL;
    }

    qsort(intervals, intervalsSize, sizeof(int*), compareIntervals);

    int** merged = (int**)malloc(intervalsSize * sizeof(int*));
    *returnColumnSizes = (int*)malloc(intervalsSize * sizeof(int));

    int count = 0;
    merged[count] = (int*)malloc(2 * sizeof(int));
    merged[count][0] = intervals[0][0];
    merged[count][1] = intervals[0][1];
    (*returnColumnSizes)[count] = 2;

    for (int i = 1; i < intervalsSize; ++i) {
        if (intervals[i][0] <= merged[count][1]) {
            if (intervals[i][1] > merged[count][1]) {
                merged[count][1] = intervals[i][1];
            }
        } else {
            count++;
            merged[count] = (int*)malloc(2 * sizeof(int));
            merged[count][0] = intervals[i][0];
            merged[count][1] = intervals[i][1];
            (*returnColumnSizes)[count] = 2;
        }
    }

    *returnSize = count + 1;
    return merged;
}