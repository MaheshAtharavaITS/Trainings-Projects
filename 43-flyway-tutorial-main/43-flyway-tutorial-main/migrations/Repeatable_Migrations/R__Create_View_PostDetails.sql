CREATE OR REPLACE VIEW post_details AS
SELECT
    p.post_id,
    p.title,
    u.username AS author,
    c.text AS latest_comment
FROM
    posts p
    JOIN users u ON p.user_id = u.user_id
    LEFT JOIN comments c ON p.post_id = c.post_id
ORDER BY p.created_at DESC;
