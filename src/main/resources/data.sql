-- Users
INSERT INTO users (user_id, email, password) VALUES (default, 'user@gmail.com', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, email, password) VALUES (default, 'admin@gmail.com', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');



INSERT INTO profiles (
    first_name, 
    last_name, 
    phone_number, 
    street, 
    city, 
    region, 
    postal_code, 
    country, 
    user_id, 
    is_shipping, 
    is_subscribed
) VALUES (
    'John',              -- first_name
    'Doe',               -- last_name
    '123-456-7890',      -- phone_number
    '123 Elm St',        -- street
    'Springfield',       -- city
    'IL',                -- region
    '62704',             -- postal_code
    'USA',               -- country
    1,                   -- user_id (make sure this exists in your user table)
    true,                -- is_shipping (true or false)
    true                 -- is_subscribed (true or false)
);


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