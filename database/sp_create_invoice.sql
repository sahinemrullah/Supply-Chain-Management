DELIMITER $$
CREATE PROCEDURE sp_create_invoice(
									IN retailer_id INT,
                                    IN order_id INT,
                                    OUT isSuccess BOOLEAN,
									OUT errorMessage NVARCHAR(255)
								  )
BEGIN

	DECLARE checkConstraintException BOOL DEFAULT 0;
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET checkConstraintException = 1;
    
    SET @supplier_id = (SELECT supplier_id 
						FROM order_m 
						WHERE order_m_id = order_id);
    
    IF @supplier_id IS NOT NULL THEN

		SET @product_ids = (SELECT GROUP_CONCAT(p.product_id)
							FROM product AS p
							JOIN order_m_d_d AS odd ON p.product_id = odd.product_id
							JOIN order_m_d AS od ON odd.order_m_d_id = od.order_m_d_id
							JOIN order_m AS om ON om.order_m_id = od.order_m_id
							LEFT JOIN invoiceitem AS it ON it.order_m_d_d_id = odd.order_m_d_d_id
							WHERE om.order_m_id = order_id AND od.retailer_id = retailer_id AND it.invoice_item_id IS NULL);
							
		IF @product_ids IS NOT NULL THEN
			SET autocommit = OFF;
			
			START TRANSACTION;
			
			INSERT INTO invoice(retailer_id, supplier_id, created_date) VALUES(retailer_id, @supplier_id, CURRENT_TIMESTAMP());
			SET @invoice_id = LAST_INSERT_ID();
			
			SELECT product_id
			FROM product
			WHERE FIND_IN_SET(product_id, @product_ids)
			FOR UPDATE;

			INSERT INTO invoiceitem(order_m_d_d_id, invoice_id) 
			SELECT odd.order_m_d_d_id, @invoice_id
			FROM order_m_d_d AS odd
			JOIN order_m_d as od ON odd.order_m_d_id = od.order_m_d_id
			WHERE FIND_IN_SET(product_id, @product_ids) AND od.order_m_id = order_id;

			UPDATE product AS p
			JOIN order_m_d_d AS odd ON p.product_id = odd.product_id
			JOIN order_m_d AS od ON odd.order_m_d_id = od.order_m_d_id
			JOIN order_m AS om ON om.order_m_id = od.order_m_id
			SET p.stock = (p.stock - odd.quantity)
			WHERE om.order_m_id = order_id AND od.retailer_id = retailer_id;

			IF checkConstraintException THEN
				SET errorMessage = "Ürünün stoğu bulunmamaktadır.";
				SET isSuccess = 0;
				ROLLBACK;
			ELSE
				SET isSuccess = 1;
				COMMIT;
			END IF;

			SET autocommit = ON;
		END IF;
	ELSE
		SET isSuccess = 0;
        SET errorMessage = "İstenilen sipariş bulunamadı.";
    END IF;
    
END $$
DELIMITER ;