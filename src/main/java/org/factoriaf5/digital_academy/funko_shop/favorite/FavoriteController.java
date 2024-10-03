package org.factoriaf5.digital_academy.funko_shop.favorite;


import org.factoriaf5.digital_academy.funko_shop.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("${api-endpoint}/favorites")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<Page<FavoriteDTO>> getFavorites(Principal connectedUser, @PageableDefault(size = 8, sort = {"product"}) Pageable pageable) {
        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        Page<FavoriteDTO> favorites = favoriteService.getFavoriteByUserId(user.getId(), pageable);
        return ResponseEntity.ok(favorites);
    }

    @PostMapping
    public ResponseEntity <List<FavoriteDTO>> addToFavorite(Principal connectedUser, @RequestBody Long productId) {
        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        List<FavoriteDTO> addFavorite = favoriteService.addProductToFavorite(user.getId(),productId);
        return ResponseEntity.ok(addFavorite);
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeFromFavorite(Principal connectedUser, @PathVariable Long productId) {
        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
    
        favoriteService.removeProductFromFavorite(user.getId(), productId);
        return ResponseEntity.noContent().build();
    }
}
