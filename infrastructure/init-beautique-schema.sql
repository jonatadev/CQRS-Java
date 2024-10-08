DO $$
BEGIN
    IF NOT EXISTS(
        SELECT
            SCHEMA_NAME
        FROM
            INFORMATION_SCHEMA.SCHEMATA
        WHERE
            SCHEMA_NAME = 'beautique'
    ) THEN
        EXECUTE 'CREATE SCHEMA beautique_schema';
    END IF;
END
$$;
