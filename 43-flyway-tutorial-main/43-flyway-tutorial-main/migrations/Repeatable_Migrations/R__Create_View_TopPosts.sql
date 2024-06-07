CREATE OR REPLACE VIEW top_posts AS
SELECT
    p.post_id,
    p.title,
    p.rating,
    COUNT(l.like_id) AS like_count
FROM
    posts p
    LEFT JOIN likes l ON p.post_id = l.post_id
GROUP BY
    p.post_id
ORDER BY
    like_count DESC, p.rating DESC;
