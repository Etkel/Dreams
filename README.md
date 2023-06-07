# Dream-shop

<a src="https://dreamshopu.herokuapp.com">Dream-shop</a> is a full-featured online store with registration, add/delete/update products and categories, user and order management functions. The website displays detailed product descriptions and photos, helping users choose the right product. After placing an order, customers receive a notification via email. The popular products on the homepage change depending on their quantity in orders. 

## :gear: Tech Stack

+ Spring Boot
+ Spring MVC
+ Spring IoC
+ Spring Data
+ Spring Web
+ Spring Security
+ MySQL (PostgreSQL for Deploying)
+ JPA/Hibernate
+ Log4j
+ HTML/CSS/Thymeleaf
+ JS/JQuery
+ Bootstrap 5.3
+ Docker
+ Flyway
+ Maven

## :sparkles: Used APIs

+ GCP Storage  
+ JavaMail

Most of the web application follows a standard approach to development. However, in order to enhance the user-friendly experience, REST API was utilized.

## :joystick: User features

So, the user needs to register first to work with the store. The following domains are supported: 
+ "gmail.com" 
+ "yahoo.com" 
+ "outlook.com" 
+ "hotmail.com" 
+ "aol.com" 
+ "icloud.com" 
+ "mail.com" 
+ "protonmail.com" 
+ "gmx.com" 
+ "ukr.net" 
+ "mail.ua" 
+ "i.ua" 
+ "meta.ua" 
+ "ex.ua"

After that, the user can select items to add to their shopping cart, and they will also have options to view their orders and access personal settings to modify their profile.

<i>All passwords are encrypted using Bcrypt and stored in that form in the database. So, nobody else except for you has access to your password, even if the database is hacked.</i>

## :joystick: Admin features
So, the admin has the following capabilities:

1. Creation, modification, and deletion of categories and products.

2. Viewing both all orders and the products of a specific user.

3. Creation of new users, deletion, and the ability to ban users.

4. All capabilities of a regular user.

5. Ability to send mass emails.

<i>When new orders appear, there is a counter for unprocessed orders (all orders with the status "CREATED"). To process an order it is necessary to change the status to any other. Additionally, all new orders will be highlighted in the Admin :arrow_right: Orders panel.</i>

# Screenshots

![](https://raw.githubusercontent.com/Etkel/Pictures/main/2.PNG)

![](https://raw.githubusercontent.com/Etkel/Pictures/main/1.PNG)

![](https://raw.githubusercontent.com/Etkel/Pictures/main/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA12.PNG)

![](https://raw.githubusercontent.com/Etkel/Pictures/main/324.PNG)

![](https://raw.githubusercontent.com/Etkel/Pictures/main/2134.PNG)

![](https://raw.githubusercontent.com/Etkel/Pictures/main/6.PNG)

![](https://raw.githubusercontent.com/Etkel/Pictures/main/5.PNG)

![](https://raw.githubusercontent.com/Etkel/Pictures/main/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA-5.PNG)

![](https://raw.githubusercontent.com/Etkel/Pictures/main/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA-6.PNG)

![](https://raw.githubusercontent.com/Etkel/Pictures/main/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA-9.PNG)



