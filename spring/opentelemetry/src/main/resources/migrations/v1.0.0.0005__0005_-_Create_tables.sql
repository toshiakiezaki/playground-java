CREATE TABLE postal_code (
    id           UUID             NOT NULL DEFAULT gen_random_ulid(),
    code         CHAR(8)          NOT NULL,
    street       VARCHAR(60)      NOT NULL,
    neighborhood VARCHAR(40)      NOT NULL, 
    city         VARCHAR(50)      NOT NULL,
    state        CHAR(2)          NOT NULL,
    side         POSTAL_CODE_SIDE,
    start_range  INTEGER,
    end_range    INTEGER,
    CONSTRAINT pk_postal_code     PRIMARY KEY (id),
    CONSTRAINT uk_postal_code_001 UNIQUE (code)
);
