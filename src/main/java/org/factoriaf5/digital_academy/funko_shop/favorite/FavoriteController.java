package org.factoriaf5.digital_academy.funko_shop.favorite;


import org.factoriaf5.digital_academy.funko_shop.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("${api-endpoint}/favorite")
@PreAuthorize("hasRole('USER')")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    // @GetMapping
    // public ResponseEntity<List<FavoriteDTO>> getFavorites(Principal connectedUser) {
    //     User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
    //     List<FavoriteDTO> favorites = favoriteService.getFavoriteByUserId(user.getId());
    //     return ResponseEntity.ok(favorites);
    // }

    @PostMapping
    public ResponseEntity <List<FavoriteDTO>> addToFavorite(Principal connectedUser, @RequestBody Long productId) {
        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        List<FavoriteDTO> addFavorite = favoriteService.addProductToFavorite(user.getId(),productId);
        return ResponseEntity.ok(addFavorite);
    }


    // @DeleteMapping("/{productId}")
    // public ResponseEntity<Void> removeFromFavorite(Principal connectedUser, @PathVariable Long productId) {
    //     User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
    
    //     favoriteService.removeProductFromFavorite(user.getId(), productId);
    //     return ResponseEntity.noContent().build();
    // }
}
