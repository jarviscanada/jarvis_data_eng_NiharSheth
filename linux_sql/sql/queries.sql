-- Group hosts by hardware info
SELECT
    cpu_number, id,
    total_mem
FROM
    host_info
ORDER BY
    total_mem DESC;

-- Free memory in 5 minute intervals
SELECT
    host_id,
    date_trunc('hour', timestamp_usage) + date_part('minute', timestamp_usage):: int / 5 * interval '5 min',
    memory_free
FROM host_usage;

-- Get today's host_usage entries
SELECT * FROM host_usage WHERE date_trunc('day', timestamp_usage) = CURRENT_DATE;