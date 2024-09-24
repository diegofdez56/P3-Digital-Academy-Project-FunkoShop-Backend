-- Users
INSERT INTO users (user_id, email, password, role) VALUES (default, 'user@gmail.com', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO', 'USER');
INSERT INTO users (user_id, email, password, role) VALUES (default, 'admin@gmail.com', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO', 'ADMIN');


-- Profiles
INSERT INTO profiles (first_name, last_name, phone_number, street, city, region, postal_code, country, user_id, is_shipping, is_subscribed) VALUES
('Carlos', 'Gómez', '5551234567', '123 Avenida Principal', 'Ciudad de México', 'CDMX', '01000', 'México', 1, false, false),
('Sofía', 'Martínez', '5559876543', '456 Calle Secundaria', 'Guadalajara', 'Jalisco', '44100', 'México', 2, true, true);

-- Categories
INSERT INTO categories (category_id, name) VALUES (default, 'Animación'),
 (default, 'Anime & Manga'),
 (default, 'Marvel'),
 (default, 'DC Comics');

 -- Additional Products
INSERT INTO products (product_id, name, image, description, price, stock, is_available, category_id, discount_id) VALUES
(default, 'Spider-Man Funko Pop', 'spiderman.jpg', 'A collectible Spider-Man figure from Marvel.', 12.99, 50, true, 3, null),
(default, 'Batman Funko Pop', 'batman.jpg', 'A collectible Batman figure from DC Comics.', 11.99, 40, true, 4, null),
(default, 'Goku Super Saiyan Funko Pop', 'goku.jpg', 'Goku in his Super Saiyan form from Dragon Ball Z.', 14.99, 30, true, 2, null),
(default, 'Sailor Moon Funko Pop', 'sailormoon.jpg', 'Sailor Moon figure from the classic anime series.', 13.99, 20, true, 2, null),
(default, 'Mickey Mouse Funko Pop', 'mickey.jpg', 'Classic Mickey Mouse collectible figure.', 9.99, 60, true, 1, null),
(default, 'Iron Man Funko Pop', 'ironman.jpg', 'Iron Man figure with metallic finish.', 15.99, 25, true, 3, null),
(default, 'Wonder Woman Funko Pop', 'wonderwoman.jpg', 'Wonder Woman figure from DC Comics.', 12.49, 35, true, 4, null),
(default, 'Naruto Uzumaki Funko Pop', 'naruto.jpg', 'Naruto figure in his classic outfit.', 13.49, 45, true, 2, null),
(default, 'SpongeBob SquarePants Funko Pop', 'spongebob.jpg', 'SpongeBob figure from the animated series.', 10.99, 50, true, 1, null),
(default, 'Captain America Funko Pop', 'captainamerica.jpg', 'Captain America with his shield.', 12.99, 30, true, 3, null),
(default, 'Thanos Funko Pop', 'thanos.jpg', 'Thanos figure with Infinity Gauntlet.', 16.99, 15, true, 3, null),
(default, 'Joker Funko Pop', 'joker.jpg', 'Joker figure from DC Comics.', 11.49, 40, true, 4, null),
(default, 'Luffy Funko Pop', 'luffy.jpg', 'Monkey D. Luffy from One Piece.', 14.49, 35, true, 2, null),
(default, 'Elsa Funko Pop', 'elsa.jpg', 'Elsa figure from Frozen.', 13.99, 25, true, 1, null),
(default, 'Deadpool Funko Pop', 'deadpool.jpg', 'Deadpool in a classic pose.', 12.99, 50, true, 3, null),
(default, 'Superman Funko Pop', 'superman.jpg', 'Superman figure from DC Comics.', 12.99, 45, true, 4, null),
(default, 'Vegeta Funko Pop', 'vegeta.jpg', 'Vegeta from Dragon Ball Z.', 14.99, 30, true, 2, null),
(default, 'Pikachu Funko Pop', 'pikachu.jpg', 'Pikachu figure from Pokémon.', 11.99, 60, true, 2, null),
(default, 'Harley Quinn Funko Pop', 'harleyquinn.jpg', 'Harley Quinn figure from DC Comics.', 12.49, 35, true, 4, null),
(default, 'Black Panther Funko Pop', 'blackpanther.jpg', 'Black Panther figure from Marvel.', 13.99, 25, true, 3, null);


-- Orders
INSERT INTO orders (order_id, status, total_price, total_items, is_paid, user_id) VALUES
(default, 'PENDING', 19.99, 1, true, 1),
(default, 'PENDING', 89.99, 5, true, 2);

-- Discounts
INSERT INTO discounts (discount_id, percentage, is_active) VALUES
(default, 10, true),
(default, 15, true),
(default, 20, true),
(default, 25, true),
(default, 30, true),
(default, 35, true),
(default, 40, true),
(default, 45, true),
(default, 50, true),
(default, 55, true),
(default, 60, true),
(default, 75, true),
(default, 80, true);

--  OrderItems
INSERT INTO order_items (order_item_id, quantity, order_id, product_id)
VALUES (default, 2, 1, 1);
INSERT INTO order_items (order_item_id, quantity, order_id, product_id)
VALUES (default, 3, 1, 1);

-- Reviews
INSERT INTO reviews (review_id, rating, order_item_id, user_id) VALUES (default, 5, 1, 1);
INSERT INTO reviews (review_id, rating, order_item_id, user_id) VALUES (default, 4, 2, 1);