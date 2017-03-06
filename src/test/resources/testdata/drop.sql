BEGIN;

DELETE FROM public.users_roles;
DELETE FROM public.users;
DELETE FROM public.roles;

ALTER SEQUENCE roles_id_seq RESTART WITH 1;
ALTER SEQUENCE users_id_seq RESTART WITH 1;

COMMIT;