* Receipt, and Discount are utility classes. Since the requirements did not specifically describe what constitutes each they have been left as simple entities without attributes or operations
* PaymentMethod was designated as an Enumeration because I assumed that they're are a set ways to make a payment: (i.e. cash, Visa, etc.) that were not specified in the requirements
* An arrow or a triangle points away from the entity performing the action and to the entity being performed on
* The cardinalitities associated with the EZShop and QRScanner entity assume per operation. For example the QRScanner reads 1 QRCode at a time and the EZShop app initializes one Shopping Cart per session
