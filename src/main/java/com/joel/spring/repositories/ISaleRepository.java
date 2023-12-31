package com.joel.spring.repositories;

import com.joel.spring.dtos.sales.SaleInfoDTO;
import com.joel.spring.dtos.sales.SaleMaxAmountDTO;
import com.joel.spring.entities.Product;
import com.joel.spring.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, String >{
    @Query("SELECT s.products FROM Sale s WHERE s.id = :saleId")
    List<Product> findProductsBySaleId(@Param("saleId") String saleId);
    @Query("SELECT sales FROM Sale sales WHERE sales.saleDate = :saleDate")
    List<Sale> getSaleByDate(@Param("saleDate") Date saleDate);
    @Query("SELECT COUNT(sale) FROM Sale sale WHERE sale.saleDate = :saleDate")
    int countSalesByDate(@Param("saleDate") Date saleDate);
    @Query("SELECT SUM(s.totalPrice) FROM Sale s WHERE s.saleDate = :saleDate")
    Double totalAmountByDate(@Param("saleDate") Date saleDate);
    @Query("SELECT s.id AS saleId, s.totalPrice AS totalPrice, c.firstName AS name, c.lastName AS lastName FROM Sale s INNER JOIN UserEntity c ON s.client.id = c.id WHERE s.totalPrice = (SELECT MAX(totalPrice) FROM Sale)")
    List<SaleMaxAmountDTO> saleMaxAmount();

    @Query("SELECT new com.joel.spring.dtos.sales.SaleInfoDTO(s.id, s.totalPrice," +
            " new com.joel.spring.dtos.users.UserPersonalInfoDTO(u.id, u.firstName, u.lastName, u.email)) FROM Sale s JOIN UserEntity u")
    List<SaleInfoDTO> getAllSalesDTOs();

    @Query("SELECT new com.joel.spring.dtos.sales.SaleInfoDTO(s.id, s.totalPrice," +
            " new com.joel.spring.dtos.users.UserPersonalInfoDTO(u.id, u.firstName, u.lastName, u.email)) FROM Sale s JOIN UserEntity u WHERE s.id =:saleId")
    Optional<SaleInfoDTO> getSaleByIdDTO(@Param(("saleId")) String saleId);
}
