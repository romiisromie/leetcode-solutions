using System;
using System.Collections.Generic;

public class RangeModule {
    private SortedDictionary<int, int> intervals;

    public RangeModule() {
        intervals = new SortedDictionary<int, int>();
    }
    
    public void AddRange(int left, int right) {
        int start = left;
        int end = right;
        List<int> toRemove = new List<int>();

        foreach (var kvp in intervals) {
            if (kvp.Value < start) continue;
            if (kvp.Key > end) break;
            
            start = Math.Min(start, kvp.Key);
            end = Math.Max(end, kvp.Value);
            toRemove.Add(kvp.Key);
        }

        foreach (int key in toRemove) {
            intervals.Remove(key);
        }

        intervals[start] = end;
    }
    
    public bool QueryRange(int left, int right) {
        foreach (var kvp in intervals) {
            if (kvp.Key <= left && right <= kvp.Value) {
                return true;
            }
            if (kvp.Key > left) {
                break;
            }
        }
        return false;
    }
    
    public void RemoveRange(int left, int right) {
        List<int> toRemove = new List<int>();
        List<KeyValuePair<int, int>> toAdd = new List<KeyValuePair<int, int>>();

        foreach (var kvp in intervals) {
            if (kvp.Value <= left) continue;
            if (kvp.Key >= right) break;

            toRemove.Add(kvp.Key);

            if (kvp.Key < left) {
                toAdd.Add(new KeyValuePair<int, int>(kvp.Key, left));
            }
            if (kvp.Value > right) {
                toAdd.Add(new KeyValuePair<int, int>(right, kvp.Value));
            }
        }

        foreach (int key in toRemove) {
            intervals.Remove(key);
        }

        foreach (var kvp in toAdd) {
            intervals[kvp.Key] = kvp.Value;
        }
    }
}