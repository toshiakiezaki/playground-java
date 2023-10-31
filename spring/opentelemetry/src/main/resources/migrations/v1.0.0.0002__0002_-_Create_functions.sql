CREATE OR REPLACE FUNCTION gen_random_ulid() RETURNS uuid
AS $$
    SELECT (lpad(to_hex(floor(extract(epoch FROM clock_timestamp()) * 1000)::bigint), 12, '0') || encode(public.gen_random_bytes(10), 'hex'))::uuid;
$$ LANGUAGE sql;
