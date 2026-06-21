class Solution(object):
    def maxIceCream(self, costs, coins):
        max_cost = max(costs)
        count = [0] * (max_cost + 1)
        
        for cost in costs:
            count[cost] += 1
            
        ice_cream_count = 0
        
        for price in range(1, max_cost + 1):
            if count[price] == 0:
                continue
                
            if coins < price:
                break
                
            quantity = min(count[price], coins // price)
            ice_cream_count += quantity
            coins -= quantity * price
            
        return ice_cream_count