WITH FilteredStadium AS (
    -- 1. Grab only the rows with >= 100 people
    SELECT id, visit_date, people
    FROM Stadium
    WHERE people >= 100
),
GroupedIslands AS (
    SELECT 
        id, 
        visit_date, 
        people,
        id - ROW_NUMBER() OVER (ORDER BY id) AS island_id
    FROM FilteredStadium
),
IslandCounts AS (
    SELECT 
        id, 
        visit_date, 
        people,
        COUNT(*) OVER (PARTITION BY island_id) AS group_size
    FROM GroupedIslands
)
SELECT id, visit_date, people
FROM IslandCounts
WHERE group_size >= 3
ORDER BY visit_date ASC;