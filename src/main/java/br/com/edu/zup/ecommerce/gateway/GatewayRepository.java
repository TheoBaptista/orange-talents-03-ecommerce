package br.com.edu.zup.ecommerce.gateway;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatewayRepository extends JpaRepository<Gateway,Long> {

    Gateway findGatewayByType(EnumGateway enumGateway);

}
