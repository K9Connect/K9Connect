use k9connect_db;
# use knineconnectapp_db;

insert into users_details (bio, pfp, phone_number, zipcode)
values ('Power! Unlimited POWER!',
        'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/palpatine-snoke-1555684735.jpg?crop=0.493xw:0.986xh;0.507xw,0&resize=980:*',
        '000-555-0001',
        0),
       ('I used to be able to name every nut that there was. And it used to drive my mother crazy, because she used to say, "Harlan Pepper, if you don''t stop naming nuts," and the joke was that we lived in Pine Nut, and I think that''s what put it in my mind at that point. So she would hear me in the other room, and she''d just start yelling. I''d say, "Peanut. Hazelnut. Cashew nut. Macadamia nut." That was the one that would send her into going crazy. She''d say, "Would you stop naming nuts!" And Hubert used to be able to make the sound, he couldn''t talk, but he''d go "rrrawr rrawr" and that sounded like Macadamia nut. Pine nut, which is a nut, but it''s also the name of a town. Pistachio nut. Red pistachio nut. Natural, all natural white pistachio nut.',
        'https://i.pinimg.com/564x/be/92/a3/be92a383de35327ead61ad181328600e.jpg',
        '856-555-9002',
        27007),
       ('I am originally from Norway, but I moved to Alaska a long time ago. I''m a sled dog breeder, trainer and musher living in Nome.',
        'https://upload.wikimedia.org/wikipedia/en/3/3a/Leonhard_Seppala.jpg',
        '907-555-8003',
        99762),
       ('Hi, my name is Penny. I''m 13 and live in Hollywood, California with my mom and my dog, Bolt, who also works with me on our TV show.',
        'https://i.pinimg.com/736x/fb/ae/2c/fbae2cb4929597a238e4e6cd0e5ac461.jpg',
        '323-555-7004',
        90210);

insert into users (username, password, status, email, is_admin, userdetails_id)
values ('Palpatine', '$2a$10$e0RI5tbWB0LPfKR0Q8TSteoX.DJCTctz7A64zdK8COY3P9Hw/6jHy', 'active', 'emperor@.galaxy.far',
        true, 1),
       ('Harlan', '$2a$10$vLfLZAuCVhKGsF3vUdpesuIt0hAwfHQlyOcYKrrL0tnzEJiR6/Lw.', 'active', 'harlan@pepper.com', false,
        2),
       ('Leonhard', '$2a$10$Bov.rsW8fXWhMiw2SPr6.OLir5iWDe.6mONXmXnKssBOV95UVw7Me', 'active', 'leonhard@nome.alaska',
        false, 3),
       ('Penny', '$2a$10$5tt9bSdIJhEy3kbAV2H2I.VA00E0J8lxinPhFmf9waxpYiypXzTUe', 'active', 'penny@disney.com', false,
        4);

insert into dogs_details (age, bio, has_certs)
values (10, 'Of course I have no love for Beatrice. I really wanted an AT-AT, those are so much fun to play fetch with, but this is being a pitiful dog site, so be it.', false),
       (3, 'Hubert is not only a great show dog, but if the police ever needed a dog for a manhunt, you know, if there was some kind of escaped convict or something, he would be the one to find him.', false),
       (8, 'Togo was a mischievous puppy, often sick and difficult to train, but he eventually gained my confidence and became my lead sled dog.', false),
       (5, 'Bolt is a super dog. He is brave, fiercely loyal and he never gives up.', false);

insert into dogs (breed, gender, name, reputation, owner_id, dog_details_id)
values ('Weimaraner', 'F', 'Beatrice', 0, 1, 1),
       ('Bloodhound', 'M', 'Hubert', 0, 2, 2),
       ('Siberian Husky', 'M', 'Togo', 0, 3, 3),
       ('German Shepherd', 'M', 'Bolt', 0, 4, 4);

insert into photos (url, dog_id)
values ('https://i.stack.imgur.com/MlIm2.jpg', 1),
       ('https://i.stack.imgur.com/IVYwO.jpg', 2),
       ('https://s3.amazonaws.com/cdn-origin-etr.akc.org/wp-content/uploads/2020/03/02162516/Togo-in-Front-of-Team-circa-1925-1.jpg', 3),
       ('https://animalso.com/wp-content/uploads/2016/12/White-German-Shepherd-4-372x277.jpg', 4);

insert into user_reviews (review, stars, reviewed_user_id, reviewer_user_id)
values ('Leonhard''s feeble dog handling skills are no match for the power of the Dark Side. At this momemnt an entire legion of my best troops is on its way to Alaska. [evil cackle]',
        1, 3, 1),
       ('Leonhard is one of the best dog trainers I''ve ever met. His dogs are top notch. You can''t go wrong, especially if you''re looking for a Siberian Husky. I wouldn''t wait if I were you, I''d contact Leonhard faster than a walnut rolling off of a henhouse roof.',
        5, 3, 2),
       ('Leonhard is awesome. His dog Togo is the best. They''ve had amazing adventures together. He''s a very nice man. Highly recommended.',
        5, 3, 4),
       ('This guy may be an efficient administrator, but he seems arrogant and a little power crazy. I don''t know if I''d trust him any more than I would a long-tailed weasel in a chicken coup.',
        2, 1, 2),
       ('Penny is a sweet girl. I like to think of her as my own daughter, if I had one. She''s smart, honest, funny and easy to talk to.',
        5, 4, 2);