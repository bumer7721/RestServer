INSERT INTO public.users(id, is_active, password, user_name) VALUES (default, true, '123456', 'zuzukin');
INSERT INTO public.users(id, is_active, password, user_name) VALUES (default, false, '321', '4apkis');

INSERT INTO public.users_roles(user_id, role_id) VALUES (1, 1);
INSERT INTO public.users_roles(user_id, role_id) VALUES (1, 2);