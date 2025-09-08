CREATE TABLE postal_code (
    id           UUID             NOT NULL DEFAULT gen_random_ulid(),
    code         CHAR(8)          NOT NULL,
    street       VARCHAR(60)      NOT NULL,
    neighborhood VARCHAR(40)      NOT NULL, 
    city         VARCHAR(50)      NOT NULL,
    state        CHAR(2)          NOT NULL,
    range_side   POSTAL_CODE_SIDE,
    range_unit   POSTAL_CODE_UNIT,
    range_start  INTEGER,
    range_end    INTEGER,
    CONSTRAINT pk_postal_code     PRIMARY KEY (id),
    CONSTRAINT uk_postal_code_001 UNIQUE (code)
);
