package pl.nicecode.anielskisty.allegroprovider.database.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.nicecode.anielskisty.allegroprovider.database.entity.EntityTradingPartners;
import java.util.UUID;
/**
 * Created by jhojczak on 1/5/16.
 */
@Repository
public interface DaoTradingPartners extends CrudRepository<EntityTradingPartners,UUID> {


}
