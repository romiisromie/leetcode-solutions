WITH CriteriaCheck AS (
    SELECT 
        tiv_2016,
        COUNT(*) OVER(PARTITION BY tiv_2015) as tiv_2015_count,
        COUNT(*) OVER(PARTITION BY lat, lon) as location_count
    FROM Insurance
)
SELECT 
    ROUND(SUM(tiv_2016), 2) AS tiv_2016
FROM CriteriaCheck
WHERE tiv_2015_count > 1 AND location_count = 1;
