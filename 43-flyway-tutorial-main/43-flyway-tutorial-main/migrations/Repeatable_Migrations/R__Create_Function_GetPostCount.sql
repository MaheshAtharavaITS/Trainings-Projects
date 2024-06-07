CREATE OR REPLACE FUNCTION get_post_count()
RETURNS INT AS $$
DECLARE
    post_count INT;
BEGIN
    SELECT COUNT(*) INTO post_count FROM posts;
    RETURN post_count;
END;
$$ LANGUAGE plpgsql;
