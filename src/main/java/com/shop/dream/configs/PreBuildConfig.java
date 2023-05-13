package com.shop.dream.configs;


import com.shop.dream.models.Category;
import com.shop.dream.models.Persona;
import com.shop.dream.models.Product;
import com.shop.dream.models.enums.Role;
import com.shop.dream.repositories.CategoryRepository;
import com.shop.dream.repositories.PersonaRepository;
import com.shop.dream.repositories.ProductRepository;
import jakarta.persistence.Cacheable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;


@Configuration
@Cacheable
public class PreBuildConfig {

    @Bean
    public CommandLineRunner onStartUsers(PersonaRepository personaRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Persona admin = Persona.builder()
                    .name("admin")
                    .lastName("admin")
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("MagicPower321"))
                    .role(Role.ADMIN)
                    .build();
            personaRepository.save(admin);

            Persona user = Persona.builder()
                    .name("user")
                    .lastName("user")
                    .email("user@gmail.com")
                    .password(passwordEncoder.encode("1234"))
                    .role(Role.USER)
                    .build();
            personaRepository.save(user);

            Persona user1 = Persona.builder()
                    .name("Ollan")
                    .lastName("Jonsan")
                    .email("ollan@gmail.com")
                    .password(passwordEncoder.encode("1234"))
                    .role(Role.USER)
                    .build();
            personaRepository.save(user1);

        };
    }


    @Bean
    public CommandLineRunner onStartCategory(CategoryRepository categoryRepository, ProductRepository productRepository) {
        return args -> {
            Category category1 = Category.builder()
                    .nameCategory("Pins")
                    .imageNameCategory("https://storage.googleapis.com/dream-shop-380613/8f83d1443145c05d14ca4ae86e23d08c.jpg")
                    .description("Express yourself with our unique collection of pins! Our pins are perfect for " +
                            "adding a touch of personality to your jackets, backpacks, hats, and more. Whether" +
                            " you're a fan of pop culture, music, animals, or just looking for a fun and quirky" +
                            " accessory, we've got you covered. Our pins are made with high-quality materials, " +
                            "ensuring that they will stay in great condition for years to come. With a variety of " +
                            "designs to choose from, you can mix and match to create your own unique style.")
                    .build();
            categoryRepository.save(category1);

            Category category2 = Category.builder()
                    .nameCategory("Keychains")
                    .imageNameCategory("https://storage.googleapis.com/dream-shop-380613/ded3a22b6b5df99ab6d62a2eb3ed4bbe.jpg")
                    .description("Keep your keys organized and add a touch of style with our " +
                            "collection of keychains! Our keychains are the perfect way to keep " +
                            "track of your keys while expressing your unique personality. Whether you're" +
                            " a fan of cute and colorful designs or prefer sleek and sophisticated styles," +
                            " we've got something for everyone. With a variety of shapes, sizes," +
                            " and materials to choose from, you're sure to find the perfect keychain to fit your " +
                            "needs.")
                    .build();
            categoryRepository.save(category2);

            Category category3 = Category.builder()
                    .nameCategory("Replica Paintings")
                    .imageNameCategory("https://storage.googleapis.com/dream-shop-380613/image094-40.jpg")
                    .description("Bring the beauty of famous artworks into your home with our collection of replica " +
                            "paintings. From classic" +
                            " works by the masters to modern favorites, we've got a wide selection to choose from. Our " +
                            "replicas are perfect for art lovers who want to enjoy the beauty of famous works without" +
                            " breaking" +
                            " the bank. With a range of sizes and styles available, you can choose the perfect painting " +
                            "to complement your home décor. Shop now and add some artistic flair to your space with our " +
                            "replica paintings!")
                    .build();
            categoryRepository.save(category3);

            Category category4 = Category.builder()
                    .nameCategory("Collectible Toys")
                    .imageNameCategory("https://storage.googleapis.com/dream-shop-380613/513OcGY%2BlgL._AC_SL1000_.jpg")
                    .description("Add some fun to your collection with our selection of collectible toys!" +
                            " From classic favorites to the latest trends, we've got a variety of toys to satisfy" +
                            " every collector. Our collectible toys are made with high-quality materials and " +
                            "attention to detail, making them perfect for display or play." +
                            "With a range of sizes and styles to choose from, you can build your collection and" +
                            " show off your fandom in style.")
                    .build();
            categoryRepository.save(category4);


//Products------------------------------------------------------------------------
            Product product1 = Product.builder()
                    .name("Funko Pop! Star Wars: The Mandalorian (Din Djarrin) Holding The Child (Grogu) Vinyl Bobblehead ")
                    .price(new BigDecimal("25.25"))
                    .img("https://storage.googleapis.com/dream-shop-380613/5176rALHhgS._AC_SL1300_.jpg")
                    .description(
                            "Vinyl. " +
                            "Imported. " +
                            "    Celebrate the most stellar fandom of them all with the Star Wars: The Mandalorian Pop! " +
                            "The Mandalorian (Din Djarrin) with The Child (Grogu). " +
                            "    Vinyl bobblehead is approximately 4.75-inches tall. " +
                            "    Item comes in a window display box, perfect for any Star Wars fan. " +
                            "    Collect them all!")
                    .categories(List.of(category4))
                    .build();
            productRepository.save(product1);

            Product product2 = Product.builder()
                    .name("Hot Toys Star Wars The Mandalorian - Television Masterpiece Series Shoretrooper 1/6" +
                            " Scale 12\" Collectible Figure ")
                    .price(new BigDecimal("145.17"))
                    .img("https://storage.googleapis.com/dream-shop-380613/51G5WPD8C4L._AC_SL1000_.jpg")
                    .description(
                            "    Size: Approximately 11.8 inches (30 " +
                            "    Movable Points: 30 " +
                            "    Accessories (Weapons) Blaster Rifle Accessories (Accessories) Replacement" +
                                    " Hand Parts (4) Special Base" +
                            "    Country of Origin: China ")
                    .categories(List.of(category4))
                    .build();
            productRepository.save(product2);

            Product product3 = Product.builder()
                    .name("Funko Pop Star Wars: The Mandalorian - The Child with Frog")
                    .price(new BigDecimal("245.01"))
                    .img("https://storage.googleapis.com/dream-shop-380613/61vcY2qOFGS._AC_SL1500_.jpg")
                    .description(
                            "    Vinyl. " +
                            "    Imported. " +
                            "    From Star Wars: The Mandalorian is The Child with Frog as a " +
                                    "stylized Pop! vinyl bobblehead from Funko! " +
                            "    Perfect Star Wars gifts for men & women. Complete " +
                                    "your Bady Yoda Star Wars The Mandalorian collection. " +
                            "    Designed with exquisite attention to detail," +
                                    " this Mandalorian action figure stands out among other Star Wars action figures. " +
                            "    Stylized collectible stands approximately 3.25 inches tall," +
                                    " perfect for any Mandalorian or Star Wars fan! " +
                            "    The Mandalorian toys are a great way to celebrate your love of Star Wars. ")
                    .categories(List.of(category4))
                    .build();
            productRepository.save(product3);

            Product product4 = Product.builder()
                    .name("Squishmallow Official Kellytoy Disney Collectible Characters Squishy Soft Stuffed Animal (Chewbacca, 5 Inch)")
                    .price(new BigDecimal("15.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/61LbuN2mL2L._AC_SL1000_.jpg")
                    .description(
                            "Brand: \tSquishmallow. " +
                            "Toy figure type: \tStuffed Toy. " +
                            "Color: \tChewbacca. " +
                            "Material: \tPolyester. " +
                            "Assembly Required: \tNo. " +
                            "Manufacturer Minimum Age (MONTHS): \t220. " +
                            "Model Name: \tCT6619. " +
                            "Outer Material: \tPolyester. " +
                            "Product Dimensions: \t7\"L x 6\"W x 8\"H. " +
                            "Batteries Required? \tNo." +
                            "    PERFECTLY SIZED - These plushies are warm cuddly fun and the right size for " +
                                    "taking with you wherever you go. " +
                            "    SUPER SOFT - Made from incredibly cozy- polyester fiber, these have a marshmallow " +
                                    "texture that's pillowy soft. " +
                            "    COLLECTIBLE CUTENESS - Squad up and collect the entire line of cute stuffed animals.")
                    .categories(List.of(category4))
                    .build();
            productRepository.save(product4);


            Product product5 = Product.builder()
                    .name("Regular Show Rigby Plush Doll 9” New Soft Brown Rare Gift Collectible ")
                    .price(new BigDecimal("1050.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/41el3Atpu3L._AC_.jpg")
                    .description("Brand: \tGeneric. " +
                            "Toy figure type: \tAction Figure. " +
                            "Color: \tMulti. " +
                            "Cartoon Character: \tRigby. " +
                            "Assembly Required: \tNo. " +
                            "Manufacturer Minimum Age (MONTHS): \t12. " +
                            "Model Name: \t1. " +
                            "Product Dimensions: \t1\"L x 1\"W x 1\"H. ")
                    .categories(List.of(category4))
                    .build();
            productRepository.save(product5);

            Product product6 = Product.builder()
                    .name("Rubie's mens Nightmare on Elm Street Supreme Edition Freddy Replica Metal Glove Costume Accessory")
                    .price(new BigDecimal("15.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/81Vkgushz%2BL._AC_SL1500_.jpg")
                    .description(
                            "    100% Polyester. " +
                            "    Freddy Krueger leather-like fingerless glove with cutout in palm designed " +
                                    "for teens and adults. " +
                            "    Metal blades and fingers (blades rounded for safety); movable knuckle. " +
                            "    Officially licensed Nightmare on Elm Street accessory, look for " +
                                    "trademark on label and packaging to help assure you've received " +
                                    "an authentic safety-tested item. " +
                            "    Adds an air of authenticity to your Freddy Kruger costume, look " +
                                    "for the entire line of licensed A Nightmare on Elm Street" +
                                    " costumes and accessories." +
                            "    Packaged in window box for easy gift giving. ")
                    .categories(List.of(category4))
                    .build();
            productRepository.save(product6);

            Product product7 = Product.builder()
                    .name("LEGO Architecture Statue of Liberty 21042 Model Building Set, Collectable New York Souvenir, Gift Idea for Her or Him, Home Décor, Creative Activity ")
                    .price(new BigDecimal("534.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/817QaKEw4lL._AC_SL1500_.jpg")
                    .description(
                            "    Features a Lady Liberty statue with a flowing robe and an intricately detailed " +
                                    "pedestal with shield & brick detailing and columned balconies. " +
                            "    Also includes broken shackles, 7-ray crown, iconic tablet and an upraised " +
                                    "arm bearing a golden torch, plus a decorative nameplate. " +
                            "    Finished with an authentic sand-green and beige color scheme, this model " +
                                    "delivers a highly satisfying building experience." +
                            "    An icon of freedom, the statue was presented as a gift to the people " +
                                    "of the United States from the people of France in 1886." +
                            "    This impressive LEGO interpretation faithfully reproduces the monument’s " +
                                    "harmonious blend of sculpture and architecture." +
                            "    LEGO Architecture model building kits make a magnificent birthday or " +
                                    "Christmas gift for teens, adults or any New York fan."
                    )
                    .categories(List.of(category4))
                    .build();
            productRepository.save(product7);

            Product product8 = Product.builder()
                    .name("Storm Collectibles - Mortal Kombat 11 - Scorpion Klassic 1/6 Scale Action Figure ")
                    .price(new BigDecimal("234.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/71xkmaw%2B3DL._AC_SL1500_.jpg")
                    .description(
                            "    Introducing Storm Collectibles’ very first 1/6 scale figure from Mortal Kombat! " +
                            "    Fully Poseable, includes infamous spear, multiple hands, weapons, and hell fire effect! " +
                            "    This product will make any \"Kollection\" stand out. " +
                            "    Only product with affixed official Bluefin label has been thoroughly tested" +
                                    " for safety and meets all North American consumer product safety regulations " +
                                    "and entitles the purchaser to product support assistance.")
                    .categories(List.of(category4))
                    .build();
            productRepository.save(product8);

            Product product9 = Product.builder()
                    .name("DIAMOND SELECT TOYS Star Wars: The Mandalorian: The Mandalorian with The Child Premier Collection Statue,Multicolor 10 inches")
                    .price(new BigDecimal("349.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/51C2q1w7GmL._AC_SL1000_.jpg")
                    .description("Theme: \tMovie. " +
                            "Brand: \tDIAMOND SELECT TOYS. " +
                            "Color: \tMulticolor. " +
                            "Material: \tResin. " +
                            "Product Dimensions: \t4\"L x 3\"W x 10\"H. " +
                            "    Measures approximately 10\" tall. " +
                            "    Detailed sculpting & paint applications. " +
                            "    Limited to only 1000 pieces. " +
                            "    Certificate of authenticity. " +
                            "    Full-color box.")
                    .categories(List.of(category4))
                    .build();
            productRepository.save(product9);

            Product product10 = Product.builder()
                    .name("STAR WARS The Black Series Boba Fett (Prototype Armor) Toy 6-Inch-Scale The Empire Strikes Back Collectible Figure, Ages 4 and Up (Amazon Exclusive) F5867 ")
                    .price(new BigDecimal("349.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/71wnYoa2dHL._AC_SL1500_.jpg")
                    .description(
                            "    BOBA FETT (PROTOTYPE ARMOR): Before he put on the Mandalorian armor, " +
                                    "this notorious bounty hunter was initially envisioned as a “super " +
                                    "trooper” in all-white. This figure captures the beginning of a " +
                                    "character that’s become a legend Boba Fett. " +
                            "    STAR WARS: THE EMPIRE STRIKES BACK: Fans and collectors can " +
                                    "imagine scenes from the Star Wars Galaxy with this premium " +
                                    "Boba Fett (Prototype Armor) toy, inspired by the Star Wars: " +
                                    "The Empire Strikes Back." +
                            "    ENTERTAINMENT-BASED CHARACTER-INSPIRED ACCESSORIES: This Star " +
                                    "Wars The Black Series action figure comes with 3 entertainment-inspired" +
                                    " accessories that make a great addition to any Star Wars collection. " +
                            "    PREMIUM ARTICULATION AND DETAILING: Star Wars fans and collectors can display " +
                                    "this fully articulated figure featuring poseable head, arms," +
                                    " and legs, as well as premium deco, in their collection.")
                    .categories(List.of(category4))
                    .build();
            productRepository.save(product10);

            Product product11 = Product.builder()
                    .name("Hot Toys John Wick: Chapter 2 MMS504 Marvel 1/6th Scale Movie Masterpiece Collectible Figure")
                    .price(new BigDecimal("609.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/51yIgK6UsCL._AC_SL1000_.jpg")
                    .description("Brand: \tHot Toys. " +
                            "Theme: \tMovie. " +
                            "Cartoon: Character \tMarvel. " +
                            "Assembly: Required \tNo. " +
                            "Style: \tClassic,Combat ." +
                            "Product Dimensions: \t15\"L x 10\"W x 5\"H. " +
                            "Batteries Required? \tNo. " +
                            "Number of Pieces: \t1. " +
                            "Collection Name: \tAction Figure. " +
                            "About this item:" +
                            " The man.")
                    .categories(List.of(category4))
                    .build();
            productRepository.save(product11);

            Product product12 = Product.builder()
                    .name("Hot Toys Hellboy 1/6 Sixth Scale Movie Masterpiece Series MMS527 - Hell Boy (2021) Collectible Action Figure ")
                    .price(new BigDecimal("450.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/61robpotu2L._AC_SL1000_.jpg")
                    .description("Brand: \tHot Toys. " +
                            "Theme: \tSuperhero. " +
                            "Toy figure type: \tAction Figure. " +
                            "Cartoon Character: \tMarvel. " +
                            "Assembly Required: \tNo. " +
                            "Batteries Required? \tNo. " +
                            "Number of Pieces: \t1. " +
                            "Collection Name: \tAction Figure.")
                    .categories(List.of(category4))
                    .build();
            productRepository.save(product12);


//-----------------------------------------------------------------------

            Product product13 = Product.builder()
                    .name("Totoro Pin Badge")
                    .price(new BigDecimal("2.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/8f83d1443145c05d14ca4ae86e23d08c.jpg")
                    .description("This pin badge features an image of Totoro - a cute and popular character from" +
                            " the Japanese animated film. The badge is made from high-quality materials and has " +
                            "a sturdy metal base, making it durable and long-lasting. It is perfect for collecting" +
                            " or for adding a touch of decoration to clothes or bags. Material: Metal. " +
                            "Color: Assorted colors. " +
                            "Type: Pin.")
                    .categories(List.of(category1))
                    .build();
            productRepository.save(product13);

            Product product14 = Product.builder()
                    .name("Totoro Rabbit Pin Badge")
                    .price(new BigDecimal("2.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/b6d4aa984f27aa12d20a81de71111d67.jpg")
                    .description("This pin badge features an image of Totoro Rabbit - " +
                            "a charming and friendly character from the Japanese animated film. " +
                            "The badge is made from high-quality materials and has a sturdy metal base," +
                            " making it durable and long-lasting. It is perfect for collecting or for adding" +
                            " a touch of originality to clothes or bags. Material: Metal. " +
                            "Color: Assorted colors. " +
                            "Type: Pin.")
                    .categories(List.of(category1))
                    .build();
            productRepository.save(product14);

            Product product15 = Product.builder()
                    .name("Unicorn Pin Badge")
                    .price(new BigDecimal("2.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/a7eb1e23f375e82f24b2b2b4952c114c.jpg")
                    .description("This pin badge features a beautifully designed unicorn image. The unicorn " +
                            "is a legendary creature known for its grace and purity, and this pin captures that " +
                            "spirit perfectly. The badge is made from high-quality metal materials and has a" +
                            " sleek and durable design. It is perfect for adding a touch of magic to clothing" +
                            " or bags.Specifications: " +
                            "Length: 25mm. " +
                            "Width (Thickness): 19mm. " +
                            "Material: Metal. " +
                            "Type: Pin.")
                    .categories(List.of(category1))
                    .build();
            productRepository.save(product15);


            Product product16 = Product.builder()
                    .name("Whale Pin Badge")
                    .price(new BigDecimal("2.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/b7bba4eb9667969cde4eeb442df65889.jpg")
                    .description("This elegant pin badge features a charming image of a whale." +
                            " The whale is a symbol of strength and wisdom, and this pin captures " +
                            "that essence beautifully. The badge is made from high-quality metal materials " +
                            "and has a luxurious golden color. It has a sleek and durable design, making it " +
                            "perfect for adding a touch of sophistication to any outfit.Specifications: " +
                            "Color: Golden. " +
                            "Material: Metal. " +
                            "Length: 21mm. " +
                            "Width (Thickness): 38mm. " +
                            "Country of Origin: China. " +
                            "Type: Pin.")
                    .categories(List.of(category1))
                    .build();
            productRepository.save(product16);


            Product product17 = Product.builder()
                    .name("Salvador Dali Cat Pin Badge")
                    .price(new BigDecimal("2.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/74da5fde3e8726d525db7f4638ef0bf7.jpg")
                    .description("This pin badge features a unique and playful design of a cat inspired by " +
                            "the artwork of the famous artist Salvador Dali. The intricate details of the cat's" +
                            " features are beautifully captured in this pin, making it a great addition to any art" +
                            " lover's collection. The badge is made from high-quality metal materials and has a sleek " +
                            "and durable design, ensuring that it will last for a long time.Specifications: " +
                            "Country of Origin: China. " +
                            "Width (Thickness): 38.5mm. " +
                            "Length: 29mm. " +
                            "Material: Metal. " +
                            "Type: Pin.")
                    .categories(List.of(category1))
                    .build();
            productRepository.save(product17);


            Product product18 = Product.builder()
                    .name("Pin badge Geometric Cat")
                    .price(new BigDecimal("2.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/c92a5a175f6778525f48d34447504278.jpg")
                    .description("This Geometric Cat pin is a stylish accessory for any cat lover." +
                            " The pin is made of metal and features a unique geometric design that " +
                            "makes it stand out. The pin is manufactured in China and has a width of 14mm " +
                            "and a length of 25mm, making it a great size for attaching to clothing, bags, or" +
                            " hats. Add this geometric cat pin to your collection or give it as a gift to someone" +
                            " special who loves cats. Specifications: " +
                            "Country of Origin: China. " +
                            "Width (Thickness): 14mm. " +
                            "Length: 25mm. " +
                            "Material: Metal. " +
                            "Type: Pin.")
                    .categories(List.of(category1))
                    .build();
            productRepository.save(product18);


            Product product21 = Product.builder()
                    .name("Arrow Cursor Pin Badge")
                    .price(new BigDecimal("2.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/1ce959d5acb597eb68d042799eedcd4a.jpg")
                    .description("This pin badge features the iconic arrow cursor symbol that we all know" +
                            " and love from our computer screens. It's a fun and playful accessory for " +
                            "anyone who spends a lot of time on their computer or just appreciates modern " +
                            "technology. The badge is made from high-quality metal materials, ensuring its " +
                            "durability and long-lasting shine. Specifications: " +
                            "Country of Origin: China. " +
                            "Width (Thickness): 14mm. " +
                            "Length: 22mm. " +
                            "Material: Metal.")
                    .categories(List.of(category1))
                    .build();
            productRepository.save(product21);

            Product product22 = Product.builder()
                    .name("Pepe Le Pew Frog Pin Badge")
                    .price(new BigDecimal("2.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/949e52b679ecd5ed07ba0b9b0ff20e7e.jpg")
                    .description("This pin badge features a charming and whimsical design " +
                            "of a frog inspired by the cartoon character Pepe Le Pew. The frog is" +
                            " depicted with intricate details and in a dynamic pose, making it a fun a" +
                            "nd playful accessory for any fan of the classic Looney Tunes cartoons. The " +
                            "badge is made from high-quality metal materials and has a durable and sleek " +
                            "design, ensuring that it will last for a long time. Specifications: " +
                            "Material: Metal. " +
                            "Color: Black, Green. " +
                            "Length: 28mm. " +
                            "Width (Thickness): 31mm.")
                    .categories(List.of(category1))
                    .build();
            productRepository.save(product22);

            Product product23 = Product.builder()
                    .name("Witch Hat Pin Badge")
                    .price(new BigDecimal("2.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/cdfa1a3fbc78c4ca094f9c36860bd67a.jpg")
                    .description("This pin badge features a unique and stylish design of a witch's hat, perfect " +
                            "for Halloween or any witch-themed occasion. The badge is made from high-quality metal" +
                            " materials, with a beautiful combination of gold, gray, and black colors. Its small and " +
                            "lightweight design makes it easy to wear on clothes or accessories, adding a touch" +
                            " of witchy flair to any outfit. " +
                            "Specifications: " +
                            "Material: Metal. " +
                            "Color: Gold, Gray, Black. " +
                            "Length: 19mm. " +
                            "Width (Thickness): 17mm. " +
                            "Type: Pin. " +
                            "Country of Origin: N/A (not specified).")
                    .categories(List.of(category1))
                    .build();
            productRepository.save(product23);


            Product product24 = Product.builder()
                    .name("Dog Pin Badge")
                    .price(new BigDecimal("2.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/be6e11f0c38b9537c7b65dc19a3cabac.jpg")
                    .description("This adorable pin badge features a cute design of a dog with intricate details on " +
                            "its fur and facial features. The badge is made from high-quality metal materials and" +
                            " is available in three different color variations - gold, orange, and white. It is a " +
                            "perfect accessory for dog lovers and can be worn on clothing, bags, or hats. " +
                            "Specifications: " +
                            "Material: Metal." +
                            "Color: Gold, Orange, White. " +
                            "Length: 21mm. " +
                            "Width (Thickness): 20mm. " +
                            "Type: Pin. " +
                            "Country of Origin: N/A (not specified).")
                    .categories(List.of(category1))
                    .build();
            productRepository.save(product24);

            Product product25 = Product.builder()
                    .name("Poop Emoji Pin Badge")
                    .price(new BigDecimal("2.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/83b9cce27c1be7a77bd9fba71dac0955.jpg")
                    .description("This pin badge features a humorous design of a poop emoji. It's a fun " +
                            "and playful accessory that can be added to backpacks, jackets, and more. The " +
                            "badge is made from high-quality metal materials and has a durable design that " +
                            "will last for a long time. " +
                            "Specifications: " +
                            "Material: Metal. " +
                            "Length: 23mm. " +
                            "Width (Thickness): 22.5mm. " +
                            "Color: Brown.")
                    .categories(List.of(category1))
                    .build();
            productRepository.save(product25);

            Product product26 = Product.builder()
                    .name("Geometric Cat Pin Badge")
                    .price(new BigDecimal("2.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/f14daf09b15044af2c068468d6e8a20c.jpg")
                    .description("This pin badge features a unique geometric design of a cat, perfect" +
                            " for anyone who loves cats or minimalist art. The simple yet striking design" +
                            " is sure to catch people's attention, and the pin is made from high-quality metal" +
                            " materials to ensure its durability. " +
                            "Specifications: " +
                            "Country of Origin: China. " +
                            "Width (Thickness): 12mm. " +
                            "Length: 20mm. " +
                            "Material: Metal. " +
                            "Type: Pin.")
                    .categories(List.of(category1))
                    .build();
            productRepository.save(product26);

            Product product27 = Product.builder()
                    .name("Geometric Penguin Pin Badge")
                    .price(new BigDecimal("2.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/abd7e6763617dfe7016a2ddc47858d72.jpg")
                    .description("This pin badge features a stylish and modern design of a geometric penguin." +
                            " The clean lines and shapes used in the design create a unique and eye-catching " +
                            "appearance that is sure to stand out. The badge is made from high-quality metal " +
                            "materials and has a sleek and durable design, ensuring that it will last for a long time. " +
                            "Specifications: " +
                            "Country of Origin: China. " +
                            "Width (Thickness): 12mm. " +
                            "Length: 22mm. " +
                            "Material: Metal. " +
                            "Type: Pin.")
                    .categories(List.of(category1))
                    .build();
            productRepository.save(product27);

            Product product28 = Product.builder()
                    .name("Geometric Fox King Pin Badge")
                    .price(new BigDecimal("2.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/4355092020_w640_h640_4355092020.webp")
                    .description("This pin badge features a unique and stylish design of a fox with a crown, " +
                            "made up of geometric shapes. The intricate details of the fox's features and crown" +
                            " are beautifully captured in this pin, making it a great addition to any collection." +
                            " The badge is made from high-quality metal materials and has a sleek and durable design," +
                            " ensuring that it will last for a long time. " +
                            "Specifications: " +
                            "Country of Origin: China. " +
                            "Width (Thickness): 12mm. " +
                            "Length: 22mm. " +
                            "Material: Metal. " +
                            "Type: Pin.")
                    .categories(List.of(category1))
                    .build();
            productRepository.save(product28);

            Product product29 = Product.builder()
                    .name("Geometric Koala Pin Badge")
                    .price(new BigDecimal("2.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/4355092015_w640_h640_4355092015.webp")
                    .description("This pin badge features a cute and unique design of a koala, created in " +
                            "a geometric style. The use of geometric shapes gives the koala a modern and trendy " +
                            "look, making it a great accessory for any outfit. The badge is made from high-quality " +
                            "metal materials and has a sturdy and durable design, ensuring it will last for a long " +
                            "time. " +
                            "Specifications: " +
                            "Country of Origin: China. " +
                            "Width (Thickness): 10mm. " +
                            "Length: 20mm. " +
                            "Material: Metal. " +
                            "Type: Pin.")
                    .categories(List.of(category1))
                    .build();
            productRepository.save(product29);

            Product product30 = Product.builder()
                    .name("Geometric Panda Pin")
                    .price(new BigDecimal("2.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/4355092012_w640_h640_4355092012.webp")
                    .description("This panda pin features a unique geometric design that captures the " +
                            "adorable essence of the animal in a modern and stylish way. The pin is " +
                            "made from high-quality metal materials and has a sleek and durable design," +
                            " ensuring that it will last for a long time. The panda's intricate details are" +
                            " beautifully captured in this pin, making it a great addition to any animal" +
                            " lover's collection. " +
                            "Specifications: " +
                            "Country of Origin: China. " +
                            "Width (Thickness): 12mm. " +
                            "Length: 22mm. " +
                            "Material: Metal. " +
                            "Type: Pin.")
                    .categories(List.of(category1))
                    .build();
            productRepository.save(product30);


            Product product20 = Product.builder()
                    .name("Kitten in a Scarf Pin Badge")
                    .price(new BigDecimal("2.99"))
                    .img("https://storage.googleapis.com/dream-shop-380613/f2864f0563131cc968e196045e3ed512.jpg")
                    .description("This adorable pin badge features a cute kitten wearing a colorful scarf, " +
                            "adding a touch of charm to any outfit or accessory. The design is detailed and" +
                            " well-crafted, capturing the playful and curious nature of kittens. The badge is " +
                            "made from high-quality metal materials, ensuring its durability and longevity. " +
                            "Specifications: " +
                            "Country of Origin: China. " +
                            "Width (Thickness): 10mm. " +
                            "Length: 26mm. " +
                            "Material: Metal. " +
                            "Color: White, Orange, Black.")
                    .categories(List.of(category1))
                    .build();
            productRepository.save(product20);


            Product product31 = Product.builder()
                    .name("Keychain Poop")
                    .price(new BigDecimal("4.00"))
                    .img("https://storage.googleapis.com/dream-shop-380613/2f70cc9a32608df6157a4cb253dfb022.jpg")
                    .description("A keychain in the shape of poop is not just a decoration for keys." +
                            " This accessory is a status symbol and individual, it reflects the character of " +
                            "its owner. Funny poop is chosen by people with a great sense of humor, who always " +
                            "have a good mood. This is a great gift for a friend if you consider him a joker. " +
                            "Product specifications: " +
                            "Material: Metal, Plastic. " +
                            "Color: Brown. " +
                            "Thickness (width) mm: 39. " +
                            "Length, mm: 40.")
                    .categories(List.of(category2))
                    .build();
            productRepository.save(product31);

            Product product32 = Product.builder()
                    .name("Reflective Smiley")
                    .price(new BigDecimal("4.00"))
                    .img("https://storage.googleapis.com/dream-shop-380613/ded3a22b6b5df99ab6d62a2eb3ed4bbe.jpg")
                    .description("This keychain is not just a cute accessory for your keys." +
                            " It also has reflective properties, making it a useful safety feature " +
                            "when walking or jogging at night. The mix of colors and the happy smiley " +
                            "face design make it a fun and cheerful addition to your keyring. It also makes a" +
                            " great gift for friends and family who enjoy outdoor activities. " +
                            "Product specifications: " +
                            "Material: Metal, Polyvinyl Chloride. " +
                            "Color: Mix of colors.")
                    .categories(List.of(category2))
                    .build();
            productRepository.save(product32);

            Product product33 = Product.builder()
                    .name("The Squid Game Keychain")
                    .price(new BigDecimal("4.00"))
                    .img("https://storage.googleapis.com/dream-shop-380613/9eb0dff56c57fa23a9094e598652f70d.jpg")
                    .description("This pocket keychain with a rubber doll charm is designed in the style of " +
                            "the popular series \"The Squid Game\". It will appeal not only to fans of the " +
                            "show, but also to anyone who wants to be in trend and stand out from the crowd. " +
                            "The rubber toy is flexible and can be used as a stress reliever. It will be " +
                            "appreciated by both adults and children. " +
                            "Product specifications: " +
                            "Material: Rubber, Metal. " +
                            "Color: Red. " +
                            "Width: 24.5 mm. " +
                            "Length: 50 mm.")
                    .categories(List.of(category2))
                    .build();
            productRepository.save(product33);

            Product product34 = Product.builder()
                    .name("The 0.5m Measuring Tape Keychain")
                    .price(new BigDecimal("4.00"))
                    .img("https://storage.googleapis.com/dream-shop-380613/fbabaf31cdd8f4b266575f6a1e60a19b.jpg")
                    .description("This keychain doubles as a 0.5m measuring tape, making it a convenient tool" +
                            " to have on hand at all times. The compact size and lightweight design make " +
                            "it easy to carry in your pocket or attach to your keys. It is perfect for anyone " +
                            "who needs to make quick measurements on the go, whether for work or personal use. " +
                            "Product specifications: " +
                            "Material: Plastic. " +
                            "Color: White. " +
                            "Width: 13 mm. " +
                            "Length: 500 mm.")
                    .categories(List.of(category2))
                    .build();
            productRepository.save(product34);

            Product product35 = Product.builder()
                    .name("Game of Thrones Keychain - House Stark Sigil")
                    .price(new BigDecimal("4.00"))
                    .img("https://storage.googleapis.com/dream-shop-380613/a3e232116ac0d35121497308a969b6d4.jpg")
                    .description("This keychain features the sigil of House Stark from the popular TV " +
                            "series \"Game of Thrones\". Made of durable metal with a pewter finish, " +
                            "it's the perfect accessory for any fan of the show. The keychain is compact and " +
                            "lightweight, making it easy to carry with you wherever you go. The 2.5 mm hole allows " +
                            "for easy attachment to your keys or other accessories. Show your support for House" +
                            " Stark with this stylish keychain. " +
                            "Product specifications: " +
                            "Material: Metal. " +
                            "Color: Pewter. " +
                            "Width: 30 mm. " +
                            "Length: 40 mm. " +
                            "Hole Diameter: 2.5 mm.")
                    .categories(List.of(category2))
                    .build();
            productRepository.save(product35);

            Product product36 = Product.builder()
                    .name("Game of Thrones Keychain - House Stark Sigil")
                    .price(new BigDecimal("4.00"))
                    .img("https://storage.googleapis.com/dream-shop-380613/b43d2a6690ff6f1e887351b65823545c.jpg")
                    .description("This keychain features the sigil of House Targaryen from the popular" +
                            " series \"Game of Thrones\". It is made of high-quality metal and has a pewter " +
                            "finish, giving it a classic and elegant look. The keychain is durable and built" +
                            " to last, ensuring that it will be a long-lasting addition to your collection. The " +
                            "Targaryen sigil represents fire and blood, making it a perfect gift for any " +
                            "fan of the show. " +
                            "Product specifications: " +
                            "Material: Metal. " +
                            "Color: Pewter. " +
                            "Width: 30 mm. " +
                            "Length: 40 mm. " +
                            "Hole Diameter: 2.5 mm.")
                    .categories(List.of(category2))
                    .build();
            productRepository.save(product36);

            Product product37 = Product.builder()
                    .name("Double Trouble Keychain")
                    .price(new BigDecimal("4.00"))
                    .img("https://storage.googleapis.com/dream-shop-380613/481b141d0fd17394edbbbb6e49f9c546.jpg")
                    .description("This unique keychain consists of two separate metal pieces that" +
                            " can be connected together to form a heart, symbolizing the connection" +
                            " between two people. The keychain features a silver finish with a black outline " +
                            "design, adding a touch of style and sophistication to your keys or bag. The durable " +
                            "metal construction ensures that this keychain will last for a long time. Give this" +
                            " Double Trouble Keychain to your significant other or best friend as a token of your" +
                            " connection and love. " +
                            "Product specifications:. " +
                            "Material: Metal. " +
                            "Color: Silver and black. " +
                            "Width: 30 mm. " +
                            "Length: 60 mm. " +
                            "Hole size: 2.5 mm")
                    .categories(List.of(category2))
                    .build();
            productRepository.save(product37);

            Product product38 = Product.builder()
                    .name("The Mountain Landscape Within Iceberg Forms")
                    .price(new BigDecimal("12.00"))
                    .img("https://storage.googleapis.com/dream-shop-380613/image117-40.jpg")
                    .description("The painting portrays a mountainous landscape enclosed within iceberg" +
                            " shapes. The mountain peaks and the pine trees are depicted as triangular " +
                            "forms. The overall mood of the painting is serene, as the viewer is " +
                            "transported to a peaceful and isolated place. The use of sharp geometric " +
                            "shapes for the mountains and trees create a strong visual contrast with the " +
                            "soft curves of the iceberg forms, giving the impression of a harmonious balance " +
                            "between natural and artificial elements. The author of this painting is unknown.")
                    .categories(List.of(category3))
                    .build();
            productRepository.save(product38);

            Product product39 = Product.builder()
                    .name("The Happy Cat in Glasses and Hat")
                    .price(new BigDecimal("12.00"))
                    .img("https://storage.googleapis.com/dream-shop-380613/image283-6.jpg")
                    .description("The painting depicts a portrait of a cat wearing glasses and a " +
                            "hat, with one ear peeking out from under the hat. The cat's expression" +
                            " is one of contentment, as if it knows a secret that no one else does." +
                            " The use of bright colors and loose brushstrokes creates a lively and " +
                            "playful atmosphere. The glasses and hat add a touch of whimsy to the" +
                            " painting, giving the cat a sense of sophistication and style. The overall " +
                            "effect is charming and delightful, making the viewer smile. The author of " +
                            "this painting is unknown.")
                    .categories(List.of(category3))
                    .build();
            productRepository.save(product39);

            Product product40 = Product.builder()
                    .name("The Limbo Youth with a Cigarette")
                    .price(new BigDecimal("12.00"))
                    .img("https://storage.googleapis.com/dream-shop-380613/image001-45.jpg")
                    .description("The painting depicts a portrait of a young man in a waistcoat who" +
                            " resembles a character from Limbo. He is holding a cigarette in his mouth " +
                            "and his eyes are closed. He appears to be either sad or tired. The use of " +
                            "dark colors and shadows creates a somber atmosphere, emphasizing the melancholic " +
                            "mood of the painting. The blurred outlines and unfinished edges of the figure" +
                            " give a sense of transience and fragility, as if the youth is caught in a " +
                            "fleeting moment. The cigarette in his mouth adds a touch of rebellion, " +
                            "hinting at a sense of defiance or dissatisfaction. The author of this " +
                            "painting is unknown.")
                    .categories(List.of(category3))
                    .build();
            productRepository.save(product40);

            Product product41 = Product.builder()
                    .name("The Happy Cat Running to Embrace or Eat in Cartoonish Style")
                    .price(new BigDecimal("24.00"))
                    .img("https://storage.googleapis.com/dream-shop-380613/image008-45-823x720.jpg")
                    .description("The painting depicts a joyful cat running with a big smile" +
                            " on its face, as if it is either running to embrace someone or " +
                            "to eat something. The cat is depicted in a cartoonish style, with" +
                            " a circular black body and a white face. The use of bright colors" +
                            " and bold lines gives the painting a playful and cheerful atmosphere. " +
                            "The cat's expression and body language convey a sense of energy and " +
                            "enthusiasm, making the viewer feel uplifted and happy. The overall effect" +
                            " is cute and endearing, making the painting a delight to behold. The author" +
                            " of this painting is unknown.")
                    .categories(List.of(category3))
                    .build();
            productRepository.save(product41);


            Product product43 = Product.builder()
                    .name("The Bunny Lady with an Umbrella")
                    .price(new BigDecimal("22.00"))
                    .img("https://storage.googleapis.com/dream-shop-380613/image012-44.jpg")
                    .description("The painting depicts a female rabbit in a dress holding an " +
                            "umbrella, with a mushroom in one hand and the other hand catching" +
                            " raindrops. Leaves are scattered on the ground, suggesting that " +
                            "autumn has arrived. The use of warm colors, such as red and orange," +
                            " creates a cozy and nostalgic atmosphere, evoking the feeling of a " +
                            "crisp fall day. The rabbit's expression and body language convey a " +
                            "sense of calmness and serenity, as if she is enjoying the peaceful moment" +
                            " amidst the rain. The use of playful elements, such as the mushroom and the " +
                            "bunny ears, adds a touch of whimsy to the painting. The overall effect is" +
                            " charming and delightful, making the viewer feel a sense of joy and " +
                            "comfort. The author of this painting is unknown.")
                    .categories(List.of(category3))
                    .build();
            productRepository.save(product43);

            Product product44 = Product.builder()
                    .name("Day of the Dead Celebration")
                    .price(new BigDecimal("14.00"))
                    .img("https://storage.googleapis.com/dream-shop-380613/image068-45.jpg")
                    .description("The painting depicts a skull commonly used in the celebration" +
                            " of the Day of the Dead in Mexico. The skull is adorned with lace" +
                            " and other decorative elements, creating a festive and ornate " +
                            "appearance. The use of bright colors, such as red and yellow, adds " +
                            "to the lively and vibrant atmosphere of the painting. The skull is a " +
                            "symbolic representation of death, but in the context of the celebration," +
                            " it is viewed as a way to honor and remember loved ones who have passed away." +
                            " The use of lace and other intricate details is a nod to the traditional " +
                            "Mexican art style, giving the painting a cultural significance. The overall" +
                            " effect is both eerie and celebratory, making the painting a unique and " +
                            "interesting piece of art. The author of this painting is unknown.")
                    .categories(List.of(category3))
                    .build();
            productRepository.save(product44);


            Product product48 = Product.builder()
                    .name("Portrait of a Beautiful Woman")
                    .price(new BigDecimal("25.00"))
                    .img("https://storage.googleapis.com/dream-shop-380613/image082-43.jpg")
                    .description("The painting depicts a stunningly beautiful woman with an expression " +
                            "of perplexity or bewilderment on her face. The artist has skillfully captured " +
                            "the woman's striking features, such as her high cheekbones, full lips, and l" +
                            "ong eyelashes. The use of soft colors, such as pink and blue, creates a serene" +
                            " and calming atmosphere, adding to the painting's overall beauty. The woman's " +
                            "expression adds an element of mystery and intrigue to the painting, making the " +
                            "viewer curious about what might be troubling her. Despite this, the portrait" +
                            " remains a gorgeous work of art, capturing the essence of feminine beauty. The " +
                            "author of this painting is unknown.")
                    .categories(List.of(category3))
                    .build();
            productRepository.save(product48);

            Product product46 = Product.builder()
                    .name("Portrait of Lisa Simpson")
                    .price(new BigDecimal("18.00"))
                    .img("https://storage.googleapis.com/dream-shop-380613/image252-13-574x720.jpg")
                    .description("The painting depicts a literal portrait of Lisa Simpson," +
                            " a beloved character from the popular television show The Simpsons." +
                            " Lisa's distinctive features, including her spiky hair, round eyes, and pearls," +
                            " are all faithfully captured in the painting. The use of bright colors, such as " +
                            "yellow and blue, are reminiscent of the show's animation style, adding to the" +
                            " playful and whimsical atmosphere of the painting. The portrait celebrates Lisa's" +
                            " intelligence, strong will, and unique personality, making it a delightful piece" +
                            " of art for fans of the show. The author of this painting is unknown.")
                    .categories(List.of(category3))
                    .build();
            productRepository.save(product46);

            Product product49 = Product.builder()
                    .name("Portrait of Mad Moxxie")
                    .price(new BigDecimal("34.00"))
                    .img("https://storage.googleapis.com/dream-shop-380613/image151-34.jpg")
                    .description("The painting features Mad Moxxie standing with a demure expression and " +
                            "holding an umbrella. She is depicted against a white background, and the " +
                            "painting's focus is on her, with her outfit and expression being the main elements " +
                            "of interest. The artist has captured her shyness and modesty well, while the " +
                            "umbrella and the shadow it casts add an intriguing element to the painting. " +
                            "Despite the simplicity of the composition, the painting is striking in its " +
                            "elegance and captures the essence of Mad Moxxie's character. The author of this " +
                            "painting is unknown.")
                    .categories(List.of(category3))
                    .build();
            productRepository.save(product49);
        };
    }
}
