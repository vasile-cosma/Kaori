package es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartItem,Integer> {
    List<CartItem> findByUser_Id(Integer id);

    Optional<CartItem> findCartItemById(Long CartItemId);

    void deleteByUser_Id(Integer id);

    void deleteCartItemByUser_Id_AndProduct_Id(Integer userId, Integer productId);

    @Query("select COUNT(distinct c) from CartItem c where c.user.id = :userId")
    Long countDistinctProductsByUser(@Param("userId") Integer userId);

    //Si el valor de la suma es null, da 0
    @Query("select COALESCE(SUM(c.units), 0) from CartItem c where c.user.id = :userId")
    Long sumTotalUnitsByUser(@Param("userId") Integer userId);

}
