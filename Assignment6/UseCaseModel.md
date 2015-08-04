# Use Case Model

**Author**: Alex Milkov

## 1 Use Case Diagram

![Use Case Diagram](https://github.com/gt-ud-softeng/6300Summer15amilkov3/tree/master/Assignment6/Diagrams/UseCase.PNG)

## 2 Use Case Descriptions

Manage Cart
- Requirements: Must allow the customer to add coupons and to add or remove items to and from his/her cart
- Pre-conditions: 
	* Customer must have launched the app
	* The app must launch the QR code scanner
	* The QR scanner must read the customer card's QR code
	* The app must initialize the customer's information based on the QR code read
	* The app must have initialized an empty shopping cart for the customer
	with an empty list of items and an empty list of coupons
-Post-conditions: An item must have been successfully added or removed from the list of items in the customer's cart or a coupon must have been added to the list of coupons in the customer's cart
-Scenarios
	* Customer launches EZShop App and his/her information and an empty cart are initialized
	* One of the following then occurs
	+ Add Item to Cart
		* Customer presses the + button on the app screen
		* The app launches the QR code scanner
		* The QR scanner reads the QR code of the item to be added, providing the item ID and price
		* The item's ID and price are added to list of current items inthe customer's cart
	+ Remove Item From Cart
		* Customer presses the + button on the app screen
		* The app launches the QR code scanner
		* The QR scanner reads the QR code of the item to be removed, providing the item ID
		* The app removes the item corresponding to that item ID from the list of items in the customer's cart
	+ Add Coupon to Cart
		* The app launches the QR code scanner
		* The QR scanner reads the QR code of the coupon, providing the item's ID for which the coupon applies and the corresponding discount
		* The app adds the item's ID and corresponding discount to the list of current coupons in the customer's cart
		*When the user is done shopping, he/she can press the Pay button to complete a transaction (proceed to the Make Payment use case) or exit the system thereby discarding their current shopping section and emptying their cart

Make Payment
- Requirements: Must allow the customer to conclude a transaction when he/she is done shopping (i.e. purchase all of the items in his/her cart via a payment method)
- Pre-conditions:
	* Customer must have launched the app
	* The app must launch the QR code scanner
	* The QR scanner must read the customer card's QR code
	* The app must initialize the customer's information based on the QR code read
	* The app must have initialized an empty shopping cart for the customer with an empty list of items and an empty list of coupons
	* The customer must have at least one item in his/her cart
	* The customer must have a form of payment
- Post-conditions: The payment will be successfully processed and an email will be sent to the customer containing their receipt
- Scenarios:
	* Customer hits the Pay button on the app's home screen
	* A QR code is generated that encodes the customer's information, the items and coupons list, and the total value of the cart
	* The customer displays the above QR code to the cashier who scans it
	* If the customer is selected for a random check, then the cashier will verify that the items list with the items physically in the cart
	* The customer provides a payment method to the cashier
	* When the payment is successfully completed, this will conclude the transaction and the customer will recieve an email containing his/her receipt 
	* Customer exits the app

		

