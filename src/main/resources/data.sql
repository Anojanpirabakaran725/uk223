INSERT INTO public.authority (id, "name")
VALUES ('f35658af-f195-491b-96fc-c58463c4b206'::uuid, 'READ'),
       ('a277f1bf-4203-4ae2-99fa-2daf6c0829d1'::uuid, 'WRITE'),
       ('dac4145d-3c12-4e59-8ba6-5ee79d8882d1'::uuid, 'DELETE'),
       ('83f3b211-7fac-4a96-9b3d-429d129517a7'::uuid, 'ALL_PRIVILEGES');
INSERT INTO public."groups" (id, description, "name")
VALUES ('9062aedd-52d4-4856-b25b-c55eb2632bc3'::uuid, 'Good group', 'Admins'),
       ('21c8d73b-53c7-4c10-abfb-5c5dba67e3f6'::uuid, 'Good group', 'Users');
INSERT INTO public."role" (id, "name")
VALUES ('14806597-21c5-4250-8894-09fc885f6059'::uuid, 'ADMIN'),
       ('7d796913-5d42-4e2f-983d-4aecea33ebd0'::uuid, 'USER'),
       ('642bfff9-a763-4981-b9fa-57a8ce803e3a'::uuid, 'GUEST');
INSERT INTO public.users (id, email, "password", username)
VALUES ('2df8352b-c7c1-4ac4-8d9d-b184f4fa21df'::uuid, 'james.bond@mi6.com', 'bond', 'james'),
       ('181ab981-ec3e-4667-8334-df72efbbf5de'::uuid, 'john.doe@yahoo.com', 'doe', 'john'),
       ('9864530c-69d4-471e-ab8d-96520eabbc20'::uuid, 'user.uasdasdf@gmail.com', 'usa', 'user1');
INSERT INTO public.users_roles (user_id, role_id)
VALUES ('2df8352b-c7c1-4ac4-8d9d-b184f4fa21df'::uuid, '14806597-21c5-4250-8894-09fc885f6059'::uuid),
       ('181ab981-ec3e-4667-8334-df72efbbf5de'::uuid, '642bfff9-a763-4981-b9fa-57a8ce803e3a'::uuid),
       ('9864530c-69d4-471e-ab8d-96520eabbc20'::uuid, '642bfff9-a763-4981-b9fa-57a8ce803e3a'::uuid);
INSERT INTO public.groups_users (groups_id, users_id)
VALUES ('9062aedd-52d4-4856-b25b-c55eb2632bc3'::uuid, '2df8352b-c7c1-4ac4-8d9d-b184f4fa21df'::uuid),
       ('21c8d73b-53c7-4c10-abfb-5c5dba67e3f6'::uuid, '181ab981-ec3e-4667-8334-df72efbbf5de'::uuid),
       ('9062aedd-52d4-4856-b25b-c55eb2632bc3'::uuid, '9864530c-69d4-471e-ab8d-96520eabbc20'::uuid);
INSERT INTO public.role_authorities (role_id, authority_id)
VALUES ('14806597-21c5-4250-8894-09fc885f6059'::uuid, '83f3b211-7fac-4a96-9b3d-429d129517a7'::uuid),
       ('7d796913-5d42-4e2f-983d-4aecea33ebd0'::uuid, 'f35658af-f195-491b-96fc-c58463c4b206'::uuid),
       ('7d796913-5d42-4e2f-983d-4aecea33ebd0'::uuid, 'a277f1bf-4203-4ae2-99fa-2daf6c0829d1'::uuid),
       ('642bfff9-a763-4981-b9fa-57a8ce803e3a'::uuid, 'f35658af-f195-491b-96fc-c58463c4b206'::uuid);