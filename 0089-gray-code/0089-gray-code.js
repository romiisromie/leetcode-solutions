/**
 * @param {number} n
 * @return {number[]}
 */
var grayCode = function(n) {
    const result = [];
    const totalNumbers = 1 << n;
    
    for (let i = 0; i < totalNumbers; i++) {
        result.push(i ^ (i >> 1));
    }
    
    return result;
};