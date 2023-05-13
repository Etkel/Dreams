package com.shop.dream.services;

import com.shop.dream.dto.CartDTO;
import com.shop.dream.dto.ProductMvpDTO;
import com.shop.dream.models.Cart;
import com.shop.dream.models.CartItem;
import com.shop.dream.models.Product;
import com.shop.dream.repositories.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemsServiceImpl implements CartItemsService {
    private final CartItemRepository cartItemRepository;
    private final PersonaService personaService;
    private final ProductService productService;

    @Override
    @Transactional(readOnly = true)
    public List<ProductMvpDTO> findProductsWithMaxAmount() {
        return cartItemRepository.findProductsWithMaxAmount().stream()
                .map(Product::productToMvpDTO)
                .limit(4)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addItemToCart(Long productId, Integer amount, Principal principal) {
        Cart cart = personaService.findCartByEmailEssence(principal.getName());

        CartItem cartItem = new CartItem();
        cartItem.setAmount(amount);
        cartItem.setProduct(productService.getReferenceByIdEssence(productId));
        cartItem.setCart(cart);

        boolean productFound = false;
        for (CartItem item : cart.getCartItems()) {
            if (item.getProduct().getId().equals(productId)) {
                item.setAmount(item.getAmount() + amount);
                productFound = true;
                break;
            }
        }

        if (!productFound || cart.getCartItems().isEmpty()) {
            CartItem savedItem = cartItemRepository.save(cartItem);
            cart.getCartItems().add(savedItem);
        }
    }

    @Override
    @Transactional
    public void updateItems(CartDTO cartDTO) {
        List<CartItem> cartItemList = cartDTO.getCartItems();
        for(CartItem temp : cartItemList) {
            CartItem item = cartItemRepository.findById(temp.getId()).get();
            if(!item.getAmount().equals(temp.getAmount())) {
                item.setAmount(temp.getAmount());
                cartItemRepository.save(item);
            }
        }
    }

    @Override
    @Transactional
    public void delete(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }

}

