CREATE OR REPLACE FUNCTION after_insert_comment()
RETURNS TRIGGER AS $$
BEGIN
    -- Your logic here
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_insert_comment
AFTER INSERT ON comments
FOR EACH ROW EXECUTE FUNCTION after_insert_comment();
