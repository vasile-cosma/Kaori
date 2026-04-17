package es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartItem,Integer> {
    List<CartItem> findByUser_Id(Integer id);

    Optional<CartItem> findCartItemById(Long CartItemId);

    void deleteByUser_Id(Integer id);

    void deleteCartItemByUser_Id_AndProduct_Id(Integer userId, Integer productId);

    Optional<CartItem> findByUser_IdAndProduct_Id(Integer userId, Integer productId);

}
