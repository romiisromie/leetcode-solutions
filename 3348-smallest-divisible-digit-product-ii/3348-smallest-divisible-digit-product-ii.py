class Solution:
    def stringSequence(self, num: str, t: int) -> str:
        pass  # Placeholder if needed, main logic below
        
    def smallestNumber(self, num: str, t: int) -> str:
        # Step 1: Factorize t into prime factors 2, 3, 5, 7
        c2 = c3 = c5 = c7 = 0
        temp_t = t
        
        for p, count_var in [(2, 'c2'), (3, 'c3'), (5, 'c5'), (7, 'c7')]:
            while temp_t % p == 0:
                temp_t //= p
                if p == 2: c2 += 1
                elif p == 3: c3 += 1
                elif p == 5: c5 += 1
                elif p == 7: c7 += 1
                
        if temp_t > 1:
            return "-1"  # Impossible if t has prime factors > 7

        # Step 2: Precompute DP for min digits needed for (r2, r3)
        # Max r2 <= 60, Max r3 <= 40 for standard integer sizes
        max_r2, max_r3 = c2 + 3, c3 + 3
        dp = [[float('inf')] * (max_r3 + 1) for _ in range(max_r2 + 1)]
        dp[0][0] = 0
        
        # Digits and their factor contributions (r2, r3)
        digit_factors_23 = [
            (2, 1, 0), (3, 0, 1), (4, 2, 0),
            (6, 1, 1), (8, 3, 0), (9, 0, 2)
        ]
        
        for r2 in range(max_r2 + 1):
            for r3 in range(max_r3 + 1):
                if r2 == 0 and r3 == 0:
                    continue
                min_d = float('inf')
                for d, f2, f3 in digit_factors_23:
                    prev_2 = max(0, r2 - f2)
                    prev_3 = max(0, r3 - f3)
                    min_d = min(min_d, 1 + dp[prev_2][prev_3])
                dp[r2][r3] = min_d

        def min_digits(r2, r3, r5, r7):
            r2 = max(0, r2)
            r3 = max(0, r3)
            r5 = max(0, r5)
            r7 = max(0, r7)
            return r5 + r7 + dp[r2][r3]

        def get_factors(d):
            f2 = f3 = f5 = f7 = 0
            while d % 2 == 0: f2 += 1; d //= 2
            while d % 3 == 0: f3 += 1; d //= 3
            if d == 5: f5 = 1
            if d == 7: f7 = 1
            return f2, f3, f5, f7

        # Step 3: Clean num to be zero-free
        num_list = list(num)
        if '0' in num_list:
            first_zero = num_list.index('0')
            num_list[first_zero] = '1'
            for j in range(first_zero + 1, len(num_list)):
                num_list[j] = '1'
        num = "".join(num_list)

        N = len(num)

        # Helper to greedily construct the smallest suffix of given length
        def fill_greedy(rem_len, req2, req3, req5, req7):
            res = []
            for _ in range(rem_len):
                for d in range(1, 10):
                    f2, f3, f5, f7 = get_factors(d)
                    n2, n3, n5, n7 = req2 - f2, req3 - f3, req5 - f5, req7 - f7
                    if min_digits(n2, n3, n5, n7) <= rem_len - len(res) - 1:
                        res.append(str(d))
                        req2, req3, req5, req7 = n2, n3, n5, n7
                        break
            return "".join(res)

        # Precalculate prefix factor sums for num
        prefix_f = [(0, 0, 0, 0)]
        for char in num:
            d = int(char)
            f2, f3, f5, f7 = get_factors(d)
            p2, p3, p5, p7 = prefix_f[-1]
            prefix_f.append((p2 + f2, p3 + f3, p5 + f5, p7 + f7))

        # Check if num itself is valid
        p2, p3, p5, p7 = prefix_f[N]
        if min_digits(c2 - p2, c3 - p3, c5 - p5, c7 - p7) == 0:
            return num

        # Try matching prefix of length i from N-1 down to 0
        for i in range(N - 1, -1, -1):
            p2, p3, p5, p7 = prefix_f[i]
            orig_d = int(num[i])
            
            for d in range(orig_d + 1, 10):
                f2, f3, f5, f7 = get_factors(d)
                req2, req3, req5, req7 = c2 - p2 - f2, c3 - p3 - f3, c5 - p5 - f5, c7 - p7 - f7
                rem_len = N - 1 - i
                
                if min_digits(req2, req3, req5, req7) <= rem_len:
                    prefix_str = num[:i] + str(d)
                    suffix_str = fill_greedy(rem_len, req2, req3, req5, req7)
                    return prefix_str + suffix_str

        # If length N is impossible, expand length to N'
        target_len = max(N + 1, min_digits(c2, c3, c5, c7))
        return fill_greedy(target_len, c2, c3, c5, c7)