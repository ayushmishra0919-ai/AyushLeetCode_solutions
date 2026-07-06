# Write your MySQL query statement below
SELECT MAX(num) AS num
FROM MyNumbers
WHERE NUM in (
    SELECT num
    FROM MyNumbers
    GROUP BY num
    HAVING COUNT(*)=1
)
