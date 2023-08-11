package co.absa.eml.lightstone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LightStonePropertyRepository extends JpaRepository<LightStonePropertiesEntity, String> {
   @Query(value ="select * from lightStone_properties_entity where buyer_idck=?1",nativeQuery = true)
    Optional<LightStonePropertiesEntity> findByBuyeIdck(String buyerIdck);

}
