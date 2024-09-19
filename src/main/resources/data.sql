-- Categories
INSERT INTO categories (category_id, name) VALUES (default, 'Animación'),
 (default, 'Anime & Manga'),
 (default, 'Marvel'),
 (default, 'DC Comics');

 -- Products
 INSERT INTO products (product_id, name, image, description, price, stock, is_available, category_id, discount_id) 
                VALUES (default, 'asdasd', 'asdasd','asdas', 10, 10, true, 1, null);
