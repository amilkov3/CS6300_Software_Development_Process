# Design Document

**Author**: Alex Milkov

## 1 Design Considerations

### 1.1 Assumptions

* Since no login/logout system is specified in the requirements document, this will not be specificaly addressed in this design document or its use cases
* The app does not maintain cross session state. For example when a customer completes a transaction or cancels a current shopping session without completing a transaction, his/her cart will be reinitialized as a new object upon launching the app next time anyway and a entirely new session (unrelated to the previous one) will begin
* There is no limit to the amount of items and coupons that can be placed in the list of items and list of coupons in the cart
* QR scanning and email capabilities are provided by libraries via utility classes
* The cashier verifies his/her list for the transaction with the items in the customer's physical cart (i.e. no interaction with the EZShop app)

### 1.2 Constraints
	
* The customer must have a smart phone
* The customer and cashier must have basic knowlege about how to scan an item/coupon(coupon in the case of the customer only) using the QR scanner

### 1.3 System Environment

* _Platform_: Android SDK
* _Operating Systems_: Android
* _User Environment_: Phone application

## 2 Architectural Design

### 2.1 Component Diagram

![Component Diagram](https://github.com/gt-ud-softeng/6300Summer15amilkov3/tree/master/Assignment6/Diagrams/Component.PNG)

### 2.2 Deployment Diagram

![Deployment Diagram](https://github.com/gt-ud-softeng/6300Summer15amilkov3/tree/master/Assignment6/Diagrams/Deployment.PNG)

## 3 Low-Level Design

### 3.1 Class Diagram

![UML Class Diagram](https://github.com/gt-ud-softeng/6300Summer15amilkov3/tree/master/Assignment6/Diagrams/UML.PNG)

## 4 User Interface Design

![UI Diagram](https://github.com/gt-ud-softeng/6300Summer15amilkov3/tree/master/Assignment6/Diagrams/UI.PNG)
