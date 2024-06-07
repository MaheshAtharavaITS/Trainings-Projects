CREATE OR REPLACE FUNCTION after_insert_post()
RETURNS TRIGGER AS $$
BEGIN
    -- Your logic here
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_insert_post
AFTER INSERT ON posts
FOR EACH ROW EXECUTE FUNCTION after_insert_post();
