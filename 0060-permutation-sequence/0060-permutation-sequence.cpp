#include <string>
#include <vector>

using namespace std;

class Solution {
public:
    string getPermutation(int n, int k) {
        vector<int> factorial(n);
        vector<int> numbers;
        
        factorial[0] = 1;
        for (int i = 1; i < n; ++i) {
            factorial[i] = factorial[i - 1] * i;
        }
        
        for (int i = 1; i <= n; ++i) {
            numbers.push_back(i);
        }
        
        k--; 
        string result = "";
        
        for (int i = n - 1; i >= 0; --i) {
            int index = k / factorial[i];
            k %= factorial[i];
            
            result += to_string(numbers[index]);
            numbers.erase(numbers.begin() + index);
        }
        
        return result;
    }
};