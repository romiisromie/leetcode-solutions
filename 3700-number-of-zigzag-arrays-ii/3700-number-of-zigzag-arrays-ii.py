class Solution(object):
    def zigZagArrays(self, n, l, r):
        """
        :type n: int
        :type l: int
        :type r: int
        :rtype: int
        """
        MOD = 10**9 + 7
        M = r - l + 1
        size = 2 * M
        
        T = [[0] * size for _ in range(size)]
        for j in range(M):
            for i in range(j):
                T[M + i][j] = 1
            for i in range(j + 1, M):
                T[i][M + j] = 1

        def matrix_multiply(A, B):
            C = [[0] * size for _ in range(size)]
            for i in range(size):
                for k in range(size):
                    if A[i][k] == 0:
                        continue
                    for j in range(size):
                        C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD
            return C

        def matrix_power(mat, power):
            result = [[0] * size for _ in range(size)]
            for i in range(size):
                result[i][i] = 1
            base = mat
            while power > 0:
                if power % 2 == 1:
                    result = matrix_multiply(result, base)
                base = matrix_multiply(base, base)
                power //= 2
            return result

        V = [0] * size
        for i in range(M):
            V[i] = i
            V[M + i] = M - 1 - i
            
        T_pow = matrix_power(T, n - 2)
        
        final_ans = 0
        for i in range(size):
            row_sum = 0
            for j in range(size):
                row_sum = (row_sum + T_pow[i][j] * V[j]) % MOD
            final_ans = (final_ans + row_sum) % MOD
            
        return final_ans