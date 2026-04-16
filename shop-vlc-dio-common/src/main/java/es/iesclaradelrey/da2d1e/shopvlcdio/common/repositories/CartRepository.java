package es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem,Integer> {
    List<CartItem> findByUser_Id(Integer id);

    void deleteByUser_Id(Integer id);

    void deleteCartItemByUser_Id_AndProduct_Id(Integer userId, Integer productId);

}
