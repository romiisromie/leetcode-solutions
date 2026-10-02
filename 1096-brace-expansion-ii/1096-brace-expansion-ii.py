import itertools

class Solution:
    def braceExpansionII(self, expression: str) -> list[str]:
        def parse(expr: str) -> set[str]:
            if not expr:
                return {""}
            
            # Find top-level commas to split union groups
            groups = []
            curr_start = 0
            brace_count = 0
            
            for i, char in enumerate(expr):
                if char == '{':
                    brace_count += 1
                elif char == '}':
                    brace_count -= 1
                elif char == ',' and brace_count == 0:
                    groups.append(expr[curr_start:i])
                    curr_start = i + 1
            
            groups.append(expr[curr_start:])
            
            # If there are top-level commas, return the union of all groups
            if len(groups) > 1:
                res = set()
                for group in groups:
                    res.update(parse(group))
                return res

            # If no top-level commas, split into concatenated parts
            # e.g., "a{b,c}d" -> parts: ["a", "{b,c}", "d"]
            parts = []
            i = 0
            n = len(expr)
            
            while i < n:
                if expr[i] == '{':
                    start = i
                    depth = 0
                    while i < n:
                        if expr[i] == '{':
                            depth += 1
                        elif expr[i] == '}':
                            depth -= 1
                            if depth == 0:
                                break
                        i += 1
                    # Strip outer braces for recursive evaluation
                    parts.append(parse(expr[start + 1 : i]))
                    i += 1
                else:
                    start = i
                    while i < n and expr[i].isalpha():
                        i += 1
                    parts.append({expr[start:i]})

            # Compute Cartesian product of all concatenated parts
            product_sets = parts[0]
            for part in parts[1:]:
                product_sets = {w1 + w2 for w1 in product_sets for w2 in part}

            return product_sets

        return sorted(list(parse(expression)))