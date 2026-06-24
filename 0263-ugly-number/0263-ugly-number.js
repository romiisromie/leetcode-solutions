var isUgly = function(n) {
    if (n <= 0) return false;
    for (let p of [2, 3, 5]) {
        while (n % p === 0) {
            n /= p;
        }
    }
    return n === 1;
};