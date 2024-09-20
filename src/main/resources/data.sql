-- Users
INSERT INTO users (user_id, email, password) VALUES (default, 'user@gmail.com', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, email, password) VALUES (default, 'admin@gmail.com', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');

-- Roles_Users (Ensure role_id and user_id are correctly mapped)
INSERT INTO user_roles (role_id, user_id) VALUES (1, 1);  -- Assign 'ROLE_USER' to user 'pepe'
INSERT INTO user_roles (role_id, user_id) VALUES (2, 2);  -- Assign 'ROLE_ADMIN' to user 'pepa'

-- Profiles
INSERT INTO profiles (first_name, last_name, phone_number, street, city, region, postal_code, country, user_id, is_shipping, is_subscribed) VALUES
('Carlos', 'Gómez', '5551234567', '123 Avenida Principal', 'Ciudad de México', 'CDMX', '01000', 'México', 1, false, false),
('Sofía', 'Martínez', '5559876543', '456 Calle Secundaria', 'Guadalajara', 'Jalisco', '44100', 'México', 2, true, true);

-- Categories
INSERT INTO categories (category_id, name) VALUES (default, 'Animación'),
 (default, 'Anime & Manga'),
 (default, 'Marvel'),
 (default, 'DC Comics');

 -- Products
 INSERT INTO products (product_id, name, image, description, price, stock, is_available, category_id, discount_id) 
                VALUES (default, 'asdasd', 'asdasd','asdas', 10, 10, true, 1, null);

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
VALUES (1, 2, 1, 1);
INSERT INTO order_items (order_item_id, quantity, order_id, product_id)
VALUES (2, 3, 1, 2);

-- Reviews
INSERT INTO reviews (review_id, rating, order_item_id, user_id) VALUES (1, 5, 1, 1);
INSERT INTO reviews (review_id, rating, order_item_id, user_id) VALUES (2, 4, 2, 1);