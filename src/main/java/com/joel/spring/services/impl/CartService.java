package com.joel.spring.services.impl;

import com.joel.spring.dtos.cart.AddProductToCartDTO;
import com.joel.spring.dtos.cart.ProductInfoDTO;
import com.joel.spring.dtosbuilder.CartDTOBuilder;
import com.joel.spring.entities.Cart;
import com.joel.spring.entities.CartProduct;
import com.joel.spring.exceptions.NotFoundException;
import com.joel.spring.repositories.ICartRepository;
import com.joel.spring.services.ICartService;
import com.joel.spring.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired private IUserService userService;
    @Autowired private ICartRepository cartRepository;


    @Override
    public void saveByEntity(Cart cart) {
        this.cartRepository.save(cart);
    }

    @Override
    public String addProduct(AddProductToCartDTO body, String id) {
        /*if (this.userService.existsById(id)) {
        }*/
        return null;
    }

    @Override
    public Cart findById(String id) throws NotFoundException {
        return this.checkCartOptional(this.cartRepository.findById(id));
    }

    @Override
    public Cart findByUserId(String userId) throws NotFoundException {
        return this.checkCartOptional(this.cartRepository.findByUser_Id(userId));
    }

    @Override
    public List<ProductInfoDTO> getUserCard(String id) throws NotFoundException {
        Cart cart = this.findByUserId(id);
        List<ProductInfoDTO> response = new ArrayList<>();
        for (CartProduct cartProduct : cart.getCartProducts()) {
            response.add(
                    ProductInfoDTO.builder()
                            .cartProductId(cartProduct.getId())
                            .productId(cartProduct.getProduct().getId())
                            .name(cartProduct.getProduct().getName())
                            .brand(cartProduct.getProduct().getBrand())
                            .price(cartProduct.getProduct().getPrice())
                            .quantity(cartProduct.getQuantity())
                            .build());
        }
        return response;
    }

    private Cart checkCartOptional(Optional<Cart> cartOptional) throws NotFoundException {
        if (cartOptional.isPresent()) {
            return cartOptional.get();
        }
        throw new NotFoundException("Cart not found");
    }


}
